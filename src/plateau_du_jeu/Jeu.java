package plateau_du_jeu;

import java.util.Scanner;
import joueurs.*;
import pioches.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;

/**
 * Elle n'est instanciable qu'une fois, grâce au patter singleton.<br>
 * Elle possède les attributs suivants:<br>
 * <code>private final Scanner sc</code> pour la gestion des entrées
 * claviers<br>
 * <code>private static Jeu instance</code> pour garantir l'unicité du Jeu.
 */
public class Jeu {

	private final Scanner sc;
	private int nbTour;
	private static Jeu instance = null;
	private Pioche paquet;
	private De de;
	private Centre centre;
	protected ArrayList<Joueur> ListJoueur;
	protected int nbJoueur;
	private PiocheDivinite piocheD;
	protected int joueurMaxPP;
	protected int joueurMinPP;

	/**
	 * C'est le design pattern singleton. Il s'assure de n'avoir qu'une instance
	 * de Jeu.
	 * 
	 * @return un Jeu.
	 */
	public static Jeu getInstance() {
		if (instance == null) {
			instance = new Jeu();
		}
		return instance;
	}
	
	public Jeu() {
		this.sc = new Scanner(System.in);
		this.ListJoueur = new ArrayList<Joueur>();
		this.nbJoueur = this.ListJoueur.size();
		this.paquet = Pioche.getInstance();
		this.piocheD = new PiocheDivinite();
		this.piocheD.melanger();
		this.paquet.melanger();
		// test : paquet.afficherPioche();
		this.de = De.getInstance();
		this.centre = Centre.getInstance();
		// test : de.lancerDe();
		this.joueurMaxPP = 0;
		this.joueurMinPP = 0;
		this.nbTour = 1;
		System.out.println("Nouveau jeu!\n");

	}

	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}

	public Pioche getPaquet() {
		return paquet;
	}

	public void setPaquet(Pioche paquet) {
		this.paquet = paquet;
	}

	public De getDe() {
		return de;
	}

	public void setDe(De de) {
		this.de = de;
	}

	public int getNbJoueur() {
		return nbJoueur;
	}

	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}

	public PiocheDivinite getPiocheD() {
		return piocheD;
	}

	public void setPiocheD(PiocheDivinite piocheD) {
		this.piocheD = piocheD;
	}

	public void setListJoueur(ArrayList<Joueur> listJoueur) {
		ListJoueur = listJoueur;
	}

	public Centre getCentre() {
		return centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}

	public static void setInstance(Jeu instance) {
		Jeu.instance = instance;
	}

	public ArrayList<Joueur> getListJoueur() {
		return ListJoueur;
	}

	public int getJoueurMaxPP() {
		int val = 0;
		int index = 0;
		for (int i = 0; i < this.nbJoueur; i++) {
			if (this.ListJoueur.get(i).getNbrPrieres() > val) {
				val = this.ListJoueur.get(i).getNbrPrieres();
				index = i;
			}
		}
		return index;
	}

	public int getJoueurMinPP() {
		int val = getJoueurMaxPP();
		int index = 0;
		for (int i = 0; i < this.nbJoueur; i++) {
			if (this.ListJoueur.get(i).getNbrPrieres() < val) {
				val = this.ListJoueur.get(i).getNbrPrieres();
				index = i;
			}
		}
		return index;
	}

	/*
	 * Créer la partie : générer la pioche, le centre, les mains des joueurs, le
	 * dé, les joueurs
	 */
	
	public void choisirJoueurs(int j) {
		Joueur humain = Humain.getInstance(this.piocheD.piocher());
		this.ListJoueur.add(humain);
		System.out.println("Il y a " +j+ " IA.");
			for (int i = 0; i < j; i++) {
				Joueur ia = new IA(this.piocheD.piocher(), null);
				this.ListJoueur.add(ia);
			}
			this.nbJoueur = this.ListJoueur.size();
			this.afficherListJoueur();
	}

	public void choisirJoueurs() {
		// choix des joueurs, du nombre, de leur type, etc.
		Joueur humain = Humain.getInstance(this.piocheD.piocher());
		this.ListJoueur.add(humain);
		System.out.println("Combien y-a-t-il de joueurs IA ?");
		try {
			int nbrJoueurIA = sc.nextInt();
			for (int i = 0; i < nbrJoueurIA; i++) {
				Joueur ia = new IA(this.piocheD.piocher(), null);
				this.ListJoueur.add(ia);
			}
			this.nbJoueur = this.ListJoueur.size();
			this.afficherListJoueur();
		} catch (InputMismatchException e) {
			System.err.println("Erreur : Vous devez entrer un nombre entier.");
		}
	}

	public void afficherListJoueur() {
		for (int i = 0; i < this.nbJoueur; i++) {
			System.out.println("\n Joueur " + (i + 1) + " : ");
			this.ListJoueur.get(i).afficherDivinite();
		}

	}

	public int getnbJoueur() {
		return this.nbJoueur;
	}

	public void jouerPartie() {
		while (this.nbJoueur != 1) {
			System.out.println("\n Tour n°" + this.nbTour + "\n");
			Iterator<Joueur> ListJoueur = this.ListJoueur.iterator();
			this.de.lancerDe();
			System.out.println();
			while (ListJoueur.hasNext()) {
				ListJoueur.next().jouerTour(this.de.getFace());
			}
			this.nbTour++;
		}
		this.arreterJeu();
	}

	public void arreterJeu() {
		System.out.println("La partie est fini.");
		Jeu.setInstance(null);
	}
}
