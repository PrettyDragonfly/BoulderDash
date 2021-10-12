package cor;

import java.io.File;

import application.Cellule;
import ent.Entite;
import ent.Monstre;
import ent.Newtonien;
import ent.Rockford;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class NewtonienSol extends Expert {

	public boolean peutSeDeplacer(Entite hote, Cellule contact) {

		if ((hote instanceof Newtonien) && (contact == null || (!(contact.getEntiteSurPlace() instanceof Rockford)
				&& !(contact.getEntiteSurPlace() instanceof Monstre)))) {
			if (((Newtonien) hote).isFalling())
				return true;
		}

		return false;
	}

	public void deplacement(Entite hote, Cellule contact) {
		((Newtonien) hote).setFalling(false);
		String musicFile = "src/sound/boom.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mp = new MediaPlayer(sound);
		mp.play();
		mp.setVolume(0.3);
	}

	public NewtonienSol() {
		super();
	}

	public NewtonienSol(Expert s) {
		super(s);
	}

}
