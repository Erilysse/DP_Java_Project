package cartes;

import capacite.ApoEffect;
import plateau_du_jeu.Jeu;

/**
 * Apocalypse est une classe héritant de Carte. Lorsque cette carte est posée,
 * un joueur est éliminé (4 joueurs ou plus) ou un joueur gagne la partie en
 * fonction des points de Prière apportés par les Croyants de chaque joueur.
 * 
 * @see Carte
 * @see Joueur
 * 
 * @author manic
 */
public class Apocalypse extends Carte {
	/**
	 * l'effet de la carte Apocalypse.
	 * 
	 * @see AppoEffect
	 * @see Apocalypse#sacrifice()
	 */
	private ApoEffect apocalypse;

	/**
	 * Constructeur Apocalypse. Utilise le Constructeur Carte.
	 * 
	 * @param type
	 *            le type de la carte.
	 * @param nom
	 *            le nom de la carte.
	 * @param dogme1
	 *            le premier dogme de la carte, si elle en a.
	 * @param dogme2
	 *            le second dogme de la carte, si elle en a.
	 * @param dogme3
	 *            le troisième dogme de la carte, si elle en a.
	 * @param origine
	 *            l'origine de la carte sous forme de nombre.
	 * @param p
	 *            la caractéristique de la carte.
	 * 
	 * @see Carte
	 */
	public Apocalypse(String type, String nom, String dogme1, String dogme2, String dogme3, String origine, String p) {
		super(type, nom, dogme1, dogme2, dogme3, origine, p);
	}

	/**
	 * Crée l'effet de Apocalypse et utilise une de ses méthodes, sacrifice(int,
	 * int, int, jeu), pour sacrifier l'objet.
	 * 
	 * @see AppoEffect
	 * @see AppoEffect#sacrifice();
	 */
	public void effet() {
		if (Jeu.getInstance().getNbTour() != 1) {
			this.apocalypse = new ApoEffect();
			this.apocalypse.sacrifice(0, 0, 1, Jeu.getInstance());
		} else {
			System.out.println(
					"Une carte Apocalypse n'est pas utilisable à cet instant. Réessayez plus tard dans la partie.");
		}
	}
}