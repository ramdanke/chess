package gui;

import java.awt.*;
import java.util.ArrayList;

import engine.map.*;
import engine.mobile.*;

public class PaintStrategy {

    public void paintPlateau(Plateau plateau, Graphics g,
                             int taille, int offsetX, int offsetY) {

        for (int i = 0; i < plateau.getCases().length; i++) {
            for (int j = 0; j < plateau.getCases()[0].length; j++) {

                Case c = plateau.getCases()[i][j];

                g.setColor(traduireCouleur(c.getCol()));

                g.fillRect(
                        offsetX + j * taille,
                        offsetY + i * taille,
                        taille,
                        taille
                );

                g.setColor(Color.BLACK);
                g.drawRect(
                        offsetX + j * taille,
                        offsetY + i * taille,
                        taille,
                        taille
                );
            }
        }
    }

    public void paintPieces(Plateau plateau, Graphics g,
                            int taille, int offsetX, int offsetY) {

        for (Case c : plateau.getPieces().keySet()) {

            Piece p = plateau.getPieces().get(c);

            int x = offsetX + c.getColonne() * taille;
            int y = offsetY + c.getLigne() * taille;

            if (p.getCol().name().equals("ROUGE"))
                g.setColor(Color.RED);
            else
                g.setColor(Color.BLACK);

            g.fillOval(x + taille/6, y + taille/6,
                       taille - taille/3, taille - taille/3);

            g.setColor(Color.WHITE);
            g.drawString(
                    p.getClass().getSimpleName(),
                    x + taille/4,
                    y + taille/2
            );
        }
    }

    public void paintDeplacements(ArrayList<Case> coups, Graphics g,
                                   int taille, int offsetX, int offsetY) {

        if (coups == null) return;

        g.setColor(new Color(0, 255, 0, 120));

        for (Case c : coups) {

            int x = offsetX + c.getColonne() * taille;
            int y = offsetY + c.getLigne() * taille;

            g.fillRect(x, y, taille, taille);
        }
    }

    private Color traduireCouleur(Couleur col) {

        switch (col) {
            case JAUNE: return Color.YELLOW;
            case BLANC: return Color.WHITE;
            case NOIR: return Color.GRAY;
            case BLUE: return Color.BLUE;
            case ROUG: return Color.RED;
            default: return Color.WHITE;
        }
    }
}