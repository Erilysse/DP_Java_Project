package strategie_IA;

import joueurs.Joueur;
import plateau_du_jeu.Jeu;

/**
 * Strategy est une interface utilis� dans le cadre du design pattern Strategy.
 * Elle correspond � une unique m�thode, faire(), qui va permettre aux classes
 * impl�mentant cette interface d'adopter un comportement. Elle est la base du
 * comportement de notre IA.
 * 
 * @see IA
 * 
 * @author manic
 *
 */
public interface Strategy {

	/**
	 * Ex�cute un comportement strat�gique.
	 * 
	 * @param jeu
	 *            instance de la classe Jeu pour conna�tre les joueurs
	 *            adversaires de l'IA.
	 * @param joueur
	 *            objet de la classe Joueur, de pr�f�rence IA, qui va permettre
	 *            de conna�tre l'ensemble de son deck, et son camp.
	 */
	public void faire(Jeu jeu, Joueur joueur);
}
