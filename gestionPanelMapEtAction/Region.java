
import java.io.Serializable;

public class Region implements Serializable{
    
    //Attributs
    private String nom;
    private int effet;
    private double proba;

    //Constructeur
    public Region() {

    }

    public Region(String nom, int effet, double proba) {
        this.nom = nom;
        this.effet = effet;
        this.proba = proba;
    }

    //Getter
    public String GetNom() {
        return this.nom;
    }

    public int GetEffet() {
        return this.effet;
    }
    
    public double GetProba() {
        return this.proba;
    }

    //Setter
    public void SetNom(String nom) {
        this.nom = nom;
    }

    public void SetEffet(int effet) {
        this.effet = effet;
    }

    public void SetProba(double proba) {
        this.proba = proba;
    }

    
}
