package junit;

import org.junit.Test;
import static org.junit.Assert.*;

import engine.map.*;
import engine.mobile.*;

public class PlateauTest {

    @Test
    public void testInitialisationPlateau() {
        Plateau plateau = new Plateau();

        assertNotNull(plateau.getCases());
        assertNotNull(plateau.getPieces());
    }

    @Test
    public void testPresencePieces() {
        Plateau plateau = new Plateau();

        assertTrue(plateau.getPieces().size() > 0);
    }

    @Test
    public void testJoueurInitial() {
        Plateau plateau = new Plateau();

        assertEquals(CouleurePiece.ROUGE, plateau.getJoueurCourant());
    }

    @Test
    public void testChangerJoueur() {
        Plateau plateau = new Plateau();

        plateau.ChangerJoueur();

        assertEquals(CouleurePiece.NOIR, plateau.getJoueurCourant());
    }

    @Test
    public void testExistPiece() {
        Plateau plateau = new Plateau();

        Case c = plateau.getCases()[0][0]; 

        assertTrue(plateau.existPiece(c));
    }

    @Test
    public void testCaseVide() {
        Plateau plateau = new Plateau();

        Case c = plateau.getCases()[5][5]; 

        assertFalse(plateau.existPiece(c));
    }

    @Test
    public void testBordure() {
        Plateau plateau = new Plateau();

        Case c = plateau.getCases()[0][0];

        assertTrue(plateau.EnBordure(c));
    }

    @Test
    public void testNonBordure() {
        Plateau plateau = new Plateau();

        Case c = plateau.getCases()[5][5];

        assertFalse(plateau.EnBordure(c));
    }

    @Test
    public void testPresenceRoi() {
        Plateau plateau = new Plateau();

        Case roiRouge = plateau.CaseRoi(CouleurePiece.ROUGE);

        assertNotNull(roiRouge);
    }

    @Test
    public void testDeplacement() {
        Plateau plateau = new Plateau();

        Case depart = plateau.getCases()[3][0]; 
        Case arrivee = plateau.getCases()[4][0];

        plateau.jouerCup(depart, arrivee);

        assertFalse(plateau.existPiece(depart));
        assertTrue(plateau.existPiece(arrivee));
    }

    @Test
    public void testCoupValide() {
        Plateau plateau = new Plateau();

        Case depart = plateau.getCases()[3][0]; 
        Case arrivee = plateau.getCases()[4][0];

        boolean resultat = plateau.coupValide(depart, arrivee);

        assertTrue(resultat);
    }

    @Test
    public void testCoupInvalide() {
        Plateau plateau = new Plateau();

        Case depart = plateau.getCases()[3][0];
        Case arrivee = plateau.getCases()[0][0]; 

        boolean resultat = plateau.coupValide(depart, arrivee);

        assertFalse(resultat);
    }

    @Test
    public void testChangementJoueurApresCoup() {
        Plateau plateau = new Plateau();

        Case depart = plateau.getCases()[3][0];
        Case arrivee = plateau.getCases()[4][0];

        plateau.jouerCup(depart, arrivee);

        assertEquals(CouleurePiece.NOIR, plateau.getJoueurCourant());
    }

    @Test
    public void testCapture() {
        Plateau plateau = new Plateau();

        Case depart = plateau.getCases()[3][0];
        Case cible = plateau.getCases()[7][0];

        plateau.DeplacerePiece(depart, cible);

        assertTrue(plateau.getCapturesNoir().size() > 0);
    }

    @Test
    public void testRoiExiste() {
        Plateau plateau = new Plateau();

        assertNotNull(plateau.CaseRoi(CouleurePiece.ROUGE));
        assertNotNull(plateau.CaseRoi(CouleurePiece.NOIR));
    }

    @Test
    public void testRoiFaceAFace() {
        Plateau plateau = new Plateau();

        boolean resultat = plateau.RoiFacAfac();

        assertFalse(resultat); 
    }

    @Test
    public void testPasEnEchecAuDebut() {
        Plateau plateau = new Plateau();

        boolean echec = plateau.EnEchec(CouleurePiece.ROUGE);

        assertFalse(echec);
    }
}