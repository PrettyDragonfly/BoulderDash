package cor;

import application.Cellule;
import ent.Entite;
import ent.Rocher;
import ent.Vide;

public class RocherGlisse extends Expert {

	public boolean peutSeDeplacer(Entite hote, Cellule contact) {
		if (hote instanceof Rocher && (hote.getSurCellule().getSud() != null)
				&& (hote.getSurCellule().getSud().getEntiteSurPlace() instanceof Rocher)
				&& ((hote.getSurCellule().getEst() != null
						&& hote.getSurCellule().getEst().getEntiteSurPlace() instanceof Vide
						&& hote.getSurCellule().getEst().getSud() != null
						&& hote.getSurCellule().getEst().getSud().getEntiteSurPlace() instanceof Vide)
						|| (hote.getSurCellule().getOuest() != null
								&& hote.getSurCellule().getOuest().getEntiteSurPlace() instanceof Vide
								&& hote.getSurCellule().getOuest().getSud() != null
								&& hote.getSurCellule().getOuest().getSud().getEntiteSurPlace() instanceof Vide))) {
			return true;
		} else
			return false;
	}

	public void deplacement(Entite hote, Cellule contact) {
		if ((hote.getSurCellule().getEst() != null && hote.getSurCellule().getEst().getEntiteSurPlace() instanceof Vide
				&& hote.getSurCellule().getEst().getSud() != null
				&& hote.getSurCellule().getEst().getSud().getEntiteSurPlace() instanceof Vide)
				&& (hote.getSurCellule().getOuest() != null
						&& hote.getSurCellule().getOuest().getEntiteSurPlace() instanceof Vide
						&& hote.getSurCellule().getOuest().getSud() != null
						&& hote.getSurCellule().getOuest().getSud().getEntiteSurPlace() instanceof Vide)) {
			if (Math.random() > 0.5)
				hote.goEst();
			else
				hote.goOuest();
			hote.goSud();
		} else if (hote.getSurCellule().getOuest() != null
				&& hote.getSurCellule().getOuest().getEntiteSurPlace() instanceof Vide
				&& hote.getSurCellule().getOuest().getSud() != null
				&& hote.getSurCellule().getOuest().getSud().getEntiteSurPlace() instanceof Vide) {
			hote.goOuest();
			hote.goSud();
		} else {
			hote.goEst();
			hote.goSud();
		}
	}

	public RocherGlisse() {
		super();
	}

	public RocherGlisse(Expert s) {
		super(s);
	}

}
