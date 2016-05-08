package GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import board.Board;
import board.Case;
import players.AGenericPlayer;

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
		this.tourJoueur1 = Main.tourJoueur1;
	}
	
	public void shoot(Board board){
		this.board = board;
		this.plateau = board.getTableau();
		this.joueur1 = board.getFirstPlayer();
		this.joueur2 = board.getSecondPlayer();
		this.tourJoueur1 = Main.tourJoueur1;
	}
	
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
	
	public static  Save load ()throws FileNotFoundException,IOException,ClassNotFoundException{
		FileInputStream file = new FileInputStream("./save.dat");
	   	ObjectInputStream ois = new ObjectInputStream(file);
	   	Save save = (Save) ois.readObject();
	   	return save;
	}
		
}
