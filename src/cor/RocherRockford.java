package cor;

import java.io.File;

import application.Cellule;
import ent.Entite;
import ent.Rocher;
import ent.Rockford;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import modele.exceptions.BoulderMortException;

public class RocherRockford extends Expert {

	public boolean peutSeDeplacer(Entite hote, Cellule contact) {

		if (hote instanceof Rocher && contact != null && contact.getEntiteSurPlace() instanceof Rockford) {
			Rocher P = (Rocher) hote;
			if (P.isFalling())
				return true;
		}

		return false;
	}

	public void deplacement(Entite hote, Cellule contact) throws BoulderMortException {
		Rockford R = (Rockford) contact.getEntiteSurPlace();
		R.aMal();
		Rocher P = (Rocher) hote;
		String musicFile = "src/sound/hurt.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mp = new MediaPlayer(sound);
		mp.play();
		mp.setVolume(0.3);
		P.setFalling(false);
	}

	public RocherRockford() {
		super();
	}

	public RocherRockford(Expert s) {
		super(s);
	}

}
