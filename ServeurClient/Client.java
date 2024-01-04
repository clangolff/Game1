
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.io.IOException;

import java.util.Scanner;


public class Client {
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket socketClient;

	private int indexJoueur;
	private String nomJoueur;

	private PlateauDeJeu plateau;
	private VueMain vue;

	private GestionnaireVueClient gestionnaire;

	public Client(Socket socketC, ObjectInputStream in, ObjectOutputStream out) {
		this.socketClient = socketC;
		this.in = in;
		this.out = out;
		this.gestionnaire = new GestionnaireVueClient();
	}

	public PlateauDeJeu getPlateau() {
		return this.plateau;
	}

	public void setPlateau(PlateauDeJeu p) {
		this.plateau = p;
	}

	public VueMain getVue() {
		return this.vue;
	}

	public void setVue(VueMain v) {
		this.vue = v;
	}

	public ObjectOutputStream getOut() {
		return this.out;
	}

 	public ObjectInputStream getIn() {
		return this.in;
	}
	
	public void setOut(ObjectOutputStream o) {
		this.out = o;
	}

 	public void setIn(ObjectInputStream i) {
		this.in = i;
	}

	public int getIndexJ() {
		return this.indexJoueur;
	}
	
	public void setIndexJ(int i) {
		this.indexJoueur = i;
	}

	public Socket getSocket() {
		return this.socketClient;
	}

	public void setSocket(Socket s) {
		this.socketClient = s;
	}

	public GestionnaireVueClient getGestionnaire() {
		return this.gestionnaire;
	}

	public void setGestionnaire(GestionnaireVueClient g) {
		this.gestionnaire = g;
	}

	public void setNom(String nom) {
		this.nomJoueur = nom;
	}
	
	public String getNom() {
		return this.nomJoueur;
	}
	
	// ############" LOGIN ###############
	private void seConnecter() {	
		// Ici on ne fait que créer la fenêtre de connexion mais sans l'afficher
		this.gestionnaire.initialiserVueConnexion();

		try {
			// on récupère l'index attribué au joueur par le serveur
            		int indexJ = this.in.readInt();
			this.setIndexJ(indexJ);

			// on rend visible la fenetre de login
			this.gestionnaire.showVueConnexion();
			
			// on récupére le nom du joueur
			this.gestionnaire.addActionListenerLogin();
			String nom = this.gestionnaire.getNomLogin();

			this.setNom(nom);
			
			// on envoie le nom du joueur au serveur
            		this.out.writeUTF(nom);
			this.out.flush();

			Message message;
			// on attend le lestgo du serveur
			do {
				message = (Message) this.in.readObject();
			} while (message != Message.letsgo);
			
			this.plateau = (PlateauDeJeu) this.in.readObject();
		
			System.out.println(message.toString());
	
		} catch (Exception e) {
            		e.printStackTrace();
        	}
	}

	public static void main(String[] args) {
		Socket socketClt;
		ObjectInputStream input;
		ObjectOutputStream output;
		Client client;		
	
		Action action;
		int numeroNoeud;
		Noeud posPerso;
		int indexJQJ; 
		Message message;
		PlateauDeJeu p = new PlateauDeJeu();	
		GestionnaireVueClient gvc;

		try {
			socketClt = new Socket(InetAddress.getLocalHost(),1234);
			input = new ObjectInputStream(socketClt.getInputStream());
			output = new ObjectOutputStream(socketClt.getOutputStream());
			client = new Client(socketClt,input,output);

			client.seConnecter();
			
			gvc = client.getGestionnaire();

			gvc.initialiserVue(client.getPlateau(),client.getNom());

			do {		
				p = client.getPlateau();
				gvc.updateFrame(p);
				indexJQJ = p.getIndexJQJ();
				for (int nbAction=0;nbAction<2;nbAction++) {	
					if (indexJQJ == client.getIndexJ()) {
						posPerso = p.GetListePersonnage().get(client.getIndexJ()).GetPosition(); 
						
						// 1 récupération de l'action 
						action = gvc.retournerAction(p,posPerso);
						System.out.println("Action recuperee par le bouton : " + action);  
						// 2 récupération du noeud
						numeroNoeud = gvc.retournerIndexNoeud(action,p,posPerso);
						System.out.println("Noeud envoye au serveur : " + numeroNoeud); 
						
						// on envoie l'action au serveur
						client.getOut().writeObject(action);
						client.getOut().reset();
						client.getOut().flush();
							
						// on envoie le noeud au serveur
						client.getOut().writeInt(numeroNoeud);
						client.getOut().reset();
						client.getOut().flush();
				
						System.out.println("Envoie au serveur"); 
					}
				
					System.out.println("J'attends le message de l'action jouee"); 
					message = (Message) client.getIn().readObject();
					client.getIn().reset();
					System.out.println(message); 
					//client.getGestionnaire().getVue().PopUp(message);	
					p = (PlateauDeJeu) client.getIn().readObject();
					client.getIn().reset();
					client.setPlateau(p);

					System.out.println("\nTour actualise : \n" + p.toString());			
				}
			} while (!client.getPlateau().estFini());
			
			client.getIn().close();
			client.getOut().close();
			client.getSocket().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
