package moteur_graphique;

import java.awt.Color;
import javax.swing.JPanel;

/**
 * <b>Fond est la classe personnalisée d'un JPanel.</b>
 */
public class Fond extends JPanel {
	/**
	 * Constante permettant d'avoir une couleur vert tapis
	 */
	public static final Color couleurFond = new Color(253, 241, 184);
	/**
	 * Constructeur du PanneauVert
	 */
	public Fond() {
		super();
		this.setBackground(couleurFond);
	}
}