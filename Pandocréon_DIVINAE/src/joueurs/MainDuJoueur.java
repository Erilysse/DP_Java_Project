package joueurs;

import java.util.ArrayList;
import java.util.Observable;

import cartes.Carte;

/**
 * MainDuJoueur est une classe qui définit le deck du Joueur. Elle possède une
 * liste de cartes et un nombre de cartes. Elle implémente l'interface Observable.
 * 
 * @see Observable
 * @see Joueur
 * @see Carte
 * 
 * @author manic
 */
public class MainDuJoueur extends Observable {

	/**
	 * L'ensemble des cartes que possèdent le joueur. Il y a une méthode
	 * d'affichage. Il est aussi possible d'ajouter et de retirer une carte de
	 * cette collection.
	 * 
	 * @see MainDuJoueur#getMain()
	 * @see MainDuJoueur#setMain(ArrayList)
	 * @see MainDuJoueur#ajouterCarte(Carte)
	 * @see MainDuJoueur#defausserCarte(int)
	 * @see MainDuJoueur#afficherMain()
	 */
	private ArrayList<Carte> main;

	/**
	 * Nombre de cartes que possèdent le joueur, et donc le nombre d'élément de
	 * la main.
	 * 
	 * @see MainDuJoueur#getNbCarte()
	 * @see MainDuJoueur#setNbCarte(int)
	 */
	private int nbCarte;

	/**
	 * Constructeur MainDuJoueur. Par défaut, la main est une nouvelle
	 * collection, et le nombre de cartes est la taille de cette collection.
	 */
	public MainDuJoueur() {
		this.main = new ArrayList<Carte>();
		this.nbCarte = main.size();
	}

	/**
	 * Retourne l'ensemble des cartes que possèdent le joueur.
	 * 
	 * @return une liste de cartes.
	 */
	public ArrayList<Carte> getMain() {
		return main;
	}

	/**
	 * Met à jour l'ensemble des cartes que possèdent le joueur.
	 * 
	 * @param main
	 *            nouveau deck du joueur.
	 */
	public void setMain(ArrayList<Carte> main) {
		this.main = main;
	}

	/**
	 * Retourne le nombre de cartes de la collection.
	 * 
	 * @return un nombre.
	 */
	public int getNbCarte() {
		return nbCarte;
	}

	/**
	 * Met à jour le nombre de cartes de la collection.
	 * 
	 * @param nbCarte
	 *            le nouveau nombre de cartes.
	 */
	public void setNbCarte(int nbCarte) {
		this.nbCarte = nbCarte;
	}

	/**
	 * Ajoute une carte à la collection, et change alors le nombre de cartes.
	 * 
	 * @param carte
	 *            la carte qui va être ajouté a la collection.
	 * 
	 * @see MainDuJoueur#main
	 * @see Carte
	 */
	public void ajouterCarte(Carte carte) {
		this.main.add(carte);
		this.nbCarte = this.main.size();
		setChanged();
		notifyObservers("pioche");
	}
	/**
	 * Retire une carte de la collection, et change alors le nombre de cartes.
	 * 
	 * @param i
	 *            numéro de la carte dans sa liste.
	 * @return la carte qui est retiré.
	 * 
	 * @see Carte
	 */
	public Carte defausserCarte(int i) {
		Carte c = this.main.remove(i);
		this.nbCarte = this.main.size();
		setChanged();
		notifyObservers("defausse");	
		return c;
	}

	/**
	 * Affiche l'ensemble des cartes que possèdent le joueur.
	 * 
	 * @see Carte
	 */
	public void afficherMain() {
		System.out.println("Composition de la main :\n");
		for (int i = 0; i < this.nbCarte; i++) {
			System.out.println("Carte n°" + i + " :");
			this.main.get(i).afficherCarte();
		}
	}
}