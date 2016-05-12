package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import board.Board;
import players.AGenericPlayer;
import players.HumanPlayer;
/**
 * Permet de detecter qu'un joueur veut se deplacer vers une case en mode graphique
 * @author Eduardo
 *
 */
public class MoveListener implements ActionListener{
	
	private int x;
	private int y;
	private GuiCase myCase;
	private static AGenericPlayer joueur1;
	private static  AGenericPlayer joueur2;
	public static boolean notDone;
	
	
	public MoveListener(int x,int y,GuiCase myCase){
		this.x = x;
		this.y = y;
		this.myCase = myCase;
		joueur1 = Board.getFirstPlayer();
		joueur2 = Board.getSecondPlayer();
	}
	/**
	 * Permet de se deplacer vers la case cliquee si c'est possible
	 */
	public void actionPerformed(ActionEvent e){
		if(Main.tourJoueur1){
			if (joueur1.move(this.x, this.y)){
				notDone = false;
			}
		}
		else{
			if (joueur2.move(this.x, this.y)){
				notDone = false;
			}
		}
	}
}
