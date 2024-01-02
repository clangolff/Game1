import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
		
public class PanelNoeud extends JPanel {

	ImagePanel perso;
	ImagePanel objet;
	PanelLocalisation pLocal;
	JLabel titreVille;

	JPanel p;	
	JPanel jw;
	JPanel je;

	Noeud n;

	public PanelNoeud(Noeud noeud) {

		this.n = noeud;

		this.setLayout(new BorderLayout());
		this.setOpaque(false);

		objet = new ImagePanel("bouclier.png");
		titreVille = new JLabel(n.GetNom(),SwingConstants.CENTER);
		pLocal = new PanelLocalisation();
		perso = new ImagePanel("J1.png");

		p = new JPanel(new BorderLayout());
		//p.setBackground(Color.GREEN);
		p.setOpaque(false);
		
		p.add(titreVille,BorderLayout.SOUTH);
		p.add(pLocal,BorderLayout.CENTER);
	

		je = new JPanel();
		je.setOpaque(false);
		//je.setBackground(Color.RED);

		jw = new JPanel();
		//jw.setBackground(Color.BLUE);
		jw.setOpaque(false);

		updateNode(this.n);
	
	}

	

	private void configureLayout() {
		int w = getWidth();
        	int h = getHeight();
		
		Dimension dim = new Dimension(w/4,h);

		perso.setPreferredSize(dim);
		je.setPreferredSize(dim);

		objet.setPreferredSize(dim);
		jw.setPreferredSize(dim);
		
		int pw = p.getWidth();
		int ph = p.getHeight();
		titreVille.setPreferredSize(new Dimension(pw,ph/4));
	}

	protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
       		configureLayout();
	}

       
	public void updateNode(Noeud updatedNode) {
		if (this.getComponentCount() > 0) {
           		removeAll();
        	}

		this.n = updatedNode;

		this.add(p,BorderLayout.CENTER);

		if (n.GetConsommable() != null) {
			this.add(objet,BorderLayout.EAST);
		} else {
			this.add(je,BorderLayout.EAST);
		}
			
		if (n.GetPersonnage() != null) {
			this.add(perso,BorderLayout.WEST);

		} else {
			this.add(jw,BorderLayout.WEST);
		}
    	}

	public JButton getBtn() {
		return pLocal.getBtn();
	}

	public static void main(String args[]) {
		final int WIDTH = 800;
		final int HEIGHT = 600;
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = f.getContentPane();
	
	
		Noeud n = new Noeud("couco",0,new Region());
		n.SetPersonnage(new Personnage());
		n.SetConsommable(new Consommable());

		Noeud vide = new Noeud("blblblb",0,new Region());
		PanelNoeud pn = new PanelNoeud(vide);
		c.add(pn);
		f.pack();
		f.setSize(800,600);
		f.setVisible(true);
		
		f.revalidate();
		f.repaint();
		pn.updateNode(n);
		//pn.updateNode(vide);
		f.revalidate();
		f.repaint();	
	}
}
