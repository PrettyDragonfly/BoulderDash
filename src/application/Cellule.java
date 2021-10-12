package application;

import ent.Entite;
import ent.Vide;

public class Cellule {
	private int x;
	private int y;
	private Entite entiteSurPlace;
	private Cellule nord;
	private Cellule sud;
	private Cellule est;
	private Cellule ouest;
	private Grille surGrille;

	/**
	 * Permet d'obtenir la cellule voisine nord de la cellule
	 *
	 * @return la cellule voisine au nord, null si il n'y en a pas
	 */
	public Cellule getNord() {
		return nord;
	}

	/**
	 * Initialise la voisine Nord d'une cellule
	 */
	public void setNord() {
		if (getX() == 0)
			this.nord = null;
		else
			this.nord = surGrille.getCell(getX() - 1, getY());
	}

	/**
	 * Permet d'obtenir la cellule voisine sud de la cellule
	 *
	 * @return la cellule voisine au sud, null si il n'y en a pas
	 */
	public Cellule getSud() {
		return sud;
	}

	/**
	 * Initialise la voisine Sud d'une cellule
	 */
	public void setSud() {
		if (getX() + 1 == getSurGrille().getSizeX())
			this.sud = null;
		else
			this.sud = surGrille.getCell(getX() + 1, getY());
	}

	/**
	 * Permet d'obtenir la cellule voisine est de la cellule
	 *
	 * @return la cellule voisine est, null si il n'y en a pas
	 */
	public Cellule getEst() {
		return est;
	}

	/**
	 * Initialise la voisine est d'une cellule
	 */
	public void setEst() {
		if (getY() + 1 == getSurGrille().getSizeY())
			this.est = null;
		else
			this.est = surGrille.getCell(getX(), getY() + 1);
	}

	/**
	 * Permet d'obtenir la cellule voisine nord de la cellule
	 *
	 * @return la cellule voisine ouest, null si il n'y en a pas
	 */
	public Cellule getOuest() {
		return ouest;
	}

	/**
	 * Initialise la voisine Ouest d'une cellule
	 */
	public void setOuest() {
		if (getY() == 0)
			this.ouest = null;
		else
			this.ouest = surGrille.getCell(getX(), getY() - 1);
	}

	/**
	 * Constructeur d'une cellule, requiert sa position et la grille dans laquelle
	 * elle se trouve
	 *
	 * @param x, la position en abcisse
	 * @param y, la position en ordonnée
	 * @param g, la grille
	 */
	public Cellule(int x, int y, Grille g) {
		setX(x);
		setY(y);
		setSurGrille(g);
		setEntiteSurPlace(new Vide(this));
	}

	/**
	 * Initialises les cellules voisines d'une cellule
	 */
	public void setVoisins() {
		setNord();
		setSud();
		setEst();
		setOuest();
	}

	/**
	 * Renvoie la position en abcisse de la cellule
	 *
	 * @return la position en abcisse
	 */
	public int getX() {
		return x;
	}

	/**
	 * Change la position en absisse de la cellule
	 *
	 * @param x, la position en abcisse
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Renvoie la position en ordonnée de la cellule
	 *
	 * @return la position en ordonnée
	 */
	public int getY() {
		return y;
	}

	/**
	 * Change la position en ordonnée de la cellule
	 *
	 * @param y la position en ordonnée
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Renvoie l'entité qui se trouve sur la cellule
	 *
	 * @return l'entité sur la cellule
	 */
	public Entite getEntiteSurPlace() {
		return entiteSurPlace;
	}

	/**
	 * Change l'entité qui se trouve sur la cellule
	 *
	 * @param entiteSurPlace, l'entité a mettre sur la cellule
	 */
	public void setEntiteSurPlace(Entite entiteSurPlace) {
		this.entiteSurPlace = entiteSurPlace;
	}

	/**
	 * Renvoie la grille sur laquel se trouve la cellule @return, la grille
	 */
	public Grille getSurGrille() {
		return surGrille;
	}

	/**
	 * Change la grille sur laquel se trouve la cellule
	 *
	 * @param surGrille, la grille
	 */
	public void setSurGrille(Grille surGrille) {
		this.surGrille = surGrille;
	}

	/**
	 *
	 * @return vrai si la cellule ne contient pas d'entité
	 */
	public boolean estVide() {
		if (getEntiteSurPlace() == null) {
			return true;
		}
		return false;
	}

	/**
	 * Afficheur de la cellule en mode console
	 */
	public String toString() {
		if (getEntiteSurPlace() == null)
			return "_";
		else
			return "" + getEntiteSurPlace();
	}
}
