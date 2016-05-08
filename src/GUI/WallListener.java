package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import board.Board;
import board.Case;
import mainAndRules.Rules;
import players.AGenericPlayer;
import players.HumanPlayer;

public class WallListener implements ActionListener {   
	
	private int x;
	private int y;
	private boolean isVertical;
	private static AGenericPlayer joueur2 = Board.getSecondPlayer();
	private static AGenericPlayer joueur1 = Board.getFirstPlayer();
	private static Case[][] realTab = Board.getTableau();
	public static boolean notDone;
	
	public WallListener(int x,int y,boolean isVertical){
		this.x = x;
		this.y = y;
		this.isVertical = isVertical;
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("notDone avant : " + notDone);
		if (isVertical){
			if(Main.tourJoueur1){
				
				if ( Rules.canPutWallRight(Board.getTableau()[x][y]) && Rules.canReallyPutWallRight(Board.getTableau()[x][y])){
					joueur1.putWallRight(Board.getTableau()[x][y]);
					System.out.println("avant modif tourJoueur1");
					notDone = false;
					System.out.println("après modif tourJoueur1");
				}
			}
			else{
				if (Rules.canPutWallRight(Board.getTableau()[x][y]) && Rules.canReallyPutWallRight(Board.getTableau()[x][y])){
					joueur2.putWallRight(Board.getTableau()[x][y]);
					System.out.println("avant modif tourJoueur2");
					notDone = false;
					System.out.println("après modif tourJoueur2");
				}
			}
		}
		else{
			if(Main.tourJoueur1){
				if (Rules.canPutWallDown(Board.getTableau()[x][y]) && Rules.canReallyPutWallDown(Board.getTableau()[x][y])){
					joueur1.putWallDown(Board.getTableau()[x][y]);
					System.out.println("avant modif tourJoueur1");
					notDone = false;
					System.out.println("après modif tourJoueur1");
				}
			}
			else{
				if (Rules.canPutWallDown(Board.getTableau()[x][y]) && Rules.canReallyPutWallDown(Board.getTableau()[x][y])){
					joueur2.putWallDown(Board.getTableau()[x][y]);
					System.out.println("avant modif tourJoueur2");
					notDone = false;
					System.out.println("après modif tourJoueur2");
				}
			}
		}
		System.out.println("notDone après : " + notDone);
	}
}
