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

public class AffichageMainDuJoueur extends JPanel implements ActionListener, Observer{
	private Jeu jeu;
	private ArrayList<JButton> cartes;
	private Control control;
	
	public AffichageMainDuJoueur(){
		super();
		jeu = Jeu.getInstance();
		control = Control.getInstance();
		jeu.getListJoueur().get(0).getMainjoueur().addObserver(this);
		cartes = new ArrayList<JButton>();
		int nbButtons = jeu.getListJoueur().get(0).getMainjoueur().getNbCarte();
		this.setLayout(new GridLayout(1,nbButtons));
		if(nbButtons>0){
			for(int i=0; i<nbButtons; i++){
				JButton j = new JButton(jeu.getListJoueur().get(0).getMainjoueur().getMain().get(i).toString());
				cartes.add(j);
				this.add(j);
			}
		}else{
			JLabel vide = new JLabel("La main est vide");
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
		int nbButtons = jeu.getListJoueur().size();
		if(nbButtons>0){
			for(int i=0; i<nbButtons; i++){
				JButton j = new JButton("JOUER");
				cartes.add(j);
				this.add(j);
			}
		}else{
			JLabel vide = new JLabel("La main est vide");
			vide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			this.add(vide);
		}	
	}
}
