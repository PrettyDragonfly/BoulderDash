package application;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ControlGameOver {
	private static MediaPlayer mp;
	@FXML
	private Button _rejouer;
	public void initialize() {
		FenetrePrincipale.mediaPlayer.stop();
		String musicFile = "src/sound/gameovervoice.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mp = new MediaPlayer(sound);
		if(!FenetrePrincipale.victoire)mp.play();
		mp.setVolume(0.3);
		if(FenetrePrincipale.victoire) {
			Media sound2 = new Media(new File("src/sound/themefinal.mp3").toURI().toString());
			FenetrePrincipale.mediaPlayer = new MediaPlayer(sound2);
			FenetrePrincipale.mediaPlayer.play();
		}
	}
	public void Quitter() {
		Platform.exit();
	}
	public void Rejouer() throws FileNotFoundException {
		FenetrePrincipale.mediaPlayer.stop();
		Media sound = new Media(new File("src/sound/aquatic.mp3").toURI().toString());
		FenetrePrincipale.mediaPlayer = new MediaPlayer(sound);
		FenetrePrincipale.mediaPlayer.play();
		FenetrePrincipale.mediaPlayer.setVolume(0.2);
		FenetrePrincipale.mediaPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				Media sound = new Media(new File("src/sound/aquatic.mp3").toURI().toString());
				FenetrePrincipale.mediaPlayer = new MediaPlayer(sound);
				FenetrePrincipale.mediaPlayer.setVolume(0.1);
				FenetrePrincipale.mediaPlayer.play();
			}
		});
		Stage dialog = (Stage)_rejouer.getScene().getWindow();
		FenetrePrincipale.niveau = 0;
		FenetrePrincipale.initGrille(FenetrePrincipale.G);
		FenetrePrincipale.R.setDiams(0);
		FenetrePrincipale.R.setHealth(3);
		FenetrePrincipale.victoire = false;
		dialog.close();
	}
	
}