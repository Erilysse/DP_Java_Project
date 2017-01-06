package compteur;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class Frame implements Observer{
	private Compteur compteur;
	private JFrame fenetre;
	private JLabel texte;
	private JButton demarrer;
	
	public Frame(Compteur c){
		compteur = c;
		compteur.addObserver(this);
		
		texte = new JLabel("Compteur: "+c.getValeur());
		demarrer = new JButton("Demarrer");
		fenetre = new JFrame("Compteur");
		Container reservoir = fenetre.getContentPane();
		reservoir.add(texte, BorderLayout.NORTH);
		reservoir.add(demarrer, BorderLayout.SOUTH);
		
		demarrer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				compteur.compter();
			}
		});
		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.pack();
		fenetre.setVisible(true);
	}
	
	public void update(Observable o, Object arg){
		texte.setText(/*"Compteur: "+*/compteur.getValeur()+" prout");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Compteur c = new Compteur();

		Frame f = new Frame(c);
		for(int i=0; i<10000000; i++){
			c.compter();
		}
	}

}
