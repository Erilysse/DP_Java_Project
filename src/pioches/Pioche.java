package pioches;

import java.util.LinkedList;

import javax.swing.ImageIcon;

import java.awt.Image;
import cartes.*;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Pioche est une classe qui modélise un paquet de cartes. Il n'y a qu'une seule
 * instanciation de la classe, grâce au design pattern singleton.
 * 
 * @author manic
 */
public class Pioche {

	/**
	 * Collection, liste d'objets de la classe Carte
	 * 
	 * @see Carte
	 * @see Pioche#getPaquet()
	 * @see Pioche#setPaquet(LinkedList)
	 */
	private LinkedList<Carte> paquet;

	/**
	 * Nombre de cartes présente dans le <code>paquet</code>.
	 * 
	 * @see Pioche#getNbCarte()
	 * @see Pioche#setNbCarte(int)
	 */
	private int nbCarte;

	/**
	 * Unique objet de la classe Pioche, grâce au design pattern singleton.
	 * 
	 * @see Pioche#getInstance()
	 */
	private static Pioche instance = null;

	/**
	 * Constructeur Pioche. A partir d'un fichier texte, le constructeur sépare
	 * les différentes valeurs contenues dans chaque ligne entre les "*", puis
	 * stockent ces valeurs dans un tableau. On vérifie alors le type de la
	 * carte afin de créer la carte dans la bonne classe.
	 */
	public Pioche() {
		paquet = new LinkedList<Carte>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("ressources/cartedivinae.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = in.readLine()) != null) {
				String[] decoupee = line.split("\\;");
				switch (decoupee[0]) {
				case ("Croyants"):
					Croyant croyant = new Croyant(decoupee[0], decoupee[1], decoupee[2], decoupee[3], decoupee[4],
							decoupee[5], decoupee[6], new ImageIcon("ressources/"+decoupee[7]+".png"));
					this.paquet.addLast(croyant);
					break;
				case ("Guide Spirituel"):
					GuideSpirituel GS = new GuideSpirituel(decoupee[0], decoupee[1], decoupee[2], decoupee[3],
							decoupee[4], decoupee[5], decoupee[6], new ImageIcon("ressources/"+decoupee[7]+".png"));
					this.paquet.addLast(GS);
					break;
				case ("Apocalypse"):
					Apocalypse apo = new Apocalypse(decoupee[0], decoupee[1], decoupee[2], decoupee[3], decoupee[4],
							decoupee[5], decoupee[6], new ImageIcon("ressources/"+decoupee[7]+".png"));
					this.paquet.addLast(apo);
					break;
				case ("Deus Ex"):
					DeusEx DE = new DeusEx(decoupee[0], decoupee[1], decoupee[2], decoupee[3], decoupee[4], decoupee[5],
							decoupee[6], new ImageIcon("ressources/"+decoupee[7]+".png"));
					this.paquet.addLast(DE);
					break;
				default:
					System.err.println("pioche vide");
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.nbCarte = this.paquet.size();
	}

	/**
	 * Retourne le <code>paquet</code>, c'est à dire l'ensemble des cartes que
	 * possède la Pioche.
	 * 
	 * @return une collection, LinkedList d'objets de la classe Carte.
	 * 
	 * @see Carte
	 */
	public LinkedList<Carte> getPaquet() {
		return paquet;
	}

	/**
	 * Met à jour le <code>paquet</code>
	 * 
	 * @param paquet
	 *            nouvelle liste d'objets de la classe Carte.
	 */
	public void setPaquet(LinkedList<Carte> paquet) {
		this.paquet = paquet;
	}

	/**
	 * Retourne le nombre de cartes du <code>paquet</code>.
	 * 
	 * @return un nombre de cartes.
	 */
	public int getNbCarte() {
		return nbCarte;
	}

	/**
	 * Met à jour le nombre de cartes.
	 * 
	 * @param nbCarte
	 *            nouveau nombre de cartes.
	 */
	public void setNbCarte(int nbCarte) {
		this.nbCarte = nbCarte;
	}

	/**
	 * C'est le design pattern singleton. Il s'assure de n'avoir qu'une instance
	 * de Pioche.
	 * 
	 * @return une Pioche.
	 * 
	 */
	public static Pioche getInstance() {
		if (instance == null) {
			instance = new Pioche();
		}
		return instance;
	}

	/**
	 * Mélange la collection <code>paquet</code>.
	 */
	public void melanger() {
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(this.paquet);
		}
	}

	/**
	 * Retire une carte du <code>paquet</code>.
	 * 
	 * @return un objet de la classe Carte, retiré du <code>paquet</code>.
	 */
	public Carte piocher() {
		Carte p = this.paquet.remove();
		nbCarte = this.paquet.size();
		return p;
	}

	/**
	 * Ajoute une carte dans la pioche.
	 * 
	 * @param carte
	 *            objet de la classe Carte qui va être ajouté par la méthode.
	 */
	public void recuperer(Carte carte) {
		this.paquet.addLast(carte);
		nbCarte = this.paquet.size();
	}

	/**
	 * Affiche l'ensemble des cartes de la Pioche.
	 * 
	 */
	public void afficherPioche() {
		for (int i = 0; i < nbCarte; i++) {
			this.paquet.get(i).afficherCarte();
		}
	}
}