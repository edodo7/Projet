package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import board.Board;
import board.Case;
import players.HumanPlayer;

public class WallListener implements ActionListener {   
	
	private int x;
	private int y;
	private boolean isVertical;
	private static HumanPlayer joueur2 = (HumanPlayer) Board.getSecondPlayer();
	private static Case[][] realTab = Board.getTableau();
	
	public WallListener(int x,int y,boolean isVertical){
		this.x = x;
		this.y = y;
		this.isVertical = isVertical;
	}
	
	public void actionPerformed(ActionEvent e){
		//System.out.println("x : "+this.x+"\n"+"y : "+this.y + "\n"+"Mur Vertical : "+isVertical);
		if (isVertical){
			joueur2.putWallRight(realTab[x][y]);
		}
		else{
			joueur2.putWallDown(realTab[x][y]);
		}
	}
}
