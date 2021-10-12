package cor;

import application.Cellule;
import ent.Entite;
import ent.Rockford;
import ent.Vide;

public class RockfordVide extends Expert {

	public boolean peutSeDeplacer(Entite hote, Cellule contact) {
		if (hote instanceof Rockford && (contact == null || contact.getEntiteSurPlace() instanceof Vide))
			return true;
		else
			return false;
	}

	public void deplacement(Entite hote, Cellule contact) {
		hote.goToward(contact);
	}

	public RockfordVide() {
		super();
	}

	public RockfordVide(Expert s) {
		super(s);
	}

}
