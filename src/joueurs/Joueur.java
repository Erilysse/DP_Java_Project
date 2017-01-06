package joueurs;

import java.util.*;
import cartes.*;
import controleur.Control;
import pioches.Pioche;
import plateau_du_jeu.CampDuJoueur;
import plateau_du_jeu.Centre;

/**
 * Joueur est une classe qui mod�lise un joueur. Il impl�mente l'interface
 * Observable. Il poss�de un camp, une main, un nombre de points de pri�res, une
 * liste de Points d'action. Il est repr�sent� par une divinit�, et conna�t le
 * centre et la pioche du jeu.
 * 
 * @see Observable
 * @see Jeu
 * @see Divinite
 * @see CampDuJoueur
 * @see MainDuJoueur
 * @see Centre
 * @see Pioche
 * 
 * @author manic
 */
public abstract class Joueur extends Observable {

	/**
	 * Instance de la classe Control qui permets de g�rer les entr�es claviers.
	 * 
	 */
	private Control sca;

	/**
	 * Bool�en permettant de savoir si le joueur est un humain ou un IA.
	 * 
	 * @see Joueur#isHuman()
	 */
	private final boolean humain;

	/**
	 * Bool�en permettant de savoir si l'objet de la classe Joueur est bien le
	 * joueur qui est en train de jouer actuellement.
	 * 
	 * @see Joueur#getJoueurActif()
	 * @see Joueur#setJoueurActif(boolean)
	 */
	private boolean joueurActif;

	/**
	 * Bool�en disant quand le joueur a fini son tour de jeu.
	 * 
	 * @see Joueur#isaFiniSonTour()
	 * @see Joueur#setaFiniSonTour(boolean)
	 */
	private boolean aFiniSonTour;

	/**
	 * Bool�en permettant de savoir si le joueur peut jouer ou pas, c'est � dire
	 * s'il a assez de Points d'Action pour jouer la carte qu'il le d�sire.
	 * 
	 * @see Joueur#iscanPlay()
	 * @see Joueur#setcanPlay(boolean)
	 */
	private boolean canPlay;

	/**
	 * Bool�en permettant de savoir si des cartes Croyant peuvent �tre rattach�s
	 * aux guides spirituels du joueur.
	 * 
	 * @see Joueur#isCanRattached()
	 * @see Joueur#setCanRattached(boolean)
	 * @see Joueur#canRattached(int)
	 */
	private boolean canRattached;

	/**
	 * Objet de la classe Divinit�, qui repr�sente le joueur dans le jeu.
	 * 
	 * @see Jeu
	 * @see Divinite
	 * @see Joueur#getDiviniteRepresentee()
	 * @see Joueur#setDiviniteRepresentee(Divinite)
	 */
	private Divinite diviniteRepresentee;

	/**
	 * Objet de la classe CampDuJoueur qui repr�sente le camp du joueur, c'est �
	 * dire l� o� sont pos�s ses Guides Spirituels et les Croyants qui y sont
	 * rattach�s.
	 * 
	 * @see GuideSpirituel
	 * @see Croyant
	 * @see CampDuJoueur
	 * @see Joueur#getCampjoueur()
	 * @see Joueur#setCampjoueur(CampDuJoueur)
	 */
	private CampDuJoueur campjoueur;

	/**
	 * Objet de la classe MainDuJoueur qui repr�sente l'ensemble des cartes que
	 * poss�dent le joueur et qui ne sont pas encore pos�s sur le plateau de
	 * jeu.
	 * 
	 * @see MainDuJoueur
	 * @see Joueur#getMainjoueur()
	 * @see Joueur#setMainjoueur(MainDuJoueur)
	 * @see Joueur#
	 */
	private MainDuJoueur mainjoueur;

	/**
	 * Objet de la classe Pioche pour permettre au joueur de piocher et
	 * d�fausser des cartes.
	 * 
	 * @see Carte
	 * @see Pioche
	 * @see Joueur#getPioche()
	 */
	private Pioche pioche;

	/**
	 * Objet de la classe Centre permettant au joueur de poser des Croyants et
	 * interagir avec les Croyants d�j� dedans.
	 * 
	 * @see Croyant
	 * @see Centre
	 * @see Joueur#getCentre()
	 */
	private Centre centre;

