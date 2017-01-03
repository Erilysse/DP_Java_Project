package moteur_graphique;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import contrôleur.Control;
import plateau_du_jeu.Jeu;

public class AffichageCampDuJoueur extends JPanel implements ActionListener, Observer{
	private Jeu jeu;
	private ArrayList<JButton> cartes;
	private Control control;
	private int index;
	
	public AffichageCampDuJoueur(int nb){
		super();
		jeu = Jeu.getInstance();
		control = Control.getInstance();
		cartes = new ArrayList<JButton>();
		jeu.getListJoueur().get(index).getCampjoueur().addObserver(this);
		index = nb;
		int nbButtons = jeu.getListJoueur().get(index).getCampjoueur().getNbCarte();
		this.setLayout(new GridLayout(1,nbButtons));
		if(nbButtons>0){
			for(int i=0; i<nbButtons; i++){
				JButton j = new JButton(jeu.getListJoueur().get(index).getCampjoueur().getCamp().toString());
				cartes.add(j);
				this.add(j);
			}
		}else{
			JLabel vide = new JLabel("Le camp est vide");
			vide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			this.add(vide);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		int nbButtons = jeu.getListJoueur().get(index).getCampjoueur().getNbCarte();
		if(nbButtons>0){
			for(int i=0; i<nbButtons; i++){
				JButton j = new JButton(jeu.getListJoueur().get(index).getCampjoueur().getCamp().toString());
				cartes.add(j);
				this.add(j);
			}
		}else{
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