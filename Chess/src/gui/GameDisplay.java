package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import engine.map.*;
import engine.mobile.*;
import engine.process.Bot;
import engine.process.GameManager;

public class GameDisplay extends JPanel implements MouseListener {

    private static final long serialVersionUID = 1L;

    private GameManager gameManager;
    private Plateau plateau;
    private Bot bot;

    private PaintStrategy paintStrategy;

    private Piece pieceSelectionnee = null;
    private Case caseSelectionnee = null;

    private int tailleCase;
    private int offsetX;
    private int offsetY;

    private boolean FinParti = false;

    public GameDisplay(GameManager gameManager, Bot bot) {

        this.gameManager = gameManager;
        this.plateau = gameManager.getPlateau();
        this.bot = bot;

        this.paintStrategy = new PaintStrategy();

        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        calculDimensions();

        paintStrategy.paintPlateau(plateau, g, tailleCase, offsetX, offsetY);
        paintStrategy.paintPieces(plateau, g, tailleCase, offsetX, offsetY);

       
        if (pieceSelectionnee != null &&
            pieceSelectionnee.getCol() == plateau.getJoueurCourant()) {

            ArrayList<Case> coups = new ArrayList<>();

            ArrayList<Case> coupsPossibles =
                    pieceSelectionnee.getDeplacement(plateau);

            for (Case c : coupsPossibles) {
                if (plateau.coupValide(caseSelectionnee, c)) {
                    coups.add(c);
                }
            }

            paintStrategy.paintDeplacements(
                    coups, g, tailleCase, offsetX, offsetY
            );
        }
    }

    private void calculDimensions() {

        int largeur = getWidth();
        int hauteur = getHeight();

        tailleCase = Math.min(
                largeur / plateau.getCases()[0].length,
                hauteur / plateau.getCases().length
        );

        int plateauLargeur = tailleCase * plateau.getCases()[0].length;
        int plateauHauteur = tailleCase * plateau.getCases().length;

        offsetX = (largeur - plateauLargeur) / 2;
        offsetY = (hauteur - plateauHauteur) / 2;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (FinParti) return;

        calculDimensions();

        int colonne = (e.getX() - offsetX) / tailleCase;
        int ligne = (e.getY() - offsetY) / tailleCase;

        if (ligne < 0 || colonne < 0 ||
            ligne >= plateau.getCases().length ||
            colonne >= plateau.getCases()[0].length) {
            return;
        }

        Case c = plateau.getCases()[ligne][colonne];
        Piece p = plateau.getPieces().get(c);

     
        if (pieceSelectionnee == null) {

            if (p != null && p.getCol() == plateau.getJoueurCourant()) {
                pieceSelectionnee = p;
                caseSelectionnee = c;
            }
        }

        
        else {

        
            gameManager.jouerCoup(caseSelectionnee, c);

           
            if (gameManager.estEchecEtMat()) {
                JOptionPane.showMessageDialog(this, "ECHEC ET MAT !");
                FinParti = true;
                return;
            }
            else if (gameManager.estEchec()) {
                JOptionPane.showMessageDialog(this, "ECHEC !");
            }

            
            if (bot != null && !FinParti) {

                new Thread(() -> {

                    try {
                        Thread.sleep(500); // délai
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    bot.jouer(plateau, CouleurePiece.NOIR);

                    // retour au thread Swing
                    SwingUtilities.invokeLater(() -> {

                        if (gameManager.estEchecEtMat()) {
                            JOptionPane.showMessageDialog(this, "ECHEC ET MAT !");
                            FinParti = true;
                        }
                        else if (gameManager.estEchec()) {
                            JOptionPane.showMessageDialog(this, "ECHEC !");
                        }

                        repaint();
                    });

                }).start();
            }

            pieceSelectionnee = null;
            caseSelectionnee = null;
        }

        repaint();
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}