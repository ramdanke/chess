package engine.mobile;
import java.util.ArrayList;

import engine.map.*;

public class Soldat extends Piece {
 public Soldat(CaseNormal pos,CouleurePiece col) {
	 super(pos,col);
 }
 
@Override
 public ArrayList<Case> getDeplacement(Plateau P){
 	ArrayList<Case> list=new ArrayList<Case>(); 
 	CaseNormal cas=this.getPosition();
 	int i=cas.getLigne()+1;
 	int j=cas.getColonne();
 	if(!P.ToutEnHaut(cas) ) {
 		if(!P.getCases()[i][j].getCol().equals(Couleur.BLUE)) {
 		if(P.getPieces().get(P.getCases()[i][j])==null ) {
 			list.add(P.getCases()[i][j]);
 		}
 		else if(!P.getPieces().get(P.getCases()[i][j]).getCol().equals(this.getCol())){
 			list.add(P.getCases()[i][j]);
 		}
 		}else {
 			if(P.getPieces().get(P.getCases()[i+1][j])==null ) {
 	 			list.add(P.getCases()[i+1][j]);
 	 		}
 	 		else if(!P.getPieces().get(P.getCases()[i+1][j]).getCol().equals(this.getCol())){
 	 			list.add(P.getCases()[i+1][j]);
 	 		}	
 		}
 	}
 	return list;
 }

 
}
