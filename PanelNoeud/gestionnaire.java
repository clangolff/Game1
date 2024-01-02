import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class gestionnaire {
	
	public gestionnaire(){}

	public static void main(String[] args) {
               	JFrame f1 = new JFrame();
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f1.setSize(300, 200);


		Noeud n = new Noeud("blblblb",0,new Region());
		//n.SetConsommable(new Consommable("bouclier",0,0));
		//n.SetPersonnage(new Personnage("thierry"));

               	PanelNoeud pn = new PanelNoeud(n);
	
		JButton btn = pn.getBtn();	
		btn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                System.out.println("Bouton cliqué");
                        }
                });


                JButton button = new JButton("changez l'effet");
                button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                btn.setEnabled(!btn.isEnabled());
                        }
                });


                BordEtincelantRect bord = new BordEtincelantRect(button);
                button.setBorder(bord);

                JFrame f2 = new JFrame();
                f2.getContentPane().add(button);
                f2.setSize(300,200);
                f2.setVisible(true);

		f1.add(pn);
		f1.setVisible(true);
	
		try {	
			Thread.sleep(2000);
		} catch (Exception e) {}
		
		SwingUtilities.invokeLater(() -> {
            		n.SetConsommable(new Consommable("bouclier", 0, 0));
            		n.SetPersonnage(new Personnage("thierry"));
            		pn.revalidate();
            		pn.repaint();
        	});
	}
}
