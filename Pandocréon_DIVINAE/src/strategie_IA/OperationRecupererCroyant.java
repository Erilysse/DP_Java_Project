package strategie_IA;

import cartes.Carte;
import joueurs.Joueur;
import plateau_du_jeu.Jeu;

/**
 * OperationRecupererCroyant est une classe qui implémente l'interface Strategy.
 * Elle permets à un joueur de récupérer un croyant.
 * 
 * @see Strategy
 * 
 * @author manic
 *
 */
public class OperationRecupererCroyant implements Strategy {

	/**
	 * Affiche à l'utilisateur que le joueur rattache un Croyant à un Guide
	 * Spirituel. Prends la première carte Guide Spirituel du Camp du Joueur et
	 * rattache le premier croyant du centre à cette carte.
	 * 
	 * @param joueur
	 *            un objet de la classe joueur, généralement un joueurIA.
	 * @param jeu
	 *            l'instance de la classe jeu.
	 */
	public void faire(Jeu jeu, Joueur joueur) {
		Carte carte = joueur.getCampjoueur().getCamp().get(0);
		String nom = joueur.getDiviniteRepresentee().getNom();
		System.out.println(nom + " rattache un Croyant à son guide Spirituel " + carte.getNom());
		carte.rattacherCroyants(joueur.getCentre().donnerCroyant(0));

	}

}
