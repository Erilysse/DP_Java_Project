package contrôleur;

import java.util.Scanner;

import plateau_du_jeu.Jeu;

/**
 * Control est une classe qui simule le contrôleur dans le design pattern MVC
 * (Modele View Controler).
 * 
 * @author manic
 */
public class Control {

	/**
	 * Objet de la classe Scanner qui gère les entrées claviers.
	 * 
	 * @see Control#getSc()
	 * @see Control#setSc(Scanner)
	 */
	private Scanner sc;

	/**
	 * Nombre.
	 * 
	 * @see Control#getAnswer()
	 * @see Control#setAnswer(int)
	 */
	public int answer;

	/**
	 * Objet de la classe Control, qui permet l'unique instanciation de la
	 * classe grâce au design pattern singleton.
	 * 
	 * @see Control#getInstance()
	 * @see Control#setInstance(Control)
	 */
	private static Control instance = null;

	/**
	 * Constructeur Control. Création d'un objet de la classe Scanner et
	 * initialisation à 1 pour l'attribut <code>answer</code>.
	 */
	public Control() {
		sc = new Scanner(System.in);
		answer = 1;
	}

	/**
	 * Retourne un objet de la classe Scanner.
	 * 
	 * @return un objet de la classe Scanner.
	 * 
	 * @see Scanner
	 */
	public Scanner getSc() {
		return sc;
	}

	/**
	 * Met à jour l'objet de la classe Scanner.
	 * 
	 * @param sc
	 *            un nouvel objet de la classe Scanner.
	 * 
	 * @see Scanner
	 */
	public void setSc(Scanner sc) {
		this.sc = sc;
	}

	/**
	 * Retourne le nombre <code>answer</code>.
	 * 
	 * @return un nombre.
	 */
	public int getAnswer() {
		return answer;
	}

	/**
	 * Met à jour le nombre <code>answer</code>.
	 * 
	 * @param answer
	 *            le nouveau nombre qui va remplacer l'actuel.
	 */
	public void setAnswer(int answer) {
		this.answer = answer;
	}

	/**
	 * Retourne l'instance de la classe Control grâce au design pattern
	 * singleton.
	 * 
	 * @return un objet de la classe Control.
	 */
	public static Control getInstance() {
		if (instance == null) {
			instance = new Control();
		}
		return instance;
	}

	/**
	 * Met à jour l'instance de la classe Control.
	 * 
	 * @param instance
	 *            un nouvel objet de la classe Control.
	 */
	public static void setInstance(Control instance) {
		Control.instance = instance;
	}

	public void repInt() {
		if (Jeu.vue == false) {
			setAnswer(sc.nextInt());
			System.out.println(answer);
		}
	}
}