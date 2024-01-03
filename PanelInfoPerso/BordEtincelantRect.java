import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BordEtincelantRect extends JComponent {

    	// Durée de l'animation en millisecondes (contrôle vitesse)
    	private final int duree = 1000;
    	private long tempsInitial;
    	private Timer timer;

    	private boolean effectActif = false;
    	private final int marge = 5;
    	private String codeHEXA = "#f0cb2d";

    	public BordEtincelantRect() {
        	this.setOpaque(false);
	    	this.tempsInitial = System.currentTimeMillis();
        
		timer = new Timer(10, new ActionListener() {
            		public void actionPerformed(ActionEvent e) {
                		if (effectActif) {
                    			repaint();
                		}
            		}
        	});
        	timer.start();
    	}

    
	protected void paintComponent(Graphics g) {
        	super.paintComponent(g);

        	// calcul sinus pour faire varier la transparence
        	long temps = System.currentTimeMillis();
        	long deltaT1 = temps - tempsInitial;
        	long deltaT2 = temps - tempsInitial + deltaT1 / 8;

        	Graphics2D g2d1 = (Graphics2D) g.create();
        	Graphics2D g2d2 = (Graphics2D) g.create();

        	Color light = Color.decode(codeHEXA);
        	int r = light.getRed();
        	int v = light.getGreen();
        	int b = light.getBlue();
        
		float alpha1 = (float) Math.abs(Math.sin((double) deltaT1 / duree * Math.PI));
        	float alpha2 = (float) Math.abs(Math.sin((double) deltaT2 / duree * Math.PI));

        	int transparence1;
        	int transparence2;

        	if (effectActif) {
            		transparence1 = (int) (alpha1 * 255);
            		transparence2 = (int) (alpha2 * 255);
        	} else {
            		transparence1 = 0;
            		transparence2 = 0;
        	}

        	Color borderColor1 = new Color(r, v, b, transparence1);
        	Color borderColor2 = new Color(r, v, b, transparence2);

        	g2d1.setColor(borderColor1);
        	g2d1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha1));
        	g2d1.setStroke(new BasicStroke(marge));
        	g2d1.drawRect(marge / 2, marge / 2, getWidth() - marge, getHeight() - marge);

        	g2d2.setColor(borderColor2);
        	g2d2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha2));
        	g2d2.setStroke(new BasicStroke(marge));
        	g2d2.drawRect(marge, marge, getWidth() - 2 * marge, getHeight() - 2 * marge);

        	g2d1.dispose();
        	g2d2.dispose();

	}

    
	public void startEffect() {
        	this.effectActif = true;
    	}

    	public void stopEffect() {
        	this.effectActif = false;
        	repaint();
    	}

	public boolean isActif() {
		return this.effectActif;
	}
}
