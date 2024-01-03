import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class PanelTest extends JPanel {

	ImagePanel imgp;
	BordEtincelantRect bord;
	
    	public PanelTest() {
        this.setBackground(Color.RED);

        imgp = new ImagePanel("J1.png");
        bord = new BordEtincelantRect();

        // Set layout to BorderLayout to allow components to overlap
        this.setLayout(new BorderLayout());

        // Add ImagePanel to the center
        this.add(imgp, BorderLayout.CENTER);

        // Add BordEtincelantRect as an overlay
        this.add(bord, BorderLayout.CENTER);
	bord.setLayout(new GridLayout(1,1));	
	bord.add(imgp);	
	}

	public BordEtincelantRect getBord() {
		return this.bord;
	}

	public static void main(String[] args) {
                JFrame f1 = new JFrame();
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f1.setSize(300, 600);

		PanelTest pt = new PanelTest();
                Container c = f1.getContentPane();
                c.add(pt);
                f1.pack();
                f1.setSize(1298,696);

		f1.setLocationRelativeTo(null);
                f1.setVisible(true);

		JFrame f2 = new JFrame();
      	        JButton button = new JButton("Changez l'effet");
        	button.addActionListener(new ActionListener() {
            		public void actionPerformed(ActionEvent e) {
            			if(pt.getBord().isActif()) {
					pt.getBord().stopEffect();
				} else {
					pt.getBord().startEffect();
				}
			}
        	});

		
		
		f2.getContentPane().add(button);
        	f2.setSize(300, 200);
        	f2.setVisible(true);
	}
}
