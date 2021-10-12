package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import ent.Acier;
import ent.Diamant;
import ent.Entite;
import ent.Monstre;
import ent.Newtonien;
import ent.Rocher;
import ent.Rockford;
import ent.Terre;
import ent.Vide;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.exceptions.BoulderEndLevelException;
import modele.exceptions.BoulderMortException;
import modele.obs.Observable;
import modele.obs.Observateur;
import ui.PanneauFooter;
import ui.Timer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FenetrePrincipale est la classe centrale de Boulderdash Elle contient la
 * méthode main, et construit l'ensemble de la fenetre de jeu
 */
public class FenetrePrincipale extends Application implements Observateur {
	public static boolean victoire = false;
	/**
	 * Un MediaPlayer qui joue la musique centrale du jeu
	 */
	public static MediaPlayer mediaPlayer;
	/**
	 * Une variable qui permet de stocké le numero du plateau actuel du jeu (0 à
	 * 3)
	 */
	private static MediaPlayer m;
	/**
	 * Mediaplayer pour le son de rechargement, passage en statique pour éviter
	 * qu'il ne se voit récupérer par le garbage collector
	 */
	public static int niveau = 0;
	/**
	 * La grille graphique du jeu correspondant à la vue du plateau
	 */
	private static Canvas grillePane;
	/**
	 * Le conteneur de la fenetre principale, qui se caractérise par une grille et
	 * un footer
	 */
	private static BorderPane root;
	/*
	 * La scene graphique de l'application
	 */
	private Scene scene;
	/*
	 * Le Footer qui contiendra toutes les informations textuelles du jeu tel que le
	 * nombre de vie, le temps écoulé, le nombre de diamants...etc
	 */
	private PanneauFooter panneauFooter;

	/*
	 * Une variable contenant le primaryStage de l'application
	 */
	@SuppressWarnings("unused")
	private Stage ps;

	/**
	 * Un tableau contenant les chemins relatifs vers les données de plateau du jeu
	 */
	private static String[] levels = { "src/plateaux/niveau1.csv", "src/plateaux/niveau2.csv",
			"src/plateaux/niveau3.csv", "src/plateaux/niveau4.csv", };

	/**
	 * Taille en cases du plateau
	 */
	private static final int NB_LIG = 10;
	private static final int NB_COL = 10;

	/**
	 * Une HashMap qui permet de faire le lien entre une classe (Des instances
	 * d'entités) avec l'image qui lui est associée dans le jeu
	 */
	private HashMap<Class<?>, Image> tabImage;

	/**
	 * Un timer qui constitue un observable, déclaré en static car un même timer
	 * est utiliser par de nombreuses classes du jeu.
	 */
	static Timer timer = new Timer(0.1);

	/**
	 * La Grille, qui contient les cellules du jeu
	 */
	public static Grille G = new Grille(NB_LIG, NB_COL, timer);

	/**
	 * Le personnage Rockford
	 */
	public static Rockford R = new Rockford(null, 3);

