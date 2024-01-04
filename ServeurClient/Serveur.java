
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;

import java.util.ArrayList;



public class Serveur {

	// gestion des flux des connectés
	private ServerSocket serverSocket;
	private ArrayList<ObjectOutputStream> listOut;
	private ArrayList<ObjectInputStream>  listIn;
	private ArrayList<Socket> listeSocket;

	// gestion du jeu
	private final int nbJoueurMAX = 4; ////////////////////////////////////
	private PlateauDeJeu plateau;
	private GestionnairePlateau maitreDuJeu;

	// gestion entrée des flux
	private Action action;
	private int indexNoeud;

	public Serveur(GestionnairePlateau gest) { 
		this.maitreDuJeu = gest;
		this.maitreDuJeu.initialiserPlateau();
		
		this.plateau = maitreDuJeu.getPlateau();

		int nbClient = 0;
		String nom = "";
		
		listeSocket = new ArrayList<Socket>();		
		listOut = new ArrayList<ObjectOutputStream>();
		listIn = new ArrayList<ObjectInputStream>();
	}
/*
	public static void main(String[] args) {
		GestionnairePlateau gestionnaireJeu = new GestionnairePlateau();
		Serveur serveur = new Serveur(gestionnaireJeu);

		serveur.connecterClient();
		serveur.deroulerPartie();
		serveur.deconnecterClient();
	}	
*/

