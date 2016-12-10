package Strategie;

import divinae.Carte;
import divinae.Jeu;
import divinae.Joueur;

public class OperationPiocher implements Strategy {

	@Override
	public void faire(Jeu jeu, Joueur joueur) {
		while (joueur.getMainjoueur().getNbCarte() < 7) {
			Carte carte = joueur.getPioche().piocher();
			joueur.getMainjoueur().ajouterCarte(carte);
		}
	}

}
