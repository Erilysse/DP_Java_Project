package pioches;

import java.util.LinkedList;
import cartes.*;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Pioche {
	private LinkedList<Carte> paquet;
	private int nbCarte;
	private static Pioche instance = null;

	public LinkedList<Carte> getPaquet() {
		return paquet;
	}

	public void setPaquet(LinkedList<Carte> paquet) {
		this.paquet = paquet;
	}

	public int getNbCarte() {
		return nbCarte;
	}

	public void setNbCarte(int nbCarte) {
		this.nbCarte = nbCarte;
	}

	/**
	 * C'est le design pattern singleton. Il s'assure de n'avoir qu'une instance
	 * de Pioche.
	 * 
	 * @return une Pioche.
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static Pioche getInstance() {
		if (instance == null) {
			instance = new Pioche();
		}
		return instance;
	}

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
			in = new BufferedReader(new FileReader("cartedivinae.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = in.readLine()) != null) {
				String[] decoupee = line.split("\\;");
				switch (decoupee[0]) {
				case ("Croyants"):
					Croyant croyant = new Croyant(decoupee[0], decoupee[1], decoupee[2], decoupee[3], decoupee[4],
							decoupee[5], decoupee[6]);
					this.paquet.addLast(croyant);
					break;
				case ("Guide Spirituel"):
					GuideSpirituel GS = new GuideSpirituel(decoupee[0], decoupee[1], decoupee[2], decoupee[3], decoupee[4],
							decoupee[5], decoupee[6]);
					this.paquet.addLast(GS);
					break;
				case ("Apocalypse"):
					Apocalypse apo = new Apocalypse(decoupee[0], decoupee[1], decoupee[2], decoupee[3], decoupee[4],
							decoupee[5], decoupee[6]);
					this.paquet.addLast(apo);
					break;
				case ("Deus Ex"):
					DeusEx DE = new DeusEx(decoupee[0], decoupee[1], decoupee[2], decoupee[3], decoupee[4], decoupee[5],
							decoupee[6]);
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
	 * 
	 */
	public void melanger() {
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(this.paquet);
		}
	}

	/**
	 * 
	 * @return
	 */
	public Carte piocher() {
		nbCarte = this.paquet.size();
		return this.paquet.remove();
	}

	/**
	 * 
	 * @param carte
	 */
	public void recuperer(Carte carte) {
		this.paquet.addLast(carte);
		nbCarte = this.paquet.size();
	}

	/**
	 * 
	 */
	public void afficherPioche() {
		for (int i = 0; i < nbCarte; i++) {
			this.paquet.get(i).afficherCarte();
		}
	}
}
