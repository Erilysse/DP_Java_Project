package joueurs;

import cartes.Divinite;

/**
 * Humain est une classe qui hérite de Joueur. Seul l'utilisateur console peut
 * être un humain, il n'y a donc qu'une seule instanciation de la classe.
 * 
 * @see Joueur
 * 
 * @author manic
 */
public class Humain extends Joueur {

	/**
	 * Instance de la classe Humain, vide par défaut.
	 * 
	 * @see Humain#getInstance(Divinite)
	 */
	private static Humain instance = null;

	/**
	 * Constructeur Humain.
	 * 
	 * Utilise le Constructeur Joueur.
	 * 
	 * @param divinite
	 *            Divinité représentant l'Humain tout au long de la partie.
	 */
	public Humain(Divinite divinite) {
		super(true, divinite);
	}

	/**
	 * Retourne l'instance de la classe Humain.
	 * 
	 * @param divinite
	 *            objet de la classe Divinite représentant le joueur Humain.
	 * @return un objet de la classe Humain.
	 */
	public static Humain getInstance(Divinite divinite) {
		if (instance == null) {
			instance = new Humain(divinite);
		}
		return instance;
	}
}