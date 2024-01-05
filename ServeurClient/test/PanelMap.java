import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class PanelMap extends JPanel{
	
	private Image imageMap;

	private ArrayList<PanelNoeud> listePanelNoeud = new ArrayList<PanelNoeud>();
	private int[][] matriceLocalisationNoeud = 	
		{{ 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0},
		{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
		{ 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
		{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
		{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
		{ 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0}};
	
	private int[][] matriceAdjacence;
			
	private ArrayList<Noeud> listeNoeud;
	private ArrayList<Personnage> listePerso;

	// Sert pour gerer l'interaction avec le client
	//private boolean btnCliquer = false;


	public PanelMap(/*int l, int h, */PlateauDeJeu p) {
	
		this.listeNoeud = p.GetListeNoeud();
		this.listePerso = p.GetListePersonnage();
		matriceAdjacence = p.GetMatriceAdjacence();
		
		imageMap = new ImageIcon("Map.png").getImage();

		this.setOpaque(false);
		this.setLayout(new GridLayout(8,11));
		
		this.listePanelNoeud = new ArrayList<PanelNoeud>();
		int k = 0; // numéro du noeud
		for (int i=0;i<8;i++) {
			for (int j=0;j<11;j++) {

				if (matriceLocalisationNoeud[i][j]==0) {
					JPanel panel = new JPanel();
					panel.setVisible(false);
					this.add(panel);
				} else {
					//Noeud n = new Noeud("blblbl",0,new Region());

	                	        //n.SetPersonnage(new Personnage());
        		                //n.SetConsommable(new Consommable());
					Noeud n = this.listeNoeud.get(k);
					PanelNoeud noeud = new PanelNoeud(n);
					k+=1;
					this.add(noeud);
					listePanelNoeud.add(noeud); 	
				}
			} 
		}
	}


	public ArrayList<PanelNoeud> getListePanelNoeud() {
		return this.listePanelNoeud;
	}
	

	public void enableNoeud(boolean b, Noeud n) {
		int index = n.GetNumero();
		this.listePanelNoeud.get(index).getBtn().setEnabled(b);
	}


	public boolean isNoeudEnabled(Noeud n) {
		return this.listePanelNoeud.get(n.GetNumero()).getBtn().isEnabled();
	}

/*
	public boolean getBtnCliquer() {
		boolean res = false;
		for (PanelNoeud pn : listePanelNoeud) {
			if (pn.getBtnCliquer()) {
				res = true;
				break;
			}
		}
		return res;
	}

	public void setBtnCliquer(boolean b) {
		for (PanelNoeud pn : listePanelNoeud) {
			pn.setBtnCliquer(b);
		}
	}
*/
	
	// Sert pour Gestionnaire vue client
	public JButton getBtn(Noeud n) {
		int k = n.GetNumero();
		return this.listePanelNoeud.get(k).getBtn();  
	}
	
	// Remarque : Cette fonction est appelée quand on est sur qu'il y a panelNoeud qui a son btnCliquer a vrai
/*	public int getNumeroNoeud() {
		int res = 0;
		for (int i = 0; i < listePanelNoeud.size(); i++) {
			if (listePanelNoeud.get(i).getBtnCliquer()) {
				res = i;
				break;
			}
		}
		return res;
	}
*/
	// Traitement des lignes 
	protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
        	if (imageMap != null) {
           		 g.drawImage(imageMap, 0, 0, getWidth(), getHeight(), this);
		}
		
		
		if (listePanelNoeud != null) {
			Point pointStart = new Point(0,0);
			Point pointEnd = new Point(0,0);
			for (int i=0;i<listePanelNoeud.size();i++) {
				PanelNoeud PanelStart = listePanelNoeud.get(i);
				for(int j=0;j<=i;j++) {
					if (matriceAdjacence[i][j] == 1) {
						PanelNoeud PanelEnd = listePanelNoeud.get(j);
						pointStart = getCenterPoint(PanelStart);
						pointEnd = getCenterPoint(PanelEnd);
						g.drawLine(pointStart.x,pointStart.y,pointEnd.x,pointEnd.y);
					}
				}
			}
		}
    	}

	// Calcul les coordonnées du centre du PanelNoeuds
	private Point getCenterPoint(Component component) {
        	int x = component.getX() + component.getWidth() / 2;
        	int y = component.getY() + component.getHeight() / 2;
        	return new Point(x, y);
	}

	// Definit les coordonnees du PanelNoeud
	public class Point {
		public int x;
		public int y;
		
		public Point(int X,int Y) {
			this.x = X;
			this.y = Y;
		}
	}


	public void updatePanel(ArrayList<Noeud> listeNoeud) {
		for (Noeud n : listeNoeud){
			PanelNoeud pn = this.listePanelNoeud.get(n.GetNumero());
			pn.updateNode(n);
		}
		revalidate();
		repaint();
	}


	public static void main(String[] argv) {
	
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


		JFrame f1 = new JFrame();
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f1.setSize(1298, 696);

                JFrame f2 = new JFrame();
                f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f2.setSize(300, 200);



                PanelMap pm = new PanelMap(plateau);
		Container c = f1.getContentPane();
		c.add(pm);
		f1.pack();
		f1.setSize(1298,696);

	
		/*
                JButton btnPN = pn.getBtn();
                btnPN.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                System.out.println("Bouton cliqué");
                        }
                });
*/


		Noeud vide = new Noeud("VIDE",0,new Region());
		LN.remove(n1);
		LN.add(vide);

                JButton button = new JButton("changez l'effet");
                button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	pm.updatePanel(LN);
				for (PanelNoeud pn : pm.getListePanelNoeud()) {
					pn.getBtn().setEnabled(!pn.getBtn().isEnabled());
				}
				f1.pack();
				f1.setSize(1298,696);	
			}
                });


		for (PanelNoeud pn : pm.getListePanelNoeud()) {
			JButton btn = pn.getBtn();
			int k = pn.getNoeud().GetNumero();
			btn.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
                			System.out.println("bouton "+k+" Cliqué");
            			}
        		});

		}
                
                f2.add(button);

                
		f1.setLocationRelativeTo(null);
                f1.setVisible(true);
		f2.setVisible(true);
	}
}
