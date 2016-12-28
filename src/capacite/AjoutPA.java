package capacite;

import plateau_du_jeu.Jeu;

/**
 * AjoutPA est la classe repr�sentant l'effet d'ajouter un nombre de PA � un
 * joueur d'une certaine origine. Elle impl�mente l'interface Sacrifice.
 * 
 * @see Joueur
 * @see Sacrifice
 * 
 * @author manic
 *
 */

public class AjoutPA implements Sacrifice {

	/**
	 * Constructeur AjoutPA.
	 */
	public AjoutPA() {
	}

	/**
	 * Impl�mente l'effet de la classe. Ajoute un nombre nb de PA de l'origine
	 * origine au joueur joueur.
	 * 
	 * @param origine
	 *            origine (Jour, Nuit, N�ant..) sous forme de nombre.
	 * @param nb
	 *            nombre de PA que l'on va ajouter.
	 * @param joueur
	 *            objet de la classe Joueur qui va �tre le destinataire de ces
	 *            PA.
	 * @param jeu
	 *            instance de la classe Jeu pour avoir acc�s � la partie en
	 *            cours.
	 * 
	 * @see Joueur
	 * @see Jeu
	 */
	public void sacrifice(int origine, int nb, int joueur, Jeu jeu) {
		for (int i = 0; i < nb; i++) {
			jeu.getListJoueur().get(joueur).setListPAindex(origine, nb);
		}

	}

	/**
	 * Evite que l'application bug car le sacrifice de la carte n'est pas
	 * impl�ment�.
	 */
	public void sacrifice() {
		System.out.println("mauvaise m�thode");
	}

}