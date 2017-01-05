package cartes;

import java.awt.Image;

import javax.swing.ImageIcon;

import plateau_du_jeu.Jeu;

/**
 * Croyant est une classe qui hérite de Carte. La classe a les mêmes attributs.
 * Elle possède aussi un attribut "pointPriere" qui indique le nombre de points
 * de prière que la carte Croyant peut donner au joueur.
 * 
 * @see Carte
 * @see Joueur
 * 
 * @author manic
 */
public class Croyant extends Carte {
	private int pointPriere;

	/**
	 * Constructeur Croyant.
	 * 
	 * Utilise le Constructeur Carte. Met par défaut la valeur des points de
	 * prière à p.
	 * 
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
	 *            nombre de points de prière de la carte.
	 */
	public Croyant(String type, String nom, String dogme1, String dogme2, String dogme3, String origine, String p, ImageIcon imageIcon) {
		super(type, nom, dogme1, dogme2, dogme3, origine, p, imageIcon);
		this.setPointPriere(Integer.parseInt(p));
	}

	/**
	 * Retourne le nombre de points de prière de la carte.
	 * 
	 * @return un nombre de points de prières.
	 */
	public int getPointPriere() {
		return pointPriere;
	}

	/**
	 * Met à jour les points de prière de la carte.
	 * 
	 * @param pointPriere
	 *            nouveau nombre de points de prière de la carte.
	 */
	public void setPointPriere(int pointPriere) {
		this.pointPriere = pointPriere;
	}

	/**
	 * Affiche la carte Croyant. Utilise l'affichage de la Carte avec la méthode
	 * afficherCarte().
	 * 
	 * @see Carte
	 * @see Carte#afficherCarte()
	 */
	public void afficherCroyant() {
		super.afficherCarte();
	}

	/**
	 * Sacrifie la carte Croyant et implémente l'effet de sacrifice, en
	 * "matchant" le nom de la carte avec son effet.
	 * 
	 * @see Sacrifice
	 * @see AjoutPA
	 * @see AjoutPA#sacrifice(int, int, int, jeu)
	 */
	public void sacrifice() {
		switch (super.getNom()) {
		case ("Moines"):
			this.getApa().sacrifice(0, 1, 1, Jeu.getInstance());
			break;
		case ("Démons"):
			this.getApa().sacrifice(1, 1, 1, Jeu.getInstance());
			break;
		case ("Esprits"):
			this.getApa().sacrifice(2, 1, 1, Jeu.getInstance());
			break;
		}
	}
}