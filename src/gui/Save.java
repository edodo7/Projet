package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import board.Board;
import board.Case;
import mains.GuiMain;
import players.AGenericPlayer;
/**
 * Permet de sauvegarder une partie en cours
 * @author Eduardo
 *
 */
public class Save implements Serializable {
	public  Case[][] plateau;
	public  AGenericPlayer joueur1;
	public  AGenericPlayer joueur2;
	public  boolean tourJoueur1;
	public Board board;
	
	public Save(Board board){
		this.board = board;
		this.plateau = board.getTableau();
		this.joueur1 = board.getFirstPlayer();
		this.joueur2 = board.getSecondPlayer();
		this.tourJoueur1 = GuiMain.tourJoueur1;
	}
	/**
	 * Permet d'enregistrer les donnees actuelles de la partie
	 * @param board
	 */
	public void shoot(Board board){
		this.board = board;
		this.plateau = board.getTableau();
		this.joueur1 = board.getFirstPlayer();
		this.joueur2 = board.getSecondPlayer();
		this.tourJoueur1 = GuiMain.tourJoueur1;
	}
	/**
	 * Permet d'ecrire les donnees de sauvegarde dans un fichier pour pouvoir les recuperer plus tard
	 * @param save ,l'objet contenant les donnees necessaires  pour pouvoir reprendre la partie 
	 */
	public static void save( Save save){
		try{
			FileOutputStream fichier = new FileOutputStream("./save.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(save);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * Permet de charger un objet save contenu dans un fichier afin de pouvoir reconstituer une partie precedement sauvee
	 * @return L'objet save contenant les donnees de la derniere sauvegarde
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static  Save load ()throws FileNotFoundException,IOException,ClassNotFoundException{
		FileInputStream file = new FileInputStream("./save.dat");
	   	ObjectInputStream ois = new ObjectInputStream(file);
	   	Save save = (Save) ois.readObject();
	   	return save;
	}
		
}
