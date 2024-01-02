import java.io.Serializable;

public class Noeud implements Serializable {
    
   	 //Attributs
  	private String nom;
 	private int numero;
 	private Region geographie;
	private Personnage perso;
	private Consommable conso;
	private boolean visite;
   
       	//Constructeur
    
	public Noeud() {

    
	}

    public Noeud(String nom, int numero, Region geographie) {
        this.nom = nom;
        this.numero = numero;
        this.geographie = geographie;
        this.perso = null;
        this.conso = null;
    }

    //Getter
    public int GetNumero() {
        return this.numero;
    }

    public Region GetGeographie() {
        return this.geographie;
    }

    public Personnage GetPersonnage() {
        return this.perso;
    }

    public Consommable GetConsommable() {
        return this.conso;
    }
    
    public String GetNom() {
        return this.nom;
    }

    public boolean IsVisited() {
    	return this.visite;
    }

    //Setter
    public void SetNumero(int numero) {
        this.numero = numero;
    }

    public void SetGeographie(Region geographie) {
        this.geographie = geographie;
    }

    public void SetPersonnage(Personnage perso) {
        this.perso = perso;
    }

    public void SetConsommable(Consommable conso) {
        this.conso = conso;
    }

    public void SetNom(String nom) {
        this.nom = nom;
    }

    public void SetVisited(boolean b) {
    	this.visite = b;
    }
}
