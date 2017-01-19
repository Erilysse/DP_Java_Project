package moteurgraphique;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Intro extends Fenetre implements ActionListener{
	private JButton game;
	private JButton rules;
	
	
	public Intro(){
		super();
		JLabel primaire =new JLabel( new ImageIcon("ressources/background_intro.png"));
		add(primaire);
		/*JPanel primaire = new JPanel();*/
		primaire.setLayout(new GridLayout(6,5));
		for(int i=0; i<12; i++){
			JLabel l = new JLabel();
			primaire.add(l);
		}
		game = new JButton("Lancer une Partie");
		game.addActionListener(this);
		primaire.add(game);
		for(int i=0; i<4; i++){
			JLabel l = new JLabel();
			primaire.add(l);
		}
		rules = new JButton("Voir les régles");
		rules.addActionListener(this);
		primaire.add(rules);
		for(int i=0; i<11; i++){
			JLabel l = new JLabel();
			primaire.add(l);
		}
		this.setContentPane(primaire);
		pack();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==game){
			new Menu();
			super.changerVisibilite();
		}
		if(e.getSource()==rules){
			URI uri = URI.create("http://www.pandocreon.com/jeux/divinae/pandocreon_divinae-regles-1.0.0.pdf");
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}
