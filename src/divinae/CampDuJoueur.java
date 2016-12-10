package divinae;

import java.util.ArrayList;

public class CampDuJoueur {
	private ArrayList<GuideSpirituel> camp;
	private int nbCarte;

	public ArrayList<GuideSpirituel> getCamp() {
		return camp;
	}

	public void setCamp(ArrayList<GuideSpirituel> camp) {
		this.camp = camp;
	}

	public int getNbCarte() {
		return nbCarte;
	}

	public void setNbCarte(int nbCarte) {
		this.nbCarte = nbCarte;
	}

	public CampDuJoueur() {
		this.camp = new ArrayList<GuideSpirituel>();
		this.nbCarte = this.camp.size();
	}

	public void ajouterCarte(GuideSpirituel carte) {
		this.getCamp().add(carte);
		this.nbCarte = this.camp.size();
	}

	public void afficherCamp() {
		System.out.println("Composition du camp du joueur : ");
		if (this.camp.isEmpty()) {
			System.out.println("Le camp est vide. \n");
		}
		else {
			for (int i = 0; i < nbCarte; i++) {
				System.out.println("Carte n°"+i+" :");
				this.camp.get(i).afficherCarte();
				this.camp.get(i).afficherCroyants();
			}
		}
	}

}
