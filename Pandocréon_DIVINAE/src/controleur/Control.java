package controleur;

import java.util.Scanner;

import plateau_du_jeu.Jeu;

/**
 * Control est une classe qui permets de d�signer le contr�leur dans le design
 * pattern MVC. Il permets notamment d'adapter les entr�es claviers si le jeu
 * est en mode console ou graphique.
 * 
 * @see Jeu
 * 
 * @author manic
 *
 */
public class Control {
	/**
	 * Objet de la classe scanner pour g�rer les entr�es claviers.
	 * 
	 * @see Control#getSc()
	 * @see Control#setSc(Scanner)
	 */
	private Scanner sc;
	/**
	 * nombre correspondant � la r�ponse de l'utilisateur, utile pour le Jeu.
	 * 
	 * @see Jeu
	 * @see Control#getAnswer()
	 * @see Control#setAnswer(int)
	 */
	public int answer;
	/**
	 * Instance de la classe Control gr�ce au design pattern singleton.
	 * 
	 * @see Control#getInstance()
	 */
	private static Control instance = null;

	/**
	 * Constructeur Control. Cr�e un nouveau scanner et initialise la variable
	 * answer � 1.
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
	 * Met � jour le scanner.
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
	 * Retourne le nombre correspondant � la r�ponse de l'utilisateur.
	 * 
	 * @return un nombre.
	 */
	public int getAnswer() {
		return answer;
	}

	/**
	 * Met � jour le nombre correspondant � la r�ponse de l'utilisateur.
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
	 * V�rifie le bool�en <code>vue</code> de Jeu. S'il est � false, cela veut
	 * dire qu'on est en mode console et que le joueur va entrer au clavier une
	 * r�ponse. On retourne donc la variable <code>answer</code> apr�s l'avoir
	 * mit � jour gr�ce au scanner. S'il est � true, on retourne simplement
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