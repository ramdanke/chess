package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import engine.map.*;
import engine.mobile.*;

public class GameDisplay extends JPanel implements MouseListener {

    private static final long serialVersionUID = 1L;

    private Plateau plateau;
    private PaintStrategy paintStrategy;

    private Piece pieceSelectionnee = null;
    private Case caseSelectionnee = null;

    // Variables dynamiques
    private int tailleCase;
    private int offsetX;
    private int offsetY;

    public GameDisplay(Plateau plateau) {
        this.plateau = plateau;
        this.paintStrategy = new PaintStrategy();
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        calculDimensions();

        paintStrategy.paintPlateau(plateau, g, tailleCase, offsetX, offsetY);
        paintStrategy.paintPieces(plateau, g, tailleCase, offsetX, offsetY);

        if (pieceSelectionnee != null) {
            ArrayList<Case> coups = pieceSelectionnee.getDeplacement(plateau);
            paintStrategy.paintDeplacements(coups, g, tailleCase, offsetX, offsetY);
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

            if (p != null) {
                pieceSelectionnee = p;
                caseSelectionnee = c;
            }

        } else {

            ArrayList<Case> coups = pieceSelectionnee.getDeplacement(plateau);

            if (coups != null && coups.contains(c)) {

                plateau.getPieces().remove(caseSelectionnee);

                if (c instanceof CaseNormal) {
                    pieceSelectionnee.setPosition((CaseNormal) c);
                    plateau.getPieces().put(c, pieceSelectionnee);
                }
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