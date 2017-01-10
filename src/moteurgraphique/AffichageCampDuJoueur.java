package moteurgraphique;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Control;
import plateau_du_jeu.CampDuJoueur;
import plateau_du_jeu.Jeu;

public class AffichageCampDuJoueur extends JPanel implements ActionListener, Observer {
	private Jeu jeu;
	private ArrayList<JButton> cartes;
	private CampDuJoueur camp;
	private Control control;
	private int index;
	private int nbButtons;

	public AffichageCampDuJoueur(int nb) {
		super();
		jeu = Jeu.getInstance();
		control = Control.getInstance();
		index = nb;
		cartes = new ArrayList<JButton>();
		camp = jeu.getListJoueur().get(nb).getCampjoueur();
		camp.addObserver(this);
		nbButtons = camp.getNbCarte();
		this.setLayout(new GridLayout(1, nbButtons));
		if (nbButtons > 0) {
			for (int i = 0; i <= nbButtons; i++) {
				JButton j = new JButton(camp.getCamp().get(i).getImage());
				cartes.add(j);
				this.add(j);
			}
		} else {
			JLabel vide = new JLabel("Le camp est vide");
			vide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			this.add(vide);
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		cartes.removeAll(cartes);
		this.removeAll();
		nbButtons = camp.getNbCarte();
		if (nbButtons > 0) {
			for (int i = 0; i < nbButtons; i++) {
				JButton j = new JButton(camp.getCamp().get(i).toString());
				cartes.add(j);
				this.add(j);
			}
		} else {
			JLabel vide = new JLabel("Le camp est vide");
			vide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			this.add(vide);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}