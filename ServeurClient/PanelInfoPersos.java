
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.util.HashMap;
import java.util.Map;

public class PanelInfoPersos extends JPanel {

	private ArrayList<Personnage> listePerso;
	private ArrayList<PanelInfoJoueur> listePanel;
	private Image backGroundMainPanel;
	private Map<Personnage, PanelInfoJoueur> joueurPanelMapping;

	public PanelInfoPersos(/*int l, int h ,*/ ArrayList<Personnage> persos) {
		
		this.listePerso = persos;

        	//this.setPreferredSize(new Dimension(l,h));
		this.setLayout(new GridLayout(4,1));
	 	this.setOpaque(false);

		backGroundMainPanel = new ImageIcon("Bords.png").getImage();
		
		Personnage p1 = persos.get(0);
		Personnage p2 = persos.get(1);
		Personnage p3 = persos.get(2);
		Personnage p4 = persos.get(3);
		
		// ############# Creation des 4 panels individuels
		PanelInfoJoueur panelPerso1 = new PanelInfoJoueur(p1,"J1.png");
		PanelInfoJoueur panelPerso2 = new PanelInfoJoueur(p2,"J1.png");
		PanelInfoJoueur panelPerso3 = new PanelInfoJoueur(p3,"J1.png");
		PanelInfoJoueur panelPerso4 = new PanelInfoJoueur(p4,"J1.png");
	
		this.add(panelPerso1);	
		this.add(panelPerso2);
		this.add(panelPerso3);
		this.add(panelPerso4);
		
		listePanel = new ArrayList<>();

		listePanel.add(panelPerso1);
		listePanel.add(panelPerso2);
		listePanel.add(panelPerso3);
		listePanel.add(panelPerso4);

		joueurPanelMapping = new HashMap<>();

	        for (int i = 0; i < listePerso.size(); i++) {
            		joueurPanelMapping.put(listePerso.get(i), listePanel.get(i));
        	}
	}

	public class PanelInfoJoueur extends JPanel {
		
		private Personnage perso;

		private JPanel panelStats;
		private JPanel panelPerso;

		private JProgressBar barreDeVie;
		private JPanel arrow;
		private JPanel shield;

		private JLabel labelFleches;
		private JLabel labelBouclier;

		ImagePanel flch;
		ImagePanel bcl;

		BordEtincelantRect bord;

		boolean doitJouer;

		public PanelInfoJoueur(Personnage p, String path) {
			
			// ############# Organisation generale du Panel
			this.setLayout(new GridLayout(1,2));
			this.setOpaque(false);
			
			this.perso = p;

			// ############# Avatar perso (SUBPanel à gauche)
			String pseudo = perso.GetNom();
		        JLabel nomPlayer = new JLabel(pseudo, SwingConstants.CENTER); 
		        nomPlayer.setFont(new Font("Arial", Font.BOLD, 15));
		        /*
		        ImageIcon avatarIcon = new ImageIcon(path);
			Image avatarImage = avatarIcon.getImage().getScaledInstance(80, 160, Image.SCALE_SMOOTH); 
			ImageIcon scaledAvatarIcon = new ImageIcon(avatarImage);
			JLabel img = new JLabel(scaledAvatarIcon);
		       */
		       	ImagePanel imgAvatar = new ImagePanel(path);
		        panelPerso = new JPanel();
		        panelPerso.setOpaque(false);
		        
		        panelPerso.setLayout(new BorderLayout());
        		panelPerso.add(imgAvatar, BorderLayout.CENTER);
        		panelPerso.add(nomPlayer, BorderLayout.SOUTH);
		        
		        // ############# Stats perso (SUBPanel à droite)
			panelStats = new JPanel();
		        panelStats.setLayout(new GridLayout(3,1));
			panelStats.setOpaque(false);
			
	        
		        // Vie
		        barreDeVie = new JProgressBar(0, 100);
       
			// Fleches
		        arrow = new JPanel(); 
		        arrow.setLayout(new GridLayout(1,2));
		        arrow.setOpaque(false);
		        
		        flch = new ImagePanel("fleche.png"); 
	        
		        // Bouclier 
		        shield = new JPanel(); 
		        shield.setLayout(new GridLayout(1,2));
		        shield.setOpaque(false);
		        
		        bcl = new ImagePanel("bouclier.png"); 
	
			bord = new BordEtincelantRect();
			bord.setLayout(new GridLayout(1,1));
			updatePanelPerso(perso);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		}

		public void updatePanelPerso(Personnage p) {
			if (this.getComponentCount() > 0) {
				this.removeAll();
				panelStats.removeAll();
				arrow.removeAll();
				shield.removeAll();
				bord.removeAll();
			}

			System.out.println("vie");	
			int vie = p.GetVie();
							        

			barreDeVie.setValue(vie);
                        barreDeVie.setFont(new Font("Arial", Font.PLAIN, 19));
                        barreDeVie.setStringPainted(true);
			
			panelStats.add(barreDeVie);
			
			System.out.println("fleche");

			int fleches = p.GetCarquois().GetQuantite();
			labelFleches = new JLabel("x" + fleches, SwingConstants.CENTER);
	        	labelFleches.setFont(new Font("Arial", Font.BOLD, 17));
		
			arrow.add(flch);
                        arrow.add(labelFleches);

			panelStats.add(arrow);
			
			System.out.println("bouclier");

			int bouclier = p.GetBouclier().GetDuree();
			labelBouclier = new JLabel(bouclier + " tour", SwingConstants.CENTER);


			System.out.println("ajout de l'image bouclier");
			shield.add(bcl);
			System.out.println("ajout du label");
                        shield.add(labelBouclier);
			
			System.out.println("ajput su shield");
			panelStats.add(shield);

			System.out.println("ajout du panel perso");
			bord.add(panelPerso);

			System.out.println("ajout du bord");
                        this.add(bord);
			System.out.println("ajout des stats");
                        this.add(panelStats);
		
			doitJouer = p.GetDoitJouer();
			
			if (doitJouer) {
				bord.startEffect();
				System.out.println("j'ai demarré l'effet");
			} else {
				bord.stopEffect();
				System.out.println("j'ai stoppé l'effet");
			}

			revalidate();
			repaint();
		}
		
		public String toString() {
			return this.perso.GetNom();
		}		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);    
	    	g.drawImage(backGroundMainPanel, 0, 0,getWidth(),getHeight(),  this);  
	}

	public void updatePanel(Personnage p) {
		System.out.println("je suis dans le panel info perso");
		System.out.println(p.GetNom());

		PanelInfoJoueur pj = joueurPanelMapping.get(p);
		
		System.out.println(pj.toString());

		pj.updatePanelPerso(p);
		revalidate();
		repaint();
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

		J1.SetDoitJouer(true);
		

                System.out.println(plateau.toString());


		JFrame f1 = new JFrame();
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f1.setSize(300, 600);

                JFrame f2 = new JFrame();
                f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f2.setSize(300, 200);



                PanelInfoPersos pi = new PanelInfoPersos(LJ);
                Container c = f1.getContentPane();
                c.add(pi);
                f1.pack();
                f1.setSize(300,600);
  
		f1.setLocationRelativeTo(null);
                f1.setVisible(true);

                JButton button = new JButton("Changez l'effet");
                button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
               			J1.SetDoitJouer(false);
				J1.SetVie(10);
				J2.SetDoitJouer(true);
				pi.updatePanel(J1);
				pi.updatePanel(J2);		
				f1.pack();
				f1.setSize(300,600);
			}
                });



                f2.getContentPane().add(button);
                f2.setSize(300, 200);
                f2.setVisible(true);

	}
}
