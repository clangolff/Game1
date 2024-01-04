import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;


public class Serveur {

	private ServerSocket ss;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket s;

	private GestionnairePlateau gp;

	private PlateauDeJeu p;
	private String nom = "";

	public Serveur(GestionnairePlateau g) {
		this.gp = g;
		this.gp.initialiserPlateau();

		this.p = this.gp.getPlateau();
		
	}

	public void connecterClient() {

		        try {
            ss = new ServerSocket(1234);
            System.out.println("En attente d'un client...");
            s = ss.accept(); // Attente de la connexion du client
            System.out.println("Client connecté.");

            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());

            // Lire le nom du client
            String nom = ois.readUTF();
            System.out.println("Nom du client : " + nom);

            gp.setIndexJQJ(0);
            envoyerMessage(Message.letsgo);
            System.out.println(gp.getPlateau().toString());
            envoyerPlateau(gp.getPlateau());
        // Attendre un signal du client pour continuer
        ois.readBoolean();
		
			} catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void envoyerMessage(Message m) {
		try {
			oos.writeObject(m);
			oos.flush();
		} catch (Exception e) {}
	}

	public void envoyerPlateau(PlateauDeJeu plateau) {
		try {
			oos.writeObject(plateau);
			oos.reset();
			oos.flush();
		} catch (Exception e) {}
	}

	public void deconnecter() {
		try {
			oos.close();
			oos.reset();
			ss.close();
		} catch (Exception e) {}
	}

public void deroulerPartie() {
    try {
        Message m = gp.MAJPlateau(Action.seDeplacer, 2);
        envoyerMessage(m);
        p = gp.getPlateau();
        System.out.println(p.toString());
        envoyerPlateau(p);
    } catch (Exception e) {
        e.printStackTrace();
    }
	}
	public static void main(String[] args) {
		GestionnairePlateau gp = new GestionnairePlateau();
		Serveur s = new Serveur(gp);
		s.connecterClient();

		s.deroulerPartie();
		s.deconnecter();
	}
}
