package cartes;

/**
 * DeusEx est une classe héritant de Carte. Elle sert à modifier les rapports de
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
	 *            le troisième dogme de la carte, si elle en a.
	 * @param origine
	 *            l'origine de la carte sous forme de nombre.
	 * @param p
	 *            la caractéristique de la carte.
	 * 
	 * @see Carte
	 */
	public DeusEx(String type, String nom, String dogme1, String dogme2, String dogme3, String origine, String p) {
		super(type, nom, dogme1, dogme2, dogme3, origine, p);
	}

	/**
	 * Sacrifie la carte et implémente son effet. Vide car non implémenté à ce
	 * jour.
	 */
	public void effet() {
	}
}