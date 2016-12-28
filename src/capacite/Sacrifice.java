package capacite;

import plateau_du_jeu.Jeu;

/**
 * Sacrifice est une interface qui permets à toute les classes l'implémentant
 * d'avoir une méthode de sacrifice. Elle sert dans les cas où le joueur veut
 * sacrifier une Carte.
 * 
 * @see Carte
 * 
 * @author manic
 *
 */
public interface Sacrifice {

	/**
	 * Sacrifie une carte en appliquant un effet sur le jeu, ou sur un joueur.
	 * 
	 * @param origine
	 *            origine particulière utilisé.
	 * @param nb
	 *            nombre.
	 * @param joueur
	 *            numéro du joueur ciblé dans la liste des joueurs. par défaut :
	 *            1
	 * @param jeu
	 *            instance de jeu.
	 * 
	 */
	public void sacrifice(int origine, int nb, int joueur, Jeu jeu);

}
