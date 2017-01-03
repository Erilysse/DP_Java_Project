package plateau_du_jeu;

import java.util.ArrayList;
import java.util.Observable;

import cartes.Croyant;

/**
 * Centre est une classe qui modélise un morceau du plateau du jeu, là où sont
 * posés les cartes Croyant. Les joueurs interragissent avec.
 * 
 * @see Croyant
 * @see Jeu
 * 
 * @author manic
 */
public class Centre extends Observable {
	
	/**
	 * Liste d'objets de la classe Croyant. Il y a une méthode d'affichage, et
	 * il est aussi possible d'ajouter et de retirer une carte Croyant de la
	 * collection.
	 * 
	 * @see Croyant
	 * @see Centre#getCentre()
	 * @see Centre#setCentre(ArrayList)
	 * @see Centre#afficherCentre()
	 * @see Centre#ajouterCroyant(Croyant)
	 * @see Centre#donnerCroyant(int)
	 */
	private ArrayList<Croyant> centre;
	
	/**
	 * Nombre de Cartes dans la liste <code>centre</code>.
	 * 
	 * @see Carte
	 * @see Centre#getNbCarte()
	 * @see Centre#setNbCarte(int)
	 */
	private int nbCarte;
	
	/**
	 * Instance de la classe Centre, initialement vide. Vient du design pattern
	 * singleton afin qu'il n'y ai qu'un seul objet de la classe Centre.
	 * 
	 * @see Centre#getInstance()
	 */
	private static Centre instance = null;

	/**
	 * Constructeur Centre. Par défaut, il créé une liste d'objets de la classe
	 * Croyant pour le <code>centre</code>, et attribut la taille de ce dernier
	 * à <code>nbCarte</code>.
	 */
	public Centre() {
		this.centre = new ArrayList<Croyant>();
		this.nbCarte = this.centre.size();
	}

	/**
	 * Retourne la liste des cartes Croyants du centre.
	 * 
	 * @return une liste d'objets de la classe Croyant.
	 * 
	 * @see Croyant
	 */
	public ArrayList<Croyant> getCentre() {
		return centre;
	}

	/**
	 * Met à jour la liste des cartes Croyant.
	 * 
	 * @param centre
	 *            une nouvelle liste d'objets de la classe Croyant.
	 * 
	 * @see Croyant
	 */
	public void setCentre(ArrayList<Croyant> centre) {
		this.centre = centre;
	}

	/**
	 * Retourne le nombre de cartes du <code>centre</code>;
	 * 
	 * @return un nombre.
	 */
	public int getNbCarte() {
		return nbCarte;
	}

	/**
	 * Met à jour le nombre de cartes du <code>centre</code>.
	 * 
	 * @param nbCarte
	 *            un nombre.
	 */
	public void setNbCarte(int nbCarte) {
		this.nbCarte = nbCarte;
	}

	/**
	 * C'est le design pattern singleton. Il s'assure de n'avoir qu'une instance
	 * de Centre.
	 * 
	 * @return un Centre.
	 */
	public static Centre getInstance() {
		if (instance == null) {
			instance = new Centre();
		}
		return instance;
	}

	/**
	 * Affiche les cartes Croyant qui composent le <code>centre</code>. S'il n'y
	 * a pas de cartes, affiche que le <code>centre</code> est vide.
	 * 
	 * @see Croyant
	 */
	public void afficherCentre() {
		System.out.println("Composition du centre :\n");
		for (int i = 0; i < this.nbCarte; i++) {
			System.out.println("Carte n°" + i + " :");
			this.centre.get(i).afficherCarte();
		}
		if (this.getCentre().isEmpty()) {
			System.out.println("Le centre est vide.");
		}
	}

	/**
	 * Retire une carte Croyant du <code>centre</code>.
	 * 
	 * @param i
	 *            index, numéro de la carte Croyant qui veut être retiré.
	 * @return un objet de la classe Croyant.
	 * 
	 * @see Croyant
	 */
	public Croyant donnerCroyant(int i) {
		Croyant croyant = this.centre.remove(i);
		setChanged();
		notifyObservers();
		return croyant;
	}

	/**
	 * Ajoute une carte Croyant au <code>centre</code> et ajuste
	 * <code>nbCarte</code>.
	 * 
	 * @param croyant
	 *            objet de la classe Croyant qui veut être ajouté.
	 */
	public void ajouterCroyant(Croyant croyant){
		this.centre.add(croyant);
		setChanged();
		notifyObservers();
		this.nbCarte = this.centre.size();
	}
}