package cartes;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * GuideSpirituel est une classe qui h�rite de Carte. A pour r�le d'amener � un
 * joueur un nombre variable de cartes de Croyants. A les m�mes attributs que la
 * classe Carte. Poss�de aussi les caract�ristiques suivantes :
 * 
 * - un nombre de Croyants Rattachables � la carte, c'est � dire le nombre de
 * cartes Croyants qui peuvent �tre rattach�s.
 * 
 * - un nombre de Croyants Rattach�s, qui ne peut exc�der la capacit� de
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
	 * Nombre de cartes Croyant pouvant �tre rattach�s � une carte
	 * GuideSpirituel.
	 * 
	 * @see Croyant
	 * @see GuideSpirituel#getNbCroyantsRattachables()
	 * @see GuideSpirituel#setNbCroyantsRattachables(int)
	 */
	private int nbCroyantsRattachables;
	
	/**
	 * Tableaux des cartes Croyants rattach�s � la carte GuideSpirituel. Sa
	 * taille est d�termin� par le nombre de Croyants rattachables.
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
	 *            son troisi�me dogme, si elle en a.
	 * @param origine
	 *            son origine.
	 * @param p
	 *            sa caract�ristique.
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
	 * Met � jour le nombre de croyants rattachables.
	 * 
	 * @param nbCroyantsRattachables
	 *            nouveau nombre de croyants rattachables � la carte.
	 * 
	 * @see Croyant
	 */
	public void setNbCroyantsRattachables(int nbCroyantsRattachables) {
		this.nbCroyantsRattachables = nbCroyantsRattachables;
	}

	/**
	 * Retourne la liste de Croyant rattach�s � la carte.
	 * 
	 * @return une ArrayList de Croyant.
	 * 
	 * @see Croyant
	 */
	public ArrayList<Croyant> getCroyantsRattaches() {
		return croyantsRattaches;
	}

	/**
	 * Met � jour la liste des croyants rattach�s � la carte.
	 * 
	 * @param croyantsRattaches
	 *            nouvelle liste de croyants rattach�s � la carte.
	 * 
	 * @see Croyant
	 */
	public void setCroyantsRattaches(ArrayList<Croyant> croyantsRattaches) {
		this.croyantsRattaches = croyantsRattaches;
	}

	/**
	 * Retourne le nombre de points de pri�re comptabilis� par tout les croyants
	 * rattach�s � la carte. Pour le nombre de Croyants rattach�s, on cherche le
	 * nombre de points de pri�res qu'un croyant donne et on le comptabilise
	 * dans PP.
	 * 
	 * @return un nombre de points de pri�res.
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
	 * Affiche la carte GuideSpirituel en utilisant la m�thode afficherCarte().
	 * 
	 * @see Carte#afficherCarte()
	 */
	public void afficherGS() {
		super.afficherCarte();
	}

	/**
	 * Affiche les diff�rents croyants rattach�s au GuideSpirituel. Affichage
	 * console.
	 * 
	 * @see Croyant
	 * @see GuideSpirituel#croyantsRattaches
	 */
	public void afficherCroyants() {
		if (!this.croyantsRattaches.isEmpty()) {
			System.out.println("Les croyants rattach�s de ce guide spirituel sont :");
			for (int i = 0; i < croyantsRattaches.size(); i++) {
				System.out.println("Croyant n� " + i);
				this.croyantsRattaches.get(i).afficherCarte();
			}
		}
	}

	/**
	 * Rattache un Croyant � un Guide Spirituel. L'Ajoute dans la liste des
	 * croyants rattach�s si le nombre de Croyant rattachables n'est pas d�j�
	 * atteint.
	 * 
	 * @param croyant
	 *            objet de la classe Croyant, celui qui a �t� choisi par le
	 *            joueur pour �tre rattach� � son Guide Spirituel.
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
	 * Enl�ve un Croyant de la liste des Croyants rattach�s. Utilis� dans le cas
	 * o� un Croyant est sacrifi� ou retir� de force.
	 * 
	 * @param index
	 *            num�ro de la carte Croyant � enlever dans la liste de croyants
	 *            rattach�s.
	 * @return un croyant, celui qu'on a retir� des croyants rattach�s au
	 *         GuideSpirituel.
	 * 
	 * @see Croyant
	 * @see GuideSpirituel#croyantsRattaches
	 */
	public Croyant retirerCroyant(int index) {
		return this.croyantsRattaches.remove(index);
	}

	/**
	 * Sacrifie la carte GuideSpirituel et impl�mente son effet. Vide car non
	 * impl�ment� � ce jour.
	 */
	public void sacrifice() {
	}
}