package engine.mobile;
import java.util.ArrayList;

import engine.map.*;
public class Conseillere extends Piece {

	public Conseillere(CaseNormal pos,CouleurePiece col) {
		super(pos,col);
	}
	 @Override
	 public ArrayList<Case> getDeplacement(Plateau P){
	 	ArrayList<Case> list=new ArrayList<Case>(); 
	 	CaseNormal cas=this.getPosition();
	 	
	 	int i=cas.getLigne()+1;
	 	int j=cas.getColonne()+1;
	 	if(P.getCases()[i][j].getCol().equals(Couleur.JAUNE) ) {
	 		if(P.getPieces().get(P.getCases()[i][j])==null ) {
	 			list.add(P.getCases()[i][j]);
	 		}
	 		else if(!P.getPieces().get(P.getCases()[i][j]).getCol().equals(this.getCol())){
	 			list.add(P.getCases()[i][j]);
	 		} 
	 	}
	 	i=cas.getLigne()+1;
	 	j=cas.getColonne()-1;
	 	if(P.getCases()[i][j].getCol().equals(Couleur.JAUNE) ) {
	 		if(P.getPieces().get(P.getCases()[i][j])==null ) {
	 			list.add(P.getCases()[i][j]);
	 		}
	 		else if(!P.getPieces().get(P.getCases()[i][j]).getCol().equals(this.getCol())){
	 			list.add(P.getCases()[i][j]);
	 		} 
	 	}
	 	i=cas.getLigne()-1;
	 	j=cas.getColonne()+1;
	 	if(P.getCases()[i][j].getCol().equals(Couleur.JAUNE) ) {
	 		if(P.getPieces().get(P.getCases()[i][j])==null ) {
	 			list.add(P.getCases()[i][j]);
	 		}
	 		else if(!P.getPieces().get(P.getCases()[i][j]).getCol().equals(this.getCol())){
	 			list.add(P.getCases()[i][j]);
	 		} 
	 	}
	 	i=cas.getLigne()-1;
	 	j=cas.getColonne()-1;
	 	if(P.getCases()[i][j].getCol().equals(Couleur.JAUNE) ) {
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