	/**
	 * Nombre de points de pri�res que le joueur amasse gr�ce � ses Croyants.
	 * 
	 * @see Croyant
	 * @see Joueur#getNbrPrieres()
	 * @see Joueur#setNbrPrieres(int)
	 */
	private int nbrPrieres;

	/**
	 * Liste en forme de tableau des points d'action selon ses origines, lors
	 * d'un tour de jeu.
	 * 
	 * @see Joueur#getListPA()
	 * @see Joueur#setListPA(int[])
	 * @see Joueur#setListPAindex(int, int)
	 * @see Joueur#afficherListPA()
	 * @see Joueur#regenererPA()
	 * @see Joueur#ajouterPA(int)
	 * @see Joueur#verifierConsommerPA(int)
	 */
	private int ListPA[];

	/**
	 * Liste en forme de tableau des points d'action selon leur origine,
	 * comptabilisant tout les points d'action que le joueur a d�j� eu.
	 * 
	 * @see Joueur#getListPAmax()
	 * @see Joueur#setListPAmax(int[])
	 */
	private int ListPAmax[];

	/**
	 * Constructeur Joueur. Par d�faut, initialisation des bool�ens � false ;
	 * initialisation de la liste des points d'actions et du nombre de points de
	 * pri�re � 0 ; cr�ation du deck, du camp du joueur, et du contr�leur.
	 * 
	 * @param humain
	 *            bool�en permettant de savoir si le joueur est humain (true) ou
	 *            IA (false).
	 * @param divinite
	 *            Divinit� qui repr�sente le joueur, piocher dans la pioche �
	 *            Divinit�.
	 * 
	 * @see Divinite
	 */
	public Joueur(boolean humain, Divinite divinite) {
		this.humain = humain;
		this.diviniteRepresentee = divinite;
		this.aFiniSonTour = false;
		this.canPlay = false;
		this.canRattached = false;
		this.nbrPrieres = 0;
		this.ListPA = new int[3];
		this.ListPA[0] = 0;
		this.ListPA[1] = 0;
		this.ListPA[2] = 0;
		this.mainjoueur = new MainDuJoueur();
		this.campjoueur = new CampDuJoueur();
		this.pioche = Pioche.getInstance();
		this.centre = Centre.getInstance();
		this.sca = Control.getInstance();
	}

	/**
	 * Retourne le bool�en permettant de savoir si le joueur est humain ou un
	 * IA.
	 * 
	 * @return un bool�en.
	 */
	public boolean isHuman() {
		return humain;
	}

	/**
	 * Retourne le bool�en permettant de savoir si le joueur est en train de
	 * jouer ou pas.
	 * 
	 * @return un bool�en.
	 */
	public boolean getJoueurActif() {
		return joueurActif;
	}

	/**
	 * Met � jour le bool�en permettant de savoir si le joueur est en train de
	 * jouer ou pas.
	 * 
	 * @param joueurActif
	 *            le nouveau bool�en qui va remplacer l'actuel.
	 */
	public void setJoueurActif(boolean joueurActif) {
		this.joueurActif = joueurActif;
	}

	/**
	 * Retourne le bool�en permettant de savoir si le joueur a fini son tour ou
	 * pas.
	 * 
	 * @return un bool�en.
	 */
	public boolean isaFiniSonTour() {
		return aFiniSonTour;
	}

	/**
	 * Met � jour le bool�en permettant de savoir si le joueur a fini son tour
	 * ou pas.
	 * 
	 * @param aFiniSonTour
	 *            le nouveau bool�en red�finissant l'actuel.
	 */
	public void setaFiniSonTour(boolean aFiniSonTour) {
		this.aFiniSonTour = aFiniSonTour;
	}

	/**
	 * Retourne le bool�en permettant de savoir si le joueur peut jouer ou non.
	 * 
	 * @return un bool�en.
	 */
	public boolean iscanPlay() {
		return canPlay;
	}

	/**
	 * Met � jour le bool�en permettant de savoir si le joueur peut jouer ou
	 * non.
	 * 
	 * @param canPlay
	 *            le nouveau bool�en qui va remplacer l'actuel.
	 */
	public void setcanPlay(boolean canPlay) {
		this.canPlay = canPlay;
	}

