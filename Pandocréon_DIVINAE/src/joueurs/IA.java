
package joueurs;

import cartes.Divinite;
import plateau_du_jeu.Jeu;
import strategie_IA.*;

/**
 * IA est une classe qui h�rite de Joueur. C'est un joueur ordinateur, simul�.
 * Elle poss�de les m�mes attributs, ainsi qu'une strat�gie de jeu, un objet de
 * l'interface Strategy.
 * 
 * @see Joueur
 * @see Strategy
 * 
 * @author manic
 */
public class IA extends Joueur {

	/**
	 * Objet de la classe Strategy, d�finissant la strat�gie adopt� par l'IA
	 * selon le contexte.
	 * 
	 * @see Strategy
	 * @see IA#getStrategy()
	 * @see IA#setStrategy(Strategy)
	 */
	private Strategy strategy;

	/**
	 * Constructeur IA. Utilise le Constructeur Joueur.
	 * 
	 * @param divinite
	 *            la Divinit� qui repr�sente l'IA.
	 * @param str
	 *            la strat�gie de l'IA.
	 * 
	 * @see Joueur
	 */
	public IA(Divinite divinite, Strategy str) {
		super(false, divinite);
		this.strategy = str;
	}

	/**
	 * Retourne la strat�gie de l'IA.
	 * 
	 * @return un objet de la classe Strategy.
	 */
	public Strategy getStrategy() {
		return strategy;
	}

	/**
	 * Met � jour la strat�gie de l'IA.
	 * 
	 * @param strategy
	 *            un nouvel objet de la classe Strategy.
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * Utilise les diff�rentes strat�gies selon le contexte et certaines
	 * conditions. Simule un tour de jeu d'un joueur sans entr�es, la strat�gie
	 * g�n�rant automatiquement les choix de l'IA. L'IA pioche pour remplir sa
	 * main, elle ne d�fausse pas ; si sa liste de PA n'est pas vide, elle pose
	 * une carte. Sinon, elle v�rifie que le centre et le camp de l'IA n'est pas
	 * vide pour rattacher un croyant � un guide spirituel.
	 * 
	 * @see Strategy
	 * @see OperationPiocher
	 * @see OperationPoserCarte
	 * @see OperationRecupererCroyant
	 */
	public void executerStrategy() {
		int[] tab = { 0, 0, 0 };
		this.strategy = new OperationPiocher();
		strategy.faire(Jeu.getInstance(), this);
		if (this.getListPA() != tab) {
			this.strategy = new OperationPoserCarte();
			strategy.faire(Jeu.getInstance(), this);
		} else if (!this.getCentre().getCentre().isEmpty() && !this.getCampjoueur().getCamp().isEmpty()) {
			this.strategy = new OperationRecupererCroyant();
			strategy.faire(Jeu.getInstance(), this);
		}
		this.setaFiniSonTour(true);
	}

	/**
	 * V�rifie que l'IA peut faire une action en regardant sa liste de Points
	 * d'Action. Regarde l'origine de la carte que veut poser l'IA, puis v�rifie
	 * si le joueur IA a au moins un point de cette origine, ou au moins deux
	 * points de l'origine N�ant. Si c'est le cas, met � jour l'attribut
	 * <code>canPlay</code> � true et consomme les points d'actions.
	 */
	public void verifierConsommerPA(int rep) {
		switch (this.getMainjoueur().getMain().get(rep).getOrigine()) {
		case (1):
			if (this.getListPA()[0] > 0) {
				this.setcanPlay(true);
				this.getListPA()[0]--;
			}
			break;
		case (3):
			if (this.getListPA()[2] > 0) {
				this.setcanPlay(true);
				this.getListPA()[2]--;
			}

			if (this.getListPA()[0] > 2 || this.getListPA()[1] > 2) {
				for (int i = 1; i < 3; i++) {
					switch (i) {
					case (1):
						this.setcanPlay(true);
						this.getListPA()[0] = this.getListPA()[0] - 2;
						break;
					case (2):
						this.setcanPlay(true);
						this.getListPA()[1] = this.getListPA()[1] - 2;
						break;
					}
				}
			}
			break;
		case (5):
			if (this.getListPA()[1] > 0) {
				this.setcanPlay(true);
				this.getListPA()[1]--;
			}
			break;
		default:
			break;
		}
	}
}