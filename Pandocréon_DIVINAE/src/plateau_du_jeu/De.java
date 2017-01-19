package plateau_du_jeu;

import java.util.Observable;

/**
 * De est la classe qui représente le dé du jeu. Il a trois faces, Jour, Nuit et
 * Néant. Il permet, après être lancé, de distribuer des points d'actions au
 * joueur.
 * 
 * @see Joueur
 * @see Jeu
 * 
 * @author manic
 *
 */
public class De extends Observable{
	/**
	 * Nombre traduisant la face du Dé. (0:Jour, 1:Nuit, 2:Néant).
	 * 
	 * @see De#getFace()
	 * @see De#setFace(int)
	 */
	private int face;
	/**
	 * Instance de Dé, venant du design pattern singleton. Empêche d'avoir
	 * plusieurs objets de la classe De.
	 * 
	 * @see De#getInstance()
	 */
	private static De instance = null;

	/**
	 * Constructeur De. Par défaut, la face du dé est à zéro.
	 */
	public De() {
		this.face = 0;
	}

	/**
	 * Retourne le nombre traduisant la face du dé.
	 * 
	 * @return un nombre entier compris entre 0 et 2.
	 */
	public int getFace() {
		return face;
	}

	/**
	 * Met à jour la face du dé.
	 * 
	 * @param face
	 *            nouvelle face du dé, un nombre.
	 */
	public void setFace(int face) {
		this.face = face;
	}

	/**
	 * C'est le design pattern singleton. Il s'assure de n'avoir qu'une instance
	 * de Dé
	 * 
	 * @return un Dé.
	 */
	public static De getInstance() {
		if (instance == null) {
			instance = new De();
		}
		return instance;
	}

	/**
	 * Lance le dé de façon random afin de changer la face du dé. Selon ce
	 * nombre, affiche sur quel origine le dé est tombé.
	 */
	public void lancerDe() {
		this.face = (int) (3 * Math.random());
		setChanged();
		notifyObservers("DE");
		if (face == 0) {
			System.out.println("Le dé tombe sur Jour");
		} else if (face == 1) {
			System.out.println("Le dé tombe sur Nuit");
		} else {
			System.out.println("Le dé tombe sur Néant");
		}
	}

}
