package plateau_du_jeu;

import java.util.Scanner;

import contrôleur.Control;
import joueurs.*;
import pioches.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Observable;

/**
 * Elle n'est instanciable qu'une fois, grâce au patter singleton.<br>
 * Elle possède les attributs suivants:<br>
 * - <code>private final Scanner sc</code> pour la gestion des entrées
 * claviers.<br>
 * - <code>private static Jeu instance</code> pour garantir l'unicité du Jeu.
 */
public class Jeu extends Observable {

	/**
	 * Unique objet de la classe Jeu grâce au design pattern singleton.
	 * 
	 * @see Jeu#getInstance()
	 * @see Jeu#setInstance(Jeu)
	 */
	private static Jeu instance = null;
	private Control sca;
	/**
	 * Objet de la classe Scanner pour gérer les entrées claviers.
	 * 
	 * @see Scanner
	 */
	private final Scanner sc;

	/**
	 * Liste d'objets de la classe Joueur, qui désigne l'ensemble des joueurs
	 * dans la partie.
	 * 
	 * @see Joueur
	 * @see Jeu#getListJoueur()
	 * @see Jeu#setListJoueur(ArrayList)
	 */
	private ArrayList<Joueur> ListJoueur;

	/**
	 * Nombre de Joueurs dans la partie, c'est à dire la taille de la collection
	 * <code>ListJoueur</code>.
	 * 
	 * @see Joueur
	 * @see Jeu#ListJoueur
	 * @see Jeu#getNbJoueur()
	 * @see Jeu#setNbJoueur(int)
	 */
	private int nbJoueur;

	/**
	 * Objet de la classe Pioche, qui modélise la pioche et défausse du jeu.
	 * 
	 * @see Pioche
	 * @see Jeu#getPaquet()
	 * 
	 */
	private Pioche paquet;

	/**
	 * Objet de la classe PiocheDivinite, qui permets aux joueurs de piocher une
	 * Divinité qui les représentera tout le long du Jeu.
	 * 
	 * @see Divinite
	 * @see PiocheDivinite
	 * @see Jeu#getPiocheD()
	 */
	private PiocheDivinite piocheD;

	/**
	 * Objet de la classe Centre, qui désigne le lieu où les cartes Croyant sont
	 * posés.
	 * 
	 * @see Centre
	 * @see Croyant
	 * @see Jeu#getCentre()
	 */
	private Centre centre;

	/**
	 * Objet de la classe De, qui permets de donner l'origine du tour.
	 * 
	 * @see De
	 * @see Jeu#getDe()
	 */
	private De de;

	/**
	 * Numéro du tour de jeu que joue les joueurs.
	 * 
	 * @see Jeu#getNbTour()
	 * @see Jeu#setNbTour(int)
	 */
	private int nbTour;

	/**
	 * numéro du joueur dans la <code>ListJoueur</code> qui a le nombre de
	 * points de prières le plus important.
	 * 
	 * @see Jeu#getJoueurMaxPP()
	 */
	protected int joueurMaxPP;

	/**
	 * numéro du joueur dans la <code>ListJoueur</code> qui a le nombre de
	 * points de prières le moins important.
	 * 
	 * @see Jeu#getJoueurMinPP()
	 */
	protected int joueurMinPP;

	private int joueuractuel;

	public static boolean vue;

	/**
	 * Constructeur Jeu.
	 * 
	 * Créé un objet de la classe Scanner, ainsi qu'une liste de joueurs pour
	 * <code>ListJoueur</code>. Récupère l'instance de la classe Pioche,
	 * PiocheDivinite, De et Centre. Le <code>nbJoueur</code> est la taille de
	 * la liste <code>ListJoueur</code>. Initialisation à zéro du
	 * <code>joueurMaxPP</code> et <code>joueurMinPP</code>, et à un pour
	 * <code>nbTour</code>.
	 */
	public Jeu() {
		this.sca = Control.getInstance();
		this.sc = new Scanner(System.in);
		this.ListJoueur = new ArrayList<Joueur>();
		this.nbJoueur = this.ListJoueur.size();
		this.paquet = Pioche.getInstance();
		this.paquet.melanger();
		this.piocheD = (PiocheDivinite) PiocheDivinite.getInstance();
		this.piocheD.melanger();
		this.de = De.getInstance();
		this.centre = Centre.getInstance();
		this.nbTour = 1;
		this.joueurMaxPP = 0;
		this.joueurMinPP = 0;
		this.joueuractuel = 0;
		this.vue = true;
		System.out.println("Nouveau jeu!\n");
	}

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

	/**
	 * 
	 * @param
	 */
	public static void setInstance(Jeu instance) {
		Jeu.instance = instance;
	}

	/**
	 * Retourne la liste des joueurs.
	 * 
	 * @return une liste d'objets de la classe Joueur.
	 * 
	 * @see Joueur
	 */
	public ArrayList<Joueur> getListJoueur() {
		return ListJoueur;
	}

	/**
	 * Met à jour la liste des joueurs.
	 * 
	 * @param listJoueur
	 *            une nouvelle ArrayList d'objets de la classe Joueur.
	 * 
	 * @see Joueur
	 */
	public void setListJoueur(ArrayList<Joueur> listJoueur) {
		ListJoueur = listJoueur;
	}

	/**
	 * Retourne le nombre de joueurs qui sont dans la partie.
	 * 
	 * @return un nombre de joueurs.
	 */
	public int getNbJoueur() {
		return nbJoueur;
	}

