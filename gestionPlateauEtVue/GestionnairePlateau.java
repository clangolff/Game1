
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import java.util.ArrayList;
import java.util.Random;


public class GestionnairePlateau {
	
	private PlateauDeJeu plateau;
	private int indexJQJ;

	public GestionnairePlateau() {
	}

	public int getIndexJQJ() {
		return this.indexJQJ;
	}

	public void setIndexJQJ(int i) {
		this.indexJQJ = i;
		this.plateau.GetListePersonnage().get(indexJQJ).SetDoitJouer(true);
	}

	public PlateauDeJeu getPlateau() {
		return this.plateau;
	}

	public void initialiserPlateau(){
			
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

		//n1.SetPersonnage(J1);
                //n6.SetPersonnage(J2);
                //n9.SetPersonnage(J3);
                //n16.SetPersonnage(J4);

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

		this.plateau = new PlateauDeJeu(LN, LJ, LC);
 		this.plateau.SetMatriceAdjacence(matriceAdjacence);
 		this.plateau.setIndexJQJ(0);
	}

	public void ajouterJoueur(String nom) {
		Personnage p = new Personnage(nom);
		this.plateau.GetListePersonnage().add(p);
	}


	public void affecterPersosANoeud() {
		this.plateau.GetListeNoeud().get(0).SetPersonnage(this.plateau.GetListePersonnage().get(0));
		Noeud n1 = this.plateau.GetListeNoeud().get(0);
		this.plateau.GetListePersonnage().get(0).SetPosition(n1);

		this.plateau.GetListeNoeud().get(4).SetPersonnage(this.plateau.GetListePersonnage().get(1));
		Noeud n9 = this.plateau.GetListeNoeud().get(4);
		this.plateau.GetListePersonnage().get(1).SetPosition(n9);

		this.plateau.GetListeNoeud().get(1).SetPersonnage(this.plateau.GetListePersonnage().get(2));
		Noeud n11 = this.plateau.GetListeNoeud().get(1);
		this.plateau.GetListePersonnage().get(2).SetPosition(n11);

		this.plateau.GetListeNoeud().get(15).SetPersonnage(this.plateau.GetListePersonnage().get(3));
		Noeud n16 = this.plateau.GetListeNoeud().get(15);
		this.plateau.GetListePersonnage().get(3).SetPosition(n16);

	/*	int indexNoeud;
		Random r = new  Random();

		for (Personnage p : this.plateau.GetListePersonnage()) {
			indexNoeud = r.nextInt(this.plateau.GetListeNoeudLibre().size());
			this.plateau.GetListeNoeud().get(indexNoeud).setPersonnage(p);
			Noeud noeud = this.plateau.GetListeNoeud().get(indexNoeud);
			p.setPosition(noeud);

			this.pleateu.GetListeNoeudLibre().remove(noeud);
			this.plateau.GetListeNoeudNonLibre().add(noeud);
		}*/
	}

	public void affecterPositionAPersos() {
		this.plateau.GetListePersonnage().get(0).SetPosition(this.plateau.GetListeNoeud().get(0));
		this.plateau.GetListePersonnage().get(1).SetPosition(this.plateau.GetListeNoeud().get(4));
		this.plateau.GetListePersonnage().get(2).SetPosition(this.plateau.GetListeNoeud().get(1));
		this.plateau.GetListePersonnage().get(3).SetPosition(this.plateau.GetListeNoeud().get(15));
	}

	public void affecterConsosANoeud() {

		this.plateau.GetListeNoeud().get(0).SetConsommable(this.plateau.GetListeConsommable().get(0)); // Bouclier
		this.plateau.GetListeNoeud().get(5).SetConsommable(this.plateau.GetListeConsommable().get(1)); // Fleches
		this.plateau.GetListeNoeud().get(14).SetConsommable(this.plateau.GetListeConsommable().get(1)); 
		this.plateau.GetListeNoeud().get(9).SetConsommable(this.plateau.GetListeConsommable().get(2)); // PotionVie

	/*	int indexNoeud;
		Random r = new  Random();

		for (Consommable c : this.plateau.GetListeConsommable()) {
			indexNoeud = r.nextInt(this.plateau.GetListeNoeudLibre().size());
			this.plateau.GetListeNoeud().get(indexNoeud).setConsommable(c);
			Noeud noeud = this.plateau.GetListeNoeud().get(indexNoeud);

			this.pleateu.GetListeNoeudLibre().remove(noeud);
			this.plateau.GetListeNoeudNonLibre().add(noeud);
		}*/
	}

	public Message MAJPlateau(Action action, int indexNoeud) {
		
		Noeud noeud = this.plateau.GetListeNoeud().get(indexNoeud);
		Personnage p = this.plateau.GetListePersonnage().get(this.indexJQJ);
		
		Message message = Message.persoInactif;
		
		switch(action) {
			case recuperer :
				ArrayList<Noeud> listeNoeudSansConso = this.plateau.GetPositionSansConsommables();
				this.recolterConso(p,listeNoeudSansConso);
				message = Message.persoARecupererConso;
				break;

			case seDeplacer :
				// On enleve le joueur du noeud actuel
				this.plateau.GetListeNoeud().get(p.GetPosition().GetNumero()).SetPersonnage(null); 
				
				// On deplace le joueur au bon endroit
				this.DeplacerJoueur(p,noeud);
				message = Message.persoDeplacerSurNoeud;
				break;

			case attaquerCac :
				this.JoueurAttaqueEpee(p,noeud);
				message = Message.persoAttaqueCACSurNoeud;
				break;

			case attaquerArc :
				boolean reussite = this.JoueurAttaqueArc(p,noeud);
				if (reussite) {
					message = Message.persoAttaqueARCSurNoeud;
					System.out.println("Tir reussi"); 
				} else {
					message = Message.persoRateAttaqueSurNoeud;
					System.out.println("Tir rate");
				}
				break;
		}

		return message;
	}