	/**
	 * Getter de grillePane
	 *
	 * @return la grillePane, l'élément graphique qui correspond à la Grille
	 */
	public Canvas getGrillePane() {
		return grillePane;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			ps = primaryStage;
			primaryStage.setTitle("Boulder Dash");

			Media sound = new Media(new File("src/sound/aquatic.mp3").toURI().toString());
			mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
			mediaPlayer.setVolume(0.2);
			mediaPlayer.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					Media sound = new Media(new File("src/sound/aquatic.mp3").toURI().toString());
					mediaPlayer = new MediaPlayer(sound);
					mediaPlayer.setVolume(0.1);
					mediaPlayer.play();
				}
			});

			root = new BorderPane(grillePane);

			initFooter();

			scene = new Scene(root);

			scene.setOnKeyPressed(new HandlerClavier());

			initImages();
			initGrille(G);
			timer.add(this);
			dessinerGrille();

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialise la HashMap, associant à chaque entité une image.
	 */
	private void initImages() {
		tabImage = new HashMap<Class<?>, Image>();
		Image image;

		image = new Image(getClass().getResourceAsStream("/images/Rockford.png"));
		tabImage.put(Rockford.class, image);
		image = new Image(getClass().getResourceAsStream("/images/Vide.png"));
		tabImage.put(Vide.class, image);
		image = new Image(getClass().getResourceAsStream("/images/Acier.png"));
		tabImage.put(Acier.class, image);
		image = new Image(getClass().getResourceAsStream("/images/Terre.png"));
		tabImage.put(Terre.class, image);
		image = new Image(getClass().getResourceAsStream("/images/Diamant.png"));
		tabImage.put(Diamant.class, image);
		image = new Image(getClass().getResourceAsStream("/images/Rocher.png"));
		tabImage.put(Rocher.class, image);
		image = new Image(getClass().getResourceAsStream("/images/Monstre.png"));
		tabImage.put(Monstre.class, image);

	}

	/**
	 * Dessine la grille de jeu
	 */
	private void dessinerGrille() {
		for (int l = 0; l < NB_LIG; l++) {
			for (int c = 0; c < NB_COL; c++) {
				Image image = tabImage.get(G.getCell(l, c).getEntiteSurPlace().getClass());
				getGrillePane().getGraphicsContext2D().drawImage(image, c * 64, l * 64);
			}
		}
	}

	/**
	 * Initialise la grille de jeu avec le CSV qui correspond au niveau sauvegardé
	 *
	 * @param G
	 * @throws FileNotFoundException
	 */
	public static void initGrille(Grille G) throws FileNotFoundException {
		if (R.getSurCellule() != null) {
			R.getSurCellule().setEntiteSurPlace(new Vide(R.getSurCellule()));
			R.setSurCellule(null);
		}

		int lGrille = 64 * NB_LIG;
		int hGrille = 64 * NB_COL;
		grillePane = new Canvas(lGrille, hGrille);
		root.setCenter(grillePane);
		new CsvReader(G, levels[niveau]);
		R.setSurCellule(G.getCell(1, 1));
		G.getCell(1, 1).setEntiteSurPlace(R);
		grillePane.getGraphicsContext2D();
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 *
	 * Handler qui traite l'appuie d'une touche du clavier Si cette touche est R, le
	 * niveau est rechargé, sinon, on passe par la chaîne de responsabilité avec
	 * comme entité hôte Rockford et en second paramètre le keyEvent.
	 */
	private final class HandlerClavier implements EventHandler<KeyEvent> {
		public void handle(KeyEvent ke) {
			if (ke.getCode() == KeyCode.R) {
				Media sound = new Media(new File("src/sound/REVERSE.mp3").toURI().toString());
				MediaPlayer m = new MediaPlayer(sound);
				m.play();
				R.setDiams(0);
				try {
					initGrille(G);
				} catch (FileNotFoundException e) {
					Platform.exit();
					e.printStackTrace();
				}
			}
			Cellule contact = null;
			if (R.getSurCellule() != null) {
				switch (ke.getCode()) {
				case UP:
					contact = R.getSurCellule().getNord();
					break;
				case LEFT:
					contact = R.getSurCellule().getOuest();
					break;
				case RIGHT:
					contact = R.getSurCellule().getEst();
					break;
				case DOWN:
					contact = R.getSurCellule().getSud();
					break;
				default:
				}
				try {
					G.getPremierExp().detecter(R, contact);
				} catch (BoulderMortException e) {
					gameOver();
				} catch (BoulderEndLevelException e) {
					nextLevel();
				}
			}
			dessinerGrille();
		}
	}

	/**
	 * Initialise le footer du jeu
	 */
	private void initFooter() {
		panneauFooter = new PanneauFooter(R, timer);
		root.setBottom(panneauFooter);
	}

	/**
	 * Tout les laps du timer, la fenêtre principale observe si le niveau est
	 * réussi, si tel est le cas il passe au niveau suivant, sauf si le joueur
	 * atteint le dernier niveau, dans ce cas, il gagne le jeu.
	 *
	 */

	@Override
	public void recevoirNotification(Observable observable) {

		// On applique la gravite:
		for (int i = G.getSizeX() - 1; i >= 0; i--) {
			for (int j = G.getSizeY() - 1; j >= 0; j--) {
				Entite e = G.getCell(i, j).getEntiteSurPlace();
				if (e instanceof Newtonien)
					try {
						G.appliquerGravite(e);
					} catch (BoulderMortException e1) {
						gameOver();
					} catch (BoulderEndLevelException e1) {
						nextLevel();
					}
			}
		}
		// Reactualise la vue
		dessinerGrille();

	}

	public void gameOver() {
		Media sound = new Media(new File("src/sound/gameover.mp3").toURI().toString());
		m = new MediaPlayer(sound);
		R.getSurCellule().setEntiteSurPlace(new Vide(R.getSurCellule()));
		R.setSurCellule(null);
		m.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				final Stage dialog = new Stage();
				dialog.initModality(Modality.APPLICATION_MODAL);
				FXMLLoader loader;
				if(!victoire) loader = new FXMLLoader(getClass().getResource("/gameover.fxml"));
				else loader = new FXMLLoader(getClass().getResource("/credit.fxml"));
				ControlGameOver cont_boite = new ControlGameOver();
				loader.setController(cont_boite);
				Pane grille = null;
				try {
					grille = loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene dialogScene = new Scene(grille, 600, 400);

				dialog.setResizable(false);
				dialog.setScene(dialogScene);
				dialog.show();
			}
		});
		m.play();
	}

	public void nextLevel() {
		R.setDiams(0);
		niveau++;
		if (niveau == 4) {
			victoire = true;
			gameOver();
		} else {
			try {
				Media sound = new Media(new File("src/sound/teleport.mp3").toURI().toString());
				m = new MediaPlayer(sound);
				m.play();
				R.setDiams(0);
				initGrille(G);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
