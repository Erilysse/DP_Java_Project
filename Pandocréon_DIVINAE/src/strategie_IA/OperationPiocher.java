package strategie_IA;

import cartes.Carte;
import joueurs.Joueur;
import plateau_du_jeu.Jeu;

/**
 * OperationPiocher est une classe qui implémente l'interface Strategy.
 * 
 * @see Strategy
 * 
 * @author manic
 *
 */
public class OperationPiocher implements Strategy {

	/**
	 * Pioche des cartes jusqu'à ce que la main du joueur soit rempli de 7
	 * cartes.
	 * 
	 * @param joueur
	 *            un objet de la classe joueur, généralement un joueurIA.
	 * @param jeu
	 *            l'instance de la classe jeu.
	 */
	public void faire(Jeu jeu, Joueur joueur) {
		while (joueur.getMainjoueur().getNbCarte() < 7) {
			Carte carte = joueur.getPioche().piocher();
			joueur.getMainjoueur().ajouterCarte(carte);
		}
	}

}
