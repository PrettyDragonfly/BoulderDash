package cor;

import java.io.File;

import application.Cellule;
import ent.Diamant;
import ent.Entite;
import ent.Rockford;
import ent.Vide;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import modele.exceptions.BoulderMortException;

public class DiamantRockford extends Expert {

	public boolean peutSeDeplacer(Entite hote, Cellule contact) {

		if (hote instanceof Diamant && contact != null && contact.getEntiteSurPlace() instanceof Rockford) {

			if (((Diamant) hote).isFalling())
				return true;
		}

		return false;
	}

	public void deplacement(Entite hote, Cellule contact) throws BoulderMortException {
		Rockford R = (Rockford) contact.getEntiteSurPlace();
		R.setDiams(R.getDiams() + 1);
		R.aMal();
		String musicFile = "src/sound/hurt.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mp = new MediaPlayer(sound);
		mp.play();
		mp.setVolume(0.3);
		hote.getSurCellule().setEntiteSurPlace(new Vide(hote.getSurCellule()));

		musicFile = "src/sound/diamant.mp3"; // For example
		sound = new Media(new File(musicFile).toURI().toString());
		mp = new MediaPlayer(sound);
		mp.play();

	}

	public DiamantRockford() {
		super();
	}

	public DiamantRockford(Expert s) {
		super(s);
	}

}
