package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import engine.map.*;
import engine.mobile.*;
import engine.process.Bot;
import engine.process.Coup;
import engine.process.GameManager;

public class GameDisplay extends JPanel implements MouseListener {

    private static final long serialVersionUID = 1L;

    private GameManager gameManager;
    private Plateau plateau;
    private Bot bot;
    private boolean dimensionsCalculees = false;
    private PaintStrategy paintStrategy;

    private Piece pieceSelectionnee = null;
    private Case  caseSelectionnee  = null;
 
    private int tailleCase;
    private int offsetX;
    private int offsetY;

    private boolean FinParti = false;
 
    private JTextPane  historique;
    private JTextField joueurActuelField;

    private JPanel captureHaut;
    private JPanel captureBas;
    private JPanel plateauPanel;

    public GameDisplay(GameManager gameManager, Bot bot) {

        this.gameManager  = gameManager;
        this.plateau      = gameManager.getPlateau();
        this.bot          = bot;
        this.paintStrategy = new PaintStrategy();

        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(220, 220, 220));

       
        captureHaut = new JPanel(new FlowLayout(FlowLayout.LEFT));
        captureHaut.setPreferredSize(new Dimension(0, 80));
        TitledBorder borderHaut = BorderFactory.createTitledBorder("♟ CAPTURES");
        borderHaut.setTitleColor(Color.RED);
        borderHaut.setTitleJustification(TitledBorder.CENTER);
        borderHaut.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        captureHaut.setBorder(borderHaut);
        captureHaut.setBackground(new Color(255, 150, 150));
        captureHaut.setOpaque(true);

        // CAPTURES DU NOIR         
        captureBas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        captureBas.setPreferredSize(new Dimension(0, 80));
        TitledBorder borderBas = BorderFactory.createTitledBorder("♙ CAPTURES");
        borderBas.setTitleColor(Color.BLACK);
        borderBas.setTitleJustification(TitledBorder.CENTER);
        borderBas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        captureBas.setBorder(borderBas);
        captureBas.setBackground(new Color(200, 200, 200));
        captureBas.setOpaque(true);

        //  PLATEAU
        plateauPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (!dimensionsCalculees) {
                    calculDimensions();
                    dimensionsCalculees = true;
                }

                paintStrategy.paintPlateau(plateau, g, tailleCase, offsetX, offsetY);
                paintStrategy.paintPieces(plateau, g, tailleCase, offsetX, offsetY);

