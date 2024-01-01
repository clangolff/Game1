import javax.swing.*;
import java.awt.*;

public class PanelNoeudTest extends JPanel {

	// panel principaux
	ImagePanel perso;
	JPanel mainPanel;

	// subpanel
	JPanel images;
	ImagePanel objet;
	PanelLocalisation pLocal;
	JLabel titreVille;
	
	JPanel jn;
	JPanel js;

	public PanelNoeudTest() {

		this.setLayout(new BorderLayout());
		this.setOpaque(false);

		// panel principaux
		perso = new ImagePanel("J1.png");
		
		mainPanel = new JPanel();
		//mainPanel.setBackground(Color.RED);
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new BorderLayout());

		// subpanel
		titreVille = new JLabel("blablblbl");
		
		images = new JPanel(new BorderLayout());
		images.setOpaque(false);

		objet = new ImagePanel("bouclier.png");
		pLocal = new PanelLocalisation();

		jn = new JPanel();
		jn.setOpaque(false);
		js = new JPanel();
		js.setOpaque(false);

		this.add(perso,BorderLayout.WEST);
		this.add(mainPanel,BorderLayout.EAST);

		mainPanel.add(titreVille,BorderLayout.NORTH);
		mainPanel.add(js,BorderLayout.SOUTH);
		mainPanel.add(images,BorderLayout.CENTER);	
		images.add(pLocal,BorderLayout.WEST);
		images.add(objet,BorderLayout.EAST);
		
		configureLayout();

	}

	

	private void configureLayout() {
        	int w = this.getWidth();
        	int h = this.getHeight();

		perso.setPreferredSize(new Dimension(w/4,h));
		mainPanel.setPreferredSize(new Dimension(3*w/4,h));

		int subW = mainPanel.getWidth();
		int subH = mainPanel.getHeight();

		titreVille.setPreferredSize(new Dimension(subW,subH/8));
		js.setPreferredSize(new Dimension(subW,subH/8));

		int ssubW = images.getWidth();
		int ssubH = images.getHeight();
		System.out.println(ssubW+" "+ssubH);	
		pLocal.setPreferredSize(new Dimension(2*ssubW/3,ssubH));
		objet.setPreferredSize(new Dimension(ssubW/3,ssubH));
	}

	protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
       		configureLayout();
	}


	public static void main(String args[]) {
		final int WIDTH = 800;
		final int HEIGHT = 600;
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(WIDTH,HEIGHT);

		Container c = f.getContentPane();
		PanelNoeudTest pn = new PanelNoeudTest();
		c.add(pn);
		f.setVisible(true);
		pn.repaint();

	}
}
