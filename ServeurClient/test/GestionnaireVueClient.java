import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.BorderFactory;

public class GestionnaireVueClient {
	
	private VueMain vue;
	private VueConnexion vueConnexion;

	private PanelMap pm;
	private PanelAction pa;
	private PanelInfoPersos pi;

	private Action actionChoisie;
	private int numNoeudChoisi;
	private String nomJ;
	
	private Object o;

	final int NBNOEUDMAX = 16;


	public GestionnaireVueClient(/*VueMain v*/) {
		this.o = new Object();
/*		this.vue = v;
		this.pm = v.getPanelMap();
		this.pa = v.getPanelAction();

		addActionListenerBtnNoeud();
		addActionListenerBtnAction();
*/	}
	
	public void setVue(VueMain v) {
		this.vue = v;
	}

	public VueMain getVue() {
		return this.vue;
	}
	
	public void initialiserVue(PlateauDeJeu p, String nom) {
		this.vue = new VueMain(p,nom);
		this.pm  = this.vue.getPanelMap();
		this.pa = this.vue.getPanelAction();

		addActionListenerBtnNoeud();
		addActionListenerBtnAction();
	}

	public void initialiserVueConnexion() {
		this.vueConnexion = new VueConnexion();
	}

	public void showVueConnexion() {
		this.vueConnexion.montre();
	}

	public void updateFrame(PlateauDeJeu p) {
		System.out.println("plateau vu depuis le gestionnaire");
		System.out.println(p.toString());
		this.vue.updateFrame(p);
	}

