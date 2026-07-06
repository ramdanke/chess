package engine.mobile;

import java.util.ArrayList;

import engine.map.*;

public class Soldat extends Piece {
	public Soldat(CaseNormal pos, CouleurePiece col) {
		super(pos, col);
	}

	@Override
	public ArrayList<Case> getDeplacement(Plateau P) {
		ArrayList<Case> list = new ArrayList<Case>();
		CaseNormal cas = this.getPosition();
		if (this.getCol() == CouleurePiece.ROUGE) {
			int i = cas.getLigne() + 1;
			int j = cas.getColonne();
			if (!P.ToutEnHaut(cas)) {
				if (!P.getCases()[i][j].getCol().equals(Couleur.BLUE)) {
					if (P.getPieces().get(P.getCases()[i][j]) == null) {
						list.add(P.getCases()[i][j]);
					} else if (!P.getPieces().get(P.getCases()[i][j]).getCol().equals(this.getCol())) {
						list.add(P.getCases()[i][j]);
					}
				} else {
					if (P.getPieces().get(P.getCases()[i + 1][j]) == null) {
						list.add(P.getCases()[i + 1][j]);
					} else if (!P.getPieces().get(P.getCases()[i + 1][j]).getCol().equals(this.getCol())) {
						list.add(P.getCases()[i + 1][j]);
					}
				}

			}
			if (cas.getLigne() >= 6 && !P.ToutAdroit(cas) && !P.ToutAgauche(cas)) {
				// gere les colonne rouge
				i = cas.getLigne();

				if (P.getCases()[i][j + 1].getCol().equals(Couleur.ROUG)) {
					if (P.getPieces().get(P.getCases()[i][j + 2]) == null
							|| !P.getPieces().get(P.getCases()[i][j + 2]).getCol().equals(this.getCol())) {
						list.add(P.getCases()[i][j + 2]);
					}
				} else {
					if (j < 10) {
						if (P.getPieces().get(P.getCases()[i][j + 1]) == null
								|| !P.getPieces().get(P.getCases()[i][j + 1]).getCol().equals(this.getCol())) {
							list.add(P.getCases()[i][j + 1]);
						}
					}
				}
				if (P.getCases()[i][j - 1].getCol().equals(Couleur.ROUG)) {
					if (P.getPieces().get(P.getCases()[i][j - 2]) == null
							|| !P.getPieces().get(P.getCases()[i][j - 2]).getCol().equals(this.getCol())) {
						list.add(P.getCases()[i][j - 2]);
					}
				} else {
					if (j > 0) {
						if (P.getPieces().get(P.getCases()[i][j - 1]) == null
								|| !P.getPieces().get(P.getCases()[i][j - 1]).getCol().equals(this.getCol())) {
							list.add(P.getCases()[i][j - 1]);
						}
					}
				}
			}
			if (cas.getLigne() >= 6 && P.ToutAdroit(cas)) {
				i = cas.getLigne();
				if (P.getPieces().get(P.getCases()[i][j - 1]) == null
						|| !P.getPieces().get(P.getCases()[i][j - 1]).getCol().equals(this.getCol())) {
					list.add(P.getCases()[i][j - 1]);
				}
			} else if (cas.getLigne() >= 6 && P.ToutAgauche(cas)) {
				i = cas.getLigne();
				if (P.getPieces().get(P.getCases()[i][j + 1]) == null
						|| !P.getPieces().get(P.getCases()[i][j + 1]).getCol().equals(this.getCol())) {
					list.add(P.getCases()[i][j + 1]);
				}
			}

		} else {

			int i = cas.getLigne() - 1;
			int j = cas.getColonne();
			if (!P.ToutEnBas(cas)) {
				if (!P.getCases()[i][j].getCol().equals(Couleur.BLUE)) {
					if (P.getPieces().get(P.getCases()[i][j]) == null) {
						list.add(P.getCases()[i][j]);
					} else if (!P.getPieces().get(P.getCases()[i][j]).getCol().equals(this.getCol())) {
						list.add(P.getCases()[i][j]);
					}
				} else {
					if (P.getPieces().get(P.getCases()[i - 1][j]) == null) {
						list.add(P.getCases()[i - 1][j]);
					} else if (!P.getPieces().get(P.getCases()[i - 1][j]).getCol().equals(this.getCol())) {
						list.add(P.getCases()[i - 1][j]);
					}
				}

			}
			if (cas.getLigne() <= 4 && !P.ToutAdroit(cas) && !P.ToutAgauche(cas)) {
				// gere les colonne rouge
				i = cas.getLigne();
				if (P.getCases()[i][j + 1].getCol().equals(Couleur.ROUG)) {
					if (P.getPieces().get(P.getCases()[i][j + 2]) == null
							|| !P.getPieces().get(P.getCases()[i][j + 2]).getCol().equals(this.getCol())) {
						list.add(P.getCases()[i][j + 2]);
					}

					if (P.getPieces().get(P.getCases()[i][j - 1]) == null
							|| !P.getPieces().get(P.getCases()[i][j - 1]).getCol().equals(this.getCol())) {
						list.add(P.getCases()[i][j - 1]);
					}
				} else if (P.getCases()[i][j - 1].getCol().equals(Couleur.ROUG)) {
					if (P.getPieces().get(P.getCases()[i][j - 2]) == null
							|| !P.getPieces().get(P.getCases()[i][j - 2]).getCol().equals(this.getCol())) {
						list.add(P.getCases()[i][j - 2]);
					}

					if (P.getPieces().get(P.getCases()[i][j + 1]) == null
							|| !P.getPieces().get(P.getCases()[i][j + 1]).getCol().equals(this.getCol())) {
						list.add(P.getCases()[i][j + 1]);
					}
				}

				else {
					if (j < 10) {
						if (P.getPieces().get(P.getCases()[i][j + 1]) == null
								|| !P.getPieces().get(P.getCases()[i][j + 1]).getCol().equals(this.getCol())) {
							list.add(P.getCases()[i][j + 1]);
						}

					}
					if (j > 0) {
						if (P.getPieces().get(P.getCases()[i][j - 1]) == null) {
							list.add(P.getCases()[i][j - 1]);
						} else {
							if (!P.getPieces().get(P.getCases()[i][j - 1]).getCol().equals(this.getCol())) {
								list.add(P.getCases()[i][j - 1]);
							}
						}
					}
				}
			}
			if (cas.getLigne() <= 4 && P.ToutAdroit(cas)) {
				i = cas.getLigne();
				if (P.getPieces().get(P.getCases()[i][j - 1]) == null
						|| !P.getPieces().get(P.getCases()[i][j - 1]).getCol().equals(this.getCol())) {
					list.add(P.getCases()[i][j - 1]);
				}
			} else if (cas.getLigne() <= 4 && P.ToutAgauche(cas)) {
				i = cas.getLigne();
				if (P.getPieces().get(P.getCases()[i][j + 1]) == null
						|| !P.getPieces().get(P.getCases()[i][j + 1]).getCol().equals(this.getCol())) {
					list.add(P.getCases()[i][j + 1]);
				}
			}
		}
		return list;
	}
}
