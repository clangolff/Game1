
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VueConnexion extends JFrame{
	
	private int hauteur;
	private int largeur;

	private JButton btnValider;
	private JTextField textUser;

	public VueConnexion() {

		this.hauteur = 900;
		this.largeur = 1800;

		setTitle("Furious Fighters");
		setSize(largeur,hauteur);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		ImagePanel PanneauConnexion = new ImagePanel("Spartiate.jpg");
		PanneauConnexion.setOpaque(false);
		PanneauConnexion.setLayout(new GridLayout(3,1));

		//Première ligne de la grille
		JPanel connexion = new JPanel();
		connexion.setLayout(new BorderLayout());
		connexion.setOpaque(false);
		
		JPanel e = new JPanel();
		JPanel w = new JPanel();
		JPanel s = new JPanel();
		
		e.setOpaque(false);
		w.setOpaque(false);
		s.setOpaque(false);

		connexion.add(e, BorderLayout.EAST);
		connexion.add(w, BorderLayout.WEST);

		JLabel textconnexion = new JLabel("Connectez-vous à Furious Figthers", SwingConstants.CENTER);
		textconnexion.setFont(new Font("Serif", Font.BOLD, 45));
		textconnexion.setForeground(Color.WHITE);
		textconnexion.setOpaque(true);
		textconnexion.setBackground(new Color(213, 134, 145, 180));
		s.add(textconnexion);

		connexion.add(s, BorderLayout.SOUTH);

		//Seconde ligne de la grille
		
		JPanel NomUtilisateur = new JPanel();
		NomUtilisateur.setOpaque(false);
		NomUtilisateur.setLayout(new GridLayout(1,3));


		JPanel temp1 = new JPanel();
		JPanel login = new JPanel();
		JPanel temp2 = new JPanel();
		
		temp1.setOpaque(false);
		login.setOpaque(false);
		temp2.setOpaque(false);
		
		NomUtilisateur.add(temp1);
		NomUtilisateur.add(login);
		NomUtilisateur.add(temp2);

		// on remplit celui du milieu 
		JPanel intitule = new JPanel();
		intitule.setLayout(new GridLayout(3,1));
		intitule.setOpaque(false);

		// texte
		JLabel textIntitule = new JLabel("Pseudo joueur :", SwingConstants.CENTER);
		textIntitule.setFont(new Font("Serif", Font.BOLD, 23));
		textIntitule.setForeground(Color.WHITE);
		textIntitule.setOpaque(true);
		textIntitule.setBackground(new Color(213, 134, 145, 180));

		// entrée du nom
		this.textUser = new JTextField();
	
		// bouton valider
		this.btnValider = new JButton("Valider");
		
		intitule.add(textIntitule);
		intitule.add(textUser);
		intitule.add(btnValider);
		
		login.add(intitule);

		// on rajoute les 2 gros panels sur le panel principale
		PanneauConnexion.add(connexion);
		PanneauConnexion.add(NomUtilisateur);

		this.getContentPane().add(PanneauConnexion);

		setVisible(true);
	}

	public void setBtnValider(JButton btn) {
		this.btnValider = btn;
	}

	public JButton getBtnValider() {
		return this.btnValider;
	}

	public JTextField getTextField() {
		return this.textUser;
	}

	public void montre() {
		this.setVisible(true);
	}

    	public static void main(String[] args){
    		VueConnexion login = new VueConnexion();
    		login.montre();
    	}

}

