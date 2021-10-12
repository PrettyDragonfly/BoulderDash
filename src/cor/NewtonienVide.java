package cor;

import application.Cellule;
import ent.Entite;
import ent.Newtonien;
import ent.Vide;

public class NewtonienVide extends Expert {

	public boolean peutSeDeplacer(Entite hote, Cellule contact) {
		if (hote instanceof Newtonien && contact != null && contact.getEntiteSurPlace() instanceof Vide) {
			return true;
		} else
			return false;
	}

	public void deplacement(Entite hote, Cellule contact) {
		((Newtonien) hote).appliquerGravite();
	}

	public NewtonienVide() {
		super();
	}

	public NewtonienVide(Expert s) {
		super(s);
	}

}
