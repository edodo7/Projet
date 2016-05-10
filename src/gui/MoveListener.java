package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import board.Board;
import players.AGenericPlayer;
import players.HumanPlayer;

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
	
	public void actionPerformed(ActionEvent e){
		//System.out.println("notDone avant : " + notDone);
		if(Main.tourJoueur1){
			if (joueur1.move(this.x, this.y)){
				//System.out.println("avant modif tourJoueur1");
				notDone = false;
				//System.out.println("après modif tourJoueur1");
			}
		}
		else{
			if (joueur2.move(this.x, this.y)){
				//System.out.println("avant modif tourJoueur2");
				notDone = false;
				//System.out.println("après modif tourJoueur2");
			}
		}
		//System.out.println("notDone après : " + notDone);
	}
}
