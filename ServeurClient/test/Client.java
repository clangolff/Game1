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
			scanner.close();
			Message m = (Message) ois.readObject();
			System.out.println(m.toString());
			p = (PlateauDeJeu) ois.readObject();
			System.out.println(p.toString());
        // Envoyer un signal au serveur pour indiquer que la connexion est terminée
        oos.writeBoolean(true);
        oos.flush();
		} catch (Exception e) {}
	} 

	public void deroulerPartie() {
		try {
			Message m = (Message) ois.readObject();
			System.out.println(m.toString());
			p = (PlateauDeJeu) ois.readObject();
			System.out.println(p.toString());
			ois.reset();
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
