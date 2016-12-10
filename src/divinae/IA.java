package divinae;

import Strategie.OperationPiocher;
import Strategie.OperationPoserCarte;
import Strategie.OperationRecupererCroyant;
import Strategie.Strategy;

public class IA extends Joueur {
	private Strategy strategy;

	public IA(Divinite divinite, Strategy str) {
		super(false, divinite);
		this.strategy = str;
		// TODO Auto-generated constructor stub
	}
	
	public void verifierConsommerPA(int rep) {
		switch (this.getMainjoueur().getMain().get(rep).getOrigine()) {
		case (1):
			if (this.getListPA()[0] > 0) {
				this.setCanPlay(true);
				this.getListPA()[0]--;
			}
			break;
		case (3):
			if (this.getListPA()[2] > 0) {
				this.setCanPlay(true);
				this.getListPA()[2]--;
			}
		
			if (this.getListPA()[0] > 2 || this.getListPA()[1] > 2) {
				for (int i=1; i<3; i++) {
					switch (i) {
					case (1):
						this.setCanPlay(true);
						this.getListPA()[0] = this.getListPA()[0] - 2;
						break;
					case (2):
						this.setCanPlay(true);
						this.getListPA()[1] = this.getListPA()[1] - 2;
						break;
					}
				}
			}
			break;
		case (5):
			if (this.getListPA()[1] > 0) {
				this.setCanPlay(true);
				this.getListPA()[1]--;
			}
			break;
		default:
			break;
		}
	}

	public void executerStrategy() {
		int[] tab =  {0,0,0};
		this.strategy = new OperationPiocher();
		strategy.faire(Jeu.getInstance(), this);
		if (this.getListPA() != tab) {
			this.strategy = new OperationPoserCarte();
			strategy.faire(Jeu.getInstance(), this);
		}
		else if (!this.getCentre().getCentre().isEmpty()) {
			this.strategy = new OperationRecupererCroyant();
			strategy.faire(Jeu.getInstance(), this);
		}
		this.setaFiniSonTour(true);
	}
}