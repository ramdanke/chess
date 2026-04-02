	package engine.process;

	import java.util.*;

	import engine.map.*;
	import engine.mobile.*;

	public class Bot {

	    private Niveau niveau;
	    private Random random = new Random();

	    public Bot(Niveau niveau) {
	        this.niveau = niveau;
	    }

	    public void jouer(Plateau plateau, CouleurePiece couleurBot) {

	        switch (niveau) {

	            case FACILE:
	                jouerAleatoire(plateau, couleurBot);
	                break;

	            case MOYEN:
	                jouerCapture(plateau, couleurBot);
	                break;

	            case DIFFICILE:
	                jouerMeilleurCoup(plateau, couleurBot);
	                break;
	        }
	    }

	    // 		 NIVEAU FACILE 
	    private void jouerAleatoire(Plateau plateau, CouleurePiece couleurBot) {

	        ArrayList<Coup> coups = tousLesCoups(plateau, couleurBot);

	        if (coups.isEmpty()) return;

	        Coup c = coups.get(random.nextInt(coups.size()));

	        plateau.jouerCup(c.depart, c.arrivee);
	    }

	    // 			 NIVEAU MOYEN 
	    private void jouerCapture(Plateau plateau, CouleurePiece couleurBot) {

	        ArrayList<Coup> coups = tousLesCoups(plateau, couleurBot);

	        ArrayList<Coup> captures = new ArrayList<>();

	        for (Coup c : coups) {
	            if (plateau.getPieces().get(c.arrivee) != null) {
	                captures.add(c);
	            }
	        }

	        if (!captures.isEmpty()) {
	            Coup c = captures.get(random.nextInt(captures.size()));
	            plateau.jouerCup(c.depart, c.arrivee);
	        } else {
	            jouerAleatoire(plateau, couleurBot);
	        }
	    }

	    // 			 NIVEAU DIFFICILE 
	    private void jouerMeilleurCoup(Plateau plateau, CouleurePiece couleurBot) {

	        ArrayList<Coup> coups = tousLesCoups(plateau, couleurBot);

	        Coup meilleur = null;
	        int meilleureValeur = -999;

	        for (Coup c : coups) {

	            Piece cible = plateau.getPieces().get(c.arrivee);

	            int valeur = valeurPiece(cible);

	            if (valeur > meilleureValeur) {
	                meilleureValeur = valeur;
	                meilleur = c;
	            }
	        }

	        if (meilleur != null) {
	            plateau.jouerCup(meilleur.depart, meilleur.arrivee);
	        } else {
	            jouerAleatoire(plateau, couleurBot);
	        }
	    }

	    // 			 UTILS 
 
	    private ArrayList<Coup> tousLesCoups(Plateau plateau, CouleurePiece couleur) {

	        ArrayList<Coup> coups = new ArrayList<>();

	
	        ArrayList<Case> cases = new ArrayList<>(plateau.getPieces().keySet());

	        for (Case c : cases) {

	            Piece p = plateau.getPieces().get(c);

	            if (p != null && p.getCol() == couleur) {

	                ArrayList<Case> deplacements = p.getDeplacement(plateau);

	                for (Case dest : deplacements) {

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

