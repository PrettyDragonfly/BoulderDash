package cor;

import java.io.File;

import application.Cellule;
import ent.Entite;
import ent.Monstre;
import ent.Rockford;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import modele.exceptions.BoulderMortException;

public class RockfordMonstre extends Expert {

	public boolean peutSeDeplacer(Entite hote, Cellule contact) {
		if (hote instanceof Rockford && contact != null && contact.getEntiteSurPlace() instanceof Monstre)
			return true;
		else
			return false;
	}

	public void deplacement(Entite hote, Cellule contact) {
		Rockford R = (Rockford) hote;
		R.goToward(contact);
		String musicFile = "src/sound/hurt.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		mediaPlayer.setVolume(0.3);
		try {
			R.aMal();
		} catch (BoulderMortException e) {
			e.printStackTrace();
		}
		musicFile = "src/sound/monsterpain.mp3";
		sound = new Media(new File(musicFile).toURI().toString());
		mp = new MediaPlayer(sound);
		mp.play();
		mp.setVolume(0.6);
	}

	public RockfordMonstre() {
		super();
	}

	public RockfordMonstre(Expert s) {
		super(s);
	}

}
