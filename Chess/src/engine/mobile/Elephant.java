package engine.mobile;
import java.util.ArrayList;

import engine.map.*;
public class Elephant extends Piece {
	
public Elephant(CaseNormal pos,CouleurePiece col) {
	super(pos,col);
}
@Override
public ArrayList<Case> getDeplacement(Plateau P){
	ArrayList<Case> list=new ArrayList<Case>(); 
	CaseNormal cas=this.getPosition();
	int i=cas.getLigne()+1;
	int j=cas.getColonne()+1;
	if(i<=10 && j<=10) {
	if(P.getCases()[i][j].getCol().equals(Couleur.ROUG) || P.getCases()[i+1][j+1].getCol().equals(Couleur.ROUG)) {
		if(P.getPieces().get(P.getCases()[i+1][j+2])==null ) {
			list.add(P.getCases()[i+1][j+2]);
		}
		else if(!P.getPieces().get(P.getCases()[i+1][j+2]).getCol().equals(this.getCol())){
			list.add(P.getCases()[i+1][j+2]);
		} 	
	}else if(!P.ToutEnHaut(cas) && !P.getCases()[i][j].getCol().equals(Couleur.ROUG)     ) {
		if(P.getPieces().get(P.getCases()[i+1][j+1])==null ) {
			list.add(P.getCases()[i+1][j+1]);
		}
		else if(!P.getPieces().get(P.getCases()[i+1][j+1]).getCol().equals(this.getCol())){
			list.add(P.getCases()[i+1][j+1]);
		} 
	}
	}
	i=cas.getLigne()+1;
	j=cas.getColonne()-1;
	
	
	if(i<=10 && j>=0) {
	if(P.getCases()[i][j].getCol().equals(Couleur.ROUG) || P.getCases()[i+1][j-1].getCol().equals(Couleur.ROUG)) {
		if(P.getPieces().get(P.getCases()[i+1][j-2])==null ) {
			list.add(P.getCases()[i+1][j-2]);
		}
		else if(!P.getPieces().get(P.getCases()[i+2][j-2]).getCol().equals(this.getCol())){
			list.add(P.getCases()[i+1][j-2]);
		} 
	}else if(!P.ToutEnHaut(cas) && !P.getCases()[i][j].getCol().equals(Couleur.ROUG) ) {
		if(P.getPieces().get(P.getCases()[i+1][j-1])==null ) {
			list.add(P.getCases()[i+1][j-1]);
		}
		else if(!P.getPieces().get(P.getCases()[i+1][j-1]).getCol().equals(this.getCol())){
			list.add(P.getCases()[i+1][j-1]);
		} 
	}
	}
	i=cas.getLigne()-1;
	j=cas.getColonne()+1;
	if(i>=0 && j<=10) {
	if(P.getCases()[i][j].getCol().equals(Couleur.ROUG) ||P.getCases()[i-1][j+1].getCol().equals(Couleur.ROUG) ) {
		if(P.getPieces().get(P.getCases()[i-1][j+2])==null ) {
			list.add(P.getCases()[i-1][j+2]);
		}
		else if(!P.getPieces().get(P.getCases()[i-1][j+2]).getCol().equals(this.getCol())){
			list.add(P.getCases()[i-1][j+2]);
		} 
	}else if(!P.ToutEnHaut(cas) && !P.getCases()[i][j].getCol().equals(Couleur.ROUG) ) {
		if(P.getPieces().get(P.getCases()[i-1][j+1])==null ) {
			list.add(P.getCases()[i-1][j+1]);
		}
		else if(!P.getPieces().get(P.getCases()[i-1][j+1]).getCol().equals(this.getCol())){
			list.add(P.getCases()[i-1][j+1]);
		} 
	}
	}
	i=cas.getLigne()-1;
	j=cas.getColonne()-1;
	if(i>=0 && j>=0) {
	if(P.getCases()[i][j].getCol().equals(Couleur.ROUG) ||P.getCases()[i-1][j-1].getCol().equals(Couleur.ROUG) ) {
		if(P.getPieces().get(P.getCases()[i-1][j-2])==null ) {
			list.add(P.getCases()[i-1][j-2]);
		}
		else if(!P.getPieces().get(P.getCases()[i-1][j-2]).getCol().equals(this.getCol())){
			list.add(P.getCases()[i-1][j-2]);
		} 
	}else if(!P.ToutEnHaut(cas) && !P.getCases()[i][j].getCol().equals(Couleur.ROUG) ) {
		if(P.getPieces().get(P.getCases()[i-1][j-1])==null ) {
			list.add(P.getCases()[i-1][j-1]);
		}
		else if(!P.getPieces().get(P.getCases()[i-1][j-1]).getCol().equals(this.getCol())){
			list.add(P.getCases()[i-1][j-1]);
		} 
	}
	}
	return list;
}
}
