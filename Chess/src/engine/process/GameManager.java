package engine.process;

import engine.map.*;
import engine.mobile.*;

public class GameManager {

    private Plateau plateau;

    public GameManager() {
        plateau = new Plateau();
    }

    // donne accès au plateau pour l'affichage
    public Plateau getPlateau() {
        return plateau;
    }

    // joue un coup
    public void jouerCoup(Case depart, Case arrivee) {
        plateau.jouerCup(depart, arrivee);
    }

    // joueur courant
    public CouleurePiece getJoueurCourant() {
        return plateau.getJoueurCourant();
    }

    // vérifier échec
    public boolean estEchec() {
        return plateau.EnEchec(plateau.getJoueurCourant());
    }

    // vérifier échec et mat
    public boolean estEchecEtMat() {
        return plateau.EnEchecEtMat(plateau.getJoueurCourant());
    }

    // accès aux pièces
    public Piece getPiece(Case c) {
        return plateau.getPieces().get(c);
    }
}
