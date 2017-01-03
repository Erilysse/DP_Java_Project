package cartes;

/**
 * Divinit� est une classe qui h�rite de Carte. Elle incarne un joueur. Elle
 * poss�de une capacit� sp�ciale, utilisable une seule fois en cours de Jeu.
 * 
 * @see Jeu
 * @see Joueur
 * @see Carte
 * 
 * @author manic
 */
public class Divinite extends Carte {

	/**
	 * Bool�en indiquant si la capacit� sp�ciale de la Divinit� a �t� utilis� ou
	 * non.
	 * 
	 * @see Divinite#isaUtiliseSaCapacite()
	 * @see Divinite#setaUtiliseSaCapacite(boolean)
	 */
	private boolean aUtiliseSaCapacite = false;

	/**
	 * Constructeur Divinit�.
	 * 
	 * Utilise le Constructeur Carte.
	 * 
	 * @param type
	 *            le type de la carte.
	 * @param nom
	 *            le nom de la carte.
	 * @param dogme1
	 *            son premier dogme, si elle en a.
	 * @param dogme2
	 *            son second dogme, si elle en a.
	 * @param dogme3
	 *            son troisi�me dogme, si elle en a.
	 * @param origine
	 *            son origine.
	 * @param p
	 *            sa caract�ristique.
	 */
	public Divinite(String type, String nom, String dogme1, String dogme2, String dogme3, String origine, String p) {
		super(type, nom, dogme1, dogme2, dogme3, origine, p);
		this.aUtiliseSaCapacite = false;
	}

	/**
	 * Retourne le bool�en permettant de savoir si la capacit� de la Divinit� a
	 * �t� utilis� ou non.
	 * 
	 * @return un bool�en.
	 */
	public boolean isaUtiliseSaCapacite() {
		return aUtiliseSaCapacite;
	}

	/**
	 * Met � jour le bool�en indiquant si la capacit� de la Divinit� a �t�
	 * utilis� ou non.
	 * 
	 * @param aUtiliseSaCapacite
	 *            nouveau bool�en.
	 */
	public void setaUtiliseSaCapacite(boolean aUtiliseSaCapacite) {
		this.aUtiliseSaCapacite = aUtiliseSaCapacite;
	}

	/**
	 * Affiche la Divinit� en utilisant la m�thode afficherCarte().
	 * 
	 * @see Carte#afficherCarte()
	 */
	public void afficherDivinite() {
		super.afficherCarte();
	}

	/**
	 * Affiche la Divinit�, non plus comme une carte mais comme une phrase o�
	 * seul son nom, son origine et ses dogmes sont indiqu�s.
	 * 
	 */
	public void afficherDivinitebis() {
		System.out.println("Divinit� " + getNom() + ", est d'origine " + afficherOrigine() + " et a pour dogmes : ");
		for (int i = 0; i < this.getDogme().length; i++) {
			System.out.println(this.afficherDogme(i));
		}
	}

	/**
	 * Utilise la capacit� sp�ciale de la Divinit�. Utilisable une seule fois.
	 * On doit "matcher" les noms des Divinit�s avec leur capacit�.
	 */
	public void utiliserCapacite() {
		if (this.aUtiliseSaCapacite = false) {
			System.out.println(
					"La capacit� de la divivinit� n'a aucun effet. L'impl�mentation n'a pas �t� faite. Sorry !");
		} else {
			System.out.println("Le joueur a d�j� utilis� sa capacit� de Divinit�. ");
		}
		this.aUtiliseSaCapacite = true;
	}
}