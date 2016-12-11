package divinae;

import java.util.*;

public abstract class Joueur {
	private boolean aFiniSonTour;
	private int ListPA[];
	private int ListPAmax[];
	private Divinite diviniteRepresentee;
	private int nbrPrieres;
	private CampDuJoueur campjoueur;
	private MainDuJoueur mainjoueur;
	private Pioche pioche;
	private final boolean humain;
	private static Joueur joueurActif;
	private Centre centre;
	private Scanner sc;
	private boolean canPlay;

	public Joueur(boolean humain, Divinite divinite) {
		this.aFiniSonTour = false;
		// attribution de la divinité
		this.diviniteRepresentee = divinite;
		// initialisation du nombre de points de prières à zéro.
		this.nbrPrieres = 0;
		// initialisation des PA à zéro
		this.ListPA = new int[3];
		this.ListPA[0] = 0;
		this.ListPA[1] = 0;
		this.ListPA[2] = 0;
		// on dit que si le joueur est humain ou pas.
		this.humain = humain;
		// Attribution de la divinité
		// création de son deck et son camp
		this.mainjoueur = new MainDuJoueur();
		this.campjoueur = new CampDuJoueur();
		this.pioche = Pioche.getInstance();
		this.centre = Centre.getInstance();
		// création du scanner
		sc = new Scanner(System.in);
		// initialisation de canPlay : attribut permettant ou pas de jouer une
		// carte
		this.setCanPlay(false);
	}

	public int getNbrPrieres() {
		return nbrPrieres;
	}

	public void setNbrPrieres(int pointsPrieres) {
		this.nbrPrieres = pointsPrieres;
	}

	public CampDuJoueur getCampjoueur() {
		return campjoueur;
	}

	public void setCampjoueur(CampDuJoueur campjoueur) {
		this.campjoueur = campjoueur;
	}

	public Divinite getDiviniteRepresentee() {
		return diviniteRepresentee;
	}

	public void setDiviniteRepresentee(Divinite diviniteRepresentee) {
		this.diviniteRepresentee = diviniteRepresentee;
	}

	public Centre getCentre() {
		return centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}

	public MainDuJoueur getMainjoueur() {
		return mainjoueur;
	}

	public void setMainjoueur(MainDuJoueur mainjoueur) {
		this.mainjoueur = mainjoueur;
	}

	public boolean isHuman() {
		return humain;
	}

	public boolean isaFiniSonTour() {
		return aFiniSonTour;
	}

	public void setaFiniSonTour(boolean aFiniSonTour) {
		this.aFiniSonTour = aFiniSonTour;
	}

	public Pioche getPioche() {
		return pioche;
	}

