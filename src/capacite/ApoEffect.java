package capacite;

import plateau_du_jeu.Jeu;

/**
 * AppoEffect est la classe repr�sentant l'effet de la carte Apocalypse.
 * Elle impl�mente l'interface Sacrifice.
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
	 * sacrifice() sert � d�cider si un joueur a gagn� ou a perdu. Pour trouver le vainqueur quand le nombre de joueurs est inf�rieur � 4,
	 * elle cherche celui qui a le plus de points de pri�res et arr�te le jeu. Pour trouver le perdant, elle cherche celui qui a le moins
	 * de points de pri�res et le retire de la liste des joueurs.
	 * @param jeu indique l'instance actuelle de Jeu. Elle permets � la m�thode de savoir la liste des joueurs et ainsi leurs nombres
	 * de points de pri�res.
	 * @param origine et @param nb est commun � toutes les classes impl�metant l'interface Sacrifice.
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
			System.out.println(jeu.getListJoueur().get(jeu.getJoueurMaxPP())+"Le joueur "+(jeu.getJoueurMaxPP()+1)+" a gagn� !");
			jeu.arreterJeu();
		}
		else{
			System.out.println(jeu.getListJoueur().get(jeu.getJoueurMinPP())+"Le joueur "+(jeu.getJoueurMinPP()+1)+" est �limin� !");
			jeu.getListJoueur().remove(jeu.getJoueurMinPP());
		}
	}
}
