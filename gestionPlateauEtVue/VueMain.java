
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;


public class VueMain extends JFrame {
        
	final int largeur = 1600;
        final int hauteur = 800;

    	private PanelAction panelAction;
	private PanelBanniere banniere;
	private PanelMap panelMap;
	private PanelInfoPersos panelInfoPersos;

	private PlateauDeJeu plateau;

	private String nomJoueur;

	public VueMain(PlateauDeJeu p, String nomJ) {
		this.plateau = p;
		this.nomJoueur = nomJ; 
	
		// frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.setTitle(nomJ);

		this.banniere = new PanelBanniere(); 
		this.panelAction =  new PanelAction();
		this.panelMap = new PanelMap(this.plateau);
		this.panelInfoPersos = new PanelInfoPersos(this.plateau.GetListePersonnage());
		
		Container c = this.getContentPane();
	
		c.add(this.banniere, BorderLayout.NORTH);
                c.add(this.panelAction, BorderLayout.EAST);
                c.add(this.panelMap, BorderLayout.CENTER);
                c.add(this.panelInfoPersos, BorderLayout.WEST);

		banniere.setPreferredSize(new Dimension(largeur,hauteur/10));
                panelAction.setPreferredSize(new Dimension(largeur/10,9*hauteur/10));
                //panelMap.setPreferredSize(new Dimension());
                panelInfoPersos.setPreferredSize(new Dimension(largeur/6,9*hauteur/10));

		this.revalidate();
		this.repaint();
		this.pack();
		this.setSize(largeur,hauteur);
		this.setVisible(true);
	
	}


	public void updateFrame(PlateauDeJeu p) {
		this.plateau = p;
		System.out.println("mise a jour des panel perso");
		int k = 0;
		for(Personnage perso : p.GetListePersonnage()) {
                        this.panelInfoPersos.updatePanel(perso);
			System.out.println("mise a jour du perso "+k);
			k+=1;
                }

		System.out.println("mise a jour du panelMap");
                this.panelMap.updatePanel(p.GetListeNoeud());
		
		// pour etre sur
		for (Noeud n : p.GetListeNoeud()) {
			this.panelMap.enableNoeud(false,n);
			System.out.println("j'enable le noeud"+n.GetNumero());
		}

		for (Action a : Action.values()) {
			System.out.println("j'enable le bouton"+a.toString());
			this.panelAction.enableBtnAction(a,false);
		}

		this.panelAction.updatePanel();

		this.revalidate();
		this.repaint();
                //this.pack();
                this.setSize(largeur,hauteur);
	}	

	public PanelAction getPanelAction() {
		return this.panelAction;
	}

	public PanelMap getPanelMap() {
		return this.panelMap;
	}

	public PanelInfoPersos getPanelInfoPersos() {
		return this.panelInfoPersos;
	}

	public void PopUp(Message m) {
		new PopUp(m);	
	}

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





		VueMain vue = new VueMain(plateau,J1.GetNom());

		//vue.miseAJour(p);
        }

}
