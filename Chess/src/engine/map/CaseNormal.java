package engine.map;

public class CaseNormal extends Case {
	boolean jouable=true;
	private TypeCaseNormale type;
public CaseNormal(int ligne,int colonne,Couleur col,TypeCaseNormale type) {
	super(ligne,colonne,col);
	this.type=type;
}
public boolean isJouable() {
	return jouable;
}
public void setJouable(boolean jouable) {
	this.jouable = jouable;
}
public TypeCaseNormale getType() {
	return type;
}
public void setType(TypeCaseNormale type) {
	this.type = type;
}

}
