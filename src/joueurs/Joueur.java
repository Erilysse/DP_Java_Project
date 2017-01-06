package joueurs;

import java.util.*;
import cartes.*;
import controleur.Control;
import pioches.Pioche;
import plateau_du_jeu.CampDuJoueur;
import plateau_du_jeu.Centre;

/**
 * Joueur est une classe qui modélise un joueur. Il implémente l'interface
 * Observable. Il possède un camp, une main, un nombre de points de prières, une
 * liste de Points d'action. Il est représenté par une divinité, et connaît le
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
	 * Instance de la classe Control qui permets de gérer les entrées claviers.
	 * 
	 */
	private Control sca;

	/**
	 * Booléen permettant de savoir si le joueur est un humain ou un IA.
	 * 
	 * @see Joueur#isHuman()
	 */
	private final boolean humain;

	/**
	 * Booléen permettant de savoir si l'objet de la classe Joueur est bien le
	 * joueur qui est en train de jouer actuellement.
	 * 
	 * @see Joueur#getJoueurActif()
	 * @see Joueur#setJoueurActif(boolean)
	 */
	private boolean joueurActif;

	/**
	 * Booléen disant quand le joueur a fini son tour de jeu.
	 * 
	 * @see Joueur#isaFiniSonTour()
	 * @see Joueur#setaFiniSonTour(boolean)
	 */
	private boolean aFiniSonTour;

	/**
	 * Booléen permettant de savoir si le joueur peut jouer ou pas, c'est à dire
	 * s'il a assez de Points d'Action pour jouer la carte qu'il le désire.
	 * 
	 * @see Joueur#iscanPlay()
	 * @see Joueur#setcanPlay(boolean)
	 */
	private boolean canPlay;

	/**
	 * Booléen permettant de savoir si des cartes Croyant peuvent être rattachés
	 * aux guides spirituels du joueur.
	 * 
	 * @see Joueur#isCanRattached()
	 * @see Joueur#setCanRattached(boolean)
	 * @see Joueur#canRattached(int)
	 */
	private boolean canRattached;

	/**
	 * Objet de la classe Divinité, qui représente le joueur dans le jeu.
	 * 
	 * @see Jeu
	 * @see Divinite
	 * @see Joueur#getDiviniteRepresentee()
	 * @see Joueur#setDiviniteRepresentee(Divinite)
	 */
	private Divinite diviniteRepresentee;

	/**
	 * Objet de la classe CampDuJoueur qui représente le camp du joueur, c'est à
	 * dire là où sont posés ses Guides Spirituels et les Croyants qui y sont
	 * rattachés.
	 * 
	 * @see GuideSpirituel
	 * @see Croyant
	 * @see CampDuJoueur
	 * @see Joueur#getCampjoueur()
	 * @see Joueur#setCampjoueur(CampDuJoueur)
	 */
	private CampDuJoueur campjoueur;

	/**
	 * Objet de la classe MainDuJoueur qui représente l'ensemble des cartes que
	 * possèdent le joueur et qui ne sont pas encore posés sur le plateau de
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
	 * défausser des cartes.
	 * 
	 * @see Carte
	 * @see Pioche
	 * @see Joueur#getPioche()
	 */
	private Pioche pioche;

	/**
	 * Objet de la classe Centre permettant au joueur de poser des Croyants et
	 * interagir avec les Croyants déjà dedans.
	 * 
	 * @see Croyant
	 * @see Centre
	 * @see Joueur#getCentre()
	 */
	private Centre centre;

	/**
	 * Nombre de points de prières que le joueur amasse grâce à ses Croyants.
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
	 * comptabilisant tout les points d'action que le joueur a déjà eu.
	 * 
	 * @see Joueur#getListPAmax()
	 * @see Joueur#setListPAmax(int[])
	 */
	private int ListPAmax[];

	/**
	 * Constructeur Joueur. Par défaut, initialisation des booléens à false ;
	 * initialisation de la liste des points d'actions et du nombre de points de
	 * prière à 0 ; création du deck, du camp du joueur, et du contrôleur.
	 * 
	 * @param humain
	 *            booléen permettant de savoir si le joueur est humain (true) ou
	 *            IA (false).
	 * @param divinite
	 *            Divinité qui représente le joueur, piocher dans la pioche à
	 *            Divinité.
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
	 * Retourne le booléen permettant de savoir si le joueur est humain ou un
	 * IA.
	 * 
	 * @return un booléen.
	 */
	public boolean isHuman() {
		return humain;
	}

	/**
	 * Retourne le booléen permettant de savoir si le joueur est en train de
	 * jouer ou pas.
	 * 
	 * @return un booléen.
	 */
	public boolean getJoueurActif() {
		return joueurActif;
	}

	/**
	 * Met à jour le booléen permettant de savoir si le joueur est en train de
	 * jouer ou pas.
	 * 
	 * @param joueurActif
	 *            le nouveau booléen qui va remplacer l'actuel.
	 */
	public void setJoueurActif(boolean joueurActif) {
		this.joueurActif = joueurActif;
	}

	/**
	 * Retourne le booléen permettant de savoir si le joueur a fini son tour ou
	 * pas.
	 * 
	 * @return un booléen.
	 */
	public boolean isaFiniSonTour() {
		return aFiniSonTour;
	}

	/**
	 * Met à jour le booléen permettant de savoir si le joueur a fini son tour
	 * ou pas.
	 * 
	 * @param aFiniSonTour
	 *            le nouveau booléen redéfinissant l'actuel.
	 */
	public void setaFiniSonTour(boolean aFiniSonTour) {
		this.aFiniSonTour = aFiniSonTour;
	}

	/**
	 * Retourne le booléen permettant de savoir si le joueur peut jouer ou non.
	 * 
	 * @return un booléen.
	 */
	public boolean iscanPlay() {
		return canPlay;
	}

	/**
	 * Met à jour le booléen permettant de savoir si le joueur peut jouer ou
	 * non.
	 * 
	 * @param canPlay
	 *            le nouveau booléen qui va remplacer l'actuel.
	 */
	public void setcanPlay(boolean canPlay) {
		this.canPlay = canPlay;
	}

	/**
	 * Retourne le booléen permettant de savoir si le joueur peut rattacher un
	 * Croyant à un de ces GuideSpirituel.
	 * 
	 * @return un booléen.
	 */
	public boolean isCanRattached() {
		return canRattached;
	}

	/**
	 * Met à jour le booléen permettant de savoir si le joueur peut rattacher un
	 * Croyant à un de ces GuideSpirituel.
	 * 
	 * @param canRattached
	 *            le nouveau booléen qui va remplacer l'actuel.
	 */
	public void setCanRattached(boolean canRattached) {
		this.canRattached = canRattached;
	}

	/**
	 * Vérifie si le joueur peut rattacher un Croyant du Centre en comparant les
	 * dogmes du croyant et celui de la divinité qui représente le joueur.
	 * 
	 * @param indexcentre
	 *            le numéro de la carte Croyant qui va être comparé.
	 * @return le booléen canRattached pour savoir si oui ou non on peut
	 *         rattaché.
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
	 * Retourne la Divinité qui représente le joueur.
	 * 
	 * @return la divinité du joueur.
	 * 
	 * @see Divinite
	 */
	public Divinite getDiviniteRepresentee() {
		return diviniteRepresentee;
	}

	/**
	 * Met à jour la Divinité qui représente le joueur.
	 * 
	 * @param diviniteRepresentee
	 *            la nouvelle Divinité qui représentera le joueur.
	 * 
	 * @see Divinite
	 */
	public void setDiviniteRepresentee(Divinite diviniteRepresentee) {
		this.diviniteRepresentee = diviniteRepresentee;
	}

	/**
	 * Retourne le camp du joueur et donc toute les cartes qu'il a posé.
	 * 
	 * @return le camp du joueur actuel.
	 */
	public CampDuJoueur getCampjoueur() {
		return campjoueur;
	}

	/**
	 * Met à jour le camp du joueur et toutes les cartes qui le composent.
	 * 
	 * @param campjoueur
	 *            un objet de la classe CampDuJoueur.
	 */
	public void setCampjoueur(CampDuJoueur campjoueur) {
		this.campjoueur = campjoueur;
	}

	/**
	 * Retourne la main du joueur, composé de toutes les cartes qu'il n'a pas
	 * encore utilisé.
	 * 
	 * @return un objet de la classe MainDuJoueur.
	 * 
	 * @see MainDuJoueur
	 */
	public MainDuJoueur getMainjoueur() {
		return mainjoueur;
	}

	/**
	 * Met à jour la main du joueur, composé de toutes les cartes qu'il n'a pas
	 * encore utilisé.
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
	 * Retourne la pioche des cartes, où le joueur peut piocher et défausser.
	 * 
	 * @return l'instance de la classe Pioche.
	 * 
	 * @see Pioche
	 */
	public Pioche getPioche() {
		return pioche;
	}

	/**
	 * Retourne l'instance du centre, où se situent les Croyants posés par les
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
	 * Retourne le nombre de points de prière que comptabilise le joueur avec
	 * ses Croyants.
	 * 
	 * @return un nombre de points de prière;
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
	 * Met à jour le nombre de points de prières du joueur qu'il comptabilise
	 * avec ses Croyants.
	 * 
	 * @param pointsPrieres
	 *            le nouveau nombre de points de prières qui va remplacer
	 *            l'actuel.
	 * 
	 * @see Croyant
	 */
	public void setNbrPrieres(int pointsPrieres) {
		this.nbrPrieres = pointsPrieres;
	}

	/**
	 * Retourne la liste des points d'action du joueur, selon leur origine (0 :
	 * Jour, 1 : Nuit, 2 : Néant), qui sont l'ensemble des points d'actions que
	 * le joueur a possédé au moins une fois.
	 * 
	 * @return un tableau de points d'actions.
	 */
	public int[] getListPAmax() {
		return ListPAmax;
	}

	/**
	 * Met à jour la liste des points d'acion que le joueur peut avoir au
	 * maximum (c'est à dire que tout les points d'actions depuis le début du
	 * jeu sont comptavilisés).
	 * 
	 * @param listPAmax
	 *            un tableau de points d'actions.
	 */
	public void setListPAmax(int[] listPAmax) {
		ListPAmax = listPAmax;
	}

	/**
	 * Retourne la liste des points d'action du joueur, selon leur origine (0 :
	 * Jour, 1 : Nuit, 2 : Néant).
	 * 
	 * @return un tableau de points d'actions.
	 */
	public int[] getListPA() {
		return ListPA;
	}

	/**
	 * Met à jour la liste des points d'action du joueur, selon leur origine (0
	 * : Jour, 1 : Nuit, 2 : Néant).
	 * 
	 * @param listPA
	 *            un nouveau tableau de points d'actions.
	 */
	public void setListPA(int[] listPA) {
		this.ListPA = listPA;
	}

	/**
	 * Met à jour un élément du tableau des points d'actions du joueur à une
	 * certaine valeur.
	 * 
	 * @param index
	 *            le numéro de l'élément qui va être changé dans le tableau. (0
	 *            : Jour ; 1 : Nuit ; 2 : Néant)
	 * @param valeur
	 *            la valeur qui va être affecté à l'élément dans le tableau.
	 */
	public void setListPAindex(int index, int valeur) {
		for (int i = 0; i < valeur; i++) {
			this.ListPA[index]++;
		}
		this.ListPAmax[index] = this.ListPA[index];
	}

	/**
	 * Met à jour la liste des points d'actions selon la face du dé.
	 * 
	 * @param face
	 *            nombre correspondant à la face tiré quand le dé a été lancé.
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
	 * Met à jour la liste des points d'actions du joueur par la liste des
	 * points d'actions maximale.
	 */
	public void regenererPA() {
		this.setListPA(this.ListPAmax);
	}

	/**
	 * Vérifie que le joueur peut jouer la carte qu'il désire. Selon l'origine
	 * de la carte, il faut sacrifier un point de cette origine ou deux
	 * d'origine Néant. Si le joueur a assez de points d'actions, ils sont
	 * consommés.
	 * 
	 * @param rep
	 *            le numéro de la carte que le joueur veut.
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
					System.out.println("Choisir quel type de PA à dépenser:\n 1:Jour\n 2:Nuit");
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
			System.out.println("Bien essayé");
		}
	}

	/**
	 * Affiche la liste des points d'actions selon leur origine.
	 */
	public String afficherListPA() {
		System.out.println(this.getDiviniteRepresentee().getNom() + " a :\n");
		String s0 = this.getListPA()[0] + " points Jour ";
		String s1 = this.getListPA()[1] + " points Nuit ";
		String s2 = this.getListPA()[2] + " points Néant";
		String s = s0 + s1 + s2;
		return s;
	}

	/**
	 * Joue le tour d'un Joueur.<br>
	 * Pour tout joueur, cette méthode ajoute le nombre de points d'actions que
	 * donne le dé. Pour un JoueurHumain, cette méthode affiche les informations
	 * nécessaire à la prise de décision de l'utilisateur, puis elle lui propose
	 * de choisir ce qu'il veut faire (piocher, défausser, réagir).<br>
	 * Pour un JoueurIA, cette méthode exécute la stratégie du JoueurIA et
	 * affiche le plateau du jeu à la fin du tour.
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
	 * Affiche au joueur humain les possibilités qu'il a après avoir piocher
	 * et/ou défausser : Poser une carte, rattacher un croyant à un guide
	 * spirituel, utiliser la capacité de sa divinité, sacrifier une carte ou ne
	 * rien faire. Ainsi, selon le choix qu'il fait (en forme de nombre), la
	 * méthode demande soit des informations supplémentaires, soit applique la
	 * demande du joueur.
	 */
	public void reagir() {
		while (this.isaFiniSonTour() == false) {
			System.out.println("Que veux-tu faire ?\n");
			System.out.println("1 : Jouer une carte. ");
			System.out.println("2 : Rattacher un Croyant à un Guide Spirituel.");
			System.out.println("3 : Utiliser la capacité de sa Divinité.");
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
					System.out.println("Le joueur utilise sa capacité");
					this.diviniteRepresentee.utiliserCapacite();
					break;
				case 4:
					this.campjoueur.afficherCamp();
					System.out.println("1: Sacrifier un  Croyant\n2: Sacrifier un Guide Spirituel");

					sca.repInt();
					switch (sca.answer) {
					case (1):
						System.out.println("Donnez l'index du Guide Spirituel auquel le Croyant est rattaché");
						sca.repInt();
						int rep1 = sca.answer;
						System.out.println("Donnez l'index du Croyant dans la liste du Guide Spirituel");
						sca.repInt();
						int rep2 = sca.answer;
						this.campjoueur.getCamp().get(rep1).getCroyantsRattaches().get(rep2).sacrifice();
						this.campjoueur.getCamp().get(rep1).retirerCroyant(rep2);
						break;
					case (2):
						System.out.println("Donnez l'index du Guide Spirituel auquel le Croyant est rattaché");
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
						"Erreur : Vous devez entrer un numéro des cartes que vous possédez. Référez vous au nombre de carte et leur numéro attribué.");
			}
			System.out.println("As-tu fini ton tour ? (Entrez 1 pour Oui, sinon par défaut, ça sera Non.");
			sca.repInt();
			if (sca.answer != 1) {
				this.setaFiniSonTour(true);
			}
		}
	}

	/**
	 * Vérifie si le joueur possède des cartes dans sa main qu'il peut
	 * défausser, puis si c'est le cas, enlève unecette carte de la main choisie par le joueur, afin de
	 * la mettre dans la pioche.
	 * 
	 * @see MainDuJoueur
	 * @see PiochE
	 */
	public void defausserCarte() {
		System.out.println("Veux-tu défausser une carte ?(Entrez 1:Oui, sinon par défaut ça sera Non.)");
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
					System.out.println("Vous n'avez pas de cartes à défausser");
				}
			} catch (InputMismatchException e) {
				System.out.println("Erreur : vous devez entrer le nombre entier correspondant au type de PA.");
			} catch (IndexOutOfBoundsException o) {
				System.err.println("Erreur : Vous devez entrer un nombre compris entre 0 et 6.");
			}
		}
	}

	/**
	 * Vérifie qu'il n'a pas déjà sept cartes dans la main du joueur, qui est le
	 * maximum de cartes autorisées. Si ce n'est pas le cas, retire une carte de
	 * la pioche pour le mettre dans la main du joueur.
	 * 
	 * @see Carte
	 * @see Pioche
	 * @see MainDuJoueur
	 */
	public void piocherCarte() {
		if (this.getMainjoueur().getNbCarte() < 7) {
			System.out.println("Vous avez moins de 7 cartes, nous allons compléter votre main jusqu'à 7 : \n");
			while (this.getMainjoueur().getNbCarte() < 7) {
				Carte carte = this.getPioche().piocher();
				this.getMainjoueur().ajouterCarte(carte);
			}
		}
		this.mainjoueur.afficherMain();
	}

	/**
	 * Affiche la divinité qui représente le joueur.
	 * 
	 * @see Divinite
	 */
	public void afficherDivinite() {
		this.diviniteRepresentee.afficherDivinitebis();
	}

	/**
	 * Affiche le joueur qui commence à jouer, et qui devient le joueurActif
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
	 * Pose une carte après avoir demandé son index dans la main du joueur. Si
	 * c'est un Croyant, le pose dans le Centre. Si c'est un GuideSpirituel, le
	 * pose dans le camp. Si c'est une carte DeusEx ou Apocalypse, défausse la
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
		System.out.println("Donner l'index de la carte à jouer");
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
					"Erreur : vous devez entrer un nombre entier en tant qu'index. Référez vous au numéro de la carte.");
		} catch (IndexOutOfBoundsException o) {
			System.err.println("Erreur : Vous devez entrer un nombre compris entre 0 et 6.");
		}
	}
}