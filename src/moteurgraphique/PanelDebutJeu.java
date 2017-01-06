package moteurgraphique;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

public class PanelDebutJeu {

	private JButton j1 = new JButton("Lancer une partie");
	private JButton j2 = new JButton("Règles du jeu");
	private JLabel lab = new JLabel("Bienvenue sur le jeu Divinae Pandocréon", JLabel.CENTER);
	private Fenetre fenetre_principale;

	/**
		 * Constructeur du PanneauDebutJeu
		 * @param fen
		 * 			Fenetre principale
		 */
	public PanelDebutJeu(Fenetre fen) {
			this.fenetre_principale = fen;
			
			j1.addActionListener(null);
			j2.addActionListener(null);
		}

}
