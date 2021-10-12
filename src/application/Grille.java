package application;

import cor.DiamantMonstre;
import cor.DiamantRockford;
import cor.Expert;
import cor.RocherGlisse;
import cor.RocherMonstre;
import cor.RocherRockford;
import cor.NewtonienSol;
import cor.NewtonienVide;
import cor.RockfordAcier;
import cor.RockfordDiamant;
import cor.RockfordMonstre;
import cor.RockfordRocher;
import cor.RockfordTerre;
import cor.RockfordVide;
import ent.Entite;
import modele.exceptions.BoulderEndLevelException;
import modele.exceptions.BoulderMortException;
import ui.Timer;

public class Grille {
	/**
	 * Caracterise la taille de la grille
	 */
	int sizeX;
	int sizeY;

	/**
	 * Une grille se caractérise par un ensemble de cellules, plus précisément,
	 * d'un tableau de tableau de cellules.
	 */
	Cellule[][] cellules;
	/**
	 * Un lien vers le premier expert de la chaîne de responsabilité
	 */
	Expert premierExp;

	/**
	 * Constructeur de la grille
	 *
	 * @param sizeX, dimension en absisse de la grille
	 * @param sizeY, dimension en ordonnée de la grille
	 * @param timer, timer de la fenetre principale
	 */
	public Grille(int sizeX, int sizeY, Timer timer) {
		setSizeX(sizeX);
		setSizeY(sizeY);
		cellules = new Cellule[getSizeX()][getSizeY()];
		for (int i = 0; i < getSizeX(); i++) {
			for (int j = 0; j < getSizeY(); j++) {
				Cellule c = new Cellule(i, j, this);
				setCell(i, j, c);
			}
		}
		for (int i = 0; i < getSizeX(); i++) {
			for (int j = 0; j < getSizeY(); j++) {
				this.getCell(i, j).setVoisins();
			}
		}

		/**
		 * initialise la chaîne de responsaibilité
		 */
		RockfordVide primo = new RockfordVide();
		RockfordTerre secondo = new RockfordTerre(primo);
		RockfordAcier tertio = new RockfordAcier(secondo);
		NewtonienVide quartio = new NewtonienVide(tertio);
		RockfordDiamant sexo = new RockfordDiamant(quartio);
		RocherRockford hept = new RocherRockford(sexo);
		RocherGlisse octo = new RocherGlisse(hept);
		RockfordRocher nano = new RockfordRocher(octo);
		RocherMonstre Denver = new RocherMonstre(nano);
		RockfordMonstre Rio = new RockfordMonstre(Denver);
		NewtonienSol rs = new NewtonienSol(Rio);
		DiamantMonstre dm = new DiamantMonstre(rs);
		@SuppressWarnings("unused")
		DiamantRockford dr = new DiamantRockford(dm);

		setPremierExp(primo);
	}

	/**
	 * Renvoie la cellule qui se trouve a une certaine position dans la Grille
	 *
	 * @param x
	 * @param y
	 * @return la cellule caractérisée par x et y
	 */
	public Cellule getCell(int x, int y) {
		return cellules[x][y];
	}

	/**
	 * Affecte une cellule a une certaine position dans la Grille
	 *
	 * @param x
	 * @param y
	 * @param c, la cellule
	 */
	public void setCell(int x, int y, Cellule c) {
		cellules[x][y] = c;
	}

	/**
	 * @return la dimension en absisse de la grille
	 */
	public int getSizeX() {
		return sizeX;
	}

	/**
	 * Change la dimension en absisse de la grille
	 *
	 * @param sizeX la nouvelle dimension
	 */
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	/**
	 * Renvoie la dimension en ordonnée de la grille
	 *
	 * @return la dimension en ordonnée de la grille
	 */
	public int getSizeY() {
		return sizeY;
	}

	/**
	 * Change la dimension en ordonnée de la grille
	 *
	 * @param sizeY, la nouvelle dimension
	 */
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	/**
	 *
	 * @return le premiere expert de la chaine de responsabilité
	 */
	public Expert getPremierExp() {
		return premierExp;
	}

	/**
	 * Change le premier expert de la chaine de responsabilité
	 *
	 * @param premierExp, le nouveau premier expert
	 */
	public void setPremierExp(Expert premierExp) {
		this.premierExp = premierExp;
	}

	/**
	 * Affiche la grille en mode console
	 */
	public void print() {
		for (int i = 0; i < getSizeX(); i++) {
			System.out.println();
			for (int j = 0; j < getSizeY(); j++) {
				System.out.print(getCell(i, j));
			}
		}
	}

	public void appliquerGravite(Entite e) throws BoulderMortException, BoulderEndLevelException {
		getPremierExp().detecter(e, e.getSurCellule().getSud());
	}

}
