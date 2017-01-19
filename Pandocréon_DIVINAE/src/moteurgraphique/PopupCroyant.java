package moteurgraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import plateau_du_jeu.Jeu;

public class PopupCroyant extends Fenetre implements ActionListener{
	private JButton croyant;
	private JButton rattacher;
	private JButton fermer;
	private Jeu jeu;
	private int index;
	
	public PopupCroyant(int nb){
		super();
		jeu = Jeu.getInstance();
		index = nb;
		JPanel primaire = new JPanel();
		primaire.setLayout(new BoxLayout(primaire, BoxLayout.PAGE_AXIS));
		croyant = new JButton(jeu.getCentre().getCentre().get(index).getImage());
		primaire.add(croyant);
		rattacher = new JButton("rattacher");
		rattacher.addActionListener(this);
		primaire.add(rattacher);
		fermer = new JButton("fermer");
		fermer.addActionListener(this);
		primaire.add(fermer);
		
		setContentPane(primaire);
		pack();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==rattacher){
			new ChoixGS(index);
		}
		if(e.getSource()==fermer){
			super.changerVisibilite();
		}
	}

}
