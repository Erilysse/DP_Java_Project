package strategie_IA;

import cartes.*;
import joueurs.Joueur;
import plateau_du_jeu.Jeu;

/**
 * OperationPoserCarte est une classe qui impl�mente l'interface Strategy.
 * 
 * @see Strategy
 * 
 * @author manic
 *
 */
public class OperationPoserCarte implements Strategy {
	/**
	 * Pose une carte tout en v�rifiant un certain nombre de conditions : Le
	 * joueur doit pouvoir poser sa carte, donc il doit avoir assez de points
	 * d'actions de l'origine de la carte.
	 * Si sa carte est :
	 * - Apocalypse, elle est pos�e si le joueur a le plus de points de pri�res par rapport aux autres joueurs.
	 * - Croyant, elle est pos�e quelque soit le contexte.
	 * - GuideSpirituel, elle est pos�e si le camp du joueur est vide.
	 * - DeusEx, elle est pos�e quelque soit le contexte.
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
			case "Croyant":
				System.out.println(nom + " pose un Croyant " + carte.getNom() + " dans le centre.");
				joueur.getCentre().ajouterCroyant((Croyant) carte);
				break;
			case "GuideSpirituel":
				if (joueur.getCampjoueur().getCamp().isEmpty()) {
					System.out.println(nom + " pose un Guide Spirituel" + carte.getNom() + " dans son camp.");
					joueur.getCampjoueur().ajouterCarte((GuideSpirituel) carte);
				}
				break;
			default:
				System.out.println(nom + "joue une carte Deus Ex.");
				((DeusEx) carte).effet();
				break;
			}
		}
		joueur.setcanPlay(false);
	}
}