	/**
	 * Met à jour le nombre de joueurs qui sont dans la partie.
	 * 
	 * @param nbJoueur
	 *            un nouveau nombre de joueurs.
	 */
	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}

	/**
	 * Retourne la pioche du jeu.
	 * 
	 * @return l'instance de la classe Pioche.
	 * 
	 * @see Pioche
	 */
	public Pioche getPaquet() {
		return paquet;
	}

	/**
	 * Retourne l'instance de la pioche à Divinité.
	 * 
	 * @return un objet de la classe PiocheDivinite.
	 * 
	 * @see PiocheDivinite
	 */
	public PiocheDivinite getPiocheD() {
		return piocheD;
	}

	/**
	 * Retourne l'instance de la classe Centre.
	 * 
	 * @return un objet de la classe Centre.
	 * 
	 * @see Centre
	 */
	public Centre getCentre() {
		return centre;
	}

	/**
	 * Retourne le dé du jeu.
	 * 
	 * @return un objet de la classe De.
	 * 
	 * @see De
	 */
	public De getDe() {
		return de;
	}

	/**
	 * Retourne le numéro du tour que les joueurs jouent actuellement.
	 * 
	 * @return un nombre de tours.
	 */
	public int getNbTour() {
		return nbTour;
	}

	/**
	 * Met à jour le nombre de tours de jeu.
	 * 
	 * @param nbTour
	 *            le nouveau nombre de tours de jeu.
	 */
	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}

	/**
	 * Calcule et Retourne le numéro d'un joueur dans <code>ListJoueur</code>
	 * qui a le plus de points de prières.
	 * 
	 * @return un numéro d'un joueur.
	 */
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

	/**
	 * Calcule et Retourne le numéro d'un joueur dans <code>ListJoueur</code>
	 * qui a le moins d epoints de prières.
	 * 
	 * @return un numéro d'un joueur.
	 */
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

	/**
	 * Retourne le numéro du joueur qui est en train de jouer actuellement son
	 * tour dans la liste des joueurs.
	 * 
	 * @return un nombre.
	 */
	public int getJoueuractuel() {
		for (int i = 0; i < this.ListJoueur.size(); i++) {
			if (this.ListJoueur.get(i).getJoueurActif() == true) {
				return i;
			}
		}
		return joueuractuel;
	}

	/**
	 * Met à jour le numéro du joueur qui est en train de jouer actuellement son
	 * tour dans la liste des joueurs.
	 * 
	 * @param joueuractuel
	 *            le numéro du joueur actuel.
	 */
	public void setJoueuractuel(int joueuractuel) {
		this.joueuractuel = joueuractuel;
	}

	/**
	 * Créer un joueur Humain et un nombre j de joueurs IA, puis les ajoutent à
	 * la liste des joueurs <code>ListJoueur</code>. Affiche la liste des
	 * joueurs, avec les Divinités qui les représentent.
	 * 
	 * @param j
	 *            le nombre de joueurs IA qui vont être créé.
	 * 
	 * @see Joueur
	 * @see Humain
	 * @see IA
	 * @see Divinite
	 */
	public void choisirJoueurs(int j) {
		Joueur humain = Humain.getInstance(this.piocheD.piocher());
		this.ListJoueur.add(humain);
		System.out.println("Il y a " + j + " IA.");
		for (int i = 0; i < j; i++) {
			Joueur ia = new IA(this.piocheD.piocher(), null);
			this.ListJoueur.add(ia);
		}
		this.nbJoueur = this.ListJoueur.size();
		this.afficherListJoueur();
	}

	/**
	 * Créer un joueur Humain et un nombre de joueurs IA décidé par
	 * l'utilisateur par l'intermédiaire de la console, puis les ajoutent à la
	 * liste des joueurs <code>ListJoueur</code>. Affiche la liste des joueurs,
	 * avec les Divinités qui les représentent.
	 * 
	 * @see Joueur
	 * @see Humain
	 * @see IA
	 * @see Divinite
	 */
	public void choisirJoueurs() {
		Joueur humain = Humain.getInstance(this.piocheD.piocher());
		this.ListJoueur.add(humain);
		System.out.println("Combien y-a-t-il de joueurs IA ?");
		try {
			sca.repInt();
			int nbrJoueurIA = sca.answer;
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

	/**
	 * Déroule la liste des joueurs et affiche leur divinité.
	 * 
	 * @see Divinite
	 */
	public void afficherListJoueur() {
		for (int i = 0; i < this.nbJoueur; i++) {
			System.out.println("\n Joueur " + (i + 1) + " : ");
			this.ListJoueur.get(i).afficherDivinite();
		}
	}

	/**
	 * Simule la partie, avec une boucle qui empêche le jeu de se terminer tant
	 * qu'il n'y a pas un gagnant. Chaque boucle constitue un tour de jeu. Le dé
	 * est lancé, puis chaque joueur, tour à tour, vont jouer.
	 * <code>nbTour</code> est incrémenté à chaque fin de tour. Quand il reste
	 * un seul joueur, on arrête le jeu grâce à la méthode
	 * <code>arreterJeu()</code>.
	 * 
	 */
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

	/**
	 * Affiche l'arrêt du jeu et vide l'instance de Jeu, afin de pouvoir à
	 * nouveau rejouer une partie.
	 */
	public void arreterJeu() {
		System.out.println("La partie est fini.");
		Jeu.setInstance(null);
	}
}