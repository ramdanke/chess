package gui;
import javax.swing.*;

import config.GameConfiguration;
import engine.map.*;
import engine.mobile.*;

import java.awt.*;
import java.util.ArrayList;
public class MainGui {
	 JPanel panel;
	    Plateau P;
	    JFrame fenetre;

	    JButton[][] boutons = new JButton[11][11];

	    Piece pieceSelectionnee = null;
	    Case caseSelectionnee = null;
	public static void main(String[] args) { 
		new MainGui(); 
	}
	public MainGui() { 
		fenetre=new JFrame("chess");
		fenetre.setSize(GameConfiguration.LONGEURE_FENETRE,GameConfiguration.LARGEURE_FENTRE);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel =new JPanel();
		panel.setLayout(new GridLayout(11,11));
		P=new Plateau();
		
		for(int i=0;i<=10;i++) { 
			for(int j=0;j<=10;j++) {
			JButton b=new JButton(); 
			boutons[i][j] = b;
			b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			b.setBackground(TraductionDeCouleure( P.getCases()[i][j].getCol()));
			if(P.getPieces().get(P.getCases()[i][j])!=null) {
				if(P.getPieces().get(P.getCases()[i][j]) instanceof Char){
					b.setText("char");
					b.setForeground(Color.blue);
				}else if(P.getPieces().get(P.getCases()[i][j]) instanceof Soldat){
					b.setText("Soldat");
					b.setForeground(Color.blue);
				}else if(P.getPieces().get(P.getCases()[i][j]) instanceof Elephant){ 
					b.setText("Elephant");
					b.setForeground(Color.blue);
				}else if(P.getPieces().get(P.getCases()[i][j]) instanceof General){
					b.setText("General");
					b.setForeground(Color.blue);
				}else if(P.getPieces().get(P.getCases()[i][j]) instanceof Conseillere){
					b.setText("Conseillere");
					b.setForeground(Color.blue);
				}else if(P.getPieces().get(P.getCases()[i][j]) instanceof Chevalier){
					b.setText("chevalier");
					b.setForeground(Color.blue);
				}else if(P.getPieces().get(P.getCases()[i][j]) instanceof Canon){
					b.setText("canon");
					b.setForeground(Color.blue);
				}
			}
			int li = i;
            int co = j;

            b.addActionListener(e -> gererClic(li,co));
			
			panel.add(b); 
			}
		}
		fenetre.add(panel); 
		fenetre.setVisible(true); 
	}
public java.awt.Color TraductionDeCouleure(engine.map.Couleur col){
		if(col.equals(Couleur.JAUNE)){
			return java.awt.Color.yellow;
		}else
			if(col.equals(Couleur.BLANC)){
			return (java.awt.Color.white);
			}else
			if(col.equals(Couleur.NOIR)){
			return (java.awt.Color.black);
				}else if(col.equals(Couleur.BLUE)){
					return (java.awt.Color.BLUE);  
				}
					else{
				   return (java.awt.Color.red);
							}
	}
public void gererClic(int i, int j){
	rafraichir();
Case c = P.getCases()[i][j];
 Piece p = P.getPieces().get(c);
        if(pieceSelectionnee==null){ 
            if(p != null){
                pieceSelectionnee =p;
                caseSelectionnee =c;

                ArrayList<Case> coups= p.getDeplacement(P);
                if(coups != null){
                    for(Case dep : coups){
                        boutons[dep.getLigne()][dep.getColonne()].setBackground(Color.GREEN);
                    }
                }
            }
        }
        else{
 
            ArrayList<Case> coups =pieceSelectionnee.getDeplacement(P);

            if(coups != null && coups.contains(c)){
                P.getPieces().remove(caseSelectionnee);
                pieceSelectionnee.setPosition((CaseNormal)c);
                P.getPieces().put(c,pieceSelectionnee);
            }
            pieceSelectionnee = null;
            caseSelectionnee = null;
            rafraichir();
        }
    }
    public void rafraichir(){

        for(int i=0;i<=10;i++){
        for(int j=0;j<=10;j++){
        	JButton b = boutons[i][j];
                b.setBackground( 
                TraductionDeCouleure(P.getCases()[i][j].getCol() ));
                b.setText(""); 

                if(P.getPieces().get(P.getCases()[i][j])!=null) {
    				if(P.getPieces().get(P.getCases()[i][j]) instanceof Char){
    					b.setText("char");
    					b.setForeground(Color.blue);
    				}else if(P.getPieces().get(P.getCases()[i][j]) instanceof Soldat){
    					b.setText("Soldat");
    					b.setForeground(Color.blue);
    				}else if(P.getPieces().get(P.getCases()[i][j]) instanceof Elephant){
    					b.setText("Elephant");
    					b.setForeground(Color.blue);
    				}else if(P.getPieces().get(P.getCases()[i][j]) instanceof General){
    					b.setText("General");
    					b.setForeground(Color.blue);
    				}else if(P.getPieces().get(P.getCases()[i][j]) instanceof Conseillere){
    					b.setText("Conseillere");
    					b.setForeground(Color.blue);
    				}else if(P.getPieces().get(P.getCases()[i][j]) instanceof Chevalier){
    					b.setText("chevalier");
    					b.setForeground(Color.blue);
    				}else if(P.getPieces().get(P.getCases()[i][j]) instanceof Canon){
    					b.setText("canon");
    					b.setForeground(Color.blue);
    				}
    			}
            } 
        }
    }
	}



