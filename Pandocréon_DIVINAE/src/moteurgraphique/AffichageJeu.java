package moteurgraphique;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import plateau_du_jeu.De;
import plateau_du_jeu.Jeu;

public class AffichageJeu extends Fenetre implements ActionListener, Observer{
	private JButton centre;
	private JButton fin_defausse;
	private JButton fin_tour;
	private JLabel title;
	private JPanel principal;
	private AffichageMainDuJoueur afmdj;
	private AffichageCampDuJoueur afcdj;
	private AffichageJoueur aj;
	private JLabel label_de;
	private JLabel label_tour;
	private Jeu jeu;
	private De de;
	
	public AffichageJeu(){
		super();
		jeu = Jeu.getInstance();
		jeu.addObserver(this);
		de = De.getInstance();
		de.addObserver(this);
		principal = new JPanel();
		principal.setLayout(new GridLayout(3,1));
		
		JPanel primaire = new JPanel();
		primaire.setLayout(new GridLayout(4,1));
		title = new JLabel("Partie");
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		primaire.add(title);
		label_tour = new JLabel("Tour n°: "+jeu.getNbTour());
		primaire.add(label_tour);
		switch(de.getFace()){
			case 0: label_de = new JLabel("Dé : face Jour");
				break;
			case 1: label_de = new JLabel("Dé : face Nuit");
				break;
			case 2: label_de = new JLabel("Dé : face Néant");
				break;
		}
		primaire.add(label_de);
		aj = new AffichageJoueur();
		primaire.add(aj);
		
		principal.add(primaire);
		
		
		
		afcdj = new AffichageCampDuJoueur(0);
		principal.add(afcdj);
		
		JPanel secondaire = new JPanel();
		secondaire.setLayout(new BoxLayout(secondaire, BoxLayout.X_AXIS));
		centre = new JButton("centre");
		centre.addActionListener(this);
		secondaire.add(centre);
		afmdj = new AffichageMainDuJoueur();
		secondaire.add(afmdj);
		JPanel tertiaire = new JPanel();
		tertiaire.setLayout(new BoxLayout(tertiaire, BoxLayout.Y_AXIS));
		fin_defausse = new JButton("Finir de défausser");
		fin_defausse.addActionListener(this);
		tertiaire.add(fin_defausse);
		fin_tour = new JButton("Finir le Tour");
		fin_tour.addActionListener(this);
		tertiaire.add(fin_tour);
		secondaire.add(tertiaire);
		principal.add(secondaire);
			
		this.setContentPane(principal);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==centre){
			new PopupCentre();
		}
		if(e.getSource()==fin_tour){
			jeu.getListJoueur().get(0).setDefausse(false);
			jeu.getListJoueur().get(0).finirLeTour();
			for(int i=1; i<jeu.getListJoueur().size(); i++){
				jeu.getListJoueur().get(i).jouerTour(De.getInstance().getFace());
			}
			if(jeu.getNbJoueur() == 1){
				jeu.arreterJeu();
			}
			jeu.Tourplusun();
			De.getInstance().lancerDe();
			jeu.getListJoueur().get(0).setDefausse(true);
			jeu.getListJoueur().get(0).prendreLaMain();
			jeu.getJoueuractuel();
			jeu.getListJoueur().get(0).regenererPA();
			jeu.getListJoueur().get(0).ajouterPA(De.getInstance().getFace());
			jeu.getListJoueur().get(0).piocherCarte();
		}
		if(e.getSource()==fin_defausse){
			jeu.getListJoueur().get(0).setDefausse(false);
			jeu.getListJoueur().get(0).piocherCarte();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg1.equals("DE")){
			switch(de.getFace()){
			case 0: label_de.setText("Dé : face Jour");
				break;
			case 1: label_de.setText("Dé : face Nuit");
				break;
			case 2: label_de.setText("Dé : face Néant");
				break;
			}
		}
		if(arg1.equals("NEWTOUR")){
			label_tour.setText("Tour n°: "+jeu.getNbTour());
		}
	}
	
}
