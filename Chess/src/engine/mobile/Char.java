package engine.mobile;
import java.util.ArrayList;

import engine.map.*;

public class Char extends Piece {

	public Char(CaseNormal pos,CouleurePiece col){
		super(pos,col);
	}
	@Override
	public ArrayList<Case> getDeplacement(Plateau P){
		return null;
		
	}
}
