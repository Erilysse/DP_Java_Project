package plateau_du_jeu;

import java.util.ArrayList;

import cartes.GuideSpirituel;

/**
 * CampDuJoueur est une classe qui a possède les attributs suivants : -
 * <code>camp</code>, une ArrayList d'objets de la classe GuideSpirituel.
 * L'ensemble des cartes qu'a posé le joueur. - <code>nbCarte</code>, le nombre
 * de cartes que possède le <code>camp</code>.
 * 
 * @see GuideSpirituel
 * @see Carte
 * @see Joueur
 * 
 * @author manic
 */
public class CampDuJoueur {
	/**
	 * Liste d'objets de la classe <code>GuideSpirituel</code> qui représentent
	 * l'ensemble des Guides Spirituels posés par le joueur, ainsi par extension
	 * leurs Croyants rattachés. Il est possible d'afficher la liste, ainsi que
	 * d'ajouter et de retirer un Guide Spirituel de cette liste.
	 * 
	 * @see GuideSpirituel
	 * @see Croyant
	 * @see Carte
	 * @see Joueur
	 * @see CampDuJoueur#getCamp()
	 * @see CampDuJoueur#setCamp(ArrayList)
	 * @see CampDuJoueur#afficherCamp()
	 * @see CampDuJoueur#ajouterCarte(GuideSpirituel)
	 * @see CampDuJoueur#retirerGS(int)
	 */
	private ArrayList<GuideSpirituel> camp;
	/**
	 * Nombre de cartes que possèdent le <code>camp</code>.
	 * 
	 * @see CampDuJoueur#getNbCarte()
	 * @see CampDuJoueur#setNbCarte(int)
	 */
	private int nbCarte;

	/**
	 * Constructeur CampDuJoueur. Par défaut, il créé une liste de type
	 * ArrayList pour le <code>camp</code> et initialise le <code>nbCarte</code>
	 * à la taille du <code>camp</code>.
	 */
	public CampDuJoueur() {
		this.camp = new ArrayList<GuideSpirituel>();
		this.nbCarte = this.camp.size();
	}

	/**
	 * Retourne la liste des guides spirituels.
	 * 
	 * @return une collection d'objets de la classe GuideSpirituel.
	 */
	public ArrayList<GuideSpirituel> getCamp() {
		return camp;
	}

	/**
	 * Met à jour la liste des guides spirituels.
	 * 
	 * @param camp
	 *            la nouvelle liste d'objets de la classe GuideSpirituel
	 */
	public void setCamp(ArrayList<GuideSpirituel> camp) {
		this.camp = camp;
	}

	/**
	 * Retourne le nombre de cartes.
	 * 
	 * @return un nombre.
	 */
	public int getNbCarte() {
		return nbCarte;
	}

	/**
	 * Met à jour le nombre de cartes.
	 * 
	 * @param nbCarte
	 *            le nouveau nombre de cartes.
	 */
	public void setNbCarte(int nbCarte) {
		this.nbCarte = nbCarte;
	}

	/**
	 * Affiche le <code>camp</code>, la composition du camp du joueur. S'il est
	 * vide, le notifie.
	 */
	public void afficherCamp() {
		System.out.println("Composition du camp du joueur : ");
		if (this.camp.isEmpty()) {
			System.out.println("Le camp est vide. \n");
		} else {
			for (int i = 0; i < nbCarte; i++) {
				System.out.println("Carte n°" + i + " :");
				this.camp.get(i).afficherCarte();
				this.camp.get(i).afficherCroyants();
			}
		}
	}

	/**
	 * Ajoute une carte (un Guide Spirituel) au <code>camp</code>.
	 * 
	 * @param carte
	 *            un Guide Spirituel.
	 * 
	 * @see GuideSpirituel
	 */
	public void ajouterCarte(GuideSpirituel carte) {
		this.getCamp().add(carte);
		this.nbCarte = this.camp.size();
	}

	/**
	 * Retire une carte (un Guide Spirituel) du <code>camp</code>.
	 * 
	 * @param index
	 *            le numéro du Guide Spirituel dans la liste.
	 * 
	 * @see GuideSpirituel
	 */
	public void retirerGS(int index) {
		for (int i = 0; i < this.camp.get(index).getCroyantsRattaches().size(); i++) {
			Jeu.getInstance().getCentre().ajouterCroyant(this.camp.get(index).retirerCroyant(i));
		}
	}
}