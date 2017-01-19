package moteurgraphique;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import plateau_du_jeu.Jeu;

public class PopupCamp extends Fenetre implements Observer,ActionListener{
	private AffichageCampDuJoueur afcdj;
	private JButton close;
	private JLabel title;
	private JPanel principal;
	
	private int index;
	public PopupCamp(int nb){
		super();
		index = nb;
		principal = new JPanel();
		principal.setLayout(new GridLayout(3,1));
		
		title = new JLabel("Camp de "+Jeu.getInstance().getListJoueur().get(index).getDiviniteRepresentee().getNom());
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		principal.add(title);
		
		afcdj = new AffichageCampDuJoueur(index);
		principal.add(afcdj);
		
		close = new JButton("Fermer");
		close.addActionListener(this);
		principal.add(close);
		this.setContentPane(principal);
		this.pack();
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		afcdj = new AffichageCampDuJoueur(index);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== close){
			super.changerVisibilite();
		}
	}

}