	public void recolterConso(Personnage p, ArrayList<Noeud> ListeNoeudSansConso) {

		p.Recolter(); //Le Joueur recupere son consommable
		
		Consommable c = p.GetPosition().GetConsommable();
		p.GetPosition().SetConsommable(null); //on retire le consommable

		// on remet le consommable sur un autre noeud
		Random rand = new Random();
		ArrayList<Noeud> listeNoeudSansConso = this.plateau.GetPositionSansConsommables();
		Noeud n = listeNoeudSansConso.get(rand.nextInt(ListeNoeudSansConso.size()));
		n.SetConsommable(c);
		listeNoeudSansConso.remove(n);
	
		//on ajoute le noeud du perso aux noeud sans conso
		listeNoeudSansConso.add(p.GetPosition());    	
	}
   
	public void DeplacerJoueur(Personnage p, Noeud noeud) {
		System.out.println("Le joueur est déplace au noeud : " + noeud.GetNumero()); 
		p.SeDeplacer(noeud);
    }

	public void JoueurAttaqueEpee(Personnage p, Noeud noeud) {
		if(noeud.GetPersonnage().GetBouclier().GetDuree() == 0) {
			p.Attaquer(noeud, 20 + p.GetPosition().GetGeographie().GetEffet()) ;
			if (noeud.GetPersonnage().estMort()) {
				EliminerPerso(noeud.GetPersonnage());
			}
		} else {
			noeud.GetPersonnage().GetBouclier().SetDuree(0);
		}
	}

	public boolean JoueurAttaqueArc(Personnage p, Noeud noeud) {
      	 	boolean reussite;
		
		// on simule une précision
		Random rand = new Random(); 
		if (rand.nextDouble() < noeud.GetGeographie().GetProba()) {
        		if(noeud.GetPersonnage().GetBouclier().GetDuree() == 0) {
					p.Attaquer(noeud, 15 + p.GetPosition().GetGeographie().GetEffet() + noeud.GetGeographie().GetEffet()) ;
						if (noeud.GetPersonnage().estMort()) {
							EliminerPerso(noeud.GetPersonnage());
						}
				} else {
						noeud.GetPersonnage().GetBouclier().SetDuree(0);
				}
			reussite = true;
		} else {
			reussite = false;
		}
		p.GetCarquois().SetQuantite(p.GetCarquois().GetQuantite()-1);
		return reussite;
    }

	public void updateTour() {
		this.plateau.GetListePersonnage().get(indexJQJ).SetDoitJouer(false);
		this.indexJQJ =+ 1 % this.plateau.GetListePersonnage().size();
		this.plateau.setIndexJQJ(this.indexJQJ);
	}

	//Eliminer un personnage	
	public void EliminerPerso(Personnage p) {
        	this.plateau.SetNbJoueurEnVie(this.plateau.GetNbJoueurEnVie()-1);
        	p.GetPosition().SetPersonnage(null);
        	p.SetPosition(null);
    }


// ############## ENREGISTREMENT #################


 	public void EnregistrerPlateau(PlateauDeJeu p,String nomfichier) {
        	try {
            		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomfichier)));
            		oos.writeObject(p);
            		oos.close();
        	} catch (IOException e) {
            	e.printStackTrace();
        	}
    	}

    	public PlateauDeJeu ChargerPlateau( String nomfichier) {
        	PlateauDeJeu res = null;
        	try{
            		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomfichier)));
            		res = (PlateauDeJeu) ois.readObject();
            		ois.close();
            		return res;
        	} catch ( FileNotFoundException e) {
            		e.printStackTrace();
        	} catch ( IOException e) {
            		e.printStackTrace();
        	} catch (ClassNotFoundException e) {
           		 e.printStackTrace();
        	}
        	return res;
   	 }
	

	public static void main(String[] args) {
	
		GestionnairePlateau gp = new GestionnairePlateau();
		gp.initialiserPlateau();
		PlateauDeJeu plateau = gp.getPlateau();
		
		gp.affecterPersosANoeud();
		gp.affecterPositionAPersos();

		System.out.println(plateau.toString());
		
		plateau.GetListePersonnage().get(0).SetDoitJouer(true);
		VueMain vue = new VueMain(plateau,"thierry");
		GestionnaireVueClient gc = new GestionnaireVueClient(vue);
		gc.updateFrame(plateau);
	
		for (int i=0;i<8;i++) {
			Noeud posPerso = plateau.GetListePersonnage().get(0).GetPosition();
			Action a = gc.retournerAction(plateau,posPerso);
			int k = gc.retournerIndexNoeud(a,plateau,posPerso);

			System.out.println(a.toString()+" sur "+k);

			Message m = gp.MAJPlateau(a,k);
			System.out.println(m.toString());

			plateau = gp.getPlateau();
			gc.updateFrame(plateau);
 			System.out.println(plateau.toString());
		}
		
		System.out.println("PARTIE FINI");	
	}

}
