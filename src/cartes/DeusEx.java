package cartes;

/**
 * DeusEx est une classe h�ritant de Carte. Elle sert � modifier les rapports de
 * force en cours de partie.
 * 
 * @author manic
 */
public class DeusEx extends Carte {

	/**
	 * Constructeur DeusEx.
	 * 
	 * Utilise le Constructeur Carte.
	 * 
	 * @param type
	 *            le type de la carte.
	 * @param nom
	 *            le nom de la carte.
	 * @param dogme1
	 *            le premier dogme de la carte, si elle en a.
	 * @param dogme2
	 *            le second dogme de la carte, si elle en a.
	 * @param dogme3
	 *            le troisi�me dogme de la carte, si elle en a.
	 * @param origine
	 *            l'origine de la carte sous forme de nombre.
	 * @param p
	 *            la caract�ristique de la carte.
	 * 
	 * @see Carte
	 */
	public DeusEx(String type, String nom, String dogme1, String dogme2, String dogme3, String origine, String p) {
		super(type, nom, dogme1, dogme2, dogme3, origine, p);
	}

	/**
	 * Sacrifie la carte et impl�mente son effet. Vide car non impl�ment� � ce
	 * jour.
	 */
	public void effet() {
	}
}