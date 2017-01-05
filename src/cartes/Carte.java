package cartes;

import javax.swing.ImageIcon;

import capacite.AjoutPA;
import plateau_du_jeu.Jeu;

/**
 * Carte est la classe représentant une carte quelconque. Une carte est
 * caractérisé par les informations suivantes :
 * 
 * - un type. Elle peut être GuideSpirituel, Croyants, Deus Ex, Apocalypse ou
 * Divinité.
 * 
 * - un nom.
 * 
 * - des dogmes, de 0 à 3. ils expriment les croyances.
 * 
 * - une origine, exprimant la filiation.
 * 
 * - un effet de sacrifice de la classe AjoutPA.
 * 
 * @see AjoutPA
 * 
 * @author manic
 */
public class Carte {
	
	/**
	 * Le type de la carte. Elle est GuideSpirituel, Croyants, Deus Ex,
	 * Apocalypse ou Divinité.
	 * 
	 * @see Carte#getType()
	 * @see Carte#setType(String)
	 */
	private String type;
	
	/**
	 * Le nom de la carte. Il est souvent associé à un effet de sacrifice.
	 * 
	 * @see Carte#getNom()
	 * @see Carte#setNom(String)
	 */
	private String nom;
	
	/**
	 * Un tableau de dogmes, de 0 à 3 maximum. Un dogme peut être humain,
	 * symboles, mystique, nature, ou chaos.
	 * 
	 * @see Carte#getDogme()
	 * @see Carte#getDogme(int)
	 * @see Carte#afficherDogme(int)
	 * @see Carte#setDogme(String[])
	 */
	private String[] dogme;
	
	/**
	 * L'origine de la carte. Elle peut aussi ne pas en avoir. C'est un nombre
	 * représentant l'origine Jour, Nuit, Néant, Aube ou Crépuscule.
	 * 
	 * @see Carte#getOrigine()
	 * @see Carte#afficherOrigine()
	 * @see Carte#setOrigine(int)
	 */
	private int origine;
	
	/**
	 * Pour une carte Croyant, le nombre de points de prières qu'elle rapporte.
	 * Pour une carte GuideSpirituel, le nombre de Croyants qui peut être
	 * rattachés à la carte. Pour une carte DeusEx ou Apocalypse, l'attribut est
	 * vide.
	 * 
	 * @see Croyant
	 * @see GuideSpirituel
	 * @see DeusEx
	 * @see Apocalypse
	 */
	private int caracteristique;
	
	/**
	 * L'effet de la carte, plus précisement l'ajout de PA.
	 * 
	 * @see AjoutPA
	 * @see AjoutPA#sacrifice(int, int, int, Jeu)
	 * @see Carte#sacrifice()
	 */
	private AjoutPA apa;
	
	private ImageIcon image;

	/**
	 * Constructeur Carte.
	 * 
	 * A la construction de Carte, tout les attributs sont indiqués grâce aux
	 * paramètres donnés. On rejette l'exception NumberFormatException si p et
	 * origine ne sont pas au "bon" format.
	 *
	 * @see NumberFormatException
	 * 
	 * @param type
	 *            le type de la carte.
	 * @param nom
	 *            le nom de la carte.
	 * @param dogme1
	 *            son premier dogme, si elle en a.
	 * @param dogme2
	 *            son second dogme, si elle en a.
	 * @param dogme3
	 *            son troisième dogme, si elle en a.
	 * @param origine
	 *            son origine.
	 * @param p
	 *            sa caractéristique.
	 */
	public Carte(String type, String nom, String dogme1, String dogme2, String dogme3, String origine, String p, ImageIcon imageIcon) {
		try {
			this.type = type;
			this.nom = nom;
			this.dogme = new String[3];
			this.dogme[0] = dogme1;
			this.dogme[1] = dogme2;
			this.dogme[2] = dogme3;
			this.origine = Integer.parseInt(origine);
			this.caracteristique = Integer.parseInt(p);
			this.image= imageIcon;
		} catch (NumberFormatException e) {
			System.err.println("Erreur :" + e.getMessage());
		}
	}

	/**
	 * Retourne le type de la carte.
	 * 
	 * @return le type de la carte.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Met à jour le type de la carte.
	 * 
	 * @param type
	 *            nouveau type de la carte.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Retourne le nom de la carte.
	 * 
	 * @return le nom de la carte.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Met à jour le nom de la carte.
	 * 
	 * @param nom
	 *            nouveau nom de la carte.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne le tableau entier des dogmes de la carte.
	 * 
	 * @return un tableau de dogmes, pouvant être vide, ou composé de 1 à 3
	 *         éléments.
	 */
	public String[] getDogme() {
		return dogme;
	}

