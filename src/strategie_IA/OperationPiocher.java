package strategie_IA;

import cartes.Carte;
import joueurs.Joueur;
import plateau_du_jeu.Jeu;

public class OperationPiocher implements Strategy {

	@Override
	public void faire(Jeu jeu, Joueur joueur) {
		while (joueur.getMainjoueur().getNbCarte() < 7) {
			Carte carte = joueur.getPioche().piocher();
			joueur.getMainjoueur().ajouterCarte(carte);
		}
	}

}
