package gui;

import java.awt.*;
import java.util.ArrayList;

import engine.map.*;
import engine.mobile.*;

public class PaintStrategy {

    void paintPlateau(Plateau plateau, Graphics g, int taille, int offsetX, int offsetY) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < plateau.getCases().length; i++) {
            for (int j = 0; j < plateau.getCases()[0].length; j++) {
                Case c = plateau.getCases()[i][j];
                g2.setColor(traduireCouleur(c.getCol()));
                g2.fillRect( 
                        offsetX + j * taille,
                        offsetY + i * taille,
                        taille,
                        taille
                );
                g2.setColor(Color.BLACK); 
                g2.drawRect(
                        offsetX + j * taille,
                        offsetY + i * taille,
                        taille,
                        taille
                );
            }
        }
    }
    public void paintPieces(Plateau plateau, Graphics g, int taille, int offsetX, int offsetY) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        for (Case c : plateau.getPieces().keySet()) {
            Piece p = plateau.getPieces().get(c);
            int x = offsetX + c.getColonne() * taille;
            int y = offsetY + c.getLigne() * taille;
            if (p.getCol() == CouleurePiece.ROUGE)
                g2.setColor(new Color(200, 50, 50));
            else
                g2.setColor(new Color(40, 40, 40));

            g2.fillOval(
                    x + taille / 8,
                    y + taille / 8,
                    taille - taille / 4,
                    taille - taille / 4
            );

            g2.setColor(Color.WHITE);

            g2.drawOval(
                    x + taille / 8,
                    y + taille / 8,
                    taille - taille / 4,
                    taille - taille / 4
            );

  
            g2.setFont(new Font("Arial", Font.BOLD, taille / 5));

            String texte = p.getClass().getSimpleName();

            FontMetrics fm = g2.getFontMetrics();

            int textX = x + taille / 2 - fm.stringWidth(texte) / 2;
            int textY = y + taille / 2 + fm.getAscent() / 2;

            g2.drawString(texte, textX, textY);
        }
    }

    public void paintDeplacements(ArrayList<Case> coups, Graphics g, int taille, int offsetX, int offsetY) {
        if (coups == null)
            return;
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0, 255, 0, 120)); 
        for (Case c : coups) {
            int x = offsetX + c.getColonne() * taille;
            int y = offsetY + c.getLigne() * taille;

            g2.fillOval(
                    x + taille / 3,
                    y + taille / 3,
                    taille / 3,
                    taille / 3
            );
        }
    }

    private Color traduireCouleur(Couleur col) {

        switch (col) {

            case JAUNE:
                return new Color(240, 220, 120);

            case BLANC:
                return new Color(245, 245, 245);

            case NOIR:
                return new Color(120, 120, 120);

            case BLUE:
                return new Color(100, 150, 255);

            case ROUG:
                return new Color(200, 80, 80);

            default:
                return Color.WHITE;
        }
    }
}