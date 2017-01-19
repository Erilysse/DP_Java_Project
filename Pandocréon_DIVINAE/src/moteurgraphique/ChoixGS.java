package moteurgraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import plateau_du_jeu.Jeu;

public class ChoixGS extends Fenetre implements ActionListener{
	private ArrayList<JButton> carte;
	private Jeu jeu;
	private int index;
	public ChoixGS(int nb){
		super();
		jeu = Jeu.getInstance();
		index = nb;
		carte = new ArrayList<JButton>();
		JPanel primaire = new JPanel();
		primaire.setLayout(new BoxLayout(primaire, BoxLayout.LINE_AXIS));
		for(int i=0; i<jeu.getListJoueur().get(0).getCampjoueur().getCamp().size(); i++){
			JButton gs = new JButton(jeu.getListJoueur().get(0).getCampjoueur().getCamp().get(i).getImage());
			gs.addActionListener(this);
			carte.add(gs);
			primaire.add(gs);
		}
		setContentPane(primaire);
		pack();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i=0; i<carte.size(); i++){
			if(e.getSource()==carte.get(i)){
				if (jeu.getListJoueur().get(0).canRattached(index)) {
					jeu.getListJoueur().get(0).getCampjoueur().getCamp().get(i)
							.rattacherCroyants(jeu.getCentre().donnerCroyant(index));			
					}
			}
		}
		super.changerVisibilite();
	}
}
