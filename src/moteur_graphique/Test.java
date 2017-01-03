package moteur_graphique;

import javax.swing.JLabel;

/**
 * Test est une classe qui hérite de la classe Fenetre.
 * 
 * @see Fenetre
 * 
 * @author manic
 */
public class Test extends Fenetre{
	
	/**
	 * Objet de la classe AffichageJoueur.
	 */
	private AffichageJoueur AF;
	
	/**
	 * Constructeur Test.
	 */
	public Test(){
		JLabel bite = new JLabel();
		AF = new AffichageJoueur();
		bite.add(AF);
		setContentPane(AF);
	}
}