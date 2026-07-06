package engine.process;

import java.util.*;

import org.apache.log4j.Logger;

import engine.map.*;
import engine.mobile.*;
import log.LoggerUtility;

public class Bot {

    private Niveau niveau;
    private Random random = new Random();
    private static Logger logger=LoggerUtility.getLogger(GameManager.class, "html");

    public Bot(Niveau niveau) {
        this.niveau = niveau;
        logger.debug("Bot joue un coup");
    }

    
    public Coup jouer(Plateau plateau, CouleurePiece couleurBot) {

        switch (niveau) {

            case FACILE:
                return jouerAleatoire(plateau, couleurBot);

            case MOYEN:
                return jouerCapture(plateau, couleurBot);

            case DIFFICILE:
                return jouerMeilleurCoup(plateau, couleurBot);
        }

        return null;
    }

    private Coup jouerAleatoire(Plateau plateau, CouleurePiece couleurBot) {

        ArrayList<Coup> coups = tousLesCoups(plateau, couleurBot);

        if (coups.isEmpty()) return null;

        Coup c = coups.get(random.nextInt(coups.size()));
        plateau.jouerCup(c.depart, c.arrivee);

        return c;
    }

    private Coup jouerCapture(Plateau plateau, CouleurePiece couleurBot) {

        ArrayList<Coup> coups = tousLesCoups(plateau, couleurBot);

        Coup meilleur = null;
        int meilleureValeur = -1;

        for (Coup c : coups) {

            Piece piece = plateau.getPieces().get(c.depart);
            Piece cible = plateau.getPieces().get(c.arrivee);

            if (cible != null) {
 
                int valeur = valeurPiece(cible);

               
                Piece capturee = plateau.getPieces().get(c.arrivee);
                plateau.getPieces().remove(c.depart);
                plateau.getPieces().put(c.arrivee, piece);

                Case ancienne = piece.getPosition();
                piece.setPosition((CaseNormal) c.arrivee);

                
                boolean dangereux = plateau.estMenacer(c.arrivee, inverse(couleurBot));

               
                plateau.getPieces().remove(c.arrivee);
                plateau.getPieces().put(c.depart, piece);
                piece.setPosition((CaseNormal) ancienne);

                if (capturee != null) {
                    plateau.getPieces().put(c.arrivee, capturee);
                }

                
                if (!dangereux && valeur > meilleureValeur) {
                    meilleureValeur = valeur;
                    meilleur = c;
                }
            }
        }

       
        if (meilleur != null) {
            plateau.jouerCup(meilleur.depart, meilleur.arrivee);
            return meilleur;
        }

       
        return jouerAleatoire(plateau, couleurBot);
    }

    private Coup jouerMeilleurCoup(Plateau plateau, CouleurePiece couleurBot) {

        ArrayList<Coup> coups = tousLesCoups(plateau, couleurBot);

        Coup meilleur = null;
        int meilleurScore = -9999;

        for (Coup c : coups) {

            Piece piece = plateau.getPieces().get(c.depart);
            Piece cible = plateau.getPieces().get(c.arrivee);

            int score = 0;

            
            score += valeurPiece(cible) * 10;

            
            Piece capturee = plateau.getPieces().get(c.arrivee); 
            plateau.getPieces().remove(c.depart);
            plateau.getPieces().put(c.arrivee, piece);

            Case ancienne = piece.getPosition();
            piece.setPosition((CaseNormal) c.arrivee);

            
            if (plateau.estMenacer(c.arrivee, inverse(couleurBot))) {
                score -= valeurPiece(piece) * 8; 
            }

            
            if (plateau.EnEchec(inverse(couleurBot))) {
                score += 15;
            }

            
            if (!plateau.estMenacer(plateau.CaseRoi(couleurBot), inverse(couleurBot))) {
                score += 5;
            }

            
            if (plateau.RoiFacAfac()) {
                score -= 50;
            }

            
            plateau.getPieces().remove(c.arrivee);
            plateau.getPieces().put(c.depart, piece);
            piece.setPosition((CaseNormal) ancienne);

            if (capturee != null) {
                plateau.getPieces().put(c.arrivee, capturee);
            }

            
            if (score > meilleurScore) {
                meilleurScore = score;
                meilleur = c;
            }
        }

        if (meilleur != null) {
            plateau.jouerCup(meilleur.depart, meilleur.arrivee);
            return meilleur;
        }

        return jouerAleatoire(plateau, couleurBot);
    }
    private CouleurePiece inverse(CouleurePiece c) {
        if (c == CouleurePiece.ROUGE) return CouleurePiece.NOIR;
        return CouleurePiece.ROUGE;
    }

    private ArrayList<Coup> tousLesCoups(Plateau plateau, CouleurePiece couleur) {

        ArrayList<Coup> coups = new ArrayList<>();
        ArrayList<Case> cases = new ArrayList<>(plateau.getPieces().keySet());

        for (Case c : cases) {

            Piece p = plateau.getPieces().get(c);

            if (p != null && p.getCol() == couleur) {

                for (Case dest : p.getDeplacement(plateau)) {

                    if (plateau.coupValide(c, dest)) {
                        coups.add(new Coup(c, dest));
                    }
                }
            }
        }

        return coups;
    }

    private int valeurPiece(Piece p) {

        if (p == null) return 0;
        if (p instanceof General) return 100;
        if (p instanceof Char) return 9;
        if (p instanceof Canon) return 5;
        if (p instanceof Chevalier) return 4;
        if (p instanceof Elephant) return 3;
        if (p instanceof Conseillere) return 2;
        if (p instanceof Soldat) return 1;

        return 0;
    }
}