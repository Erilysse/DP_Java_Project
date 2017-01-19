package moteurgraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controleur.Control;
import plateau_du_jeu.Jeu;

public class PopupCarte extends Fenetre implements ActionListener{
	private int index;
	private Jeu jeu;
	private Control control;
	private JButton carte;
	private JButton	defausser;
	private JButton	jouer;
	private JButton	fermer;
	
	public PopupCarte(int i){
		super();
		jeu = Jeu.getInstance();
		control = Control.getInstance();
		index = i;
		JPanel primaire = new JPanel();
		primaire.setLayout(new BoxLayout(primaire, BoxLayout.PAGE_AXIS));
		
		carte = new JButton(jeu.getListJoueur().get(0).getMainjoueur().getMain().get(index).getImage());
		primaire.add(carte);
		if(jeu.getListJoueur().get(0).isDefausse()==true){
			defausser = new JButton("defausser");
			defausser.addActionListener(this);
			primaire.add(defausser);
		}
		if(jeu.getListJoueur().get(0).isDefausse()==false){
			jouer = new JButton("jouer la carte");
			jouer.addActionListener(this);
			primaire.add(jouer);
		}
		fermer = new JButton("fermer");
		fermer.addActionListener(this);
		primaire.add(fermer);
		setContentPane(primaire);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(jeu.getListJoueur().get(0).isDefausse()==true){
			if(e.getSource()==defausser){
				control.setAnswer(index);
				System.out.println(index);
				jeu.getListJoueur().get(0).defausserCarte();
				super.changerVisibilite();
			}
		}
		if(e.getSource()==jouer){
			control.setAnswer(index);
			jeu.getListJoueur().get(0).jouerCarte();
			super.changerVisibilite();
		}
		if(e.getSource()==fermer){
			super.changerVisibilite();
		}
	}

}
