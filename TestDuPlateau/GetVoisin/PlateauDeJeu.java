
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import java.util.Iterator;

public class PlateauDeJeu implements Serializable{
    
	private final int nbJoueurMAX = 4;
	private final int nbNoeudMAX = 16;

	//Attributs
	private ArrayList<Noeud> ListeNoeud;
	private ArrayList<Personnage> ListePersonnage;
	private ArrayList<Consommable> ListeConsommable;
	private int[][] MatriceAdjacence;

	private int NbJoueurEnVie;
	private int indexJQJ;
    
	//Constructeur
	public PlateauDeJeu() {
    
	}

	public PlateauDeJeu(ArrayList<Noeud> ListeNoeud, ArrayList<Personnage> ListePersonnage, ArrayList<Consommable> ListeConsommable) {
		this.ListeNoeud = ListeNoeud;
		this.ListePersonnage = ListePersonnage;
		this.ListeConsommable = ListeConsommable;
		this.MatriceAdjacence = new int[nbNoeudMAX][nbNoeudMAX];
		this.NbJoueurEnVie = nbJoueurMAX;
	}

	//Getter
	public ArrayList<Noeud> GetListeNoeud() {
		return this.ListeNoeud;
	}
	
	public ArrayList<Personnage> GetListePersonnage() {
		return this.ListePersonnage;
	}

	public ArrayList<Consommable> GetListeConsommable() {
		return this.ListeConsommable;
	}

	public int[][] GetMatriceAdjacence() {
		return this.MatriceAdjacence;
	}

	public int GetNbJoueurEnVie() {
		return this.NbJoueurEnVie;
	}

	public int getIndexJQJ() {
		return this.indexJQJ;
	}

	//Setter
	public void SetListeNoeud(ArrayList<Noeud> ListeNoeud) {
		this.ListeNoeud = ListeNoeud;
	}
	
	public void SetListePersonnage(ArrayList<Personnage> ListePersonnage) {
		this.ListePersonnage = ListePersonnage;
	}

	public void SetListeConsommable(ArrayList<Consommable> ListeConsommable) {
		this.ListeConsommable = ListeConsommable;
	}

	public void SetMatriceAdjacence(int[][] MatriceAdjacence) {
		this.MatriceAdjacence = MatriceAdjacence;
	}

	public void SetNbJoueurEnVie(int nb) {
		this.NbJoueurEnVie = nb;
	}

	public void setIndexJQJ(int i) {
		this.indexJQJ = i;
	}
	
	
	//Méthodes

	//############ Gestion Arbre Couvrant ###############

   	public void setMatriceAdjacenceZero() { //permet de mettre la matrice à 0
        	for(int i=0; i < nbNoeudMAX; i++) {
            		for(int j=0; j < nbNoeudMAX; j++) {
                		this.MatriceAdjacence[i][j] = 0;
            		}
        	}
    	}

    	public void NouvelleArbreCouvrant() {
		ArrayList<Noeud> NoeudNonConnecte = new ArrayList<>(this.GetListeNoeud());
		ArrayList<Noeud> NoeudConnecte = new ArrayList<Noeud>();
		Random rand = new Random();
		int indexA;
		int indexB;

		int numNoeudA;
		int numNoeudB;

		this.setMatriceAdjacenceZero(); //Retire tous les liens

		int index = rand.nextInt(NoeudNonConnecte.size()); //tire un noeud aléatoire dans les noeuds non connectés
		NoeudConnecte.add(NoeudNonConnecte.get(index)); //ajoute un noeud au noeud connecté, un seul noeud => Arbre
		NoeudNonConnecte.remove(index); //on retire le noeud connecté des noeuds non connectés
		for(int i=1; i < nbNoeudMAX; i++) {
			// tire un neoud connecté et recupere le numero du noeud (départ)
			indexA = rand.nextInt(NoeudConnecte.size()); 
			numNoeudA = NoeudConnecte.get(indexA).GetNumero();

			// tire un noeud non connecté et recupere le numero du noeud
			indexB = rand.nextInt(NoeudNonConnecte.size());
			numNoeudB = NoeudNonConnecte.get(indexB).GetNumero();
	
			this.MatriceAdjacence[numNoeudB][numNoeudA] = 1;
			this.MatriceAdjacence[numNoeudA][numNoeudB] = 1;
			
			NoeudConnecte.add(NoeudNonConnecte.get(indexB)); //on ajoute aux noeuds connectés le nouveau noeud
			NoeudNonConnecte.remove(indexB); //on retire le noeud que l'on vient de connecté des noeuds connectés.
		}
	}

	//Gestion Des Objets et récoltes

	//Affecter un objet à un noeud
	public void AffecterConsommableANoeud(Consommable c , Noeud n) {
		if ( n.GetConsommable() == null) {
			n.SetConsommable(c);
		}  
	}

	//Position des Noeuds SANS Consommables
	public ArrayList<Noeud> GetPositionSansConsommables() {
		ArrayList<Noeud> ListeNoeudSansConso = new ArrayList<Noeud>();
		for(Noeud n : this.GetListeNoeud()) {
			if(n.GetConsommable() == null) {
				ListeNoeudSansConso.add(n);
			}
		}
		return ListeNoeudSansConso;
	}
	
	public void AffecterPersoANoeud(Personnage p, Noeud n) {
		if ( n.GetPersonnage() == null) {
			n.SetPersonnage(p);
		}
	}    

	//Gestion de la portée

