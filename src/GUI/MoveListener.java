package GUI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import board.Board;
import players.HumanPlayer;

public class MoveListener implements ActionListener{
	
	private int x;
	private int y;
	private GuiCase myCase;
	public MoveListener(int x,int y,GuiCase myCase){
		this.x = x;
		this.y = y;
		this.myCase = myCase;
	}
	
	public void actionPerformed(ActionEvent e){
		HumanPlayer joueur1 = (HumanPlayer) Board.getSecondPlayer();
		if (joueur1.move(this.x, this.y)){
			//System.out.println("joueur déplacé");
		}
		else{
			//System.out.println("joueur ne s'est pas déplacé");
		}
	}

}
