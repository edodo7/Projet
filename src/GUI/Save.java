package GUI;

import board.Board;
import board.Case;
import players.AGenericPlayer;

public class Save {
	public static Case[][] plateau;
	public static AGenericPlayer joueur1;
	public static AGenericPlayer joueur2;
	public static boolean tourJoueur1;
	
	public Save(){
		Save.plateau = Board.getTableau();
		Save.joueur1 = Board.getFirstPlayer();
		Save.joueur2 = Board.getSecondPlayer();
		Save.tourJoueur1 = Main.tourJoueur1;
	}
	
	public void shoot(){
		Save.plateau = Board.getTableau();
		Save.joueur1 = Board.getFirstPlayer();
		Save.joueur2 = Board.getSecondPlayer();
		Save.tourJoueur1 = Main.tourJoueur1;
	}
}