	//Voisin d'un Noeud
	public ArrayList<Noeud> GetVoisinNoeud(Noeud n) {
	    	int indiceNoeud = n.GetNumero();
        	ArrayList<Noeud> NoeudVoisin = new ArrayList<Noeud>();
       
			for(int c = 0; c < nbNoeudMAX; c++) {
				if (this.GetMatriceAdjacence()[indiceNoeud][c] == 1) {
					NoeudVoisin.add(this.GetListeNoeud().get(c));
				}
			} 

        	return NoeudVoisin;
   	}
    
	public String toString() {
		String res = "";
		for (Personnage p : this.ListePersonnage){
			res += p.GetNom();
			res += "\n   Vie : ";
			res += p.GetVie();
			res += "\n   Fleches : ";
			res += String.valueOf(p.GetCarquois());
			res += "\n   Bouclier : ";
			res += String.valueOf(p.GetBouclier());
			res += "\n\n";
		}
		res += "Liste Consommables : "; 
		for (Consommable c : this.ListeConsommable) {
			res += c.GetNom();
			res +=", ";
		}
		res += "\nListe des Noeuds : \n"; 
		for (Noeud n : this.ListeNoeud) {
			res += String.valueOf(n.GetNumero());
			res += ", ";
			if (n.GetPersonnage() != null) {
				res += n.GetPersonnage().GetNom();
			} else {
				res += "vide"; 
			} 
			res += ", ";
			if (n.GetConsommable() != null) {
				res += n.GetConsommable().GetNom(); 
			} else {
				res += "vide";
			}
			res += "\n"; 
		}
		res += "Indice JQJ :  ";
		res += this.indexJQJ;
		res += "\nNB joueurs en vie ";
		res += this.NbJoueurEnVie;
		res += "\n"; 
		return res;
	}	

	public static void main(String[] args) {
		//plateau 3 defini sur messenger

                //definition des différentes régions
                Region plaine = new Region("plaine",0,0.8);
                Region volcan = new Region("volcan",5,0.8);
                Region foret = new Region("foret",0,0.4);

                //defintition des différents consomables
                Consommable bouclierPlateau = new Consommable("Bouclier",1,2);
                Consommable flechesPlateau = new Consommable("Fleches",2,0);
                Consommable potionPlateau = new Consommable("PotionVie",0,0);

                ArrayList <Consommable> LC = new ArrayList<Consommable>();
                LC.add(bouclierPlateau);
                LC.add(flechesPlateau);
                LC.add(potionPlateau);
 
	       	//def des 4 personnages
                
                Personnage J1 = new Personnage("Antoine El Follador");
                Personnage J2 = new Personnage("Alexandre El Depresivo");
                Personnage J3 = new Personnage("Fabio El dictador");
                Personnage J4 = new Personnage("Carlos busacador de culonas");

                ArrayList <Personnage> LJ = new ArrayList<Personnage>();
              	LJ.add(J1);
                LJ.add(J2);
                LJ.add(J3);
                LJ.add(J4);

                //def Noeuds
                Noeud n1 = new Noeud("Fumeroche",0, volcan);
                Noeud n2 = new Noeud("Sylvambulle",1, foret);
                Noeud n3 = new Noeud("Feuillombre",2, foret);
                Noeud n4 = new Noeud("Brûlétincelle",3, volcan);
                Noeud n5 = new Noeud("Plaineville",4, plaine);
                Noeud n6 = new Noeud("Herbevaste",5, plaine);
                Noeud n7 = new Noeud("Arborville",6, foret);
                Noeud n8 = new Noeud("Champéclat",7, plaine);
                Noeud n9 = new Noeud("Prairieland",8, plaine);
                Noeud n10 = new Noeud("Horizonnée",9, plaine);
                Noeud n11 = new Noeud("Vasteland",10, plaine);
                Noeud n12 = new Noeud("Volcaville",11, volcan);
                Noeud n13 = new Noeud("Pyrécume",12, volcan);
                Noeud n14 = new Noeud("Verdoyance",13, foret);
                Noeud n15 = new Noeud("Plaineirisée",14, plaine);
                Noeud n16 = new Noeud("Boiséclosion",15, foret);

		n1.SetPersonnage(J1);
		n9.SetPersonnage(J2);
		n11.SetPersonnage(J3);
		n16.SetPersonnage(J4);

		n1.SetConsommable(new Consommable("bouclier",0,0));
		n6.SetConsommable(new Consommable("potion",0,0));
		n15.SetConsommable(new Consommable("fleche",0,0));
		n10.SetConsommable(new Consommable("fleche",0,0));
                
		ArrayList <Noeud> LN = new ArrayList<Noeud>();
                LN.add(n1);
                LN.add(n2);
                LN.add(n3);
                LN.add(n4);
                LN.add(n5);
                LN.add(n6);
                LN.add(n7);
                LN.add(n8);
                LN.add(n9);
                LN.add(n10);
                LN.add(n11);
                LN.add(n12);
                LN.add(n13);
                LN.add(n14);
                LN.add(n15);
                LN.add(n16);

		int[][] matriceAdjacence =
                        {{0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        { 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        { 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        { 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
                        { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        { 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1},
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0},
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                        { 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}};

                PlateauDeJeu plateau = new PlateauDeJeu(LN, LJ, LC);
                plateau.SetMatriceAdjacence(matriceAdjacence);

		System.out.println(plateau.toString());
	
		
		ArrayList<Noeud> voisins; 
		
		for (Noeud N : plateau.GetListeNoeud()) {	
			System.out.println("voisin de "+N.GetNom());
			voisins =  plateau.GetVoisinNoeud(N);
			for (Noeud n : voisins) {
				System.out.println(n.GetNumero());
				System.out.println(n.GetNom());
			}
			System.out.println("");
		}
	}

}
