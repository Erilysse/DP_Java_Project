package strategie_IA;

import cartes.Carte;
import joueurs.Joueur;
import plateau_du_jeu.Jeu;

/**
 * OperationRecupererCroyant est une classe qui impl�mente l'interface Strategy.
 * Elle permets � un joueur de r�cup�rer un croyant.
 * 
 * @see Strategy
 * 
 * @author manic
 *
 */
public class OperationRecupererCroyant implements Strategy {

	/**
	 * Affiche � l'utilisateur que le joueur rattache un Croyant � un Guide
	 * Spirituel. Prends la premi�re carte Guide Spirituel du Camp du Joueur et
	 * rattache le premier croyant du centre � cette carte.
	 * 
	 * @param joueur
	 *            un objet de la classe joueur, g�n�ralement un joueurIA.
	 * @param jeu
	 *            l'instance de la classe jeu.
	 */
	public void faire(Jeu jeu, Joueur joueur) {
		Carte carte = joueur.getCampjoueur().getCamp().get(0);
		String nom = joueur.getDiviniteRepresentee().getNom();
		System.out.println(nom + " rattache un Croyant � son guide Spirituel " + carte.getNom());
		carte.rattacherCroyants(joueur.getCentre().donnerCroyant(0));

	}

}
