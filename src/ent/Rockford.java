package ent;

import application.Cellule;
import javafx.scene.media.MediaPlayer;
import modele.exceptions.BoulderMortException;

public class Rockford extends Entite {
	static MediaPlayer mediaPlayer;
	private int health;
	private int diams;

	/**
	 * Rockford, le personnage joueur, se caractérise par un nombre de vie
	 *
	 * @param position la position sur laquelle il se trouve
	 * @param h        le nombre de vie
	 */
	public Rockford(Cellule position, int h) {
		super(position);
		this.setHealth(h);
		this.setDiams(0);
	}

	/**
	 * Renvoie le nombre de vie de Rockford
	 *
	 * @return le nombre de vie
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Change le nombre de vie de Rockford
	 *
	 * @param h le nombre de vie
	 */
	public void setHealth(int h) {
		this.health = h;
	}

	/**
	 * Renvoie le nombre de diamant de Rockford
	 *
	 * @return le nombre de diamant
	 */
	public int getDiams() {
		return diams;
	}

	/**
	 * Change le nombre de diamant de Rockford
	 *
	 * @param d le nombre de diamant
	 */
	public void setDiams(int d) {
		this.diams = d;
	}

	public String toString() {
		return "R";
	}

	/**
	 * Renvoie vrai si Rockford est mort
	 *
	 * @return vrai si Rockford n'a plus de vie
	 */
	public boolean EstMort() {
		if (getHealth() < 1) {
			System.out.println("Rockford est décédé...");
			return true;
		}
		return false;
	}

	/**
	 * Fait subir un point de dégat a Rockford
	 *
	 * @throws BoulderMortException si Rockford meurt
	 */
	public void aMal() throws BoulderMortException {
		setHealth(getHealth() - 1);
		if (EstMort())
			throw new BoulderMortException("Rockford est mort");
	}

}
