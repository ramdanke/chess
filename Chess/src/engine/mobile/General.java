package engine.mobile;
import java.util.ArrayList;

import engine.map.*;

public class General extends Piece {
	 public General(CaseNormal pos,CouleurePiece col) {
		 super(pos,col);
	 }
	 @Override
	 public ArrayList<Case> getDeplacement(Plateau P){
	 	ArrayList<Case> list=new ArrayList<Case>(); 
	 	CaseNormal cas=this.getPosition();
	 	
	 	int i=cas.getLigne()+1;
	 	int j=cas.getColonne();
	 	if(!P.ToutEnHaut(cas) &&P.getCases()[i][j].getCol().equals(Couleur.JAUNE) ) {
	 		if(P.getPieces().get(P.getCases()[i][j])==null ) {
	 			list.add(P.getCases()[i][j]);
	 		}
	 		else if(!P.getPieces().get(P.getCases()[i][j]).getCol().equals(this.getCol())){
	 			list.add(P.getCases()[i][j]);
	 		} 
	 	}
	 	 i=cas.getLigne()-1;
	 	 j=cas.getColonne();
	 	if(!P.ToutEnBas(cas) &&P.getCases()[i][j].getCol().equals(Couleur.JAUNE) ) {
	 		if(P.getPieces().get(P.getCases()[i][j])==null ) {
	 			list.add(P.getCases()[i][j]);
	 		}
	 		else if(!P.getPieces().get(P.getCases()[i][j]).getCol().equals(this.getCol())){
	 			list.add(P.getCases()[i][j]);
	 		} 
	 	}
	 	i=cas.getLigne();
	 	j=cas.getColonne()+1;
	 	if(!P.ToutAdroit(cas) &&P.getCases()[i][j].getCol().equals(Couleur.JAUNE) ) {
	 		if(P.getPieces().get(P.getCases()[i][j])==null ) {
	 			list.add(P.getCases()[i][j]);
	 		}
	 		else if(!P.getPieces().get(P.getCases()[i][j]).getCol().equals(this.getCol())){
	 			list.add(P.getCases()[i][j]);
	 		} 
	 	}
	 	i=cas.getLigne();
	 	j=cas.getColonne()-1;
	 	if(!P.ToutAgauche(cas) &&P.getCases()[i][j].getCol().equals(Couleur.JAUNE) ) {
	 		if(P.getPieces().get(P.getCases()[i][j])==null ) {
	 			list.add(P.getCases()[i][j]);
	 		}
	 		else if(!P.getPieces().get(P.getCases()[i][j]).getCol().equals(this.getCol())){
	 			list.add(P.getCases()[i][j]);
	 		} 
	 	}
	
	 	return list;
	 }
	 
	 
	 
	 
}
