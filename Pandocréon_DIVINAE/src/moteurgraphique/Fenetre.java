package moteurgraphique;

import javax.swing.*;


public class Fenetre extends JFrame{
	private boolean visible;

	public Fenetre(){
		visible = true;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("DIVINAE");
		this.setSize(900, 600);
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
	public void jouerPartie(){
		new Intro();
	}
}
