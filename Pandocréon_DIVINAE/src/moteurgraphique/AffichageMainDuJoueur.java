package moteurgraphique;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
		defausse = new ArrayList<JButton>();
		nbButtons = jeu.getListJoueur().get(0).getMainjoueur().getNbCarte();
		this.setLayout(new GridBagLayout());
		if(nbButtons>0){
			for(int i=0; i<=nbButtons; i++){
				JPanel jp = new JPanel();
				jp.setLayout(new GridLayout(1,1));
				JButton j = new JButton(jeu.getListJoueur().get(0).getMainjoueur().getMain().get(i).toString());
				cartes.add(j);
				jp.add(j);
				this.add(jp);
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
				new PopupCarte(i);
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.removeAll();
		if(arg1.equals("defausse")){
			cartes.removeAll(cartes);
			defausse.removeAll(defausse);
			nbButtons = jeu.getListJoueur().get(0).getMainjoueur().getNbCarte();
			this.setLayout(new GridBagLayout());
			if(nbButtons>0){
				for(int i=0; i<nbButtons; i++){
					JPanel jp = new JPanel();
					jp.setLayout(new GridLayout(1,1));
					JButton j = new JButton(jeu.getListJoueur().get(0).getMainjoueur().getMain().get(i).getImage());
					j.addActionListener(this);
					cartes.add(j);
					jp.add(j);
					this.add(jp);
				}
			}else{
				vide = new JLabel("La main est vide");
				vide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				this.add(vide);
			}
			
		}else if(arg1.equals("pioche")){
		cartes.removeAll(cartes);
		defausse.removeAll(defausse);
		nbButtons = jeu.getListJoueur().get(0).getMainjoueur().getNbCarte();
		this.setLayout(new GridBagLayout());
		if(nbButtons>0){
			for(int i=0; i<nbButtons; i++){
				JPanel jp = new JPanel();
				jp.setLayout(new GridLayout(1,1));
				JButton j = new JButton(jeu.getListJoueur().get(0).getMainjoueur().getMain().get(i).getImage());
				j.addActionListener(this);
				cartes.add(j);
				jp.add(j);
				this.add(jp);
			}
		}else{
			vide = new JLabel("La main est vide");
			vide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			this.add(vide);
		}
		}
		this.revalidate();
	}
}
