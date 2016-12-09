package divinae;

public class IA extends Joueur {
	private Strategy strategy;

	public IA(Divinite divinite, Strategy str) {
		super(false, divinite);
		this.strategy = str;
		// TODO Auto-generated constructor stub
	}

	public void executerStrategy() {
		this.strategy = new OperationPiocher();
		strategy.faire(Jeu.getInstance(), this);
		int[] tab =  {0,0,0};
		if (this.getListPA() != tab) {
			this.strategy = new OperationPoserCarte();
		}
	}
}