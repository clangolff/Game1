import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class PanelTest extends JPanel {


	public PanelTest() {
		this.setBackground(Color.RED);
		this.setLayout(new GridLayout(6,3));
		for (int i=0;i<18;i++) {
			Noeud n = new Noeud("blblbl",0,new Region());
			
			n.SetPersonnage(new Personnage());
			n.SetConsommable(new Consommable());
			PanelNoeud pg = new PanelNoeud(n);
			pg.setBorder(BorderFactory.createLineBorder(Color.black));
			this.add(pg);
		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
                f.setSize(800, 600);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PanelTest pt = new PanelTest();

		f.getContentPane().add(pt);

		f.setLocationRelativeTo(null);
		f.pack();
		f.setSize(800,600);
		f.setVisible(true);
	}
}
