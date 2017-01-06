package divinae;

import java.util.Scanner;

import moteurgraphique.Fenetre;
import plateau_du_jeu.Jeu;

/**
 * La classe Main est une classe permettant de lancer l'application java associ�
 * au jeu Divinae.
 * 
 * @author manic
 */
public class Main {

	/**
	 * Constructeur Main. Vide.
	 */
	public Main() {
	}

	/**
	 * Cr�e un objet de la classe Jeu.
	 * 
	 * Permets au joueur de choisir s'il veut jouer en interface graphique ou en
	 * console. L'utilisateur choisit le nombre de joueurs gr�ce � la m�thode
	 * choisirJoueurs(). Lance la partie jusqu'� ce qu'il y a un vainqueur.
	 * 
	 * @see Jeu
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		Jeu partie = Jeu.getInstance();
		Scanner sc = new Scanner(System.in);
		System.out.println("Veux-tu jouer en console ou en interface graphique ? Console : 1    Graphique : 0");
		if (sc.nextInt() == 1) {
			partie.choisirJoueurs();
			partie.jouerPartie();
		} else {
			Jeu.setVue(true);
			Fenetre.main(args);
		}
	}
}