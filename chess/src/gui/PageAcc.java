package gui;

import javax.swing.*;
import java.awt.*;

public class PageAcc extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image backgroundImage;

    public PageAcc(MainGUI gui) {

        setLayout(new BorderLayout());

        
        backgroundImage = new ImageIcon(getClass().getResource("../images/photo.png")).getImage();

     
        JLabel titre = new JLabel("CHESS", SwingConstants.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 42));
        titre.setForeground(new Color(200, 0, 0));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel version = new JLabel("JEU D'ÉCHECS CHINOIS", SwingConstants.CENTER);
        version.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        version.setForeground(Color.LIGHT_GRAY);
        version.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titrePanel = new JPanel();
        titrePanel.setOpaque(false);
        titrePanel.setLayout(new BoxLayout(titrePanel, BoxLayout.Y_AXIS));
        titrePanel.add(Box.createVerticalStrut(50));
        titrePanel.add(titre);
        titrePanel.add(Box.createVerticalStrut(5));
        titrePanel.add(version);

        add(titrePanel, BorderLayout.NORTH);

       
        JButton vsOrdinateur = createStyledButton("VS BOT");
        JButton vsAmis       = createStyledButton("VS HUMAIN");

        JPanel boutonsPanel = new JPanel();
        boutonsPanel.setOpaque(false);
        boutonsPanel.setLayout(new BoxLayout(boutonsPanel, BoxLayout.Y_AXIS));
        boutonsPanel.add(Box.createVerticalGlue());
        boutonsPanel.add(vsOrdinateur);
        boutonsPanel.add(Box.createVerticalStrut(25));
        boutonsPanel.add(vsAmis);
        boutonsPanel.add(Box.createVerticalGlue());

        add(boutonsPanel, BorderLayout.CENTER);

       
        vsAmis.addActionListener(e -> gui.lancerPartie());

        vsOrdinateur.addActionListener(e -> {

            
            JPanel panel = new JPanel();
            panel.setBackground(Color.BLACK);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel titre1 = new JLabel("CHOISIR LA DIFFICULTÉ");
            titre1.setFont(new Font("Segoe UI", Font.BOLD, 16));
            titre1.setForeground(new Color(200, 0, 0));
            titre1.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(Box.createVerticalStrut(20));
            panel.add(titre1);
            panel.add(Box.createVerticalStrut(20));

            JButton facile    = createOptionButton("FACILE",    gui, engine.process.Niveau.FACILE);
            JButton moyen     = createOptionButton("MOYEN",     gui, engine.process.Niveau.MOYEN);
            JButton difficile = createOptionButton("DIFFICILE", gui, engine.process.Niveau.DIFFICILE);

            panel.add(facile);
            panel.add(Box.createVerticalStrut(10));
            panel.add(moyen);
            panel.add(Box.createVerticalStrut(10));
            panel.add(difficile);
            panel.add(Box.createVerticalStrut(20));

            JDialog dialog = new JDialog(
                    (JFrame) SwingUtilities.getWindowAncestor(this),
                    "Difficulté", true
            );
            dialog.setContentPane(panel);
            dialog.setSize(350, 300);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        });
    }

    // ===== BOUTON OPTION DIFFICULTÉ =====
    private JButton createOptionButton(String text, MainGUI gui, engine.process.Niveau niveau) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(150, 0, 0));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0), 2));
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btn.setBackground(new Color(200, 0, 0)); }
            public void mouseExited(java.awt.event.MouseEvent evt)  { btn.setBackground(new Color(150, 0, 0)); }
        });

        btn.addActionListener(e -> {
            gui.lancerPartieBot(niveau);
            Window w = SwingUtilities.getWindowAncestor(btn);
            if (w != null) w.dispose();
        });

        return btn;
    }

    
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);

        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(150, 0, 0));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0), 2));
        button.setMaximumSize(new Dimension(240, 55));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { button.setBackground(new Color(200, 0, 0)); }
            public void mouseExited(java.awt.event.MouseEvent evt)  { button.setBackground(new Color(150, 0, 0)); }
        });

        return button;
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
