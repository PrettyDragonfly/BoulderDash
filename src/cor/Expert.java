package cor;

import application.Cellule;
import ent.Entite;
import javafx.scene.media.MediaPlayer;
import modele.exceptions.BoulderEndLevelException;
import modele.exceptions.BoulderMortException;

public abstract class Expert {

	public static MediaPlayer mp;
	private Expert suivant;

	public Expert getSuivant() {
		return suivant;
	}

	public void setSuivant(Expert suivant) {
		this.suivant = suivant;
	}

	public Expert() {
		setSuivant(null);
	}

	public Expert(Expert s) {
		this();
		s.setSuivant(this);
	}

	public void detecter(Entite hote, Cellule contact) throws BoulderMortException, BoulderEndLevelException {
		if (this.peutSeDeplacer(hote, contact))
			this.deplacement(hote, contact);
		else {
			if (suivant != null) {
				suivant.detecter(hote, contact);
			}
		}
	}

	abstract boolean peutSeDeplacer(Entite hote, Cellule contact);

	abstract void deplacement(Entite hote, Cellule contact) throws BoulderMortException, BoulderEndLevelException;
}
