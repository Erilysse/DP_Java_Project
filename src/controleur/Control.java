package controleur;

import java.util.Scanner;

import plateau_du_jeu.Jeu;

/**
 * Control est une classe qui permets de désigner le contrôleur dans le design
 * pattern MVC. Il permets notamment d'adapter les entrées claviers si le jeu
 * est en mode console ou graphique.
 * 
 * @see Jeu
 * 
 * @author manic
 *
 */
public class Control {
	/**
	 * Objet de la classe scanner pour gérer les entrées claviers.
	 * 
	 * @see Control#getSc()
	 * @see Control#setSc(Scanner)
	 */
	private Scanner sc;
	/**
	 * nombre correspondant à la réponse de l'utilisateur, utile pour le Jeu.
	 * 
	 * @see Jeu
	 * @see Control#getAnswer()
	 * @see Control#setAnswer(int)
	 */
	public int answer;
	/**
	 * Instance de la classe Control grâce au design pattern singleton.
	 * 
	 * @see Control#getInstance()
	 */
	private static Control instance = null;

	/**
	 * Constructeur Control. Crée un nouveau scanner et initialise la variable
	 * answer à 1.
	 */
	public Control() {
		this.sc = new Scanner(System.in);
		this.answer = 1;
	}

	/**
	 * Retourne le scanner.
	 * 
	 * @return un objet de la classe scanner.
	 * 
	 * @see Scanner
	 */
	public Scanner getSc() {
		return sc;
	}

	/**
	 * Met à jour le scanner.
	 * 
	 * @param sc
	 *            un objet de la classe Scanner.
	 * 
	 * @see Scanner
	 */
	public void setSc(Scanner sc) {
		this.sc = sc;
	}

	/**
	 * Retourne le nombre correspondant à la réponse de l'utilisateur.
	 * 
	 * @return un nombre.
	 */
	public int getAnswer() {
		return answer;
	}

	/**
	 * Met à jour le nombre correspondant à la réponse de l'utilisateur.
	 * 
	 * @param answer
	 *            un nombre.
	 */
	public void setAnswer(int answer) {
		this.answer = answer;
	}

	/**
	 * Retourne l'instance de la classe Control.
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
	 * Vérifie le booléen <code>vue</code> de Jeu. S'il est à false, cela veut
	 * dire qu'on est en mode console et que le joueur va entrer au clavier une
	 * réponse. On retourne donc la variable <code>answer</code> après l'avoir
	 * mit à jour grâce au scanner. S'il est à true, on retourne simplement
	 * <code>answer</code> sans faire de changement.
	 * 
	 * @return
	 */
	public int repInt() {
		Jeu.getInstance();
		if (Jeu.isVue() == false) {
			setAnswer(sc.nextInt());
			return answer;
		}
		return answer;
	}
}