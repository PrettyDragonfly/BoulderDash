package cor;

import java.io.File;

import application.Cellule;
import ent.Diamant;
import ent.Entite;
import ent.Monstre;
import ent.Vide;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class DiamantMonstre extends Expert {

	public boolean peutSeDeplacer(Entite hote, Cellule contact) {

		if (hote instanceof Diamant && contact != null && contact.getEntiteSurPlace() instanceof Monstre) {
			if (((Diamant) hote).isFalling())
				return true;
		}

		return false;
	}

	public void deplacement(Entite hote, Cellule contact) {
		contact.setEntiteSurPlace(new Vide(hote.getSurCellule().getSud()));
		String musicFile = "src/sound/monsterpain.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mp = new MediaPlayer(sound);
		mp.play();
		mp.setVolume(0.6);
	}

	public DiamantMonstre() {
		super();
	}

	public DiamantMonstre(Expert s) {
		super(s);
	}

}
