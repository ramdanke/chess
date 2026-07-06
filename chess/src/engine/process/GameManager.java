package engine.process;

import org.apache.log4j.Logger;

import engine.map.*;
import engine.mobile.*;
import log.LoggerUtility;

public class GameManager {

    private Plateau plateau;
    private static Logger logger=LoggerUtility.getLogger(GameManager.class, "html");

    public GameManager() {
        plateau = new Plateau();
        logger.info("GameManager cree");
    }

    public Plateau getPlateau() {
        return plateau;
    }

    
    public boolean jouerCoup(Case depart, Case arrivee) {

        if (!plateau.existPiece(depart)) return false;
        if (!plateau.bonJoueure(depart)) return false;
        if (!plateau.coupValide(depart, arrivee)) return false;

        plateau.DeplacerePiece(depart, arrivee);
        plateau.ChangerJoueur();

        return true; 
    }
    public void setPlateau(Plateau p) {
        this.plateau = p;
    }
   
    
    public CouleurePiece getJoueurCourant() {
        return plateau.getJoueurCourant();
    }

   
    public boolean estEchec() {
        return plateau.EnEchec(plateau.getJoueurCourant());
    }

    
    public boolean estEchecEtMat() {
        return plateau.EnEchecEtMat(plateau.getJoueurCourant());
    }

    
    public Piece getPiece(Case c) {
        return plateau.getPieces().get(c);
    }
}