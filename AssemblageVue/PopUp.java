
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.imageio.ImageIO;
//import java.io.File;


public class PopUp  {

	private Message message;

    	public PopUp(Message m) {
		this.message = m;

        	JFrame fenetre = new JFrame("popUp");
        	fenetre.setUndecorated(true); //Permet d'empecher l'utilisateur de clique sur la croix de la fenetre

       		fenetre.setSize(600,400);
        
        	fenetre.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran


		ImagePanel background = new ImagePanel("popUp.jpg");
		background.setLayout(new BorderLayout());

		JPanel temp = new JPanel();
		temp.setOpaque(false);	


		JLabel message = new JLabel(m.toString());
        	message.setForeground(Color.RED);
        	message.setFont(message.getFont().deriveFont(23f)); // Augmente la taille du texte à 17
        	message.setHorizontalAlignment(SwingConstants.CENTER); // Centre le texte sur la ligne du bas
        
		background.add(message, BorderLayout.SOUTH);
		background.add(temp, BorderLayout.CENTER);

		fenetre.getContentPane().add(background);
		fenetre.setVisible(true);

	       	//definition du timer pour fermer apres 4s
       
	       	int delai = 2000; //2 secondes
       
	       	Timer timer = new Timer(delai, new ActionListener() {
		       	public void actionPerformed(ActionEvent e){
			       	fenetre.dispose();
            		}
	     	});

       		timer.setRepeats(false);
        	timer.start();
    	}

/*   
       	public static void main(String[] args) {
	       	new PopUp(Message.aToiDeJouer);
	}
*/
}