	public void addActionListenerLogin() {
		
		JButton btnValider = this.vueConnexion.getBtnValider();
		JTextField textUser = this.vueConnexion.getTextField();

		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                		nomJ = textUser.getText();
				vueConnexion.dispose();
				synchronized(o) {
					o.notify();
				}
                        }
		});
	}

	public String getNomLogin() {
		synchronized(this.o) {
			try {	
				System.out.println("J'attend le nom");
				this.o.wait();
			} catch (Exception e) {}
		} 
		
		return nomJ;
	}


	private boolean estValide(Action action, PlateauDeJeu p, Noeud posPerso) {
		
		System.out.println("Action a checker : " + action + " - Position du noeud de depart" + posPerso.GetNumero()); 
		boolean b = false;
		ArrayList<Noeud> noeudsAtteignables;

		switch(action) {
			case recuperer :
				// on check s'il y a un consommable sur le noeud du perso
				if (posPerso.GetConsommable() == null) {
					System.out.println("Pas de consommable sur la case"); 
					b = false;
				} else {
					System.out.println("Consommable sur la case --> Recolte possible");
					b = true;
				}
				break;

			case seDeplacer :
				// on check s'il y a pas de perso autour qui empeche de se deplacer
				noeudsAtteignables = p.NoeudAtteignableDeplacement(posPerso,2);
				if (noeudsAtteignables.isEmpty()) {
					System.out.println("Pas de noeuds atteignables");
					b = false;
				} else {
					System.out.println("Peut se déplacer --> Deplacement possible");
					b = true;
				}
				break;

			case attaquerCac : 
				// on check s'il y a des perso qu'on peut attaquer
				noeudsAtteignables = p.NoeudAtteignableAttaque(posPerso,1);
				if (noeudsAtteignables.isEmpty()) {
					System.out.println("Pas de noeuds atteignables CAC");
					b = false;
				} else {
					System.out.println("Peut se attaquer au CAC --> AttaqueCAC possible");
					b = true;
				}
				break;

			case attaquerArc :
				// on check s'il y a des perso qu'on peut attaquer
				noeudsAtteignables = p.NoeudAtteignableAttaque(posPerso,3);
				if (noeudsAtteignables.isEmpty()) {
					System.out.println("Pas de noeuds atteignables ARC");
					b = false;
				} else {
					System.out.println("Peut se attaquer au ARC --> AttaqueARC possible");
					b = true;
				}
				break;
		}

		return b;
	}


	public void addActionListenerBtnAction() {
		for (Action a : Action.values()) {
			
			ImageButton btn = pa.getBtn(a);
				
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionChoisie = a;
					synchronized(o) {
						o.notify();
					}
				}
			});
		}
	}


	public Action retournerAction(PlateauDeJeu p, Noeud posPerso) {
		// on enable les btn action qui sont valides
		for (Action a : Action.values()) {
			if (estValide(a,p,posPerso)) {
				System.out.println("j'enable le btn "+a.toString());
				pa.enableBtnAction(a,true);
			}
		}

		pa.updatePanel();

		synchronized (this.o) {
			try {
				System.out.println("j'attend que le client fasse une action");
				this.o.wait();
			} catch (Exception e) {}
		}

		System.out.println(actionChoisie.toString());
	
		// on disable les boutons actions
		for (Action a : Action.values()) {
			pa.enableBtnAction(a,false);
		}

		pa.updatePanel();
		return actionChoisie;
	}

	public void addActionListenerBtnNoeud() {
		for(PanelNoeud pn : pm.getListePanelNoeud()) {
			JButton btn = pn.getBtn();
			int k = pn.getNoeud().GetNumero();
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
                    			numNoeudChoisi = k;
					System.out.println("neoud "+k+" choisi");
					synchronized(o) {
						o.notify();
					}
                		}
            		});
		}
	}

	public int retournerIndexNoeud(Action action, PlateauDeJeu p, Noeud positionPerso) {
		PlateauDeJeu plateau = p;
		int indexNoeud = 0;
		Noeud posPerso = positionPerso;

		ArrayList<Noeud> noeudsAtteignables;
		switch(action) {
			case recuperer : 
				indexNoeud = plateau.GetListeNoeud().indexOf(posPerso); 
				break;
	
			case seDeplacer :
				noeudsAtteignables = plateau.NoeudAtteignableDeplacement(posPerso,2);
			
				// on enable les bouton noeud du panelMap
				for (Noeud n : noeudsAtteignables) {
       					pm.enableNoeud(true,n);
				}
				// on attend que le client appuie sur un bouton
				synchronized (this.o) {
        		               	try {
                                		System.out.println("j'attend");
                                		this.o.wait();
                        		} catch (Exception e) {}
                		}

				// on disable les bouton noeud du panelMap
       				for (Noeud n : noeudsAtteignables) {
					pm.enableNoeud(false,n);
				}
				break;

			case attaquerCac :
				noeudsAtteignables = plateau.NoeudAtteignableAttaque(posPerso,1); 
				// on enable les bouton noeud du panelMap
       				for (Noeud n : noeudsAtteignables) {
       					pm.enableNoeud(true,n);
				}

                            	
				// on attend que le client appuie sur un bouton
				synchronized (this.o) {
        		               	try {
                                		System.out.println("j'attend");
                                		this.o.wait();
                        		} catch (Exception e) {}
                		}

				// on disable les bouton noeud du panelMap
       				for (Noeud n : noeudsAtteignables) {
					pm.enableNoeud(false,n);
				}

				break;

			case attaquerArc :
				noeudsAtteignables = plateau.NoeudAtteignableAttaque(posPerso,3); 
				// on enable les bouton noeud du panelMap
				for (Noeud n : noeudsAtteignables) {
       					pm.enableNoeud(true,n);
				}


				// on attend que le client appuie sur un bouton
				synchronized (this.o) {
        		               	try {
                                		System.out.println("j'attend");
                                		this.o.wait();
                        		} catch (Exception e) {}
                		}

				// on disable les bouton noeud du panelMap
				for (Noeud n : noeudsAtteignables) {
					pm.enableNoeud(false,n);
				}

				break;

		}

		return numNoeudChoisi;
	}
/*
	public static void main(String[] args) {
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
                n6.SetPersonnage(J2);
                n9.SetPersonnage(J3);
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

		J2.SetDoitJouer(true);


		VueMain v = new VueMain(plateau,J1.GetNom());
		GestionnaireVueClient g = new GestionnaireVueClient(v);

		Action a = g.retournerAction(plateau,n6);
		int k = g.retournerIndexNoeud(a,plateau,n6);
		
		System.out.println(a.toString()+" sur "+k);

	}
*/}
