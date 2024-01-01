import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManyPanelNoeud extends JPanel{

	private ArrayList<PanelNoeud> listePanel = new ArrayList<>();

	public ManyPanelNoeud() {
		this.setLayout(new GridLayout(3,3));
		this.setOpaque(false);
		for (int i=0;i<9;i++) {
			PanelNoeud pn = new PanelNoeud((int) (1298/3),(int)(600/3));
			this.add(pn);
			listePanel.add(pn);
		}
	}

	public void miseAJour() {
		for (PanelNoeud pn : listePanel) {
			pn.revalidate();
			pn.repaint();
		}
	}


	public static void main(String[] argv) {
                JFrame f1 = new JFrame();
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f1.setSize(1298, 600);

		ManyPanelNoeud mpn = new ManyPanelNoeud();

		Container c = f1.getContentPane();
                c.add(mpn);

		//mpn.miseAJour();

                f1.setLocationRelativeTo(null);
                f1.setVisible(true);
	}
}
