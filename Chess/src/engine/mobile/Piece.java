package engine.mobile;
import java.util.ArrayList;

import engine.map.*;
public abstract class Piece {
	
private CaseNormal position ;
private CouleurePiece col;

public Piece(CaseNormal pos,CouleurePiece col) {
	position =pos;
	this.col=col;
}
public ArrayList<Case> getDeplacement(Plateau P){
	return null;
}

public CouleurePiece getCol() {
	return col;
}

public void setCol(CouleurePiece col) {
	this.col = col;
}

public CaseNormal getPosition() {
	return position;
}

public void setPosition(CaseNormal position) {
	this.position = position;
}

}
