package divinae;

public class Divinite extends Carte{
	
	private boolean aUtiliseSaCapacite = false;
	
	public Divinite(String type,String nom,String dogme1,String dogme2,String dogme3,String origine,String p){
		super(type, nom, dogme1, dogme2, dogme3, origine, p);
	}
	
	public void afficherDivinite(){
		super.afficherCarte();
	}
	
	public void utiliserCapacite() {
		if (aUtiliseSaCapacite = false) {
			System.out.println("La capacit� de la divivinit� n'a aucun effet. L'impl�mentation n'a pas �t� faite. Sorry !");
			// impl�mentation de l'effet de la capacit� de la divinit� 
		} else {
			System.out.println("Le joueur a d�j� utilis� sa capacit� de Divinit�. ");
		}
		aUtiliseSaCapacite = true;
	}
}
