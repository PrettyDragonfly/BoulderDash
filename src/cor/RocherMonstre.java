package cor;

import java.io.File;

import application.Cellule;
import ent.Diamant;
import ent.Entite;
import ent.Monstre;
import ent.Rocher;
import ent.Vide;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RocherMonstre extends Expert {

	public boolean peutSeDeplacer(Entite hote, Cellule contact) {

		if (hote instanceof Rocher && contact != null && contact.getEntiteSurPlace() instanceof Monstre) {
			Rocher P = (Rocher) hote;
			if (P.isFalling())
				return true;
		}

		return false;
	}

	public void deplacement(Entite hote, Cellule contact) {
		Rocher P = (Rocher) hote;
		contact.setEntiteSurPlace(new Diamant(hote.getSurCellule().getSud()));
		hote.getSurCellule().setEntiteSurPlace(new Vide(hote.getSurCellule()));
		String musicFile = "src/sound/monsterpain.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mp = new MediaPlayer(sound);
		mp.play();
		mp.setVolume(0.6);
		P.setFalling(false);
	}

	public RocherMonstre() {
		super();
	}

	public RocherMonstre(Expert s) {
		super(s);
	}

}
