package engine.map;
import engine.mobile.*;

import java.util.HashMap;

import config.GameConfiguration;

public class Plateau {

	private Case[][] cases=new Case [GameConfiguration.NB_LIGNES][GameConfiguration.NB_COLONNES]; 
	private HashMap<Case,Piece> pieces=new HashMap<Case,Piece>();
	
	public Plateau() {
		for(int i=0;i<GameConfiguration.NB_LIGNES;i++) {
			for(int j=0;j<GameConfiguration.NB_COLONNES;j++) {
				if(i==4 || i==8 ) {
				cases[i][j] =new CaseMort(i,j,Couleur.ROUG);
				}
				else if( j==6) {
					cases[i][j] =new CaseMort(i,j,Couleur.BLUE);
				}
				else
					if((i>=5 && i<=7) && (j<=3 || j>=9)) {
						cases[i][j]=new CaseNormal(i,j,Couleur.JAUNE,TypeCaseNormale.CASE_PALAIS);
			}else if(i % 2 ==0 && j % 2 ==0) {
				cases[i][j]=new CaseNormal(i,j,Couleur.BLANC,TypeCaseNormale.CASE_NON_PALAIS);
			}else {
				cases[i][j]=new CaseNormal(i,j,Couleur.NOIR,TypeCaseNormale.CASE_NON_PALAIS);
			}
				}
		}
			int k=0;
			int f=0;
			pieces.put(cases[k][f], new Char((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-f], new Char((CaseNormal) cases[k][10-k],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Char((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Char((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
		
		
			
			f=1;
			pieces.put(cases[k][f], new Chevalier((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-f], new Chevalier((CaseNormal) cases[k][10-k],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Chevalier((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Chevalier((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
		
	
			
			f=2;
			pieces.put(cases[k][f], new Elephant((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-k], new Elephant((CaseNormal) cases[k][10-k],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Elephant((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Elephant((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
		
		
			
			f=4;
			pieces.put(cases[k][f], new Conseillere((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-k], new Conseillere((CaseNormal) cases[k][10-k],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Conseillere((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Conseillere((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
	
			k=2;
			f=1;
			pieces.put(cases[k][f], new Canon((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-k], new Canon((CaseNormal) cases[k][10-k],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Canon((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Canon((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
		
			k=3;
			f=0;
			pieces.put(cases[k][f], new Soldat((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-k], new Soldat((CaseNormal) cases[k][10-k],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Soldat((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Soldat((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
			
			f=2;
			pieces.put(cases[k][f], new Soldat((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[k][10-k], new Soldat((CaseNormal) cases[k][10-k],CouleurePiece.ROUGE)); 
			pieces.put(cases[10-k][f], new Soldat((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
			pieces.put(cases[10-k][10-f], new Soldat((CaseNormal) cases[10-k][10-f],CouleurePiece.NOIR));
			
			
			f=5;
			pieces.put(cases[k][f], new Soldat((CaseNormal) cases[k][f],CouleurePiece.ROUGE));
			pieces.put(cases[10-k][f], new Soldat((CaseNormal) cases[10-k][f],CouleurePiece.NOIR));
		
		
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
	
}