                if (pieceSelectionnee != null &&
                    pieceSelectionnee.getCol() == plateau.getJoueurCourant()) {

                    ArrayList<Case> coups = new ArrayList<>();
                    for (Case c : pieceSelectionnee.getDeplacement(plateau)) {
                        if (plateau.coupValide(caseSelectionnee, c)) coups.add(c);
                    }

                    paintStrategy.paintDeplacements(
                            coups, g, tailleCase, offsetX, offsetY,
                            pieceSelectionnee.getCol()
                    );
                }
            }
        };
        plateauPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        plateauPanel.addMouseListener(this);

        
        JPanel panelPlateauContainer = new JPanel(new BorderLayout());
        panelPlateauContainer.add(captureHaut,  BorderLayout.NORTH);
        panelPlateauContainer.add(plateauPanel, BorderLayout.CENTER);
        panelPlateauContainer.add(captureBas,   BorderLayout.SOUTH);

        add(panelPlateauContainer, BorderLayout.CENTER);

    
        historique = new JTextPane();
        historique.setEditable(false);
        historique.setFont(new Font("Monospaced", Font.BOLD, 14));
        JScrollPane scroll = new JScrollPane(historique);
        scroll.setPreferredSize(new Dimension(230, 400));

        
        joueurActuelField = new JTextField("CURRENT PLAYER : RED");
        joueurActuelField.setEditable(false);
        joueurActuelField.setHorizontalAlignment(JTextField.CENTER);
        joueurActuelField.setFont(new Font("Arial", Font.BOLD, 16));
        joueurActuelField.setBackground(new Color(240, 240, 240));
        joueurActuelField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        updateJoueurCourant();

        
        JButton menuButton = PageAcc.createStyledButton("☰ MENU");
        menuButton.setPreferredSize(new Dimension(200, 50));
        menuButton.addActionListener(e -> ouvrirMenu());

        JPanel menuWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menuWrapper.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        menuWrapper.add(menuButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        bottomPanel.add(joueurActuelField, BorderLayout.NORTH);
        bottomPanel.add(menuWrapper,       BorderLayout.SOUTH);

        JPanel panelHistorique = new JPanel(new BorderLayout());
        panelHistorique.setBorder(BorderFactory.createTitledBorder("📜 Historique"));
        panelHistorique.add(scroll,       BorderLayout.CENTER);
        panelHistorique.add(bottomPanel,  BorderLayout.SOUTH);

        add(panelHistorique, BorderLayout.EAST);
    }

   
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() != 1) return;
        if (FinParti) return;

        

        int colonne = (e.getX() - offsetX) / tailleCase;
        int ligne   = (e.getY() - offsetY) / tailleCase;

        if (ligne < 0 || colonne < 0 ||
            ligne   >= plateau.getCases().length ||
            colonne >= plateau.getCases()[0].length) return;

        Case  c = plateau.getCases()[ligne][colonne];
        Piece p = plateau.getPieces().get(c);

      
        if (pieceSelectionnee == null) {
            if (p != null && p.getCol() == plateau.getJoueurCourant()) {
                pieceSelectionnee = p;
                caseSelectionnee  = c;
            }
        }
        
        else {
            boolean coupJoue = gameManager.jouerCoup(caseSelectionnee, c);

            if (coupJoue) {

                String texte =
                        pieceSelectionnee.getClass().getSimpleName() + " : (" +
                        (caseSelectionnee.getLigne()   + 1) + "," +
                        (caseSelectionnee.getColonne() + 1) + ") -> (" +
                        (c.getLigne()   + 1) + "," +
                        (c.getColonne() + 1) + ")\n";

                ajouterHistorique(texte,
                        pieceSelectionnee.getCol() == CouleurePiece.ROUGE ? Color.RED : Color.BLACK);

                updateCaptures();
                updateJoueurCourant();

                if (gameManager.estEchecEtMat()) {
                    JOptionPane.showMessageDialog(this, "ECHEC ET MAT !");
                    FinParti = true;
                } else if (gameManager.estEchec()) {
                    JOptionPane.showMessageDialog(this, "ECHEC !");
                }

                
                if (bot != null && !FinParti) {
                    new Thread(() -> {
                        try { Thread.sleep(500); } catch (Exception ex) {}

                        Coup coupBot = bot.jouer(plateau, CouleurePiece.NOIR);

                        SwingUtilities.invokeLater(() -> {
                            if (coupBot != null) {
                                String texteBot =
                                        "BOT : (" +
                                        (coupBot.depart.getLigne()   + 1) + "," +
                                        (coupBot.depart.getColonne() + 1) + ") -> (" +
                                        (coupBot.arrivee.getLigne()   + 1) + "," +
                                        (coupBot.arrivee.getColonne() + 1) + ")\n";
                                ajouterHistorique(texteBot, Color.BLACK);
                            }
                            updateCaptures();
                            updateJoueurCourant();
                            repaint();
                        });
                    }).start();
                }
            }

            pieceSelectionnee = null;
            caseSelectionnee  = null;
        }

        repaint();
    }

   
    private void updateJoueurCourant() {
        CouleurePiece courant = gameManager.getJoueurCourant();
        if (courant == CouleurePiece.ROUGE) {
            joueurActuelField.setText("CURRENT PLAYER : RED");
            joueurActuelField.setForeground(Color.RED);
        } else {
            joueurActuelField.setText("CURRENT PLAYER : BLACK");
            joueurActuelField.setForeground(Color.BLACK);
        }
    }

  
    private void ajouterHistorique(String texte, Color couleur) {
        StyledDocument doc   = historique.getStyledDocument();
        Style          style = historique.addStyle("Style", null);
        StyleConstants.setForeground(style, couleur);
        try {
            doc.insertString(doc.getLength(), texte, style);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private void updateCaptures() {

        captureHaut.removeAll();
        captureBas.removeAll();

        for (Piece p : plateau.getCapturesNoir()) {
            JLabel lbl = new JLabel(p.getClass().getSimpleName());
            lbl.setForeground(Color.DARK_GRAY);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
            captureHaut.add(lbl);
        }

        for (Piece p : plateau.getCapturesRouge()) {
            JLabel lbl = new JLabel(p.getClass().getSimpleName());
            lbl.setForeground(Color.RED);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
            captureBas.add(lbl);
        }

        captureHaut.revalidate();
        captureHaut.repaint();
        captureBas.revalidate();
        captureBas.repaint();
    }

    
    private void ouvrirMenu() {

        JDialog menu = new JDialog(
                SwingUtilities.getWindowAncestor(this),
                "Menu",
                Dialog.ModalityType.APPLICATION_MODAL
        );

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton reprendre     = PageAcc.createStyledButton("Reprendre");
        JButton nouvellePartie = PageAcc.createStyledButton("Nouvelle Partie");
        JButton quitter       = PageAcc.createStyledButton("Quitter");

        reprendre.addActionListener(e -> menu.dispose());
        nouvellePartie.addActionListener(e -> { menu.dispose(); nouvellePartie(); });
        quitter.addActionListener(e -> { menu.dispose(); quitterVersAccueil(); });

        panel.add(Box.createVerticalGlue());
        panel.add(reprendre);
        panel.add(Box.createVerticalStrut(15));
        panel.add(nouvellePartie);
        panel.add(Box.createVerticalStrut(15));
        panel.add(quitter);
        panel.add(Box.createVerticalGlue());

        menu.setContentPane(panel);
        menu.setSize(350, 300);
        menu.setLocationRelativeTo(this);
        menu.setVisible(true);
    }

   
    private void nouvellePartie() {

        Plateau nouveauPlateau = new Plateau();
        gameManager.setPlateau(nouveauPlateau);
        this.plateau = nouveauPlateau;

        historique.setText("");
        captureHaut.removeAll();
        captureBas.removeAll();
        captureHaut.revalidate();
        captureBas.revalidate();

        FinParti = false;
        updateJoueurCourant();
        repaint();
    }

    
    private void quitterVersAccueil() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
        new MainGUI("Xiangqi Game");
    }

    
    private void calculDimensions() {

        int largeur = plateauPanel.getWidth();
        int hauteur = plateauPanel.getHeight();

        tailleCase = Math.min(
                largeur / plateau.getCases()[0].length,
                hauteur / plateau.getCases().length
        );

        int plateauLargeur = tailleCase * plateau.getCases()[0].length;
        int plateauHauteur = tailleCase * plateau.getCases().length;

        offsetX = (largeur - plateauLargeur) / 2;
        offsetY = (hauteur - plateauHauteur) / 2;
    }

    public void mousePressed(MouseEvent e)  {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e)  {}
    public void mouseExited(MouseEvent e)   {}
}
