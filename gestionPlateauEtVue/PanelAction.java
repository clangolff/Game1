

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PanelAction extends JPanel {
	
	private Image image;

	private ArrayList<BordEtincelantRect> listeBord;	
	private ArrayList<ImageButton> listeBtn;
	private Map<BordEtincelantRect,ImageButton> panelButtonMapping;

	public PanelAction() {
		
		image = new ImageIcon("Bords.png").getImage();
		this.setOpaque(false);
		this.setLayout(new GridLayout(4,1));
	
		// FACE DROITE découpage en 4 subPanel pour les 4 boutons d'action
		ArrayList<String> listePathAction = new ArrayList<String>();
		
		listePathAction.add("SeDeplacer.png");
		listePathAction.add("Recolter.png");
		listePathAction.add("epee.png");
		listePathAction.add("fleche.png");
			
		listeBtn = new ArrayList<>();
		panelButtonMapping = new HashMap<>();
		listeBord = new ArrayList<>();

		int i = 0;
		for (Action action : Action.values()) {
			JPanel b = new JPanel();
			b.setOpaque(false);
			b.setLayout(new BorderLayout());
						
			String path = listePathAction.get(i);
			ImageButton btn = new ImageButton(path);

  			btn.setEnabled(false);

			JLabel titreAction = new JLabel(action.toString(), SwingConstants.CENTER);

            		JPanel e = new JPanel();
			JPanel w = new JPanel();
			JPanel s = new JPanel();
		
			e.setOpaque(false);
			w.setOpaque(false);
			s.setOpaque(false);

			b.add(titreAction, BorderLayout.NORTH);
            		b.add(btn, BorderLayout.CENTER);
			b.add(e, BorderLayout.EAST);
			b.add(w, BorderLayout.WEST);
			b.add(s, BorderLayout.SOUTH);
		

			BordEtincelantRect bord = new BordEtincelantRect();
			bord.setLayout(new GridLayout(1,1));
			bord.add(b);
			this.add(bord);	

			listeBord.add(bord);
			//this.add(b);
	
			//listePanel.add(b);
			listeBtn.add(btn);	
			panelButtonMapping.put(listeBord.get(i),listeBtn.get(i));
			i += 1;
		}

	
	}

	public void updatePanel() {
		for (BordEtincelantRect b : listeBord) {
			ImageButton btn = panelButtonMapping.get(b);
			if (btn.isEnabled()) {
				b.startEffect();
			} else {
				b.stopEffect();
			}
		}
	}

	public void enableBtnAction(Action a, boolean b) {
		switch(a) {
			case recuperer :
				this.listeBtn.get(1).setEnabled(b);
				break;
			case seDeplacer :
				this.listeBtn.get(0).setEnabled(b);
				break;
			case attaquerCac :
				this.listeBtn.get(2).setEnabled(b);
				break;
			case attaquerArc :
				this.listeBtn.get(3).setEnabled(b);
				break;
		}
	}

	public ImageButton getBtn(Action a) {
		ImageButton btn = new ImageButton();
		switch(a) {
			case seDeplacer : 
				btn = listeBtn.get(0);
				break;
		    	case recuperer :
				btn = listeBtn.get(1);
				break;
		 	case attaquerCac :
				btn = listeBtn.get(2);
				break;
			case attaquerArc :
				btn = listeBtn.get(3);
				break;
		}
		return btn;
	}
	
	public static void main(String[] args) {
		JFrame f1 = new JFrame();
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f1.setSize(200, 600);

		PanelAction pa = new PanelAction();

		Container c = f1.getContentPane();
		c.add(pa);

		f1.pack();
                f1.setSize(200,600);

                f1.setVisible(true);

	      	JFrame f2 = new JFrame();
                f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f2.setSize(300, 200);

               	for (Action a : Action.values()) {

			ImageButton btn = pa.getBtn(a);

                        btn.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                 	System.out.println(a);       
				}
                        });
                }


		JButton button = new JButton("changez l'effet");
                button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	for (Action action : Action.values()) {
					pa.enableBtnAction(action,true);
				}
			}
                });


		f2.add(button);
	     	f2.setVisible(true);
	}
}

