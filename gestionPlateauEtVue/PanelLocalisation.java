import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelLocalisation extends JPanel{

	private ImageIcon gif1, gif2;
 	private BufferedImage localisation;
	private JButton btn;
	private boolean isActif;
	private Image imageGIF1,imageGIF2;


    	public PanelLocalisation() {

		try {
                        localisation = ImageIO.read(new File("Localisation.png"));
		} catch (IOException e) {
                        e.printStackTrace();
                }

		gif1 = new ImageIcon(getClass().getResource("1ff6.gif"));
		gif2 = new ImageIcon(getClass().getResource("sm24.gif"));
    		imageGIF1 = gif1.getImage();
		imageGIF2 = gif2.getImage();

		btn = new JButton();
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setEnabled(false);
		btn.setBorder(null);

		this.setOpaque(false);
		this.setLayout(new GridLayout(1,1));	
		this.add(btn);

		isActif = false;
		//isActif = true;
		btn.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent e) {
                                //isActif = !isActif;
                                //setEffect(isActif);
				setEffect(btn.isEnabled());
                        }
                });
                        
	}

        public void setEffect(boolean b) {
                if (b==true){
                        startEffect();
                } else {
                        stopEffect();
                }
        }

	public void startEffect() {
                        this.isActif = true;
                }

	public void stopEffect() {
                        this.isActif = false;
			this.repaint();
                }

	public JButton getBtn() {
		return this.btn;
	}

    	protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
		if (isActif) {
        		if (gif1 != null && gif2 != null) {
            			//Image imageGIF1 = gif1.getImage();
            			g.drawImage(imageGIF1, 0, 0, getWidth(), getHeight(), this);
				//Image imageGIF2 = gif2.getImage();
            			g.drawImage(imageGIF2, 0, 0, getWidth(), getHeight(), this);
			}
		
		}

		int imgWidth = localisation.getWidth();
                int imgHeight = localisation.getHeight();

                int panelWidth = getWidth();
                int panelHeight = getHeight();

                double ratioX = (double) panelWidth / imgWidth;
                double ratioY = (double) panelHeight / imgHeight;

                double ratio = Math.min(ratioX, ratioY);

                int newWidth = (int) (imgWidth * ratio);
                int newHeight = (int) (imgHeight * ratio);

                int x = (panelWidth - newWidth) / 2;
                int y = (panelHeight - newHeight) / 2;


		g.drawImage(localisation, x, y, newWidth, newHeight, this);
    	}

    	public static void main(String[] args) {
		
		JFrame f1 = new JFrame();
            	f1.setSize(400, 300);
            	f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JFrame f2 = new JFrame();
            	f2.setSize(400, 300);
            	f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            	PanelLocalisation gifPanel = new PanelLocalisation();

		JButton button = gifPanel.getBtn();
                button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                System.out.println("Bouton cliqué");
                        }
                });



		JButton btn = new JButton("changez l'effet");
                btn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
				button.setEnabled(!button.isEnabled());
			}
                });

/*
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Mouse entered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse exited");
            }
        });

        button.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // Gérer les mouvements de souris si nécessaire
	    }
        });

		button.setFocusable(true);
*/		f1.setLocationRelativeTo(null);

            	f1.add(gifPanel);
		f2.add(btn);
            	f1.setVisible(true);
    		f2.setVisible(true);
	}
}