	public void connecterClient() {
		// liste des threads des logins
		ArrayList<LoginThread> loginSave = new ArrayList<LoginThread>();
       
       		int nbClient = 0;	
		// Partie 1 : on accepte les clients
                try {
                        // initialisation d'une Socket Serveur qui acceuille les clients et leur procure une socket
                        this.serverSocket = new ServerSocket(1234);

                        while(nbClient < this.nbJoueurMAX) {
                                Socket socketClient = serverSocket.accept();
                                this.listeSocket.add(socketClient);
                                System.out.println("Client " + (nbClient + 1) + " connecté");
                                
				ObjectOutputStream out = new ObjectOutputStream(socketClient.getOutputStream());
                                ObjectInputStream in = new ObjectInputStream(socketClient.getInputStream());
                                this.listOut.add(out);
                                this.listIn.add(in);
                                
				LoginThread lt = new LoginThread(maitreDuJeu, in, out, nbClient);
                                loginSave.add(lt);
                                lt.start();
                                
				nbClient += 1;
                        }
			
			// on attend que tous les clients soient connectés
                        for (LoginThread lt : loginSave) {
                                lt.join();
                        }

			// on finit l'initialisation une fois qu'on a recuperer les persos
			this.maitreDuJeu.setIndexJQJ(0);
			this.maitreDuJeu.affecterPersosANoeud();
			this.maitreDuJeu.affecterPositionAPersos(); 
			this.maitreDuJeu.affecterConsosANoeud();
			
			// Et c'est parti
			envoyerATousMessage(Message.letsgo);
			envoyerATousPlateau();

                } catch (IOException e) {
                        e.printStackTrace();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
	}


	public void deroulerPartie() {
	
		// Partie 2 : on boucle sur les clients pour leur envoyer un plateau
	
		// on suppose qu'on a une liste de joueurs qui jouent dans le plateau 
		Message message;
		while (!this.plateau.estFini()) { 
			
			// on récupère les données de la partie courante
			int indexJQJ = this.maitreDuJeu.getIndexJQJ(); 
			int nbAction = 0;
			
			// in et out du joueur qui joue
			ObjectInputStream in = this.listIn.get(indexJQJ);
			ObjectOutputStream out = this.listOut.get(indexJQJ);
			
			try {
				do {
					// on récupère en premier l'action
					action = (Action) in.readObject();
					in.reset();
					System.out.println("Action faite : " + action.toString() + " - Joueur "+ indexJQJ);
					// on récupère l'index du noeud sur lequel doit etre effectué l'action
					indexNoeud = in.readInt();
					System.out.println("Noeud sur lequel l'action est effectuee : " + indexNoeud);
					in.reset();
					// on fait traiter l'action par le gestionnaire
					message = this.maitreDuJeu.MAJPlateau(action,indexNoeud);
					// retourne le messsage de ce qui s'est passé					
					envoyerATousMessage(message);
					// on envoie les modif a tous le monde
					this.plateau = this.maitreDuJeu.getPlateau();
				
					// tous les clients récupèrent le plateau
					envoyerATousPlateau();
					
					nbAction += 1; 
					System.out.println("A ce moment là, l'action en cours est finie - Action " + nbAction + " terminee");
				} while (nbAction < 2);
				
				this.maitreDuJeu.updateTour();
				
			} catch (ClassNotFoundException c){
				c.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
				System.exit(0);
			}
		}

	}
	
/*	// liste des threads des logins
		ArrayList<LoginThread> loginSave = new ArrayList<LoginThread>();
                
		// Partie 1 : on accepte les clients
                try {
                        // initialisation d'une Socket Serveur qui acceuille les clients et leur procure une chaussette 'Dobby libre'
                        this.serverSocket = new ServerSocket(1234);

                        while(nbClient < this.nbJoueurMAX) {
                                Socket socketClient = serverSocket.accept();
                                listeSocket.add(socketClient);
                                System.out.println("client " + (nbClient+1) + " connecté");
                                ObjectOutputStream out = new ObjectOutputStream(socketClient.getOutputStream());
                                ObjectInputStream in = new ObjectInputStream(socketClient.getInputStream());
                                listOut.add(out);
                                listIn.add(in);
                                LoginThread lt = new LoginThread(maitreDuJeu, in, out, nbClient);
                                loginSave.add(lt);
                                lt.start();
                                nbClient += 1;
                        }
                        for (LoginThread lt : loginSave) {
                                lt.join();
                        }
			
			// on finit l'initialisation une fois qu'on a recuperer les perso
			this.maitreDuJeu.setIndexJQJ(0);
			this.maitreDuJeu.affecterPersosANoeud();
			this.maitreDuJeu.affecterConsosANoeud();

			// eeeeh zeee parti
			envoyerATousMessage(Message.letsgo);

			envoyerATousPlateau();


                } catch (IOException e) {
                        e.printStackTrace();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }



		// Partie 2 : on boucle sur les clients pour leur envoyer un plateau
	
		// on suppose qu'on a une liste de joueur qui joue dans le plateau 
		while (!plateau.estFini()) { 
			
			// on récupère les données de la partie courante
			int indexJQJ = maitreDuJeu.getIndexJQJ(); 
			int nbAction = 0;
			


			ObjectInputStream in = listIn.get(indexJQJ);
			ObjectOutputStream out = listOut.get(indexJQJ);
			
			try {
				try {
					do {
						// on récupère en premier l'action
						action = (Action) in.readObject();
						System.out.println(action.toString()+" Joueur "+indexJQJ);
						// on récupère l'index du noeud sur lequel doit etre effectué l'action
						indexNoeud = in.readInt();
						// on fait traiter l'action par le gestionnaire
						message = maitreDuJeu.MAJPlateau(action,indexNoeud);	
						// retourne le messsage de ce qui s'est passé
						envoyerATousMessage(message);
						// on envoie les modif a tous le monde
						plateau = maitreDuJeu.getPlateau();
					
						// tous les clients récupère le plateau
						envoyerATousPlateau();
						
						nbAction += 1;	
					} while (nbAction < 2);

					this.maitreDuJeu.updateTour();

				} catch (ClassNotFoundException c){
					c.printStackTrace();
				}
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		// on déconnecte proprement a la fin de la partie 
		DeconnecterClient();
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
*/

	public void envoyerATousMessage(Message m){
		try {
			for (ObjectOutputStream out : this.listOut) {
				out.writeObject(m);
				out.reset();
				out.flush();
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void envoyerATousPlateau() {
		try {
/*
			for(Noeud n : this.plateau.GetListeNoeud()) {
				System.out.println(n.GetNumero());
			}
*/			
			System.out.println(this.plateau.toString());

			for (ObjectOutputStream out : this.listOut) {
				out.writeObject(this.plateau);
				out.reset();
				out.flush();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void deconnecterClient() { 
		try{
			for( int i=0; i < nbJoueurMAX; i++) {
					this.listIn.get(i).close();
					this.listOut.get(i).close();
					this.listeSocket.get(i).close();
			}
			this.serverSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public static void main(String[] args) {
		GestionnairePlateau gp = new GestionnairePlateau();
		gp.initialiserPlateau();


		Serveur s = new Serveur(gp);
		s.connecterClient();
		s.deroulerPartie();
		s.deconnecterClient();
	}

}
