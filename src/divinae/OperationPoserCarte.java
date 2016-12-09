package divinae;

public class OperationPoserCarte implements Strategy {

	public void faire(Jeu jeu, Joueur joueur) {
		Carte carte = joueur.getMainjoueur().getMain().get((int)(7*Math.random()));
		String nom = joueur.getDiviniteRepresentee().getNom();
			switch (carte.getType()) {
				case "Apocalypse":
					if (joueur.getNbrPrieres() == jeu.getJoueurMaxPP())
					{
						System.out.println(nom + "joue une carte Apocalypse");
						((Apocalypse)carte).effet();
					}
					break;
				case "Croyant":
					System.out.println(nom + "pose un Croyant dans le centre.");
					joueur.getCentre().ajouterCroyant((Croyant)carte);
					break;
				case "GuideSpirituel":
					System.out.println(nom + "pose un Guide Spirituel dans son camp.");
					joueur.getCampjoueur().ajouterCarte((GuideSpirituel)carte);
					break;
				default:
					System.out.println(nom + "joue une carte Deus Ex.");
					break;
			}
	}
}
