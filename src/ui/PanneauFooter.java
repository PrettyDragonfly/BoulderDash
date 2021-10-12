package ui;

import ent.Rockford;
import javafx.scene.layout.HBox;

import modele.exceptions.BoulderException;

public class PanneauFooter extends HBox {

	/**
	 * Timer qui se déclenche tous les 0.1 s
	 */

	public PanneauFooter(Rockford R, Timer timer) {
		super();

		AfficheurTempsEcoule afficheur = new AfficheurTempsEcoule(this, R);
		try {
			timer.add(afficheur);
		} catch (BoulderException e) {
			e.printStackTrace();
		}
	}

}
