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
			System.out.println("La capacité de la divivinité n'a aucun effet. L'implémentation n'a pas été faite. Sorry !");
			// implémentation de l'effet de la capacité de la divinité 
		} else {
			System.out.println("Le joueur a déjà utilisé sa capacité de Divinité. ");
		}
		aUtiliseSaCapacite = true;
	}
}
