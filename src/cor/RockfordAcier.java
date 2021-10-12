package cor;

import application.Cellule;
import ent.Acier;
import ent.Entite;
import ent.Rockford;

public class RockfordAcier extends Expert {

	public boolean peutSeDeplacer(Entite hote, Cellule contact) {
		if (hote instanceof Rockford && contact != null && contact.getEntiteSurPlace() instanceof Acier)
			return true;
		else
			return false;
	}

	public void deplacement(Entite hote, Cellule contact) {
	}

	public RockfordAcier() {
		super();
	}

	public RockfordAcier(Expert s) {
		super(s);
	}

}
