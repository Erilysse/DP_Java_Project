package pioches;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import cartes.Divinite;

/**
 * PiocheDivinité est une classe qui hérite de Pioche. Elle rassemble l'ensemble
 * de toutes les cartes divinités qui peuvent représenté un joueur. Elle sert
 * une seule fois, en début de partie, afin d'attribuer une divinité à chaque
 * joueur.
 * 
 * @see Divinite
 * @see Joueur
 * @see Pioche
 * 
 * @author manic
 */
public class PiocheDivinite extends Pioche {
	

	/**
	 * Unique objet de la classe Pioche, grâce au design pattern singleton.
	 * 
	 * @see Pioche#getInstance()
	 */
	private static PiocheDivinite instance = null;
	
	/**
	 * Liste de l'ensemble des Divinités qui peuvent représenté un joueur. Elle
	 * peut être mélangé et l'on peut retirer une Divinité de cette liste.
	 * 
	 * @see Divinite
	 * @see Joueur
	 * @see PiocheDivinite#piocher()
	 * @see PiocheDivinite#melanger()
	 */
	private LinkedList<Divinite> paquet;

	/**
	 * Constructeur PiocheDivinite qui ressemble au Constructeur Pioche. Lit un
	 * fichier texte où les lignes sont découpés en plusieurs morceaux afin
	 * d'être stocké dans un tableau, puis attribué aux paramètres du
	 * Constructeur Divinité.
	 * 
	 * @see Divinite
	 * @see Divinite#Divinite(String, String, String, String, String, String,
	 *      String)
	 * @see Pioche#Pioche()
	 */
	public PiocheDivinite() {
		paquet = new LinkedList<Divinite>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("ressources/divinite.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = in.readLine()) != null) {
				String[] decoupee = line.split("\\;");
				Divinite carte = new Divinite(decoupee[0], decoupee[1], decoupee[2], decoupee[3], decoupee[4], decoupee[5],
						decoupee[6], new ImageIcon("ressources/"+decoupee[7]+".png"));
				this.paquet.addLast(carte);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * C'est le design pattern singleton. Il s'assure de n'avoir qu'une instance
	 * de PiocheDivinité.
	 * 
	 * @return une PiocheDivinité.
	 * 
	 */
	public static PiocheDivinite getInstance() {
		if (instance == null) {
			instance = new PiocheDivinite();
		}
		return instance;
	}

	/**
	 * Mélange le paquet grâce à la méthode shuffle.
	 */
	public void melanger() {
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(this.paquet);
		}
	}

	/**
	 * Retourne la première divinité du paquet.
	 * 
	 * @return une carte Divinité.
	 */
	public Divinite piocher() {
		return this.paquet.pollFirst();
	}
}