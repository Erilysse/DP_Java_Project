package capacite;

import plateau_du_jeu.Jeu;

/**
 * Sacrifice est une interface qui permets � toute les classes l'impl�mentant
 * d'avoir une m�thode de sacrifice. Elle sert dans les cas o� le joueur veut
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
	 *            origine particuli�re utilis�.
	 * @param nb
	 *            nombre.
	 * @param joueur
	 *            num�ro du joueur cibl� dans la liste des joueurs. par d�faut :
	 *            1
	 * @param jeu
	 *            instance de jeu.
	 * 
	 */
	public void sacrifice(int origine, int nb, int joueur, Jeu jeu);

}
