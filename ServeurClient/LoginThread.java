
import java.io.*;

public class LoginThread extends Thread {

	private GestionnairePlateau gestPlateau;
	private ObjectInputStream ois; 
	private ObjectOutputStream oos; 
	private int numeroClient; 
	
	public LoginThread(GestionnairePlateau gp, ObjectInputStream oinputs, ObjectOutputStream ooutputs, int numeroJoueur) {
		this.gestPlateau = gp;
		this.ois = oinputs;
		this.oos = ooutputs;
		this.numeroClient = numeroJoueur; 
	}

	public void run() {

		String nom = ""; 

		try {
			// on attribue au client un numéro 
			oos.writeInt(this.numeroClient);
			oos.flush();
			
			//on réceptionne le nom du client et on l'affecte à son personnage
			nom = ois.readUTF();
			//ois.reset();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (nom == "") {
			nom = "Tavaiskchoisir";
		}
		
		synchronized (this.gestPlateau.getPlateau().GetListePersonnage()) {
			this.gestPlateau.ajouterJoueur(nom);
		}
	}

}
