package cor;

import java.io.File;

import application.Cellule;
import ent.Diamant;
import ent.Entite;
import ent.Rockford;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import modele.exceptions.BoulderEndLevelException;
import modele.exceptions.BoulderMortException;

public class RockfordDiamant extends Expert {

	public boolean peutSeDeplacer(Entite hote, Cellule contact) {
		if (hote instanceof Rockford && contact != null && contact.getEntiteSurPlace() instanceof Diamant)
			return true;
		else
			return false;
	}

	public void deplacement(Entite hote, Cellule contact) throws BoulderMortException, BoulderEndLevelException {
		Rockford R = (Rockford) hote;
		R.setDiams(R.getDiams() + 1);
		String musicFile = "src/sound/diamant.mp3"; // For example
		R.goToward(contact);
		Media sound = new Media(new File(musicFile).toURI().toString());
		mp = new MediaPlayer(sound);
		mp.play();
		if (R.getDiams() >= 5)
			throw new BoulderEndLevelException("Fin de niveau");
	}

	public RockfordDiamant() {
		super();
	}

	public RockfordDiamant(Expert s) {
		super(s);
	}

}
