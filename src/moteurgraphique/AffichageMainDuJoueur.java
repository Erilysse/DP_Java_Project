package moteurgraphique;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Control;
import joueurs.MainDuJoueur;
import plateau_du_jeu.Jeu;

public class AffichageMainDuJoueur extends JPanel implements ActionListener, Observer{
	private Jeu jeu;
	private ArrayList<JButton> cartes;
	private ArrayList<JButton> defausse;
	private Control control;
	private MainDuJoueur maindujoueur;
	private int nbButtons;
	private JLabel vide;
	
	public AffichageMainDuJoueur(){
		super();
		jeu = Jeu.getInstance();
		control = Control.getInstance();
		maindujoueur = jeu.getListJoueur().get(0).getMainjoueur();
		maindujoueur.addObserver(this);
		jeu.getListJoueur().get(0).getMainjoueur().addObserver(this);
		cartes = new ArrayList<JButton>();
		nbButtons = jeu.getListJoueur().get(0).getMainjoueur().getNbCarte();
		this.setLayout(new GridBagLayout());
		if(nbButtons>0){
			for(int i=0; i<=nbButtons; i++){
				JButton j = new JButton(jeu.getListJoueur().get(0).getMainjoueur().getMain().get(i).getImage());
				cartes.add(j);
				this.add(j);
			}
		}else{
			vide = new JLabel("La main est vide");
			vide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			this.add(vide);
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i=0; i<cartes.size(); i++){
			if(e.getSource()==cartes.get(i)){
				//utiliser la méthode jouer carte
			}
		}
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		cartes.removeAll(cartes);
		this.removeAll();
		nbButtons = jeu.getListJoueur().get(0).getMainjoueur().getNbCarte();
		this.setLayout(new GridBagLayout());
		if(nbButtons>0){
			for(int i=0; i<=nbButtons; i++){
				JButton j = new JButton(jeu.getListJoueur().get(0).getMainjoueur().getMain().get(i).toString());
				cartes.add(j);
				this.add(j);
				JButton k = new JButton("Defausser");
				this.add(k);
			}
		}else{
			vide = new JLabel("La main est vide");
			vide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			this.add(vide);
		}
		System.out.println("cartes"+cartes);
	}
}
