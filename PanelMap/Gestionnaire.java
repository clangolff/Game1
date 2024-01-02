import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gestionnaire {


    public static void main(String[] args) {
        JFrame f1 = new JFrame();
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(300, 200);
	
	Container c = f1.getContentPane();

        Noeud n = new Noeud("blblblb", 0, new Region());
        PanelNoeud pn = new PanelNoeud(n);
		
	c.add(pn);

	f1.revalidate();
	f1.repaint();	
        
	
	JButton btn = pn.getBtn();
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton cliqué");
            }
        });

        JButton button = new JButton("Changez l'effet");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	btn.setEnabled(!btn.isEnabled());
            }
        });

        BordEtincelantRect bord = new BordEtincelantRect(button);
        button.setBorder(bord);

        JFrame f2 = new JFrame();
        f2.getContentPane().add(button);
        f2.setSize(300, 200);
        f2.setVisible(true);

	f1.pack();
	f1.setSize(800,600);
	f1.setVisible(true);
	
        // Utilisation d'un timer pour les mises à jour du noeud
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Modifier le noeud après une pause simulée et mettre à jour le PanelNoeud
                Noeud updatedNode = new Noeud("blblblb", 0, new Region());
                updatedNode.SetConsommable(new Consommable("bouclier", 0, 0));
                updatedNode.SetPersonnage(new Personnage("thierry"));
                updatePanel(f1,pn,updatedNode);
            }
        });
        timer.setRepeats(false); // Si vous souhaitez exécuter le timer une seule fois
        timer.start();
    }

    private static void updatePanel(JFrame f,PanelNoeud pnd,Noeud updatedNode) {
            pnd.updateNode(updatedNode);
            f.revalidate();
            f.repaint();
	    f.pack();
	    f.setSize(800,600);
    }
}
