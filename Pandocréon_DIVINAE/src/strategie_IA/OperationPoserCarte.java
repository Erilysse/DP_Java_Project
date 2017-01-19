package strategie_IA;

import cartes.*;
import joueurs.Joueur;
import plateau_du_jeu.Jeu;

/**
 * OperationPoserCarte est une classe qui implémente l'interface Strategy. Elle
 * permets de poser une carte par l'IA.
 * 
 * @see Strategy
 * 
 * @author manic
 *
 */
public class OperationPoserCarte implements Strategy {

	/**
	 * 
	 * Pose une carte tout en vérifiant un certain nombre de conditions : Le
	 * joueur doit pouvoir poser sa carte, donc il doit avoir assez de points
	 * d'actions de l'origine de la carte. Si sa carte est : - Apocalypse, elle
	 * est posée si le joueur a le plus de points de prières par rapport aux
	 * autres joueurs. - Croyant, elle est posée quelque soit le contexte. -
	 * GuideSpirituel, elle est posée quelque soit le contexte. - DeusEx, elle
	 * est posée quelque soit le contexte.
	 * 
	 * @param joueur
	 *            un objet de la classe joueur, généralement un joueurIA.
	 * @param jeu
	 *            l'instance de la classe jeu.
	 */
	public void faire(Jeu jeu, Joueur joueur) {
		int index = (int) ((joueur.getMainjoueur().getNbCarte() - 1) * Math.random());
		Carte carte = joueur.getMainjoueur().getMain().get(index);
		String nom = joueur.getDiviniteRepresentee().getNom();
		joueur.verifierConsommerPA(index);
		if (joueur.iscanPlay() == true) {
			switch (carte.getType()) {
			case "Apocalypse":
				if (joueur.getNbrPrieres() == jeu.getJoueurMaxPP()) {
					System.out.println(nom + " joue une carte Apocalypse");
					((Apocalypse) carte).effet();
				}
				break;
			case "Croyants":
				System.out.println(nom + " pose un Croyant " + carte.getNom() + " dans le centre.");
				joueur.getCentre().ajouterCroyant((Croyant) carte);
				break;
			case "Guide Spirituel":
				System.out.println(nom + " pose un Guide Spirituel" + carte.getNom() + " dans son camp.");
				joueur.getCampjoueur().ajouterCarte((GuideSpirituel) carte);
				break;
			default:
				System.out.println(carte.getType());
				System.out.println(nom + "joue une carte Deus Ex.");
				((DeusEx) carte).effet();
				break;
			}
		}
		joueur.setcanPlay(false);
	}
}