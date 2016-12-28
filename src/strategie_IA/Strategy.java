package strategie_IA;

import joueurs.Joueur;
import plateau_du_jeu.Jeu;

public interface Strategy {
	
	public void faire(Jeu jeu, Joueur joueur);
}
