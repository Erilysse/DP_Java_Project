package divinae;

public class Main {
 
	public Main() {
	}

	public static void main(String[] args) {
		// création d'une partie
		Jeu partie = Jeu.getInstance();
		// Choix des joueurs
		partie.choisirJoueurs();
		partie.jouerPartie();
		//partie.afficherListJoueur();
	}
}
