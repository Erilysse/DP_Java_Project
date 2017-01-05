package cartes;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * GuideSpirituel est une classe qui hérite de Carte. A pour rôle d'amener à un
 * joueur un nombre variable de cartes de Croyants. A les mêmes attributs que la
 * classe Carte. Possède aussi les caractéristiques suivantes :
 * 
 * - un nombre de Croyants Rattachables à la carte, c'est à dire le nombre de
 * cartes Croyants qui peuvent être rattachés.
 * 
 * - un nombre de Croyants Rattachés, qui ne peut excéder la capacité de
 * croyants rattachables.
 * 
 * @see Carte
 * @see Croyant
 * @see Joueur
 * 
 * @author manic
 */
public class GuideSpirituel extends Carte {
	
	/**
	 * Nombre de cartes Croyant pouvant être rattachés à une carte
	 * GuideSpirituel.
	 * 
	 * @see Croyant
	 * @see GuideSpirituel#getNbCroyantsRattachables()
	 * @see GuideSpirituel#setNbCroyantsRattachables(int)
	 */
	private int nbCroyantsRattachables;
	
	/**
	 * Tableaux des cartes Croyants rattachés à la carte GuideSpirituel. Sa
	 * taille est déterminé par le nombre de Croyants rattachables.
	 * 
	 * @see Croyant
	 * @see GuideSpirituel#nbCroyantsRattachables
	 * @see GuideSpirituel#getCroyantsRattaches()
	 * @see GuideSpirituel#setCroyantsRattaches(ArrayList)
	 */
	private ArrayList<Croyant> croyantsRattaches;

	/**
	 * Constructeur GuideSpirituel.
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
	public GuideSpirituel(String type, String nom, String dogme1, String dogme2, String dogme3, String origine,
			String p, ImageIcon imageIcon) {
		super(type, nom, dogme1, dogme2, dogme3, origine, p, imageIcon);
		this.nbCroyantsRattachables = Integer.parseInt(p);
		this.croyantsRattaches = new ArrayList<Croyant>();
	}

	/**
	 * Retourne le nombre de Croyants rattachables.
	 * 
	 * @return le nombre de Croyants rattachables.
	 * 
	 * @see Croyant
	 */
	public int getNbCroyantsRattachables() {
		return nbCroyantsRattachables;
	}

	/**
	 * Met à jour le nombre de croyants rattachables.
	 * 
	 * @param nbCroyantsRattachables
	 *            nouveau nombre de croyants rattachables à la carte.
	 * 
	 * @see Croyant
	 */
	public void setNbCroyantsRattachables(int nbCroyantsRattachables) {
		this.nbCroyantsRattachables = nbCroyantsRattachables;
	}

	/**
	 * Retourne la liste de Croyant rattachés à la carte.
	 * 
	 * @return une ArrayList de Croyant.
	 * 
	 * @see Croyant
	 */
	public ArrayList<Croyant> getCroyantsRattaches() {
		return croyantsRattaches;
	}

	/**
	 * Met à jour la liste des croyants rattachés à la carte.
	 * 
	 * @param croyantsRattaches
	 *            nouvelle liste de croyants rattachés à la carte.
	 * 
	 * @see Croyant
	 */
	public void setCroyantsRattaches(ArrayList<Croyant> croyantsRattaches) {
		this.croyantsRattaches = croyantsRattaches;
	}

	/**
	 * Retourne le nombre de points de prière comptabilisé par tout les croyants
	 * rattachés à la carte. Pour le nombre de Croyants rattachés, on cherche le
	 * nombre de points de prières qu'un croyant donne et on le comptabilise
	 * dans PP.
	 * 
	 * @return un nombre de points de prières.
	 * 
	 * @see Croyant
	 * @see GuideSpirituel#croyantsRattaches
	 */
	public int getPP() {
		int PP = 0;
		for (int i = 0; i < croyantsRattaches.size(); i++) {
			PP += this.croyantsRattaches.get(i).getCaracteristique();
		}
		return PP;
	}

	/**
	 * Affiche la carte GuideSpirituel en utilisant la méthode afficherCarte().
	 * 
	 * @see Carte#afficherCarte()
	 */
	public void afficherGS() {
		super.afficherCarte();
	}

	/**
	 * Affiche les différents croyants rattachés au GuideSpirituel. Affichage
	 * console.
	 * 
	 * @see Croyant
	 * @see GuideSpirituel#croyantsRattaches
	 */
	public void afficherCroyants() {
		if (!this.croyantsRattaches.isEmpty()) {
			System.out.println("Les croyants rattachés de ce guide spirituel sont :");
			for (int i = 0; i < croyantsRattaches.size(); i++) {
				System.out.println("Croyant n° " + i);
				this.croyantsRattaches.get(i).afficherCarte();
			}
		}
	}

	/**
	 * Rattache un Croyant à un Guide Spirituel. L'Ajoute dans la liste des
	 * croyants rattachés si le nombre de Croyant rattachables n'est pas déjà
	 * atteint.
	 * 
	 * @param croyant
	 *            objet de la classe Croyant, celui qui a été choisi par le
	 *            joueur pour être rattaché à son Guide Spirituel.
	 * 
	 * @see Croyant
	 * @see Joueur
	 * @see GuideSpirituel#nbCroyantsRattachables
	 * @see GuideSpirituel#croyantsRattaches
	 */
	public void rattacherCroyants(Croyant croyant) {
		if (croyantsRattaches.size() < nbCroyantsRattachables) {
			this.croyantsRattaches.add(croyant);
		}
	}

	/**
	 * Enlève un Croyant de la liste des Croyants rattachés. Utilisé dans le cas
	 * où un Croyant est sacrifié ou retiré de force.
	 * 
	 * @param index
	 *            numéro de la carte Croyant à enlever dans la liste de croyants
	 *            rattachés.
	 * @return un croyant, celui qu'on a retiré des croyants rattachés au
	 *         GuideSpirituel.
	 * 
	 * @see Croyant
	 * @see GuideSpirituel#croyantsRattaches
	 */
	public Croyant retirerCroyant(int index) {
		return this.croyantsRattaches.remove(index);
	}

	/**
	 * Sacrifie la carte GuideSpirituel et implémente son effet. Vide car non
	 * implémenté à ce jour.
	 */
	public void sacrifice() {
	}
}