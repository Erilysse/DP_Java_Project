package moteurgraphique;

	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
	import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

	import plateau_du_jeu.CampDuJoueur;
	import plateau_du_jeu.Jeu;

public class PopupGuideSpirituel extends Fenetre implements ActionListener{
		private int index_gs;
		private int index_j;
		private ArrayList<JButton> carte;
		private ArrayList<JButton> sacrifice_croyant;
		private Jeu jeu;
		private CampDuJoueur camp;
		private JButton gs;
		private JButton	sacrifier;
		private JButton	fermer;
		
		public PopupGuideSpirituel(int i,int j){
			super();
			jeu = Jeu.getInstance();
			index_gs = i;
			index_j = j;
			camp = jeu.getListJoueur().get(index_j).getCampjoueur();
			carte = new ArrayList<JButton>();
			sacrifice_croyant = new ArrayList<JButton>();
			JPanel primaire = new JPanel();
			primaire.setLayout(new BoxLayout(primaire, BoxLayout.PAGE_AXIS));
			
			gs = new JButton(camp.getCamp().get(index_gs).getImage());
			primaire.add(gs);
			sacrifier = new JButton("sacrifier GS");
			sacrifier.addActionListener(this);
			primaire.add(sacrifier);
			JPanel secondaire = new JPanel();
			secondaire.setLayout(new BoxLayout(secondaire, BoxLayout.LINE_AXIS));
			if(camp.getCamp().get(index_gs).getCroyantsRattaches().size()>0){
				for(int a=0; a<camp.getCamp().get(index_gs).getCroyantsRattaches().size(); a++){
					JPanel tertiaire = new JPanel();
					tertiaire.setLayout(new BoxLayout(tertiaire, BoxLayout.PAGE_AXIS));
					JButton croyant = new JButton(camp.getCamp().get(index_gs).getCroyantsRattaches().get(a).getImage());
					croyant.addActionListener(this);
					carte.add(croyant);
					tertiaire.add(croyant);
					JButton s = new JButton("sacrifier");
					s.addActionListener(this);
					sacrifice_croyant.add(s);
					tertiaire.add(s);
				
					secondaire.add(tertiaire);
				}
			}else{
				JLabel vide = new JLabel("Pas de croyants rattachés");
				vide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				secondaire.add(vide);
			}
			primaire.add(secondaire);
			fermer = new JButton("fermer");
			fermer.addActionListener(this);
			primaire.add(fermer);
			setContentPane(primaire);
			pack();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==sacrifier){
				camp.getCamp().get(index_gs).sacrifice();
				camp.retirerGS(index_gs);
				super.changerVisibilite();
			}
			if(e.getSource()==fermer){
				super.changerVisibilite();
			}
			for(int a=0; a<sacrifice_croyant.size(); a++){
				if(e.getSource()==sacrifice_croyant.get(a)){
					camp.getCamp().get(index_gs).getCroyantsRattaches().get(a).sacrifice();
					super.changerVisibilite();
				}
			}
		}

}
