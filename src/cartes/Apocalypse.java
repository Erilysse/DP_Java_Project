package cartes;

import capacite.ApoEffect;
import plateau_du_jeu.Jeu;

/**
 * Apocalypse est une classe h�ritant de Carte. Lorsque cette carte est pos�e,
 * un joueur est �limin� (4 joueurs ou plus) ou un joueur gagne la partie en
 * fonction des points de Pri�re apport�s par les Croyants de chaque joueur.
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
	 *            le troisi�me dogme de la carte, si elle en a.
	 * @param origine
	 *            l'origine de la carte sous forme de nombre.
	 * @param p
	 *            la caract�ristique de la carte.
	 * 
	 * @see Carte
	 */
	public Apocalypse(String type, String nom, String dogme1, String dogme2, String dogme3, String origine, String p) {
		super(type, nom, dogme1, dogme2, dogme3, origine, p);
	}

	/**
	 * Cr�e l'effet de Apocalypse et utilise une de ses m�thodes, sacrifice(int,
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
					"Une carte Apocalypse n'est pas utilisable � cet instant. R�essayez plus tard dans la partie.");
		}
	}
}