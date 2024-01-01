import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class PanelNoeud extends JPanel {

	private int w;
	private int h;
	
	// panel principaux
	ImagePanel perso;
	JPanel mainPanel;

	// subpanel
	ImagePanel objet;
	//PanelLocalisation pLocal;
	JLabel titreVille;

	JPanel jNorth;
	JPanel jCenter;
	JPanel jSouth;


	public PanelNoeud(int width, int height) {

		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		this.w = width;
		this.h = height;
		
		// panel principaux
		perso = new ImagePanel("J1.png");
		perso.setOpaque(false);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(false);

		// subpanel
		objet = new ImagePanel("bouclier.png");
		objet.setOpaque(false);
		//pLocal = new PanelLocalisation();
		titreVille = new JLabel("blablablal");
		titreVille.setOpaque(false);

		jNorth = new JPanel();
		jNorth.setOpaque(false);
		
		jCenter = new JPanel();
		jCenter.setOpaque(false);
		jCenter.setLayout(new BorderLayout());

		jSouth = new JPanel();
		jSouth.setOpaque(false);

		//########
		//pLocal.getBtn().setEnabled(true);
		//########
		configureLayout();	

	}
/*
	public JButton getBtn() {
		return this.pLocal.getBtn();
	}
*/
	private void configureLayout() {
        	this.w = this.getWidth();
        	this.h = this.getHeight();

		System.out.println(this.w+" "+this.h);		
		// panel principaux
        	perso.setPreferredSize(new Dimension(w / 3, h));
        	perso.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.add(perso, BorderLayout.WEST);
        	mainPanel.setPreferredSize(new Dimension(2 * w / 3, h));
        	this.add(mainPanel, BorderLayout.CENTER);

        	// subpanel
        	titreVille.setPreferredSize(new Dimension(2 * w / 3, h / 8));
        	mainPanel.add(titreVille, BorderLayout.NORTH);
        	jSouth.setPreferredSize(new Dimension(2 * w / 3, getCenterPoint().y - h / 8));
        	mainPanel.add(jSouth, BorderLayout.SOUTH);

        	//pLocal.setPreferredSize(new Dimension(w / 3, h / 2));
  		//pLocal.setBorder(BorderFactory.createLineBorder(Color.BLUE));

		//jCenter.add(pLocal, BorderLayout.WEST);

        	objet.setPreferredSize(new Dimension(w / 3, h / 2));
		objet.setBorder(BorderFactory.createLineBorder(Color.RED));

        	jCenter.add(objet, BorderLayout.CENTER);
		jCenter.setBorder(BorderFactory.createLineBorder(Color.GREEN));


        	mainPanel.add(jCenter, BorderLayout.CENTER);
    		mainPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

	}
	
	
	
	// cette méthode est appelé des qu'on resize la fenetre
	protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
        /*	this.w = this.getWidth();
        	this.h = this.getHeight();


		// panel principaux
		perso.setPreferredSize(new Dimension(w/3,h));
		this.add(perso,BorderLayout.WEST);
		mainPanel.setPreferredSize(new Dimension(2*w/3,h));
		this.add(mainPanel,BorderLayout.CENTER);

		// subpanel
		titreVille.setPreferredSize(new Dimension(2*w/3,h/8));
		mainPanel.add(titreVille,BorderLayout.NORTH);
		jSouth.setPreferredSize(new Dimension(2*w/3,getCenterPoint().y-h/8));
		mainPanel.add(jSouth,BorderLayout.SOUTH);
		
		pLocal.setPreferredSize(new Dimension(w/3,h/2));
		jCenter.add(pLocal,BorderLayout.WEST);

		objet.setPreferredSize(new Dimension(w/3,h/2));
		jCenter.add(objet,BorderLayout.CENTER);
		
		mainPanel.add(jCenter,BorderLayout.CENTER);
*/
		configureLayout();
	}

	public Point getCenterPoint() {
        	int x = this.getX() + this.getWidth() / 2;
        	int y = this.getY() + this.getHeight() / 2;
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
	public static void main(String args[]) {
		final int WIDTH = 800;
		final int HEIGHT = 600;
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(WIDTH,HEIGHT);

		f.setLayout(new GridLayout(3,3));
		Container c = f.getContentPane();

		for (int i=0;i<9;i++){
			PanelNoeud pn = new PanelNoeud(WIDTH,HEIGHT);	
			c.add(pn);
		}
		f.setVisible(true);
	}
}
