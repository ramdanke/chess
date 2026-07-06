package engine.map;
import engine.mobile.*;

import java.util.ArrayList;
import java.util.HashMap;

import config.GameConfiguration;

public class Plateau {

	private Case[][] cases=new Case [GameConfiguration.NB_LIGNES][GameConfiguration.NB_COLONNES]; 
	private HashMap<Case,Piece> pieces=new HashMap<Case,Piece>();
	private CouleurePiece joueurCourant;
	private ArrayList<Piece> capturesRouge = new ArrayList<>();
    private ArrayList<Piece> capturesNoir = new ArrayList<>();
	public Plateau() {
		for(int i=0;i<GameConfiguration.NB_LIGNES;i++) {
			for(int j=0;j<GameConfiguration.NB_COLONNES;j++) {
				if(j==3 || j==7 ) {  
				cases[i][j] =new CaseMort(i,j,Couleur.ROUG);
				}
				else if( i==5) {
					cases[i][j] =new CaseMort(i,j,Couleur.BLUE); 
				}    
				else 
					if((i<=2 && (j<=6 && j>=4)) ||(i>=8 && (j<=6 && j>=4))) { 
						cases[i][j]=new CaseNormal(i,j,Couleur.JAUNE,TypeCaseNormale.CASE_PALAIS); 
			}else if(i%2==0 && j%2!=0) {
				cases[i][j]=new CaseNormal(i,j,Couleur.BLANC,TypeCaseNormale.CASE_NON_PALAIS);  
			}else if(i%2 ==0 && j%2==0){ 
				cases[i][j]=new CaseNormal(i,j,Couleur.NOIR,TypeCaseNormale.CASE_NON_PALAIS);
			} else if(i%2!=0 && j%2!=0) {
				cases[i][j]=new CaseNormal(i,j,Couleur.NOIR,TypeCaseNormale.CASE_NON_PALAIS); 
			}else if(i%2!=0 && j%2==0) {
				cases[i][j]=new CaseNormal(i,j,Couleur.BLANC,TypeCaseNormale.CASE_NON_PALAIS); 
			} 
				}
		}
			int k=0;
			int f=0;
			pieces.put(cases[k][f], new Char((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-f], new Char((CaseNormal) cases[k][10-f],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Char((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Char((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
		
		
			
			f=1;
			pieces.put(cases[k][f], new Chevalier((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-f], new Chevalier((CaseNormal) cases[k][10-f],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Chevalier((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Chevalier((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
		
	
			
			f=2;
			pieces.put(cases[k][f], new Elephant((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-f], new Elephant((CaseNormal) cases[k][10-f],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Elephant((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Elephant((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
		
		
			
			f=4;
			pieces.put(cases[k][f], new Conseillere((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-f], new Conseillere((CaseNormal) cases[k][10-f],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Conseillere((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Conseillere((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
	
			k=2;
			f=1;
			pieces.put(cases[k][f], new Canon((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-f], new Canon((CaseNormal) cases[k][10-f],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Canon((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Canon((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
		
			k=3;
			f=0;
			pieces.put(cases[k][f], new Soldat((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-f], new Soldat((CaseNormal) cases[k][10-f],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Soldat((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Soldat((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
			
			f=2;
			pieces.put(cases[k][f], new Soldat((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-f], new Soldat((CaseNormal) cases[k][10-f],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Soldat((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Soldat((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
			
			
			f=5;
			pieces.put(cases[k][f], new Soldat((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[10-k][f], new Soldat((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			
			k=0;
			f=5;
			pieces.put(cases[k][f], new General((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[10-k][f], new General((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
	
			this.joueurCourant=CouleurePiece.ROUGE;
	}
	
public boolean ToutAdroit(Case cas) { 
	return cas.getColonne()==10;
}
public boolean ToutAgauche(Case cas) {
	return cas.getColonne()==0;
} 
public boolean ToutEnHaut(Case cas) {
	return cas.getLigne()==10;
}
public boolean ToutEnBas(Case cas) {
	return cas.getLigne()==0;
}

public boolean EnBordure(Case cas) {
	return ToutAdroit(cas) ||  ToutAgauche(cas) || ToutEnHaut(cas) || ToutEnBas(cas);
}

	public Case[][] getCases() {
		return cases;
	}

	public void setCases(Case[][] cases) {
		this.cases = cases;
	}

	public HashMap<Case, Piece> getPieces() {
		return pieces;
	}

	public void setPieces(HashMap<Case, Piece> pieces) {
		this.pieces = pieces;
	}

	public CouleurePiece getJoueurCourant() {
		return joueurCourant;
	}

	public void setJoueurCourant(CouleurePiece joueurCourant) {
		this.joueurCourant = joueurCourant; 
	}
	public void ChangerJoueur() {
		if(this.getJoueurCourant().equals(CouleurePiece.ROUGE)){
			this.setJoueurCourant(CouleurePiece.NOIR); 
			}else { 
				this.setJoueurCourant(CouleurePiece.ROUGE);
			}
	}
	public boolean existPiece(Case C) {
		return(this.getPieces().get(C)!=null);
	}
	public boolean bonJoueure(Case C) {
	    Piece p = pieces.get(C);
	    return p != null && p.getCol().equals(joueurCourant);
	}
	public Case CaseRoi(CouleurePiece couleur) {

	    for(Case c : pieces.keySet()) {

	        Piece p = pieces.get(c);

	        if(p instanceof General && p.getCol().equals(couleur)) {
	            return c;
	        }
	    }

	    return null;
	}
	public boolean RoiFacAfac() {
		Case roiRouge =CaseRoi(CouleurePiece.ROUGE);
		Case roiNoir=CaseRoi(CouleurePiece.NOIR);
		if(roiRouge.getColonne()!= roiNoir.getColonne())
			return false;
		if(roiRouge.getColonne()==roiNoir.getColonne()) {
			for(int i=roiRouge.getLigne()+1;i<roiNoir.getLigne();i++) {
				if(this.getPieces().get(getCases()[i][roiRouge.getColonne()])!=null) {
				return false;
				}
			}
		}
		return true;
		}
	public boolean coupValide(Case depart, Case arrivee) {
	    Piece piece = pieces.get(depart);
	    if(!piece.getDeplacement(this).contains(arrivee)) {
	        return false;
	    }
	    Piece pieceCapturee = pieces.get(arrivee);
	    pieces.remove(depart);
	    pieces.put(arrivee, piece);
	    Case anciennePosition = piece.getPosition(); 
	    piece.setPosition((CaseNormal) arrivee);
	    Case caseGeneral = CaseRoi(joueurCourant);
	    CouleurePiece adversaire;
	    if(joueurCourant == CouleurePiece.ROUGE) {
	        adversaire = CouleurePiece.NOIR;
	    }
	    else {
	        adversaire = CouleurePiece.ROUGE; 
	    }
	    boolean echec = estMenacer(caseGeneral, adversaire);
	    boolean face = RoiFacAfac();
	    pieces.remove(arrivee);
	    pieces.put(depart, piece);
	    piece.setPosition((CaseNormal) anciennePosition);
	    if(pieceCapturee != null) {
	        pieces.put(arrivee, pieceCapturee);
	    }
	    return !echec && !face;
	}
	
	public void jouerCup(Case depart, Case arrivee) {
	    if(!existPiece(depart)) return;
	    if(!bonJoueure(depart)) return;
	    if(!coupValide(depart, arrivee)) return;
	    DeplacerePiece(depart, arrivee);
	    ChangerJoueur(); 
	}
	public boolean estMenacer(Case c, CouleurePiece agresseur) {
	    for(Case casePiece : pieces.keySet()) {
	        Piece p = pieces.get(casePiece);
	        if(p.getCol() == agresseur) {
	            ArrayList<Case> deplacements = p.getDeplacement(this);
	            if(deplacements.contains(c)) {
	                return true;
	            } 
	        }
	    }
	    return false;
	}  
	public boolean EnEchec(CouleurePiece joueure) {
		CouleurePiece joueureAdv;
		if(joueure.equals(CouleurePiece.NOIR)) {
			joueureAdv=CouleurePiece.ROUGE;
		}else {
			joueureAdv=CouleurePiece.NOIR;
		}     
		return(this.estMenacer(CaseRoi(joueure), joueureAdv));
	}
	public boolean EnEchecEtMat(CouleurePiece joueur) { 

	    if(!EnEchec(joueur)) {
	        return false;
	    }
	    ArrayList<Case> listeCases = new ArrayList<>(pieces.keySet());
	    for (Case cle : listeCases) { 
	        Piece valeur = pieces.get(cle); 
	        if(valeur != null && valeur.getCol().equals(joueur)) {
	            ArrayList<Case> deplacements = valeur.getDeplacement(this);
	            for(Case destination : deplacements) {
	               if(coupValide(cle, destination)) {
	                    return false;
	                }
	            }
	        }
	    }
	    return true;
	}
	  public void DeplacerePiece(Case depart, Case arrivee) {

	        Piece p = pieces.get(depart);
	        Piece capturee = pieces.get(arrivee);

	        // ✅ gestion capture
	        if (capturee != null) {
	            if (capturee.getCol() == CouleurePiece.ROUGE) {
	                capturesRouge.add(capturee);
	            } else {
	                capturesNoir.add(capturee);
	            }
	        }

	        pieces.remove(depart);
	        pieces.put(arrivee, p);

	        p.setPosition((CaseNormal) arrivee);
	    }

	public ArrayList<Piece> getCapturesRouge() {
		return capturesRouge;
	}

	public void setCapturesRouge(ArrayList<Piece> capturesRouge) {
		this.capturesRouge = capturesRouge;
	}

	public ArrayList<Piece> getCapturesNoir() {
		return capturesNoir;
	}

	public void setCapturesNoir(ArrayList<Piece> capturesNoir) {
		this.capturesNoir = capturesNoir;
	}
	
}
