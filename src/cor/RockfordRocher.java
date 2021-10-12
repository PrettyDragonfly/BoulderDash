package cor;

import application.Cellule;
import ent.Entite;
import ent.Rocher;
import ent.Rockford;
import ent.Vide;

public class RockfordRocher extends Expert {

	public boolean peutSeDeplacer(Entite hote, Cellule contact) {
		if (hote instanceof Rockford && contact != null && contact.getEntiteSurPlace() instanceof Rocher) {
			if (hote.getSurCellule().getEst() == contact && contact.getEst() != null
					&& contact.getEst().getEntiteSurPlace() instanceof Vide)
				return true;
			else if (hote.getSurCellule().getOuest() == contact && contact.getOuest() != null
					&& contact.getOuest().getEntiteSurPlace() instanceof Vide)
				return true;
			else
				return false;
		}
		return false;
	}

	public void deplacement(Entite hote, Cellule contact) {
		if (hote.getSurCellule().getEst() == contact)
			contact.getEntiteSurPlace().goEst();
		if (hote.getSurCellule().getOuest() == contact)
			contact.getEntiteSurPlace().goOuest();
		hote.goToward(contact);
	}

	public RockfordRocher() {
		super();
	}

	public RockfordRocher(Expert s) {
		super(s);
	}

}
