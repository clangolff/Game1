

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;

import java.util.ArrayList;

public class PanelAction extends JPanel {
	
	private Image image;
	private ArrayList<ImageButton> listeBtn = new ArrayList<ImageButton>();

	// pour renvoyer au client le bouton action cliquer
	private boolean btnCliquer = false;
	private Action action = null; 
	
	public PanelAction(/*int l, int h*/) {
		
		image = new ImageIcon("Bords.png").getImage();
		this.setOpaque(false);
		//this.setPreferredSize(new Dimension(l,h));
		this.setLayout(new GridLayout(4,1));
	
		// FACE DROITE découpage en 4 subPanel pour les 4 boutons d'action
		ArrayList<String> listePathAction = new ArrayList<String>();
		
		listePathAction.add("SeDeplacer.png");
		listePathAction.add("Recolter.png");
		listePathAction.add("epee.png");
		listePathAction.add("fleche.png");
			
		int i = 0;
		for (Action action : Action.values()) {
			JPanel b = new JPanel();
			b.setOpaque(false);
			b.setLayout(new BorderLayout());
						
			String path = listePathAction.get(i);
			ImageButton btn = new ImageButton(path);

  			btn.setEnabled(false);
			listeBtn.add(btn);

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
			
			this.add(b);
			i += 1;	
		}
	}

	public void enableBtnAction(Action a, boolean b) {
		switch(a) {
			case recuperer :
				this.listeBtn.get(0).setEnabled(b);
				break;
			case seDeplacer :
				this.listeBtn.get(1).setEnabled(b);
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
}