	/**
	 * Retourne un élément précis du tableau des dogmes de la carte.
	 * 
	 * @param i
	 *            numéro de l'élément dans le tableau que l'on veut.
	 * 
	 * @return le dogme à la case numéro i du tableau des dogmes.
	 */
	public String getDogme(int i) {
		return dogme[i];
	}

	/**
	 * Met à jour le tableau entier des dogmes.
	 * 
	 * @param dogme
	 *            nouveau tableau de dogmes.
	 */
	public void setDogme(String[] dogme) {
		this.dogme = dogme;
	}

	/**
	 * Retourne l'origine de la carte.
	 * 
	 * @return l'origine de la carte, qui peut exister ou non.
	 */
	public int getOrigine() {
		return origine;
	}

	/**
	 * Met à jour l'origine de la carte.
	 * 
	 * @param origine
	 *            nouvelle origine de la carte.
	 */
	public void setOrigine(int origine) {
		this.origine = origine;
	}

	/**
	 * Retourne la caractéristique de la carte.
	 * 
	 * @return la caractéristique de la carte.
	 */
	public int getCaracteristique() {
		return caracteristique;
	}

	/**
	 * Met à jour la caractéristique de la carte.
	 * 
	 * @param caracteristique
	 *            la nouvelle caractéristique de la carte.
	 */
	public void setCaracteristique(int caracteristique) {
		this.caracteristique = caracteristique;
	}

	/**
	 * Retourne un l'effet AjoutPA de la carte.
	 * 
	 * @return un effet AjoutPA.
	 */
	public AjoutPA getApa() {
		return apa;
	}

	/**
	 * Met à jour l'effet AjoutPA de la carte.
	 * 
	 * @param apa
	 *            le nouvel effet AjoutPA.
	 */
	public void setApa(AjoutPA apa) {
		this.apa = apa;
	}

	/**
	 * Affiche un dogme de la carte, en faisant "matcher" l'élément du tableau,
	 * une lettre, avec un dogme (Nature, Humain, etc..)
	 * 
	 * @param i
	 *            numéro de l'élément dans le tableau des dogmes.
	 * 
	 * @return le dogme sous sa vraie forme et non plus en lettre.
	 * 
	 * @see Carte#dogme
	 */
	public String afficherDogme(int i) {
		switch (this.dogme[i]) {
		case ("A"):
			return "Nature ";
		case ("B"):
			return "Humain ";
		case ("C"):
			return "Symboles ";
		case ("D"):
			return "Mystique ";
		case ("E"):
			return "Chaos ";
		default:
			return " ";
		}
	}

	/**
	 * Affiche l'origine de la carte en faisant "matcher" son attribut origine
	 * (qui est un numéro) avec un string.
	 * 
	 * @return l'origine sous forme de mot (Jour, Aube, etc...).
	 * @see Carte#origine
	 */
	public String afficherOrigine() {
		switch (origine) {
		case (1):
			return "Jour ";
		case (2):
			return "Aube ";
		case (3):
			return "Néant ";
		case (4):
			return "Crépuscule ";
		case (5):
			return "Nuit ";
		default:
			return " ";
		}
	}

	/**
	 * Affiche la carte et ses caractéristiques (type, non, dogmes et origine)
	 * dans la console. Appelle les méthodes afficherDogme(int) et
	 * afficherOrigine() pour cela.
	 * 
	 * @see Carte#afficherDogme(int)
	 * @see Carte#afficherOrigine()
	 */
	public void afficherCarte() {
		System.out.println(type + " " + nom + " ");
		for (int i = 0; i < dogme.length; i++) {
			System.out.println(this.afficherDogme(i));
		}
		System.out.println(this.afficherOrigine());
		System.out.println(caracteristique + "\n");
	}

	/**
	 * Sacrifie la carte et implémente son effet de sacrifice en faisant
	 * "matcher" son nom avec une capacité.
	 * 
	 * @see AjoutPA
	 * @see AjoutPA#sacrifice()
	 */
	public void sacrifice() {
		this.apa = new AjoutPA();
		switch (this.nom) {
		case ("Moines"):
			this.apa.sacrifice(0, 1, 1, Jeu.getInstance());
			break;
		case ("Démons"):
			this.apa.sacrifice(1, 1, 1, Jeu.getInstance());
			break;
		case ("Esprits"):
			this.apa.sacrifice(2, 1, 1, Jeu.getInstance());
			break;
		default:
			System.out.println("Capacité non implémentée");
		}
	}
}