	/**
	 * Retourne le bool�en permettant de savoir si le joueur peut rattacher un
	 * Croyant � un de ces GuideSpirituel.
	 * 
	 * @return un bool�en.
	 */
	public boolean isCanRattached() {
		return canRattached;
	}

	/**
	 * Met � jour le bool�en permettant de savoir si le joueur peut rattacher un
	 * Croyant � un de ces GuideSpirituel.
	 * 
	 * @param canRattached
	 *            le nouveau bool�en qui va remplacer l'actuel.
	 */
	public void setCanRattached(boolean canRattached) {
		this.canRattached = canRattached;
	}

	/**
	 * V�rifie si le joueur peut rattacher un Croyant du Centre en comparant les
	 * dogmes du croyant et celui de la divinit� qui repr�sente le joueur.
	 * 
	 * @param indexcentre
	 *            le num�ro de la carte Croyant qui va �tre compar�.
	 * @return le bool�en canRattached pour savoir si oui ou non on peut
	 *         rattach�.
	 * 
	 * @see Croyant
	 * @see Centre
	 * @see Divinite
	 */
	public boolean canRattached(int indexcentre) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.centre.getCentre().get(indexcentre).getDogme(i).equals(this.diviniteRepresentee.getDogme(j))) {
					this.canRattached = true;
				}
			}
		}
		return canRattached;
	}

	/**
	 * Retourne la Divinit� qui repr�sente le joueur.
	 * 
	 * @return la divinit� du joueur.
	 * 
	 * @see Divinite
	 */
	public Divinite getDiviniteRepresentee() {
		return diviniteRepresentee;
	}

	/**
	 * Met � jour la Divinit� qui repr�sente le joueur.
	 * 
	 * @param diviniteRepresentee
	 *            la nouvelle Divinit� qui repr�sentera le joueur.
	 * 
	 * @see Divinite
	 */
	public void setDiviniteRepresentee(Divinite diviniteRepresentee) {
		this.diviniteRepresentee = diviniteRepresentee;
	}

	/**
	 * Retourne le camp du joueur et donc toute les cartes qu'il a pos�.
	 * 
	 * @return le camp du joueur actuel.
	 */
	public CampDuJoueur getCampjoueur() {
		return campjoueur;
	}

	/**
	 * Met � jour le camp du joueur et toutes les cartes qui le composent.
	 * 
	 * @param campjoueur
	 *            un objet de la classe CampDuJoueur.
	 */
	public void setCampjoueur(CampDuJoueur campjoueur) {
		this.campjoueur = campjoueur;
	}

	/**
	 * Retourne la main du joueur, compos� de toutes les cartes qu'il n'a pas
	 * encore utilis�.
	 * 
	 * @return un objet de la classe MainDuJoueur.
	 * 
	 * @see MainDuJoueur
	 */
	public MainDuJoueur getMainjoueur() {
		return mainjoueur;
	}

	/**
	 * Met � jour la main du joueur, compos� de toutes les cartes qu'il n'a pas
	 * encore utilis�.
	 * 
	 * @param mainjoueur
	 *            la nouvelle main du joueur qui va rmeplacer l'actuel.
	 * 
	 * @see MainDuJoueur
	 */
	public void setMainjoueur(MainDuJoueur mainjoueur) {
		this.mainjoueur = mainjoueur;
	}

	/**
	 * Retourne la pioche des cartes, o� le joueur peut piocher et d�fausser.
	 * 
	 * @return l'instance de la classe Pioche.
	 * 
	 * @see Pioche
	 */
	public Pioche getPioche() {
		return pioche;
	}

	/**
	 * Retourne l'instance du centre, o� se situent les Croyants pos�s par les
	 * joueurs.
	 * 
	 * @return l'instance de la classe Centre..
	 * 
	 * @see Centre
	 * @see Joueur
	 * @see Croyant
	 */
	public Centre getCentre() {
		return centre;
	}

	/**
	 * Retourne le nombre de points de pri�re que comptabilise le joueur avec
	 * ses Croyants.
	 * 
	 * @return un nombre de points de pri�re;
	 * 
	 * @see Croyant
	 */
	public int getNbrPrieres() {
		for (int i = 0; i < this.campjoueur.getCamp().size(); i++) {
			this.nbrPrieres += this.campjoueur.getCamp().get(i).getPP();
		}
		return this.nbrPrieres;
	}

	/**
	 * Met � jour le nombre de points de pri�res du joueur qu'il comptabilise
	 * avec ses Croyants.
	 * 
	 * @param pointsPrieres
	 *            le nouveau nombre de points de pri�res qui va remplacer
	 *            l'actuel.
	 * 
	 * @see Croyant
	 */
	public void setNbrPrieres(int pointsPrieres) {
		this.nbrPrieres = pointsPrieres;
	}

	/**
	 * Retourne la liste des points d'action du joueur, selon leur origine (0 :
	 * Jour, 1 : Nuit, 2 : N�ant), qui sont l'ensemble des points d'actions que
	 * le joueur a poss�d� au moins une fois.
	 * 
	 * @return un tableau de points d'actions.
	 */
	public int[] getListPAmax() {
		return ListPAmax;
	}

	/**
	 * Met � jour la liste des points d'acion que le joueur peut avoir au
	 * maximum (c'est � dire que tout les points d'actions depuis le d�but du
	 * jeu sont comptavilis�s).
	 * 
	 * @param listPAmax
	 *            un tableau de points d'actions.
	 */
	public void setListPAmax(int[] listPAmax) {
		ListPAmax = listPAmax;
	}

	/**
	 * Retourne la liste des points d'action du joueur, selon leur origine (0 :
	 * Jour, 1 : Nuit, 2 : N�ant).
	 * 
	 * @return un tableau de points d'actions.
	 */
	public int[] getListPA() {
		return ListPA;
	}

	/**
	 * Met � jour la liste des points d'action du joueur, selon leur origine (0
	 * : Jour, 1 : Nuit, 2 : N�ant).
	 * 
	 * @param listPA
	 *            un nouveau tableau de points d'actions.
	 */
	public void setListPA(int[] listPA) {
		this.ListPA = listPA;
	}

	/**
	 * Met � jour un �l�ment du tableau des points d'actions du joueur � une
	 * certaine valeur.
	 * 
	 * @param index
	 *            le num�ro de l'�l�ment qui va �tre chang� dans le tableau. (0
	 *            : Jour ; 1 : Nuit ; 2 : N�ant)
	 * @param valeur
	 *            la valeur qui va �tre affect� � l'�l�ment dans le tableau.
	 */
	public void setListPAindex(int index, int valeur) {
		for (int i = 0; i < valeur; i++) {
			this.ListPA[index]++;
		}
		this.ListPAmax[index] = this.ListPA[index];
	}

	/**
	 * Met � jour la liste des points d'actions selon la face du d�.
	 * 
	 * @param face
	 *            nombre correspondant � la face tir� quand le d� a �t� lanc�.
	 * 
	 * @see De
	 */
	public void ajouterPA(int face) {
		int origine = this.getDiviniteRepresentee().getOrigine();
		switch (face) {
		case 0:
			if (origine == 1) {
				this.ListPA[0] += 2;
			} else if (origine == 2) {
				this.ListPA[0] += 1;
			}
			break;
		case 1:
			if (origine == 5) {
				this.ListPA[1] += 2;
			} else if (origine == 4) {
				this.ListPA[1] += 1;
			}
			break;
		case 2:
			if ((origine == 2) || (origine == 4)) {
				this.ListPA[2] += 1;
			}
			break;
		}
		this.setListPAmax(this.ListPA);
	}

	/**
	 * Met � jour la liste des points d'actions du joueur par la liste des
	 * points d'actions maximale.
	 */
	public void regenererPA() {
		this.setListPA(this.ListPAmax);
	}

	/**
	 * V�rifie que le joueur peut jouer la carte qu'il d�sire. Selon l'origine
	 * de la carte, il faut sacrifier un point de cette origine ou deux
	 * d'origine N�ant. Si le joueur a assez de points d'actions, ils sont
	 * consomm�s.
	 * 
	 * @param rep
	 *            le num�ro de la carte que le joueur veut.
	 */
	public void verifierConsommerPA(int rep) {
		switch (this.getMainjoueur().getMain().get(rep).getOrigine()) {
		case (1):
			if (this.getListPA()[0] > 0) {
				this.setcanPlay(true);
				this.getListPA()[0]--;
			} else {
				System.out.println("Vous n'avez pas assez de PA pour jouer la carte");
			}
			break;
		case (3):
			try {
				if (this.getListPA()[2] > 0) {
					this.setcanPlay(true);
					this.getListPA()[2]--;
				} else if (this.getListPA()[0] > 2 || this.getListPA()[1] > 2) {
					System.out.println("Choisir quel type de PA � d�penser:\n 1:Jour\n 2:Nuit");
					sca.repInt();
					switch (sca.answer) {
					case (1):
						this.setcanPlay(true);
						this.getListPA()[0] = this.getListPA()[0] - 2;
						break;
					case (2):
						this.setcanPlay(true);
						this.getListPA()[1] = this.getListPA()[1] - 2;
						break;
					default:
						System.out.println("Vous n'avez entrer ni 1 ni 2.");
					}
				} else {
					System.out.println("Vous n'avez pas assez de PA pour jouer la carte");
				}
				break;
			} catch (InputMismatchException e) {
				System.err.println("Erreur : vous devez entrer un nombre entier.");
			}
		case (5):
			if (this.getListPA()[1] > 0) {
				this.setcanPlay(true);
				this.getListPA()[1]--;
			} else {
				System.out.println("Vous n'avez pas assez de PA pour jouer la carte");
			}
			break;
		case (0):
			this.setcanPlay(true);
			break;
		default:
			System.out.println("Bien essay�");
		}
	}

	/**
	 * Affiche la liste des points d'actions selon leur origine.
	 */
	public String afficherListPA() {
		System.out.println(this.getDiviniteRepresentee().getNom() + " a :\n");
		String s0 = this.getListPA()[0] + " points Jour ";
		String s1 = this.getListPA()[1] + " points Nuit ";
		String s2 = this.getListPA()[2] + " points N�ant";
		String s = s0 + s1 + s2;
		return s;
	}

	/**
	 * Joue le tour d'un Joueur.<br>
	 * Pour tout joueur, cette m�thode ajoute le nombre de points d'actions que
	 * donne le d�. Pour un JoueurHumain, cette m�thode affiche les informations
	 * n�cessaire � la prise de d�cision de l'utilisateur, puis elle lui propose
	 * de choisir ce qu'il veut faire (piocher, d�fausser, r�agir).<br>
	 * Pour un JoueurIA, cette m�thode ex�cute la strat�gie du JoueurIA et
	 * affiche le plateau du jeu � la fin du tour.
	 * 
	 * @see IA
	 * @see Humain
	 */
	public void jouerTour(int face) {
		this.prendreLaMain();
		this.ajouterPA(face);
		if (this.isHuman()) {
			this.mainjoueur.afficherMain();
			this.afficherListPA();
			this.centre.afficherCentre();
			this.campjoueur.afficherCamp();
			this.defausserCarte();
			this.piocherCarte();
			this.reagir();
		} else {
			this.afficherListPA();
			((IA) this).executerStrategy();
			this.centre.afficherCentre();
			this.campjoueur.afficherCamp();
		}
		this.finirLeTour();
	}

	/**
	 * Affiche au joueur humain les possibilit�s qu'il a apr�s avoir piocher
	 * et/ou d�fausser : Poser une carte, rattacher un croyant � un guide
	 * spirituel, utiliser la capacit� de sa divinit�, sacrifier une carte ou ne
	 * rien faire. Ainsi, selon le choix qu'il fait (en forme de nombre), la
	 * m�thode demande soit des informations suppl�mentaires, soit applique la
	 * demande du joueur.
	 */
	public void reagir() {
		while (this.isaFiniSonTour() == false) {
			System.out.println("Que veux-tu faire ?\n");
			System.out.println("1 : Jouer une carte. ");
			System.out.println("2 : Rattacher un Croyant � un Guide Spirituel.");
			System.out.println("3 : Utiliser la capacit� de sa Divinit�.");
			System.out.println("4 : Sacrifier une carte");
			System.out.println("5 : Ne rien faire.");
			try {
				sca.repInt();
				switch (sca.answer) {
				case 1:
					this.mainjoueur.afficherMain();
					this.jouerCarte();
					break;
				case 2:
					if (!this.campjoueur.getCamp().isEmpty() && !this.centre.getCentre().isEmpty()) {
						this.campjoueur.afficherCamp();
						System.out.println(
								"Donne-moi l'index du Guide Spirituel auquel vous voulez rattacher des cartes :");
						sca.repInt();
						int indexcamp = sca.answer;
						this.centre.afficherCentre();
						System.out.println("Donne-moi l'index du Croyant que vous voulez rattacher :");
						sca.repInt();
						int indexcentre = sca.answer;
						if (this.canRattached(indexcentre)) {
							this.campjoueur.getCamp().get(indexcamp)
									.rattacherCroyants(this.centre.donnerCroyant(indexcentre));
						} else {
							System.out.println("Rattachement impossible :  aucun dogmes en commun");
						}
					}
					System.out.println("Le camp et/ou le centre est vide, donc vous ne pouvez pas faire cela.");
					break;
				case 3:
					System.out.println("Le joueur utilise sa capacit�");
					this.diviniteRepresentee.utiliserCapacite();
					break;
				case 4:
					this.campjoueur.afficherCamp();
					System.out.println("1: Sacrifier un  Croyant\n2: Sacrifier un Guide Spirituel");

					sca.repInt();
					switch (sca.answer) {
					case (1):
						System.out.println("Donnez l'index du Guide Spirituel auquel le Croyant est rattach�");
						sca.repInt();
						int rep1 = sca.answer;
						System.out.println("Donnez l'index du Croyant dans la liste du Guide Spirituel");
						sca.repInt();
						int rep2 = sca.answer;
						this.campjoueur.getCamp().get(rep1).getCroyantsRattaches().get(rep2).sacrifice();
						this.campjoueur.getCamp().get(rep1).retirerCroyant(rep2);
						break;
					case (2):
						System.out.println("Donnez l'index du Guide Spirituel auquel le Croyant est rattach�");
						sca.repInt();
						int rep3 = sca.answer;
						this.campjoueur.getCamp().get(rep3).sacrifice();
						this.campjoueur.retirerGS(rep3);
						break;
					}
					System.out.println();
					break;
				default:
					System.out.println("Le joueur ne fait rien.");
				}
			} catch (InputMismatchException e) {
				System.err.println("Erreur : Vous devez entrer un nombre entier.");
			} catch (IndexOutOfBoundsException o) {
				System.err.println(
						"Erreur : Vous devez entrer un num�ro des cartes que vous poss�dez. R�f�rez vous au nombre de carte et leur num�ro attribu�.");
			}
			System.out.println("As-tu fini ton tour ? (Entrez 1 pour Oui, sinon par d�faut, �a sera Non.");
			sca.repInt();
			if (sca.answer != 1) {
				this.setaFiniSonTour(true);
			}
		}
	}

	/**
	 * V�rifie si le joueur poss�de des cartes dans sa main qu'il peut
	 * d�fausser, puis si c'est le cas, enl�ve unecette carte de la main choisie par le joueur, afin de
	 * la mettre dans la pioche.
	 * 
	 * @see MainDuJoueur
	 * @see PiochE
	 */
	public void defausserCarte() {
		System.out.println("Veux-tu d�fausser une carte ?(Entrez 1:Oui, sinon par d�faut �a sera Non.)");
		sca.repInt();
		int reponse = sca.answer;
		if (reponse == 1) {
			try {
				if (this.getMainjoueur().getNbCarte() > 0) {
					this.getMainjoueur().afficherMain();
					System.out.println("Donne-moi l'index de cette carte");
					sca.repInt();
					int indexCarte = sca.answer;
					this.getPioche().recuperer(this.getMainjoueur().defausserCarte(indexCarte));
				} else {
					System.out.println("Vous n'avez pas de cartes � d�fausser");
				}
			} catch (InputMismatchException e) {
				System.out.println("Erreur : vous devez entrer le nombre entier correspondant au type de PA.");
			} catch (IndexOutOfBoundsException o) {
				System.err.println("Erreur : Vous devez entrer un nombre compris entre 0 et 6.");
			}
		}
	}

	/**
	 * V�rifie qu'il n'a pas d�j� sept cartes dans la main du joueur, qui est le
	 * maximum de cartes autoris�es. Si ce n'est pas le cas, retire une carte de
	 * la pioche pour le mettre dans la main du joueur.
	 * 
	 * @see Carte
	 * @see Pioche
	 * @see MainDuJoueur
	 */
	public void piocherCarte() {
		if (this.getMainjoueur().getNbCarte() < 7) {
			System.out.println("Vous avez moins de 7 cartes, nous allons compl�ter votre main jusqu'� 7 : \n");
			while (this.getMainjoueur().getNbCarte() < 7) {
				Carte carte = this.getPioche().piocher();
				this.getMainjoueur().ajouterCarte(carte);
			}
		}
		this.mainjoueur.afficherMain();
	}

	/**
	 * Affiche la divinit� qui repr�sente le joueur.
	 * 
	 * @see Divinite
	 */
	public void afficherDivinite() {
		this.diviniteRepresentee.afficherDivinitebis();
	}

	/**
	 * Affiche le joueur qui commence � jouer, et qui devient le joueurActif
	 * (true).
	 * 
	 * @see Joueur#joueurActif
	 */
	public void prendreLaMain() {
		System.out.println(this.diviniteRepresentee.getNom() + " prends la main !");
		this.joueurActif = true;
	}

	/**
	 * Affiche la fin du tour d'un joueur, qui n'est plus le joueurActif
	 * (false).
	 * 
	 * @see Joueur#joueurActif
	 */
	public void finirLeTour() {
		System.out.println(this.diviniteRepresentee.getNom() + " a fini de jouer.");
		this.joueurActif = false;
	}

	/**
	 * Pose une carte apr�s avoir demand� son index dans la main du joueur. Si
	 * c'est un Croyant, le pose dans le Centre. Si c'est un GuideSpirituel, le
	 * pose dans le camp. Si c'est une carte DeusEx ou Apocalypse, d�fausse la
	 * carte et active son effet de sacrifice.
	 * 
	 * @see Croyant
	 * @see Centre
	 * @see GuideSpirituel
	 * @see CampDuJoueur
	 * @see DeusEx
	 * @see Apocalypse
	 */
	public void jouerCarte() {
		System.out.println("Donner l'index de la carte � jouer");
		try {
			sca.repInt();
			int index = sca.answer;
			switch (this.mainjoueur.getMain().get(index).getType()) {
			case ("Croyants"):
				this.verifierConsommerPA(index);
				if (this.canPlay == true) {
					Croyant croyant = (Croyant) this.mainjoueur.defausserCarte(index);
					this.centre.ajouterCroyant(croyant);
				} else {
					System.out.println("donc vous ne pouvez toujours pas jouer la carte");
				}
				this.canPlay = false;
				break;
			case ("Guide Spirituel"):
				this.verifierConsommerPA(index);
				if (this.canPlay == true) {
					GuideSpirituel gs = (GuideSpirituel) this.mainjoueur.defausserCarte(index);
					this.campjoueur.ajouterCarte(gs);
				} else {
					System.out.println("donc vous ne pouvez toujours pas jouer la carte");
				}
				this.canPlay = false;
				break;
			case ("Deus Ex"):
				this.verifierConsommerPA(index);
				if (this.canPlay == true) {
					DeusEx de = (DeusEx) this.mainjoueur.defausserCarte(index);
					de.effet();
				} else {
					System.out.println("donc vous ne pouvez toujours pas jouer la carte");
				}
				this.canPlay = false;
				break;
			case ("Apocalypse"):
				this.verifierConsommerPA(index);
				if (this.canPlay == true) {
					Apocalypse apo = (Apocalypse) this.mainjoueur.defausserCarte(index);
					apo.effet();
				} else {
					System.out.println("donc vous ne pouvez toujours pas jouer la carte");
				}
				this.canPlay = false;
				break;
			}
		} catch (InputMismatchException e) {
			System.err.println(
					"Erreur : vous devez entrer un nombre entier en tant qu'index. R�f�rez vous au num�ro de la carte.");
		} catch (IndexOutOfBoundsException o) {
			System.err.println("Erreur : Vous devez entrer un nombre compris entre 0 et 6.");
		}
	}
}