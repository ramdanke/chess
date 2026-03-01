package engine.map;

public abstract class Case {
private int ligne;
private int colonne;
private Couleur col;

public Case(int ligne,int colonne,Couleur col) {
	this.ligne=ligne;
	this.colonne=colonne;
	this.col=col;
}

public Couleur getCol() {
	return col;
}

public void setCol(Couleur col) {
	this.col = col;
}

public int getLigne() {
	return ligne;
}

public int getColonne() {
	return colonne; 
}

public void setLigne(int ligne) {
	this.ligne=ligne;
}

public void setColonne(int colonne) {
	this.colonne=colonne;
}

public String toString() {
	return ("case=["+ligne+" , "+colonne+"]");
} 

}
