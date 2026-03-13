package gui;

import javax.swing.*;
import java.awt.*;

public class PageAcc extends JPanel {

    private static final long serialVersionUID = 1L;

    public PageAcc(MainGUI gui) {

        setLayout(new BorderLayout());
        setBackground(new Color(30,30,30));

        // ----- TITRE -----

        JLabel titre = new JLabel("XIANGQI GAME", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 36));
        titre.setForeground(Color.WHITE);

        JLabel version = new JLabel("CHESS", SwingConstants.CENTER);
        version.setForeground(Color.LIGHT_GRAY);
 
        JPanel titrePanel = new JPanel();
        titrePanel.setBackground(new Color(30,30,30));
        titrePanel.setLayout(new BoxLayout(titrePanel, BoxLayout.Y_AXIS));

        titrePanel.add(Box.createVerticalStrut(40));
        titrePanel.add(titre);
        titrePanel.add(Box.createVerticalStrut(10));
        titrePanel.add(version);

        add(titrePanel, BorderLayout.NORTH);

        // ----- BOUTONS -----

        JButton vsOrdinateur = new JButton("VS ORDINATEUR");
        JButton vsAmis = new JButton("VS AMIS");

        vsOrdinateur.setMaximumSize(new Dimension(220,50));
        vsAmis.setMaximumSize(new Dimension(220,50));

        vsOrdinateur.setFont(new Font("Arial", Font.BOLD, 16));
        vsAmis.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel boutonsPanel = new JPanel();
        boutonsPanel.setBackground(new Color(30,30,30));
        boutonsPanel.setLayout(new BoxLayout(boutonsPanel, BoxLayout.Y_AXIS));

        boutonsPanel.add(Box.createVerticalStrut(60));
        boutonsPanel.add(vsOrdinateur);
        boutonsPanel.add(Box.createVerticalStrut(20));
        boutonsPanel.add(vsAmis);

        vsOrdinateur.setAlignmentX(Component.CENTER_ALIGNMENT);
        vsAmis.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(boutonsPanel, BorderLayout.CENTER);

        // ----- INFORMATIONS -----

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(30,30,30));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel infoTitre = new JLabel("Informations");
        JLabel info1 = new JLabel("Plateau 11 x 11 avec zones spéciales");
        JLabel info2 = new JLabel("Règles personnalisées");

        infoTitre.setForeground(Color.WHITE);
        info1.setForeground(Color.LIGHT_GRAY);
        info2.setForeground(Color.LIGHT_GRAY);

        infoTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
        info1.setAlignmentX(Component.CENTER_ALIGNMENT);
        info2.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(infoTitre);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(info1);
        infoPanel.add(info2);
        infoPanel.add(Box.createVerticalStrut(20));

        add(infoPanel, BorderLayout.SOUTH);

        // ----- ACTION -----

        vsAmis.addActionListener(e -> {
            gui.lancerPartie();
        });
    }
}
