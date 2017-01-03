package cartes;

/**
 * Divinité est une classe qui hérite de Carte. Elle incarne un joueur. Elle
 * possède une capacité spéciale, utilisable une seule fois en cours de Jeu.
 * 
 * @see Jeu
 * @see Joueur
 * @see Carte
 * 
 * @author manic
 */
public class Divinite extends Carte {

	/**
	 * Booléen indiquant si la capacité spéciale de la Divinité a été utilisé ou
	 * non.
	 * 
	 * @see Divinite#isaUtiliseSaCapacite()
	 * @see Divinite#setaUtiliseSaCapacite(boolean)
	 */
	private boolean aUtiliseSaCapacite = false;

	/**
	 * Constructeur Divinité.
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
	 *            son troisième dogme, si elle en a.
	 * @param origine
	 *            son origine.
	 * @param p
	 *            sa caractéristique.
	 */
	public Divinite(String type, String nom, String dogme1, String dogme2, String dogme3, String origine, String p) {
		super(type, nom, dogme1, dogme2, dogme3, origine, p);
		this.aUtiliseSaCapacite = false;
	}

	/**
	 * Retourne le booléen permettant de savoir si la capacité de la Divinité a
	 * été utilisé ou non.
	 * 
	 * @return un booléen.
	 */
	public boolean isaUtiliseSaCapacite() {
		return aUtiliseSaCapacite;
	}

	/**
	 * Met à jour le booléen indiquant si la capacité de la Divinité a été
	 * utilisé ou non.
	 * 
	 * @param aUtiliseSaCapacite
	 *            nouveau booléen.
	 */
	public void setaUtiliseSaCapacite(boolean aUtiliseSaCapacite) {
		this.aUtiliseSaCapacite = aUtiliseSaCapacite;
	}

	/**
	 * Affiche la Divinité en utilisant la méthode afficherCarte().
	 * 
	 * @see Carte#afficherCarte()
	 */
	public void afficherDivinite() {
		super.afficherCarte();
	}

	/**
	 * Affiche la Divinité, non plus comme une carte mais comme une phrase où
	 * seul son nom, son origine et ses dogmes sont indiqués.
	 * 
	 */
	public void afficherDivinitebis() {
		System.out.println("Divinité " + getNom() + ", est d'origine " + afficherOrigine() + " et a pour dogmes : ");
		for (int i = 0; i < this.getDogme().length; i++) {
			System.out.println(this.afficherDogme(i));
		}
	}

	/**
	 * Utilise la capacité spéciale de la Divinité. Utilisable une seule fois.
	 * On doit "matcher" les noms des Divinités avec leur capacité.
	 */
	public void utiliserCapacite() {
		if (this.aUtiliseSaCapacite = false) {
			System.out.println(
					"La capacité de la divivinité n'a aucun effet. L'implémentation n'a pas été faite. Sorry !");
		} else {
			System.out.println("Le joueur a déjà utilisé sa capacité de Divinité. ");
		}
		this.aUtiliseSaCapacite = true;
	}
}