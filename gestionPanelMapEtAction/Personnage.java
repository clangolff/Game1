
import java.util.Random;
import java.io.Serializable;

public class Personnage implements Serializable {

    //Attributs
    private String nom;
    private int vie;
    private Consommable carquois ;
    private Consommable bouclier;
    private Noeud position;
    private boolean DoitJouer;

    //Constructeur
    public Personnage() {

    }

    public Personnage(String nom) {
        this.nom = nom;
        this.vie = 100;
        this.carquois = new Consommable("Fleches",3,0); // Nom - Quantite - Duree
        this.bouclier = new Consommable("Bouclier", 0,0);
        this.position = null;
	this.DoitJouer = false;
    }

    //Getter
    public String GetNom() {
        return this.nom;
    }

    public int GetVie() {
        return this.vie;
    }

    public Consommable GetCarquois() {
        return this.carquois;
    }
    
    public Consommable GetBouclier() {
        return this.bouclier;
    }

    public Noeud GetPosition() {
        return this.position;
    }

    public boolean GetDoitJouer() {
    	return this.DoitJouer;
    }

    //Setter
    public void SetNom(String nom) {
        this.nom = nom;
    }

    public void SetVie(int vie) {
        this.vie = vie;
    }

    public void SetCarquois(Consommable carquois) {
        this.carquois = carquois;
    }

    public void SetBouclier(Consommable bouclier) {
        this.bouclier = bouclier;
    }

    public void SetPosition(Noeud position) {
        this.position = position;
    }

    public void SetDoitJouer(boolean b) {
    	this.DoitJouer = b;
    }

    //Méthodes

    public void Soigner(int pv) {
        if ( this.GetVie() + pv < 100 ) {
            this.SetVie(this.GetVie() + pv); //il lui manque suffisament de vie pour ne pas dépasser les 100 HP
        } else {
            this.SetVie(100); //sinon on mets la vie à 100 pour ne pas dépasser les 100 HP
        }
    }

    public void Recolter() {
        Consommable cons = this.GetPosition().GetConsommable();
        if (cons != null) { 
            switch(cons.GetNom()) {
                case "Fleches" : 
                    this.GetCarquois().AjouterQuantite(cons.GetQuantite());
                    break;
                case "Bouclier" : 
                    this.GetBouclier().AjouterDuree(cons.GetDuree());
                    break;
                case "PotionVie" :
                    this.Soigner(20);
                    break;
            }
        }
    }

    public void Attaquer(Noeud n , int degat) {
            Personnage Ennemi = n.GetPersonnage();
            Ennemi.SetVie(Ennemi.GetVie() - degat); 
    }

    public void SeDeplacer(Noeud n) {
        this.SetPosition(n); //on défini la nouvelle position du personnage
       	n.SetPersonnage(this); //sur le noeud d'arrivé, on set le personnage
    }

    public Boolean estMort() {
        return (this.GetVie() < 0);
    }
}
