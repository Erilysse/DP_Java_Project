package strategie_IA;

import cartes.Apocalypse;
import cartes.Carte;
import cartes.Croyant;
import cartes.GuideSpirituel;
import joueurs.Joueur;
import plateau_du_jeu.Jeu;

public class OperationPoserCarte implements Strategy {

	public void faire(Jeu jeu, Joueur joueur) {
		int index = (int)((joueur.getMainjoueur().getNbCarte()-1)*Math.random());
		Carte carte = joueur.getMainjoueur().getMain().get(index);
		String nom = joueur.getDiviniteRepresentee().getNom();
		joueur.verifierConsommerPA(index);
		if(joueur.iscanPlay() ==true){
			switch (carte.getType()) {
				case "Apocalypse":
					if (joueur.getNbrPrieres() == jeu.getJoueurMaxPP())
					{
						System.out.println(nom + " joue une carte Apocalypse");
						((Apocalypse)carte).effet();
					}
					break;
				case "Croyant":
					joueur.verifierConsommerPA(index);
					System.out.println(nom + " pose un Croyant " + carte.getNom() + " dans le centre.");
						joueur.getCentre().ajouterCroyant((Croyant)carte);
					break;
				case "GuideSpirituel":
					if (joueur.getCampjoueur().getCamp().isEmpty()) {
					System.out.println(nom + " pose un Guide Spirituel" + carte.getNom() + " dans son camp.");
					joueur.getCampjoueur().ajouterCarte((GuideSpirituel)carte);
					}
					break;
				default:
					System.out.println(nom + "joue une carte Deus Ex.");
					break;
			}
		}
		joueur.setcanPlay(false);
	}
}
