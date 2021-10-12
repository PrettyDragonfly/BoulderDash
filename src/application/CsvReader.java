package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ent.Acier;
import ent.Diamant;
import ent.Monstre;
import ent.Rocher;
import ent.Terre;
import ent.Vide;

public class CsvReader {
	/**
	 * Une classe permettant de lire un fichier CSV pour charger la grille
	 *
	 * @param G,        la grille de jeu
	 * @param filename, le chemin relatif du fichier csv
	 * @throws FileNotFoundException, si le programme ne trouve pas le csv
	 */
	public CsvReader(Grille G, String filename) throws FileNotFoundException {
		File myObj = new File(filename);
		Scanner myReader = new Scanner(myObj);
		myReader.nextLine();
		int nbLine = Integer.parseInt(myReader.nextLine().split("[;]+")[1]);
		int nbCol = Integer.parseInt(myReader.nextLine().split("[;]+")[1]);
		for (int i = 0; i < nbLine; i++) {
			String[] tokens = myReader.nextLine().split("[;]+");
			for (int j = 0; j < nbCol; j++) {
				Cellule pos = G.getCell(i, j);
				switch (tokens[j]) {
				case "T":
					pos.setEntiteSurPlace(new Terre(pos));
					break;
				case "B":
					pos.setEntiteSurPlace(new Acier(pos));
					break;
				case "R":
					pos.setEntiteSurPlace(new Rocher(pos));
					break;
				case "D":
					pos.setEntiteSurPlace(new Diamant(pos));
					break;
				case "M":
					pos.setEntiteSurPlace(new Monstre(pos));
					break;
				case "V":
					pos.setEntiteSurPlace(new Vide(pos));
					break;
				default:
				}
			}
		}
		myReader.close();
	}
}
