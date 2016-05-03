package board;
import java.util.Scanner;

import players.AGenericPlayer;
import players.RandomAI;
import players.HardAI;
import players.HumanPlayer;

/**
 * Cette classe permet de modeliser le plateau de jeu ainsi que les joueurs qui y sont presents(Qu'ils soient humains ou IA)
 * @author Eduardo Dom
 *
 */
public class Board
{
	public static Case[][] tableau = new Case[9][9];
	private static AGenericPlayer joueur1;
	private static AGenericPlayer joueur2;



	public Board (AGenericPlayer joueur1,AGenericPlayer joueur2)
	{
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				tableau[i][j] = new Case(i,j);
			}
		}
		tableau[joueur1.getX()][joueur1.getY()].setEmpty(false);
		tableau[joueur2.getX()][joueur2.getY()].setEmpty(false);
	}


	public String toString()
	{
		String res = "";
		for (int i = 0; i < tableau.length;i++)
		{
			for (int j = 0; j < tableau[i].length;j++)
			{
				if (tableau[i][j].isEmpty())
				{
					res += "[  ]";
				}
				else if (!tableau[i][j].isEmpty() &&(i == joueur1.getX() && j == joueur1.getY())){
					res += "[J1]";
				}
				else if (!tableau[i][j].isEmpty() &&(i == joueur2.getX() && j == joueur2.getY())){
					res += "[J2]";
				}
				if (j == 8){
					res += "\n";
				}
			}
		}
		return res;
	}
	
	public void putWallDown(Case case1){
		int i = case1.getX();
		int j = case1.getY();
		if (j != 8){
			tableau[i][j].setEdgeDownBegin(true);
			tableau[i][j+1].setEdgeDownEnd(true);
			tableau[i+1][j].setEdgeUpBegin(true);
			tableau[i+1][j+1].setEdgeUpEnd(true);
		}
		else{
			tableau[i][j].setEdgeDownEnd(true);
			tableau[i][j-1].setEdgeDownBegin(true);
			tableau[i+1][j].setEdgeUpEnd(true);
			tableau[i+1][j-1].setEdgeUpBegin(true);
		}
	}
	
	public void putWallUp(Case case1){
		int i = case1.getX();
		int j = case1.getY();
		if ( j != 8){
			tableau[i][j].setEdgeUpBegin(true);
			tableau[i][j+1].setEdgeUpEnd(true);
			tableau[i-1][j].setEdgeDownBegin(true);
			tableau[i-1][j+1].setEdgeDownEnd(true);
		}
		else{
			tableau[i][j].setEdgeUpEnd(true);
			tableau[i][j-1].setEdgeUpBegin(true);
			tableau[i-1][j].setEdgeDownEnd(true);
			tableau[i-1][j-1].setEdgeDownBegin(true);
		}
	}
	
	public void putWallRight(Case case1){
		int i = case1.getX();
		int j = case1.getY();
		if (i != 8){
			tableau[i][j].setEdgeRightBegin(true);
			tableau[i+1][j].setEdgeRightEnd(true);
			tableau[i][j+1].setEdgeLeftBegin(true);
			tableau[i+1][j+1].setEdgeLeftEnd(true);
		}
		else{
			tableau[i][j].setEdgeRightEnd(true);
			tableau[i-1][j].setEdgeRightBegin(true);
			tableau[i][j+1].setEdgeLeftEnd(true);
			tableau[i-1][j-1].setEdgeLeftBegin(true);
		}
	}
	
	public void putWallLeft(Case case1){
		int i = case1.getX();
		int j = case1.getY();
		if (i != 8){
			tableau[i][j].setEdgeLeftBegin(true);
			tableau[i+1][j].setEdgeLeftEnd(true);
			tableau[i][j-1].setEdgeRightBegin(true);
			tableau[i+1][j-1].setEdgeRightEnd(true);
		}
		else{
			tableau[i][j].setEdgeLeftEnd(true);
			tableau[i-1][j].setEdgeLeftBegin(true);
			tableau[i][j-1].setEdgeRightEnd(true);
			tableau[i-1][j-1].setEdgeRightBegin(true);
		}
	}
	
	public void answerToPutWallRight(){
		
	}
	
	public void answerToPutWallLeft(){
		
	}
	
	public void answerToPutWallDown(){
		
	}
	
	public void answerToPutWallUp(){
		
	}
	
	public void answerToMove(){
		
	}

	
	public void setTableau(Case[][] tableau){
		this.tableau = tableau;
	}
	
	public void setFirstPlayer(AGenericPlayer joueur1){
		Board.joueur1 = joueur1;
	}
	
	public void setSecondPlayer(AGenericPlayer joueur2){
		Board.joueur2 = joueur2;
	}

	public static Case[][] getTableau()
	{
		return tableau;
	}

	public static AGenericPlayer getFirstPlayer()
	{
		return joueur1;
	}
	
	public static AGenericPlayer getSecondPlayer(){
		return joueur2;
	}
}