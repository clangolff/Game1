
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;

public class PanelBanniere extends JPanel{

	private Image image;

	PanelBanniere() {
		
		image = new ImageIcon("Banniere.png").getImage();
	}

	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0,getWidth(),getHeight(),  this);  
	}

}
