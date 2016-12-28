package joueurs;

import java.util.*;

import cartes.Apocalypse;
import cartes.Carte;
import cartes.Croyant;
import cartes.DeusEx;
import cartes.Divinite;
import cartes.GuideSpirituel;
import pioches.Pioche;
import plateau_du_jeu.CampDuJoueur;
import plateau_du_jeu.Centre;

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
	private boolean joueurActif;
	private Centre centre;
	private Scanner sc;
	private boolean canPlay;
	private boolean canRattached;

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
		this.canPlay = false;
		this.canRattached = false;
	}

	
	public int getNbrPrieres() {
		for(int i = 0; i<this.campjoueur.getCamp().size(); i++){
			this.nbrPrieres += this.campjoueur.getCamp().get(i).getPP();
		}
		return this.nbrPrieres;
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

	public boolean getJoueurActif() {
		return joueurActif;
	}
	
	public void setJoueurActif(boolean joueurActif) {
		this.joueurActif = joueurActif;
	}
	
	public boolean isCanRattached() {
		return canRattached;
	}

	public void setCanRattached(boolean canRattached) {
		this.canRattached = canRattached;
	}

	public boolean iscanPlay() {
		return canPlay;
	}

	public void setcanPlay(boolean canPlay) {
		this.canPlay = canPlay;
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
	
	public boolean canRattached(int indexcentre){
		for (int i=0; i<3; i++) {
			for(int j=0; j<3; j++){
				if(this.centre.getCentre().get(indexcentre).getDogme(i).equals(this.diviniteRepresentee.getDogme(j))){
					this.canRattached = true;
				}
			}
		}
		return canRattached;
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

	public void reagir() {
		while (this.isaFiniSonTour() == false) {
			System.out.println("Que veux-tu faire ?\n");
			System.out.println("1 : Jouer une carte. ");
			System.out.println("2 : Rattacher un Croyant à un Guide Spirituel.");
			System.out.println("3 : Utiliser la capacité de sa Divinité.");
			System.out.println("4 : Sacrifier une carte");
			System.out.println("5 : Ne rien faire.");	
			try {
				switch (sc.nextInt()) {
				case 1:
					this.mainjoueur.afficherMain();
					this.jouerCarte();
					break;
				case 2:
					if (!this.campjoueur.getCamp().isEmpty() && !this.centre.getCentre().isEmpty()) {
						this.campjoueur.afficherCamp();
						System.out.println(
								"Donne-moi l'index du Guide Spirituel auquel vous voulez rattacher des cartes :");
							int indexcamp = sc.nextInt();
						this.centre.afficherCentre();
						System.out.println("Donne-moi l'index du Croyant que vous voulez rattacher :");
						int indexcentre = sc.nextInt();
						if(this.canRattached(indexcentre)){
							this.campjoueur.getCamp().get(indexcamp).rattacherCroyants(this.centre.donnerCroyant(indexcentre)); 
						}else{
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
					int rep = sc.nextInt();
					switch(rep){
					case(1): 
						System.out.println("Donnez l'index du Guide Spirituel auquel le Croyant est rattaché");
						int rep1 = sc.nextInt();
						System.out.println("Donnez l'index du Croyant dans la liste du Guide Spirituel");
						int rep2 = sc.nextInt();
						this.campjoueur.getCamp().get(rep1).getCroyantsRattaches().get(rep2).sacrifice();
						this.campjoueur.getCamp().get(rep1).retirerCroyant(rep2);
						break;
					case(2): 
						System.out.println("Donnez l'index du Guide Spirituel auquel le Croyant est rattaché");
						int rep3 = sc.nextInt();
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
				System.err.println("Erreur : Vous devez entrer un numéro des cartes que vous possédez. Référez vous au nombre de carte et leur numéro attribué.");
			}
				System.out.println("As-tu fini ton tour ? (Entrez Oui sinon par défaut, ça sera Non.");
				if (sc.next().equals("Oui")) {
					this.setaFiniSonTour(true);
				}
		}
	}

	public void defausserCarte() {
		System.out.println("Veux-tu défausser une carte ?(Entrez Oui, sinon par défaut ça sera Non.)");
		try {
			String reponse = sc.next();
			while (reponse.equals("Oui")) {
			// Vérification qu'il y a des cartes dans la main, on ne peut pas
			// défausser s'il n'y a pas de cartes
				if (this.getMainjoueur().getNbCarte() > 0) {
					// Enlever une carte de la main, elle est spécifié par le
					// joueur grâce à son index
					this.getMainjoueur().afficherMain();
					System.out.println("Donne-moi l'index de cette carte");
					int indexCarte = sc.nextInt();
					this.getPioche().recuperer(this.getMainjoueur().defausserCarte(indexCarte));
				} else {
					System.out.println("Vous n'avez pas de cartes à défausser");
				}
			System.out.println("Veux-tu à nouveau défausser une carte ?");
			reponse = sc.next();
			}
		} catch (InputMismatchException e) {
				System.out.println("Erreur : vous devez entrer le nombre entier correspondant au type de PA.");
		}catch (IndexOutOfBoundsException o) {
				System.err.println("Erreur : Vous devez entrer un nombre compris entre 0 et 6.");
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

	public void afficherDivinite() {
		this.diviniteRepresentee.afficherDivinitebis();
	}
	
	public void prendreLaMain() {
		System.out.println(this.diviniteRepresentee.getNom()+" prends la main !");
		this.joueurActif = true;
	}

	public void finirLeTour() {
		System.out.println(this.diviniteRepresentee.getNom()+" a fini de jouer.");
		this.joueurActif = false;
	}
	
	public void jouerCarte() {
		System.out.println("Donner l'index de la carte à jouer");
		try {
		int index = sc.nextInt();
		switch(this.mainjoueur.getMain().get(index).getType()){
			case("Croyants"): 
				this.verifierConsommerPA(index);
				if(this.canPlay==true){
					Croyant croyant = (Croyant) this.mainjoueur.defausserCarte(index);
					this.centre.ajouterCroyant(croyant);
				} else {
					System.out.println("donc vous ne pouvez toujours pas jouer la carte");
				}
				this.canPlay = false;
				break;
			case("Guide Spirituel"): 
				this.verifierConsommerPA(index);
				if(this.canPlay == true){
					GuideSpirituel gs = (GuideSpirituel) this.mainjoueur.defausserCarte(index);
					this.campjoueur.ajouterCarte(gs);
				} else {
					System.out.println("donc vous ne pouvez toujours pas jouer la carte");
				}
				this.canPlay = false;
				break;
			case("Deus Ex"): 
				this.verifierConsommerPA(index);
				if(this.canPlay==true){
					DeusEx de = (DeusEx) this.mainjoueur.defausserCarte(index);
					de.effet();
				} else {
					System.out.println("donc vous ne pouvez toujours pas jouer la carte");
				}
				this.canPlay = false;
				break;
			case("Apocalypse"): 
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
					int i = sc.nextInt();
					switch (i) {
					case (1):
						this.setcanPlay(true);
						this.getListPA()[0] = this.getListPA()[0] - 2;
						break;
					case (2):
						this.setcanPlay(true);
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

}