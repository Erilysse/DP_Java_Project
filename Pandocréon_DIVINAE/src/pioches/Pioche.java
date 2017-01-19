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
 * Pioche est une classe qui mod�lise un paquet de cartes. Il n'y a qu'une seule
 * instanciation de la classe, gr�ce au design pattern singleton.
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
	 * Nombre de cartes pr�sente dans le <code>paquet</code>.
	 * 
	 * @see Pioche#getNbCarte()
	 * @see Pioche#setNbCarte(int)
	 */
	private int nbCarte;

	/**
	 * Unique objet de la classe Pioche, gr�ce au design pattern singleton.
	 * 
	 * @see Pioche#getInstance()
	 */
	private static Pioche instance = null;

	/**
	 * Constructeur Pioche. A partir d'un fichier texte, le constructeur s�pare
	 * les diff�rentes valeurs contenues dans chaque ligne entre les "*", puis
	 * stockent ces valeurs dans un tableau. On v�rifie alors le type de la
	 * carte afin de cr�er la carte dans la bonne classe.
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
	 * Retourne le <code>paquet</code>, c'est � dire l'ensemble des cartes que
	 * poss�de la Pioche.
	 * 
	 * @return une collection, LinkedList d'objets de la classe Carte.
	 * 
	 * @see Carte
	 */
	public LinkedList<Carte> getPaquet() {
		return paquet;
	}

	/**
	 * Met � jour le <code>paquet</code>
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
	 * Met � jour le nombre de cartes.
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
	 * M�lange la collection <code>paquet</code>.
	 */
	public void melanger() {
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(this.paquet);
		}
	}

	/**
	 * Retire une carte du <code>paquet</code>.
	 * 
	 * @return un objet de la classe Carte, retir� du <code>paquet</code>.
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
	 *            objet de la classe Carte qui va �tre ajout� par la m�thode.
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