package engine.map;

public class CaseMort extends Case {
	private boolean jouable=false;
	
public CaseMort(int ligne,int colonne,Couleur col) {
	super(ligne,colonne,col);
}

public boolean isJouable() {
	return jouable;
} 
public void setJouable(boolean jouable) {
	this.jouable = jouable;
}

}
