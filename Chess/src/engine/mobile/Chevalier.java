package engine.mobile;
import java.util.ArrayList;

import engine.map.*;

public class Chevalier extends Piece {

	public Chevalier(CaseNormal pos,CouleurePiece col) {
		super(pos,col);
	} 
	@Override 
	public ArrayList<Case> getDeplacement(Plateau P){
		ArrayList<Case> list=new ArrayList<Case>();
		Case cas=this.getPosition();
		  
		int i=cas.getLigne()+1;     
		int j=cas.getColonne();
		if( i<10 ) { 
		if((P.getCases()[i][j].getCol().equals(Couleur.BLUE) && P.getPieces().get(P.getCases()[i+1][j])==null) || 
				((P.getCases()[i+1][j].getCol().equals(Couleur.BLUE) && P.getPieces().get(P.getCases()[i][j])==null))) {
			if(j<10 ) {
			if(P.getCases()[i+2][j+1].getCol().equals(Couleur.ROUG)) {
				if(P.getPieces().get(P.getCases()[i+2][j+2])==null ||
						!P.getPieces().get(P.getCases()[i+2][j+2]).getCol().equals(this.getCol())) {
				list.add(P.getCases()[i+2][j+2]);
			}
			
		}else {
			if(P.getPieces().get(P.getCases()[i+2][j+1])==null ||
					!P.getPieces().get(P.getCases()[i+2][j+1]).getCol().equals(this.getCol())) {
			list.add(P.getCases()[i+2][j+1]);  
			}
			}
			}
			if(j>0) {
			if(P.getCases()[i+2][j-1].getCol().equals(Couleur.ROUG)) {  
				if(P.getPieces().get(P.getCases()[i+2][j-2])==null ||
						!P.getPieces().get(P.getCases()[i+2][j-2]).getCol().equals(this.getCol()))
				list.add(P.getCases()[i+2][j-2]);
			}else {
					if(P.getPieces().get(P.getCases()[i+2][j-1])==null ||
							!P.getPieces().get(P.getCases()[i+2][j-1]).getCol().equals(this.getCol()))
					list.add(P.getCases()[i+2][j-1]);
			}
			}
			
		}else if(!P.getCases()[i][j].getCol().equals(Couleur.BLUE) && !P.getCases()[i+1][j].getCol().equals(Couleur.BLUE)
				&& P.getPieces().get(P.getCases()[i][j])==null) {
			
			if(j<10 ) {
			if(P.getCases()[i+1][j+1].getCol().equals(Couleur.ROUG)) {
				if(P.getPieces().get(P.getCases()[i+1][j+2])==null ||(P.getPieces().get(P.getCases()[i+1][j+2])!=null
						&& !P.getPieces().get(P.getCases()[i+1][j+2]).getCol().equals(this.getCol()))) {
				list.add(P.getCases()[i+1][j+2]);
			}
			
		}else {
			if(P.getPieces().get(P.getCases()[i+1][j+1])==null || (P.getPieces().get(P.getCases()[i+1][j+1])!=null 
					&& !P.getPieces().get(P.getCases()[i+1][j+1]).getCol().equals(this.getCol()))) {
			list.add(P.getCases()[i+1][j+1]);
			}
			}
			}
			if(j>0)	{
			if(P.getCases()[i+1][j-1].getCol().equals(Couleur.ROUG)) {
				if(P.getPieces().get(P.getCases()[i+1][j-2])==null || (P.getPieces().get(P.getCases()[i+1][j-2])!=null
						&& !P.getPieces().get(P.getCases()[i+1][j-2]).getCol().equals(this.getCol())))
				list.add(P.getCases()[i+1][j-2]);
			}else {
					if(P.getPieces().get(P.getCases()[i+1][j-1])==null || (P.getPieces().get(P.getCases()[i+1][j-1])!=null
							&& !P.getPieces().get(P.getCases()[i+1][j-1]).getCol().equals(this.getCol())))
					list.add(P.getCases()[i+1][j-1]);
			}
		}
		}
		}
		
		
		i=cas.getLigne()-1;
		j=cas.getColonne();
		
		
		
		if( i>0 ) { 
			if((P.getCases()[i][j].getCol().equals(Couleur.BLUE) && P.getPieces().get(P.getCases()[i-1][j])==null) ||
					(P.getCases()[i-1][j].getCol().equals(Couleur.BLUE) && P.getPieces().get(P.getCases()[i][j])==null)) {
				
				if(j<10 ) {
				if(P.getCases()[i-2][j+1].getCol().equals(Couleur.ROUG)) {
					if(P.getPieces().get(P.getCases()[i-2][j+2])==null ||
							(P.getPieces().get(P.getCases()[i-2][j+2])!=null &&
							!P.getPieces().get(P.getCases()[i-2][j+2]).getCol().equals(this.getCol()))) {
					list.add(P.getCases()[i-2][j+2]);
				}
				
			}else {
				if((P.getPieces().get(P.getCases()[i-2][j+1])==null) || (P.getPieces().get(P.getCases()[i-2][j+1])!=null 
						&& !P.getPieces().get(P.getCases()[i-2][j+1]).getCol().equals(this.getCol()))) {
				list.add(P.getCases()[i-2][j+1]);
				}
				}
				}
				if(j>0) {
				if(P.getCases()[i-2][j-1].getCol().equals(Couleur.ROUG)) {  
					if(P.getPieces().get(P.getCases()[i-2][j-2])==null || (P.getPieces().get(P.getCases()[i-2][j-2])!=null
							&& !P.getPieces().get(P.getCases()[i-2][j-2]).getCol().equals(this.getCol())))
					list.add(P.getCases()[i-2][j-2]);
				}else {
						if(P.getPieces().get(P.getCases()[i-2][j-1])==null || (P.getPieces().get(P.getCases()[i-2][j-1])!=null 
								&& !P.getPieces().get(P.getCases()[i-2][j-1]).getCol().equals(this.getCol())))
						list.add(P.getCases()[i-2][j-1]);
				}
				}
				
			}else if(!P.getCases()[i][j].getCol().equals(Couleur.BLUE) && !P.getCases()[i-1][j].getCol().equals(Couleur.BLUE) 
					&& P.getPieces().get(P.getCases()[i][j])==null) {
				
				if(j<10) {
				if(P.getCases()[i-1][j+1].getCol().equals(Couleur.ROUG)) {
					
					if(P.getPieces().get(P.getCases()[i-1][j+2])==null || (P.getPieces().get(P.getCases()[i-1][j+2])!=null
							&& !P.getPieces().get(P.getCases()[i-1][j+2]).getCol().equals(this.getCol()))) {
					list.add(P.getCases()[i-1][j+2]);
				}
				
			}else {
				if(P.getPieces().get(P.getCases()[i-1][j+1])==null || (P.getPieces().get(P.getCases()[i-1][j+1])!=null
						&& !P.getPieces().get(P.getCases()[i-1][j+1]).getCol().equals(this.getCol()))) {
				list.add(P.getCases()[i-1][j+1]);
				}
				}
				}
				if(j>0) {
				if(P.getCases()[i-1][j-1].getCol().equals(Couleur.ROUG)) {
					if(P.getPieces().get(P.getCases()[i-1][j-2])==null || (P.getPieces().get(P.getCases()[i-1][j-2])!=null
							&& !P.getPieces().get(P.getCases()[i-1][j-2]).getCol().equals(this.getCol())))
					list.add(P.getCases()[i-1][j-2]);
				}else {
						if(P.getPieces().get(P.getCases()[i-1][j-1])==null || (P.getPieces().get(P.getCases()[i-1][j-1])!=null
								&& !P.getPieces().get(P.getCases()[i-1][j-1]).getCol().equals(this.getCol())))
						list.add(P.getCases()[i-1][j-1]);
				}
				}
			}
			}
		
		
		
		i=cas.getLigne();
		j=cas.getColonne()+1;
		
		
		if(j<10) {
			if((P.getCases()[i][j].getCol().equals(Couleur.ROUG) && P.getPieces().get(P.getCases()[i][j+1])==null) || 
					(P.getCases()[i][j+1].getCol().equals(Couleur.ROUG) && P.getPieces().get(P.getCases()[i][j])==null)) {
				if(i<10) {
				if(P.getCases()[i+1][j+2].getCol().equals(Couleur.BLUE)) {
					if(P.getPieces().get(P.getCases()[i+2][j+2])==null || (P.getPieces().get(P.getCases()[i+2][j+2])!=null
							&& !P.getPieces().get(P.getCases()[i+2][j+2]).getCol().equals(this.getCol()))) {
					list.add(P.getCases()[i+2][j+2]);
				}
				}else {
				if(P.getPieces().get(P.getCases()[i+1][j+2])==null || (P.getPieces().get(P.getCases()[i+1][j+2])!=null
						&& !P.getPieces().get(P.getCases()[i+1][j+2]).getCol().equals(this.getCol()))) {
				list.add(P.getCases()[i+1][j+2]);
				}
				}
				}
				if(i>0) {
				if(P.getCases()[i-1][j+2].getCol().equals(Couleur.BLUE)) {
					if(P.getPieces().get(P.getCases()[i-2][j+2])==null || (P.getPieces().get(P.getCases()[i-2][j+2])!=null
							&& !P.getPieces().get(P.getCases()[i-2][j+2]).getCol().equals(this.getCol())))
					list.add(P.getCases()[i-2][j+2]);
				}else {
						if(P.getPieces().get(P.getCases()[i-1][j+2])==null || (P.getPieces().get(P.getCases()[i-1][j+2])!=null
								&& !P.getPieces().get(P.getCases()[i-1][j+2]).getCol().equals(this.getCol())))
						list.add(P.getCases()[i-1][j+2]);
				}
				}
				
				
			}else if(!P.getCases()[i][j].getCol().equals(Couleur.ROUG) && !P.getCases()[i][j+1].getCol().equals(Couleur.ROUG) 
					&& P.getPieces().get(P.getCases()[i][j])==null) { 
				
				if(i<10 ) {
				if(P.getCases()[i+1][j+1].getCol().equals(Couleur.BLUE)) {
					if(P.getPieces().get(P.getCases()[i+2][j+1])==null || (P.getPieces().get(P.getCases()[i+2][j+1])!=null
							&& !P.getPieces().get(P.getCases()[i+2][j+1]).getCol().equals(this.getCol()))) {
					list.add(P.getCases()[i+2][j+1]);
				}
				
			}else {
				if(P.getPieces().get(P.getCases()[i+1][j+1])==null || (P.getPieces().get(P.getCases()[i+1][j+1])!=null
						&& !P.getPieces().get(P.getCases()[i+1][j+1]).getCol().equals(this.getCol()))) {
				list.add(P.getCases()[i+1][j+1]);
				}
				}
				}
				if(i>0) {
				if(P.getCases()[i-1][j+1].getCol().equals(Couleur.BLUE)) {
					if(P.getPieces().get(P.getCases()[i-2][j+1])==null || (P.getPieces().get(P.getCases()[i-2][j+1])!=null 
							&& !P.getPieces().get(P.getCases()[i-2][j+1]).getCol().equals(this.getCol())))
					list.add(P.getCases()[i-2][j+1]);
				}else {
						if(P.getPieces().get(P.getCases()[i-1][j+1])==null || (P.getPieces().get(P.getCases()[i-1][j+1])!=null
								&& !P.getPieces().get(P.getCases()[i-1][j+1]).getCol().equals(this.getCol())))
						list.add(P.getCases()[i-1][j+1]);
				}
				}
			}
			}
		
		
		
		i=cas.getLigne();
		j=cas.getColonne()-1;
		
		
		if( j>0) {
			if((P.getCases()[i][j].getCol().equals(Couleur.ROUG) && P.getPieces().get(P.getCases()[i][j-1])==null) || 
					(P.getCases()[i][j-1].getCol().equals(Couleur.ROUG) && P.getPieces().get(P.getCases()[i][j])==null)) {
				if(i<10) {
				if(P.getCases()[i+1][j-2].getCol().equals(Couleur.BLUE)) {
					if(P.getPieces().get(P.getCases()[i+2][j-2])==null || (P.getPieces().get(P.getCases()[i+2][j-2])!=null
							&& !P.getPieces().get(P.getCases()[i+2][j-2]).getCol().equals(this.getCol()))) {
					list.add(P.getCases()[i+2][j-2]);
				}
			}else {
				if(P.getPieces().get(P.getCases()[i+1][j-2])==null || (P.getPieces().get(P.getCases()[i+1][j-2])!=null
						&& !P.getPieces().get(P.getCases()[i+1][j-2]).getCol().equals(this.getCol()))) {
				list.add(P.getCases()[i+1][j-2]);
				}
				}
				}
				if(i>0) {
				if(P.getCases()[i-1][j-2].getCol().equals(Couleur.BLUE)) {
					if(P.getPieces().get(P.getCases()[i-2][j-2])==null || (P.getPieces().get(P.getCases()[i-2][j-2])!=null
							&& !P.getPieces().get(P.getCases()[i-2][j-2]).getCol().equals(this.getCol())))
					list.add(P.getCases()[i-2][j-2]);
				}else {
						if(P.getPieces().get(P.getCases()[i-1][j-2])==null || (P.getPieces().get(P.getCases()[i-1][j-2])!=null
								&& !P.getPieces().get(P.getCases()[i-1][j-2]).getCol().equals(this.getCol())))
						list.add(P.getCases()[i-1][j-2]);
				}
				}
				
			}else if(!P.getCases()[i][j].getCol().equals(Couleur.ROUG) && !P.getCases()[i][j-1].getCol().equals(Couleur.ROUG) 
					&& P.getPieces().get(P.getCases()[i][j])==null) {
				
				if(i<10) {
				if(P.getCases()[i+1][j-1].getCol().equals(Couleur.BLUE)) {
					if(P.getPieces().get(P.getCases()[i+2][j-1])==null || (P.getPieces().get(P.getCases()[i+2][j-1])!=null
							&& !P.getPieces().get(P.getCases()[i+2][j-1]).getCol().equals(this.getCol()))) {
					list.add(P.getCases()[i+2][j-1]);
				}
				
			}else { 
				if(P.getPieces().get(P.getCases()[i+1][j-1])==null || (P.getPieces().get(P.getCases()[i+1][j-1])!=null
						&& !P.getPieces().get(P.getCases()[i+1][j-1]).getCol().equals(this.getCol()))) {
				list.add(P.getCases()[i+1][j-1]);
				}
				}
				}
				if(i>0) {
				if(P.getCases()[i-1][j-1].getCol().equals(Couleur.BLUE)) {
					if(P.getPieces().get(P.getCases()[i-2][j-1])==null || (P.getPieces().get(P.getCases()[i-2][j-1])!=null
							&& !P.getPieces().get(P.getCases()[i-2][j-1]).getCol().equals(this.getCol())))
					list.add(P.getCases()[i-2][j-1]);
				}else { 
						if(P.getPieces().get(P.getCases()[i-1][j-1])==null || (P.getPieces().get(P.getCases()[i-1][j-1])!=null
								&& !P.getPieces().get(P.getCases()[i-1][j-1]).getCol().equals(this.getCol())))
						list.add(P.getCases()[i-1][j-1]);
				}
				}
			}
			}
		return list;
}
}
