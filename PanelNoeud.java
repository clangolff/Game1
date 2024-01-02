import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
		
public class PanelNoeud extends JPanel {

	ImagePanel perso;
	ImagePanel objet;
	PanelLocalisation pLocal;
	JLabel titreVille;

	JPanel p;	

	public PanelNoeud() {

		this.setLayout(new BorderLayout());
		this.setOpaque(false);

		perso = new ImagePanel("J1.png");
		titreVille = new JLabel("blablblbl",SwingConstants.CENTER);
		objet = new ImagePanel("bouclier.png");
		pLocal = new PanelLocalisation();
		
		p = new JPanel(new BorderLayout());
		p.setOpaque(false);

		this.add(p,BorderLayout.CENTER);
		this.add(perso,BorderLayout.WEST);
		this.add(objet,BorderLayout.EAST);

		p.add(titreVille,BorderLayout.SOUTH);
		p.add(pLocal,BorderLayout.CENTER);

	}

	

	private void configureLayout() {
		int w = getWidth();
        	int h = getHeight();

		perso.setPreferredSize(new Dimension(w/4,h));
		objet.setPreferredSize(new Dimension(w/4,h));


		int pw = p.getWidth();
		int ph = p.getHeight();
		titreVille.setPreferredSize(new Dimension(pw,ph/4));
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
		PanelNoeud pn = new PanelNoeud();
		c.add(pn);
		f.setVisible(true);
		pn.repaint();

	}
}
