package strategie_IA;

import joueurs.Joueur;
import plateau_du_jeu.Jeu;

/**
 * Strategy est une interface utilisé dans le cadre du design pattern Strategy.
 * Elle correspond à une unique méthode, faire(), qui va permettre aux classes
 * implémentant cette interface d'adopter un comportement. Elle est la base du
 * comportement de notre IA.
 * 
 * @see IA
 * 
 * @author manic
 *
 */
public interface Strategy {

	/**
	 * Exécute un comportement stratégique.
	 * 
	 * @param jeu
	 *            instance de la classe Jeu pour connaître les joueurs
	 *            adversaires de l'IA.
	 * @param joueur
	 *            objet de la classe Joueur, de préférence IA, qui va permettre
	 *            de connaître l'ensemble de son deck, et son camp.
	 */
	public void faire(Jeu jeu, Joueur joueur);
}
