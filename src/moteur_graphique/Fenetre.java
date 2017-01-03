package moteur_graphique;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.*;

public class Fenetre extends JFrame{
	private boolean visible;

	public Fenetre(){
		visible = true;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("DIVINAE");
		this.setSize(1366, 768);
		this.setVisible(visible);
	}
	public void changerVisibilite(){
		if(visible){
			this.visible=false;
		}else{
			this.visible=true;
		}
		this.setVisible(visible);
	}
	public static void main(String[] args){
		// Fenetre f1 = new Fenetre();
		Menu m = new Menu();
		PopupCentre c = new PopupCentre();
		AffichageJeu af = new AffichageJeu();
	}
}
