package gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import engine.map.*;
import engine.mobile.*;

public class PaintStrategy {

    private HashMap<String, Image> images = new HashMap<>();

    public PaintStrategy() {

        //  ROUGE
        images.put("general_rouge",     load("/images/general_rouge.png"));
        images.put("char_rouge",        load("/images/char_rouge.png"));
        images.put("canon_rouge",       load("/images/canon_rouge.png"));
        images.put("chevalier_rouge",   load("/images/chevalier_rouge.png"));
        images.put("elephant_rouge",    load("/images/elephant_rouge.png"));
        images.put("conseillere_rouge", load("/images/conseillere_rouge.png"));
        images.put("soldat_rouge",      load("/images/soldat_rouge.png"));

        // NOIR
        images.put("general_noir",     load("/images/general_noir.png"));
        images.put("char_noir",        load("/images/char_noir.png"));
        images.put("canon_noir",       load("/images/canon_noir.png"));
        images.put("chevalier_noir",   load("/images/chevalier_noir.png"));
        images.put("elephant_noir",    load("/images/elephant_noir.png"));
        images.put("conseillere_noir", load("/images/conseillere_noir.png"));
        images.put("soldat_noir",      load("/images/soldat_noir.png"));
    } 

    //  chargement sécurisé
    private Image load(String path) {
        try {
            return new ImageIcon(getClass().getResource(path)).getImage();
        } catch (Exception e) {
            System.out.println("Image non trouvée : " + path);
            return null;
        }
    }

    //  plateau
    public void paintPlateau(Plateau plateau, Graphics g, int taille, int offsetX, int offsetY) {

        for (int i = 0; i < plateau.getCases().length; i++) {
            for (int j = 0; j < plateau.getCases()[0].length; j++) {

                Case c = plateau.getCases()[i][j];

                g.setColor(traduireCouleur(c.getCol()));
                g.fillRect(offsetX + j * taille, offsetY + i * taille, taille, taille);

                g.setColor(Color.DARK_GRAY);
                g.drawRect(offsetX + j * taille, offsetY + i * taille, taille, taille);
            }
        }
    }

    //  pièces avec images
    public void paintPieces(Plateau plateau, Graphics g, int taille, int offsetX, int offsetY) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        for (Case c : plateau.getPieces().keySet()) {

            Piece p = plateau.getPieces().get(c);

            int x = offsetX + c.getColonne() * taille;
            int y = offsetY + c.getLigne() * taille;

            String key = p.getClass().getSimpleName().toLowerCase()
                       + "_"
                       + p.getCol().name().toLowerCase();

            Image img = images.get(key);

            if (img != null) {
                g2.drawImage(img, x + 5, y + 5, taille - 10, taille - 10, null);
            } else {
                System.out.println("Image manquante pour : " + key);
                // fallback : cercle coloré + nom
                if (p.getCol() == CouleurePiece.ROUGE)
                    g.setColor(Color.RED);
                else
                    g.setColor(Color.BLACK);
                g.fillOval(x + taille / 6, y + taille / 6, taille - taille / 3, taille - taille / 3);
                g.setColor(Color.WHITE);
                g.drawString(p.getClass().getSimpleName(), x + taille / 4, y + taille / 2);
            }
        }
    }

    //  déplacements possibles (avec couleur du joueur)
    public void paintDeplacements(ArrayList<Case> coups, Graphics g,
                                  int taille, int offsetX, int offsetY,
                                  CouleurePiece couleur) {

        if (coups == null) return;

        if (couleur == CouleurePiece.ROUGE) {
            g.setColor(new Color(255, 0, 0, 120));
        } else {
            g.setColor(new Color(0, 0, 255, 120));
        }

        for (Case c : coups) {
            int x = offsetX + c.getColonne() * taille;
            int y = offsetY + c.getLigne() * taille;
            g.fillRect(x, y, taille, taille);
        }
    }

    //  couleurs cases
    private Color traduireCouleur(Couleur col) {
        switch (col) {
            case JAUNE: return new Color(240, 217, 181);
            case BLANC: return Color.WHITE;
            case NOIR:  return new Color(181, 136, 99);
            case BLUE:  return Color.BLUE;
            case ROUG:  return Color.RED;
            default:    return Color.GRAY;
        }
    }
}
