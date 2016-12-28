package moteurgraphique;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import plateau_du_jeu.Centre;

public class PopupCentre extends JFrame implements Observer{
	private Centre centre;
	private JLabel title;
	private HashMap<JButton, Integer> map;
	
	public PopupCentre(){
		
		centre = Centre.getInstance();
		map = new HashMap<JButton, Integer>();
		this.setTitle("CENTRE");
		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());
		title = new JLabel("Centre");
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		principal.add(title, BorderLayout.NORTH);
		
		JPanel secondaire = new JPanel();
		secondaire.setLayout(new FlowLayout());
		if(centre.getNbCarte()>0){
			for(int i=0; i<centre.getNbCarte(); i++){
				secondaire.add(new JButton());
			}
		}else{
			JLabel vide = new JLabel("Le Centre est vide");
			secondaire.add(vide);
		}
		principal.add(secondaire, BorderLayout.CENTER);
		this.setContentPane(principal);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
