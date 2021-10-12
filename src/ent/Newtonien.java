package ent;

import application.Cellule;

public class Newtonien extends Entite {
	/**
	 * un booleen qui renvoie vrai le l'entité est en train de chuter
	 */
	private boolean falling;

	public Newtonien(Cellule position) {
		super(position);
	}

	/**
	 * Permet de savoir si l'entité est en train de chuter
	 *
	 * @return vrai si l'entité chute
	 */
	public boolean isFalling() {
		return falling;
	}

	/**
	 * Change la valeur du booléen qui caractérise si une entité chute
	 *
	 * @param falling vrai si l'entité chute, faux sinon
	 */
	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	/**
	 * Applique la gravité sur un diamant, ce qui le fera descendre d'une case et
	 * changer son booléen de chute
	 */
	public void appliquerGravite() {
		setFalling(true);
		goSud();
	}

}
