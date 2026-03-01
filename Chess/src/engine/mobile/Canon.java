package engine.mobile;
import java.util.ArrayList;
import engine.map.*;

public class Canon extends Piece {
	
public Canon (CaseNormal pos ,CouleurePiece col) {
	super(pos,col);
}
@Override
public ArrayList<Case> getDeplacement(Plateau P){
	ArrayList<Case> list=new ArrayList<Case>();  
	CaseNormal cas =this.getPosition();
	int i=cas.getLigne()+1;  
	int j=cas.getColonne();
	boolean B=true;
	while(B && i<=10 ) {  
		Piece p = P.getPieces().get(P.getCases()[i][j]); 
		if((P.getCases()[i][j].getCol().equals(Couleur.BLANC) ||P.getCases()[i][j].getCol().equals(Couleur.NOIR) || 
				P.getCases()[i][j].getCol().equals(Couleur.JAUNE)) && p==null ) {
			list.add(P.getCases()[i][j]);
			i++;
		}else if(P.getCases()[i][j].getCol()==Couleur.BLUE) {
			i++; 
			}
		else if (p!=null ){
			for(int k=i+1;k<=10;k++) {
				if(P.getPieces().get(P.getCases()[k][j])!=null && 
						!P.getPieces().get(P.getCases()[k][j]).getCol().equals(this.getCol())){
					list.add(P.getCases()[k][j]);
					break;
				}
			}
			B=false;
		} 
		else {
			B=false;
		} 
	} 
	 i=cas.getLigne()-1;
	 j=cas.getColonne();
	 B=true;
	 while(B && i>=0) {
		 Piece p = P.getPieces().get(P.getCases()[i][j]);
			if((P.getCases()[i][j].getCol().equals(Couleur.BLANC) ||P.getCases()[i][j].getCol().equals(Couleur.NOIR) || 
					P.getCases()[i][j].getCol().equals(Couleur.JAUNE)) && p==null ) {
				list.add(P.getCases()[i][j]);
				i--;
			}
			else if(P.getCases()[i][j].getCol()==Couleur.BLUE) {
				i--;
			}
			else if (p!=null ){
				for(int k=i-1;k>=0;k--) {
					if(P.getPieces().get(P.getCases()[k][j])!=null && 
							!P.getPieces().get(P.getCases()[k][j]).getCol().equals(this.getCol())){
						list.add(P.getCases()[k][j]);
						break;
					}
				}
				B=false;
				
				}else   {
				B=false;
			}
		} 
	 i=cas.getLigne();
	 j=cas.getColonne()+1;
	 B=true;
	 while(B && j<=10) {
		 Piece p = P.getPieces().get(P.getCases()[i][j]);
			if((P.getCases()[i][j].getCol().equals(Couleur.BLANC) ||P.getCases()[i][j].getCol().equals(Couleur.NOIR) || 
					P.getCases()[i][j].getCol().equals(Couleur.JAUNE)) && p==null ) {
				list.add(P.getCases()[i][j]);
				j++;
			}else if(P.getCases()[i][j].getCol()==Couleur.ROUG) {
				j++;
			}else if (p!=null){
				for(int k=j+1;k<=10;k++) {
					if(P.getPieces().get(P.getCases()[i][k])!=null && 
							!P.getPieces().get(P.getCases()[i][k]).getCol().equals(this.getCol())){
						list.add(P.getCases()[i][k]);
						break;
					}
				}
				B=false; 
			}else { 
				B=false; 
			}  
		} 
	 i=cas.getLigne(); 
	 j=cas.getColonne()-1;   
	 B=true;
	 while(B && j>=0) { 
		 Piece p = P.getPieces().get(P.getCases()[i][j]);
			if((P.getCases()[i][j].getCol().equals(Couleur.BLANC) ||P.getCases()[i][j].getCol().equals(Couleur.NOIR) || 
					P.getCases()[i][j].getCol().equals(Couleur.JAUNE)) && p==null ) {
				list.add(P.getCases()[i][j]);
				j--;
			}else if(P.getCases()[i][j].getCol()==Couleur.ROUG) {
				j--;
			} else if (p!=null){ 
				for(int k=j-1;k>=0;k--) {
					if(P.getPieces().get(P.getCases()[i][k])!=null && 
							!P.getPieces().get(P.getCases()[i][k]).getCol().equals(this.getCol())){
						list.add(P.getCases()[i][k]);
						break;
					}
				}
				B=false;
			}else {
				B=false; 
			}
			}
	return list;
	
}

} 