	public void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}

	public static Joueur getJoueurActif() {
		return joueurActif;
	}

	public static void setJoueurActif(Joueur joueurActif) {
		Joueur.joueurActif = joueurActif;
	}

	public boolean iscanPlay() {
		return isCanPlay();
	}

	public void setcanPlay(boolean canPlay) {
		this.setCanPlay(canPlay);
	}

	public int[] getListPAmax() {
		return ListPAmax;
	}

	public void setListPAmax(int[] listPAmax) {
		ListPAmax = listPAmax;
	}

	public void setListPA(int[] listPA) {
		this.ListPA = listPA;
	}

	public int[] getListPA() {
		return ListPA;
	}

	public void setListPAindex(int index, int valeur) {
		for (int i = 0; i < valeur; i++) {
			this.ListPA[index]++;
		}
		this.ListPAmax[index] = this.ListPA[index];
	}

	public void afficherListPA() {
		System.out.println(this.getDiviniteRepresentee().getNom() + " a :\n");
		System.out.println(this.getListPA()[0] + " points Jour");
		System.out.println(this.getListPA()[1] + " points Nuit");
		System.out.println(this.getListPA()[2] + " points Néant\n");
	}

	public void regenererPA() {
		this.setListPA(this.ListPAmax);
	}

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
	 * Joue le tour d'un Joueur.<br>
	 * Pour un JoueurHumain, cette méthode affiche les informations nécessaire à
	 * la prise de décision de l'utilisateur, puis elle lui propose de choisir
	 * ce qu'il veut faire.<br>
	 * Pour un JoueurIA, cette méthode paramètre le ChoixJoueur du JoueurIA.
	 */

	public void jouerTour(int face) {
		this.prendreLaMain();
		this.ajouterPA(face);
		// Affichage de la main du joueur, du centre et de son camp
		if (this.isHuman()) {
			this.mainjoueur.afficherMain();
			this.afficherListPA();
			this.centre.afficherCentre();
			this.campjoueur.afficherCamp();
			// Demande au joueur s'il veut défausser ou piocher une carte
			this.defausserCarte();
			this.piocherCarte();
			// Demande au joueur ce qu'il veut faire
			this.reagir();
		} else {
			this.afficherListPA();
			((IA) this).executerStrategy();
			this.centre.afficherCentre();
			this.campjoueur.afficherCamp();
		}
		// Annonce la fin du tour du joueur
		this.finirLeTour();
	}

	public void reagir() throws InputMismatchException {
		while (this.isaFiniSonTour() == false) {
			System.out.println("Que veux-tu faire ?\n");
			System.out.println("1 : Jouer une carte. ");
			System.out.println("2 : Rattacher un Croyant à un Guide Spirituel.");
			System.out.println("3 : Utiliser la capacité de sa Divinité.");
			System.out.println("Par défaut : Ne rien faire.");
			try {
				switch (sc.nextInt()) {
				case 1:
					this.getMainjoueur().afficherMain();
					this.jouerCarte();
					break;
				case 2:
					if (!this.getCampjoueur().getCamp().isEmpty() && !this.getCentre().getCentre().isEmpty()) {
						this.getCampjoueur().afficherCamp();
						System.out.println(
								"Donne-moi l'index du Guide Spirituel auquel vous voulez rattacher des cartes :");
						int indexcamp = sc.nextInt();
						this.getCentre().afficherCentre();
						System.out.println("Donne-moi l'index du Croyant que vous voulez rattacher :");
						int indexcentre = sc.nextInt();
						this.getCampjoueur().getCamp().get(indexcamp)
								.rattacherCroyants(this.getCentre().donnerCroyant(indexcentre));
					}
					System.out.println("Le camp et/ou le centre est vide, donc vous ne pouvez pas faire cela.");
					break;
				case 3:
					System.out.println("Le joueur utilise sa capacité");
					this.getDiviniteRepresentee().utiliserCapacite();
					break;
				default:
					System.out.println("Le joueur ne fait rien.");
				}
				System.out.println("As-tu fini ton tour ? (Entrez Oui sinon par défaut, ça sera Non.");
				if (sc.next().equals("Oui")) {
					this.setaFiniSonTour(true);
				}
			} catch (InputMismatchException e) {
				System.err.println("Erreur : Vous devez entrer un nombre entier.");
			} catch (IndexOutOfBoundsException o) {
				System.err.println("Erreur : Vous devez entrer un nombre compris entre 0 et le nombre total de cartes de la main / ou du centre / ou de votre camp, moins 1.");
			}
		}
	}

	public void defausserCarte() {
		System.out.println("Veux-tu défausser une carte ?(Entrez Oui, sinon par défaut ça sera Non.)");
		String reponse = sc.next();
		if (reponse.equals("Oui")) { // pour comparer des strings c'est mieux de
										// faire comme ça que des ==
			// Vérification qu'il y a des cartes dans la main, on ne peut pas
			// défausser s'il n'y a pas de cartes
			try {
				if (this.getMainjoueur().getNbCarte() > 0) {
					// Enlever une carte de la main, elle est spécifié par le
					// joueur
					// grâce à son index
					this.getMainjoueur().afficherMain();
					System.out.println("Donne-moi l'index de cette carte");
					int indexCarte = sc.nextInt();
					this.getPioche().recuperer(this.getMainjoueur().defausserCarte(indexCarte));
				} else {
					System.out.println("Vous n'avez pas de cartes à défausser");
				}
			} catch (InputMismatchException e) {
				System.out.println("Erreur : vous devez entrer le nombre entier correspondant au type de PA.");
			}catch (IndexOutOfBoundsException o) {
				System.err.println("Erreur : Vous devez entrer un nombre compris entre 0 et 6.");
			}
		}
	}

	public void piocherCarte() {
		/*
		 * vérification qu'il n'a pas déjà 7 cartes en main, le maximum de
		 * cartes autorisee.
		 */
		if (this.getMainjoueur().getNbCarte() < 7) {
			System.out.println("Vous avez moins de 7 cartes, nous allons compléter votre main jusqu'à 7 : \n");
			while (this.getMainjoueur().getNbCarte() < 7) {
				// Demande à la pioche de donner une carte
				Carte carte = this.getPioche().piocher();
				// Ajout à la main de la carte piochée
				this.getMainjoueur().ajouterCarte(carte);
			}

		}
		this.mainjoueur.afficherMain();
	}

	public void prendreLaMain() {
		joueurActif = this;
		System.out.println(joueurActif.getDiviniteRepresentee().getNom() + " prends la main ! \n");
	}

	public void finirLeTour() {
		System.out.println(joueurActif.getDiviniteRepresentee().getNom() + " a fini de jouer.");
	}

	public void jouerCarte() {
		System.out.println("Donner l'index de la carte à jouer");
		try {
			int index = sc.nextInt();
			this.verifierConsommerPA(index);
			if (this.isCanPlay() == true) {
				switch (this.getMainjoueur().getMain().get(index).getType()) {
				case ("Croyants"):
					Croyant croyant = (Croyant) this.getMainjoueur().defausserCarte(index);
					this.getCentre().ajouterCroyant(croyant);
					break;
				case ("Guide Spirituel"):
					GuideSpirituel gs = (GuideSpirituel) this.getMainjoueur().defausserCarte(index);
					this.getCampjoueur().ajouterCarte(gs);
					break;
				case ("Deus Ex"):
					DeusEx de = (DeusEx) this.getMainjoueur().defausserCarte(index);
					de.effet();
					break;
				case ("Apocalypse"):
					Apocalypse apo = (Apocalypse) this.getMainjoueur().defausserCarte(index);
					apo.effet();
					break;
				}
			} else {
				System.out.println("Vous ne pouvez pas jouer la carte. \n");
			}
			this.setCanPlay(false);
		} catch (InputMismatchException e) {
			System.err.println(
					"Erreur : vous devez entrer un nombre entier en tant qu'index. Référez vous au numéro de la carte.");
		} catch (IndexOutOfBoundsException o) {
			System.err.println("Erreur : Vous devez entrer un nombre compris entre 0 et 6.");
		}
	}

	public void verifierConsommerPA(int rep) {
		switch (this.getMainjoueur().getMain().get(rep).getOrigine()) {
		case (1):
			if (this.getListPA()[0] > 0) {
				this.setCanPlay(true);
				this.getListPA()[0]--;
			} else {
				System.out.println("Vous n'avez pas assez de PA pour jouer la carte");
			}
			break;
		case (3):
			try {
				if (this.getListPA()[2] > 0) {
					this.setCanPlay(true);
					this.getListPA()[2]--;
				} else if (this.getListPA()[0] > 2 || this.getListPA()[1] > 2) {
					System.out.println("Choisir quel type de PA à dépenser:\n 1:Jour\n 2:Nuit");
					int i = sc.nextInt();
					switch (i) {
					case (1):
						this.setCanPlay(true);
						this.getListPA()[0] = this.getListPA()[0] - 2;
						break;
					case (2):
						this.setCanPlay(true);
						this.getListPA()[1] = this.getListPA()[1] - 2;
						break;
					default : System.out.println("Vous n'avez entrer ni 1 ni 2.");
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
				this.setCanPlay(true);
				this.getListPA()[1]--;
			} else {
				System.out.println("Vous n'avez pas assez de PA pour jouer la carte");
			}
			break;
		case (0):
			this.setCanPlay(true);
			break;
		default:
			System.out.println("Bien essayé");
		}
	}

	public void afficherDivinite() {
		this.diviniteRepresentee.afficherDivinitebis();
	}

	public boolean isCanPlay() {
		return canPlay;
	}

	public void setCanPlay(boolean canPlay) {
		this.canPlay = canPlay;
	}
}