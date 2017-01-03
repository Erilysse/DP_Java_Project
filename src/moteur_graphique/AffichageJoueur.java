package moteur_graphique;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import plateau_du_jeu.Jeu;

public class AffichageJoueur extends JPanel implements ActionListener{
	private JLabel l10,l20,l30,l40;
	private Jeu jeu;
	private ArrayList<JButton> boutons;
	
	public AffichageJoueur(){
		super();
		jeu = Jeu.getInstance();
		boutons = new ArrayList<JButton>();
		
		this.setLayout(new GridLayout(1,jeu.getListJoueur().size()));
		
		JLabel secondaire0= new JLabel(); 
		secondaire0.setLayout(new GridLayout(6, 1));
		l10 = new JLabel(jeu.getListJoueur().get(0).getDiviniteRepresentee().getNom());
		secondaire0.add(l10);
		l20 = new JLabel(jeu.getListJoueur().get(0).getDiviniteRepresentee().afficherOrigine());
		secondaire0.add(l20);
		l30 = new JLabel(jeu.getListJoueur().get(0).afficherListPA());
		secondaire0.add(l30);
		l40 = new JLabel("PP: "+jeu.getListJoueur().get(0).getNbrPrieres());
		secondaire0.add(l40);
		this.add(secondaire0);		
		for(int i=1; i<jeu.getListJoueur().size(); i++){
			JLabel secondaire= new JLabel(); 
			secondaire.setLayout(new GridLayout(5, 1));
			JLabel l1 = new JLabel(jeu.getListJoueur().get(i).getDiviniteRepresentee().getNom());
			secondaire.add(l1);
			JLabel l2 = new JLabel(jeu.getListJoueur().get(i).getDiviniteRepresentee().afficherOrigine());
			secondaire.add(l2);
			JLabel l3 = new JLabel(jeu.getListJoueur().get(i).afficherListPA());
			secondaire.add(l3);
			JLabel l4 = new JLabel("PP: "+jeu.getListJoueur().get(i).getNbrPrieres());
			secondaire.add(l4);
			JButton camp = new JButton("Voir camp");
			camp.addActionListener(this);
			secondaire.add(camp);
			boutons.add(camp);
			this.add(secondaire);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i=0; i<boutons.size(); i++){
			if(e.getSource()==boutons.get(i)){
				//fenetre du camp qui correspond au joueur
			}
		}
	}
}