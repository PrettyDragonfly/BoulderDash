package ent;

import application.Cellule;

/**
 * La classe Entite caractérise les personnages, obstacles et autres éléments
 * essentiels au jeu, une entite se caractérise par le fait qu'elle se trouve
 * sur une cellule de la grille
 */
public abstract class Entite {

	/*
	 * la cellule sur laquel se trouve l'entité.
	 */
	private Cellule surCellule;

	/**
	 * Constructeur de l'entité, nécessite de préciser la cellule sur laquelle
	 * l'entité doit se trouver
	 *
	 * @param position, la cellule
	 */
	public Entite(Cellule position) {
		if (position != null) {
			setSurCellule(position);
			getSurCellule().setEntiteSurPlace(this);
		}
	}

	/**
	 * Renvoie la cellule sur laquel l'entité se trouve
	 *
	 * @return la cellule
	 */
	public Cellule getSurCellule() {
		return surCellule;
	}

	/**
	 * Permet de déplacer une entité sur une cellule voisine sans savoir dans quel
	 * direction elle se trouve
	 *
	 * @param cible la cellule voisine
	 */
	public void goToward(Cellule cible) {
		if (getSurCellule().getNord() == cible)
			goNord();
		else if (getSurCellule().getEst() == cible)
			goEst();
		else if (getSurCellule().getSud() == cible)
			goSud();
		else if (getSurCellule().getOuest() == cible)
			goOuest();
	}

	/**
	 * Déplace l'entité sur la cellule au nord
	 */
	public void goNord() {
		if (getSurCellule().getNord() != null)
			setSurCellule(getSurCellule().getNord());
	}

	/**
	 * Déplace l'entité sur la cellule à l'est
	 */
	public void goEst() {
		if (getSurCellule().getEst() != null)
			setSurCellule(getSurCellule().getEst());
	}

	/**
	 * Déplace l'entité sur la cellule au sud
	 */
	public void goSud() {
		if (getSurCellule().getSud() != null)
			setSurCellule(getSurCellule().getSud());
	}

	/**
	 * Déplace l'entité sur la cellule a l'ouest
	 */
	public void goOuest() {
		if (getSurCellule().getOuest() != null)
			setSurCellule(getSurCellule().getOuest());
	}

	/**
	 * Change la cellule sur laquel se trouve l'entité
	 *
	 * @param surCellule, la cellule
	 */
	public void setSurCellule(Cellule surCellule) {
		if (getSurCellule() != null) {
			Vide v = new Vide(getSurCellule());
			getSurCellule().setEntiteSurPlace(v);
		}
		this.surCellule = surCellule;
		if (surCellule != null)
			getSurCellule().setEntiteSurPlace(this);
	}

}
