package compteur;

import java.util.Observable;

public class Compteur extends Observable{
	public final static int TEMPO = 500;
	protected int compteur;
	protected boolean compte;
	
	public Compteur(){
		this.compteur=0;
		this.compte=false;
	}
	
	public void compter(){
		this.compte=true;
		while(compte){
			compteur++;
			setChanged();
			notifyObservers();
			this.arreter();
		}
	}
	
	public void arreter(){
		this.compte = false;
	}
	
	public int getValeur(){
		return this.compteur;
	}
	
	protected void attendre(){
		try {
			Thread.sleep(Compteur.TEMPO);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
