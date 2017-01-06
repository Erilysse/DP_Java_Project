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
 * PiocheDivinit� est une classe qui h�rite de Pioche. Elle rassemble l'ensemble
 * de toutes les cartes divinit�s qui peuvent repr�sent� un joueur. Elle sert
 * une seule fois, en d�but de partie, afin d'attribuer une divinit� � chaque
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
	 * Unique objet de la classe Pioche, gr�ce au design pattern singleton.
	 * 
	 * @see Pioche#getInstance()
	 */
	private static PiocheDivinite instance = null;
	
	/**
	 * Liste de l'ensemble des Divinit�s qui peuvent repr�sent� un joueur. Elle
	 * peut �tre m�lang� et l'on peut retirer une Divinit� de cette liste.
	 * 
	 * @see Divinite
	 * @see Joueur
	 * @see PiocheDivinite#piocher()
	 * @see PiocheDivinite#melanger()
	 */
	private LinkedList<Divinite> paquet;

	/**
	 * Constructeur PiocheDivinite qui ressemble au Constructeur Pioche. Lit un
	 * fichier texte o� les lignes sont d�coup�s en plusieurs morceaux afin
	 * d'�tre stock� dans un tableau, puis attribu� aux param�tres du
	 * Constructeur Divinit�.
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
	 * de PiocheDivinit�.
	 * 
	 * @return une PiocheDivinit�.
	 * 
	 */
	public static PiocheDivinite getInstance() {
		if (instance == null) {
			instance = new PiocheDivinite();
		}
		return instance;
	}

	/**
	 * M�lange le paquet gr�ce � la m�thode shuffle.
	 */
	public void melanger() {
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(this.paquet);
		}
	}

	/**
	 * Retourne la premi�re divinit� du paquet.
	 * 
	 * @return une carte Divinit�.
	 */
	public Divinite piocher() {
		return this.paquet.pollFirst();
	}
}