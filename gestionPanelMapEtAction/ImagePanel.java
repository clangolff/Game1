import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {

      	private BufferedImage image;

        int imgWidth;
        int imgHeight;

        int panelWidth;
        int panelHeight;

        double ratioX;
        double ratioY;
        double ratio;

        int newWidth;
        int newHeight;
        int x;
        int y;


     	public ImagePanel(String path) {
                        setOpaque(false);
                  	try {
                        	image = ImageIO.read(new File(path));
	                } catch (IOException e) {
        	                e.printStackTrace();
                	}

                }

		public ImagePanel() {
			super();
		}

                protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                	
			imgWidth = image.getWidth();
                	imgHeight = image.getHeight();

                	panelWidth = getWidth();
               	 	panelHeight = getHeight();

                	ratioX = (double) panelWidth / imgWidth;
                	ratioY = (double) panelHeight / imgHeight;

                	ratio = Math.min(ratioX, ratioY);

                	newWidth = (int) (imgWidth * ratio);
                	newHeight = (int) (imgHeight * ratio);

                	x = (panelWidth - newWidth) / 2;
                	y = (panelHeight - newHeight) / 2;

                        g.drawImage(image, x, y,newWidth,newHeight, this);  
                }

        }
