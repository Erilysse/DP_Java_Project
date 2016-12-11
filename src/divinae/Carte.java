package divinae;

public class Carte {
	private String type;
	private String nom;
	private int cara;
	private String[] dogme;
	private int origine;
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String[] getDogme() {
		return dogme;
	}

	public void setDogme(String[] dogme) {
		this.dogme = dogme;
	}

	public int getOrigine() {
		return origine;
	}

	public void setOrigine(int origine) {
		this.origine = origine;
	}

	public Carte(String type,String nom,String dogme1,String dogme2,String dogme3,String origine,String p){
		try {
			this.dogme = new String[3];
			this.type=type;
			this.nom=nom;
			this.dogme[0]=dogme1;
			this.dogme[1]=dogme2;
			this.dogme[2]=dogme3;
			this.origine= Integer.parseInt(origine);
			this.cara=Integer.parseInt(p);
		} catch (NumberFormatException e) {
			System.err.println("Erreur :" +e.getMessage());
		}
		
	}
	
	public String afficherDogme(int i) {
		switch(this.dogme[i]){
				case("A"): return "Nature ";
		  		case("B"): return "Humain ";
		  		case("C"): return "Symboles ";
		  		case("D"): return "Mystique ";
		  		case("E"): return "Chaos ";
		  		default :  return " ";
		}
	}
	
	public String afficherOrigine() {
		switch(origine){
			case(1): return "Jour ";
			case(2): return "Aube ";
			case(3): return "Néant ";
			case(4): return "Crépuscule ";
			case(5): return "Nuit ";
			default: return " ";
		}
	}
	public void afficherCarte(){
		System.out.println(type+" "+nom+" ");
		for(int i=0; i< dogme.length; i++){
			System.out.println(this.afficherDogme(i));
		}
		System.out.println(this.afficherOrigine());
		System.out.println(cara + "\n");
	}
	
	public void sacrifice(){
		
	}

}
