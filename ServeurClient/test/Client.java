import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.io.IOException;

import java.util.Scanner;

public class Client {

	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket s;
	private Scanner scanner;

	private String nom = "";

	private PlateauDeJeu  p;

	public Client(Socket socket,ObjectOutputStream os,ObjectInputStream is) {
	
		this.s = socket;
		this.oos = os;
		this.ois = is;
		scanner = new Scanner(System.in);
	}

	public void seConnecter() {
		try {
			System.out.print("Veuillez entrer une chaîne : ");
        		nom = scanner.nextLine();
			oos.writeUTF(nom);
			oos.flush();
			//scanner.close();
			Message m = (Message) ois.readObject();
			System.out.println(m.toString());
			p = (PlateauDeJeu) ois.readObject();
			System.out.println(p.toString());
        		oos.writeBoolean(true);
        		oos.flush();
		} catch (Exception e) {}
	} 

	public void deroulerPartie() {
		try {
			//Message m = (Message) ois.readObject();
			//System.out.println(m.toString());
			//p = (PlateauDeJeu) ois.readObject();
			//System.out.println(p.toString());
			//ois.reset();
			int nbAction = 0;
			Action a = Action.recuperer;
			int k;
			do {
				System.out.println("entre une action");
				String action = scanner.nextLine();
				switch(action) {
					case "seDeplacer" : 
						a = Action.seDeplacer;
						break;
					case "recuperer" :
						a = Action.recuperer;
						break;
					case "attaquerArc" :
						a = Action.attaquerArc;
						break;
					case "attaquerCac" :
						a = Action.attaquerCac;
						break;
					default :
						a = Action.recuperer;
						break;
				}
				oos.writeObject(a);
				oos.flush();
				System.out.println("entre un entier");
				String ent = scanner.nextLine();
				k = Integer.parseInt(ent);
				oos.writeInt(k);
				oos.flush();
				
				Message m = (Message) ois.readObject();
				System.out.println(m.toString());
				p = (PlateauDeJeu) ois.readObject();
				System.out.println(p.toString());
			
				nbAction += 1;
			} while (nbAction != 4);
		} catch (Exception e) {}
	}

	public static void main(String[] args) {
		try {
			Socket s = new Socket(InetAddress.getLocalHost(),1234);
			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

			Client c = new Client(s,os,is);
			c.seConnecter();
			c.deroulerPartie();
		} catch (Exception e) {}
	}
}
