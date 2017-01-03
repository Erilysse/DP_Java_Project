package moteur_graphique;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import plateau_du_jeu.Centre;

public class PopupCentre extends Fenetre implements Observer, ActionListener{
	private Centre centre;
	private JLabel title;
	private JButton close;
	private JPanel principal;
	private HashMap<JButton, Integer> map;
	
	public PopupCentre(){
		super();
		centre = Centre.getInstance();
		map = new HashMap<JButton, Integer>();
		principal = new JPanel();
		principal.setLayout(new BorderLayout());
		title = new JLabel("Centre");
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		principal.add(title, BorderLayout.NORTH);
		
		JPanel secondaire = new JPanel();
		secondaire.setLayout(new FlowLayout());
		if(centre.getNbCarte()>0){
			for(int i=0; i<centre.getNbCarte(); i++){
				JButton j = new JButton(centre.getCentre().get(i).toString());
				map.put(j, i);
				secondaire.add(j);
			}
		}else{
			close = new JButton("Le Centre est vide");
			close.addActionListener(this);
			secondaire.add(close);
		}
		principal.add(secondaire, BorderLayout.CENTER);
		this.setContentPane(principal);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		JPanel secondaire = new JPanel();
		secondaire.setLayout(new FlowLayout());
		if(centre.getNbCarte()>0){
			for(int i=0; i<centre.getNbCarte(); i++){
				secondaire.add(new JButton(centre.getCentre().get(i).getNom()+" "+centre.getCentre().get(i).getOrigine()));
			}
			close = new JButton("Revenir au Menu");
			close.addActionListener(this);
			secondaire.add(close);
		}else{
			close = new JButton("Le Centre est vide");
			close.addActionListener(this);
			secondaire.add(close);
		}
		principal.add(secondaire, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== close){
			super.changerVisibilite();
		}
	}
}