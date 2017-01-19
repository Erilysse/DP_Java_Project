package moteurgraphique;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Control;
import plateau_du_jeu.De;
import plateau_du_jeu.Jeu;

public class Menu extends Fenetre implements ActionListener{
	private Control control;
	private Jeu jeu;
	private JButton j1,j2,j3,j4,j5,j6;
	private JLabel title;
	private JPanel principal;
	
	public Menu(){
		super();
		jeu = Jeu.getInstance();
		control= Control.getInstance();
		
		principal = new JPanel();
		principal.setLayout(new GridLayout(2,1));
		title = new JLabel("Choisir le nombre d'IA");
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		principal.add(title);
		
		JPanel boutons = new JPanel();
		boutons.setLayout(new FlowLayout());
		
		j1 = new JButton("1 IA");
		j1.addActionListener(this);
		boutons.add(j1);
		j2 = new JButton("2 IA");
		j2.addActionListener(this);
		boutons.add(j2);
		j3 = new JButton("3 IA");
		j3.addActionListener(this);
		boutons.add(j3);
		j4 = new JButton("4 IA");
		j4.addActionListener(this);
		boutons.add(j4);
		j5 = new JButton("5 IA");
		j5.addActionListener(this);
		boutons.add(j5);
		j6 = new JButton("6 IA");
		j6.addActionListener(this);
		boutons.add(j6);

		principal.add(boutons);
		
		this.setContentPane(principal);
		super.pack();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==j1){
			control.setAnswer(1);
			jeu.choisirJoueurs();
		}
		if(e.getSource()==j2){
			control.setAnswer(2);
			jeu.choisirJoueurs();
		}
		if(e.getSource()==j3){
			control.setAnswer(3);
			jeu.choisirJoueurs();
		}
		if(e.getSource()==j4){
			control.setAnswer(4);
			jeu.choisirJoueurs();
		}
		if(e.getSource()==j5){
			control.setAnswer(5);
			jeu.choisirJoueurs();
		}
		if(e.getSource()==j6){
			control.setAnswer(6);
			jeu.choisirJoueurs();
		}
		jeu.setCommence(true);
		new AffichageJeu();
		De.getInstance().lancerDe();
		jeu.getListJoueur().get(0).prendreLaMain();
		jeu.getListJoueur().get(0).ajouterPA(De.getInstance().getFace());
		jeu.getListJoueur().get(0).piocherCarte();
		super.changerVisibilite();
	}
}
