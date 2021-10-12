package ui;

import ent.Rockford;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import modele.obs.Observable;
import modele.obs.Observateur;

public class AfficheurTempsEcoule implements Observateur {
	private double secondes = 0;
	private Label labelTempsEcoule = new Label("");
	private Rockford joueur;
	private final static String LABEL = "Temps écoulé : ";

	public AfficheurTempsEcoule(Pane panneau, Rockford joueur) {
		this.joueur = joueur;
		panneau.getChildren().add(labelTempsEcoule);
	}

	@Override
	public void recevoirNotification(Observable observable) {
		Timer timer = (Timer) observable;
		secondes += timer.getLaps();

		// YL : astuces pour formater un réel avec 1 seul chiffre après la virgule
		String strSecondes = String.format("%.1f", secondes);

		labelTempsEcoule
				.setText(LABEL + strSecondes + " | Diamant: " + joueur.getDiams() + "/5 | Vie: " + joueur.getHealth());
	}

}
