import java.io.Serializable;

public class Consommable implements Serializable {

    //Attributs
    private String nom;
    private int quantite;
    private int duree;

    //Constructeur
    public Consommable() {

    }

    public Consommable(String nom, int quantite, int duree) {
        this.nom = nom;
        this.quantite = quantite;
        this.duree = duree;
    }

    //Getter
    public String GetNom() {
        return this.nom ;
    }

    public int GetQuantite() {
        return this.quantite;
    }

    public int GetDuree() {
        return this.duree;
    }

    //Setter

    public void SetNom( String nom ) {
        this.nom = nom;
    }

    public void SetQuantite( int quantite) {
        this.quantite = quantite;
    }

    public void SetDuree( int duree ) {
        this.duree = duree;
    }

    //Méthodes
    public void AjouterQuantite( int ajout ) {
        this.quantite = this.quantite + ajout;
    }

    public void AjouterDuree( int ajout ) {
        this.duree = this.duree + ajout;
    }

    public String toString() {
    	return this.nom;
    }
}
