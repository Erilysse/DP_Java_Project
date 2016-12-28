package capacite;

import plateau_du_jeu.Jeu;

/**
 * AppoEffect est la classe représentant l'effet de la carte Apocalypse.
 * Elle implémente l'interface Sacrifice.
 * 
 * @see Apocalypse
 * @see Sacrifice
 * @author manic
 *
 */

public class ApoEffect implements Sacrifice{
	
	public ApoEffect(){
	}
	
	/**
	 * sacrifice() sert à décider si un joueur a gagné ou a perdu. Pour trouver le vainqueur quand le nombre de joueurs est inférieur à 4,
	 * elle cherche celui qui a le plus de points de prières et arrête le jeu. Pour trouver le perdant, elle cherche celui qui a le moins
	 * de points de prières et le retire de la liste des joueurs.
	 * @param jeu indique l'instance actuelle de Jeu. Elle permets à la méthode de savoir la liste des joueurs et ainsi leurs nombres
	 * de points de prières.
	 * @param origine et @param nb est commun à toutes les classes implémetant l'interface Sacrifice.
	 * 
	 * @see Joueur
	 * @see Jeu
	 * @see Jeu#getListJoueur()
	 * @see Jeu#getJoueurMaxPP()
	 * @see Jeu#getJoueurMinPP()
	 * @see Jeu#getnbJoueur()
	 */
	
	public void sacrifice(int origine, int nb, int joueur, Jeu jeu){
		if(jeu.getnbJoueur()<4){
			System.out.println(jeu.getListJoueur().get(jeu.getJoueurMaxPP())+"Le joueur "+(jeu.getJoueurMaxPP()+1)+" a gagné !");
			jeu.arreterJeu();
		}
		else{
			System.out.println(jeu.getListJoueur().get(jeu.getJoueurMinPP())+"Le joueur "+(jeu.getJoueurMinPP()+1)+" est éliminé !");
			jeu.getListJoueur().remove(jeu.getJoueurMinPP());
		}
	}
}
