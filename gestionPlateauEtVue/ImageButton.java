
import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton {

                private Image image;

		public ImageButton() {
			super();
		}

                public ImageButton(String path) {
                        setOpaque(false);
                        image = new ImageIcon(path).getImage();
                }

                protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(image, 0, 0,getWidth(),getHeight(),  this);  
                }

        }

