package cor;

import java.io.File;

import application.Cellule;
import ent.Entite;
import ent.Rockford;
import ent.Terre;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RockfordTerre extends Expert {

	public boolean peutSeDeplacer(Entite hote, Cellule contact) {
		if (hote instanceof Rockford && contact != null && contact.getEntiteSurPlace() instanceof Terre)
			return true;
		else
			return false;
	}

	public void deplacement(Entite hote, Cellule contact) {
		int nombreAleatoire = (int) (Math.random() * 3);
		String musicFile = "src/sound/dig" + nombreAleatoire + ".mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mp = new MediaPlayer(sound);
		mp.setVolume(0.5);
		mp.play();
		hote.goToward(contact);
	}

	public RockfordTerre() {
		super();
	}

	public RockfordTerre(Expert s) {
		super(s);
	}

}
