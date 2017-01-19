package plateau_du_jeu;

import java.util.Observable;

/**
 * De est la classe qui repr�sente le d� du jeu. Il a trois faces, Jour, Nuit et
 * N�ant. Il permet, apr�s �tre lanc�, de distribuer des points d'actions au
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
	 * Nombre traduisant la face du D�. (0:Jour, 1:Nuit, 2:N�ant).
	 * 
	 * @see De#getFace()
	 * @see De#setFace(int)
	 */
	private int face;
	/**
	 * Instance de D�, venant du design pattern singleton. Emp�che d'avoir
	 * plusieurs objets de la classe De.
	 * 
	 * @see De#getInstance()
	 */
	private static De instance = null;

	/**
	 * Constructeur De. Par d�faut, la face du d� est � z�ro.
	 */
	public De() {
		this.face = 0;
	}

	/**
	 * Retourne le nombre traduisant la face du d�.
	 * 
	 * @return un nombre entier compris entre 0 et 2.
	 */
	public int getFace() {
		return face;
	}

	/**
	 * Met � jour la face du d�.
	 * 
	 * @param face
	 *            nouvelle face du d�, un nombre.
	 */
	public void setFace(int face) {
		this.face = face;
	}

	/**
	 * C'est le design pattern singleton. Il s'assure de n'avoir qu'une instance
	 * de D�
	 * 
	 * @return un D�.
	 */
	public static De getInstance() {
		if (instance == null) {
			instance = new De();
		}
		return instance;
	}

	/**
	 * Lance le d� de fa�on random afin de changer la face du d�. Selon ce
	 * nombre, affiche sur quel origine le d� est tomb�.
	 */
	public void lancerDe() {
		this.face = (int) (3 * Math.random());
		setChanged();
		notifyObservers("DE");
		if (face == 0) {
			System.out.println("Le d� tombe sur Jour");
		} else if (face == 1) {
			System.out.println("Le d� tombe sur Nuit");
		} else {
			System.out.println("Le d� tombe sur N�ant");
		}
	}

}
