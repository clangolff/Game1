import javax.swing.*;
import java.awt.*;

public class PanelGif extends JPanel{

	private ImageIcon gif;

    	public PanelGif(String gifPath) {
        
		 gif = new ImageIcon(getClass().getResource(gifPath));
		 //this.setBackground(Color.RED);
    	}


    	protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
        	if (gif != null) {
            		Image image = gif.getImage();
            		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
    	}

    	public static void main(String[] args) {
    		JFrame frame = new JFrame();
            	frame.setSize(400, 300);
            	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            	PanelGif gifPanel = new PanelGif("1ff6.gif");
            	frame.add(gifPanel);

            	frame.setVisible(true);
    	}
}

