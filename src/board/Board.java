package board;
import java.io.Serializable;

import players.AGenericPlayer;

/**
 * Cette classe permet de modeliser le plateau de jeu ainsi que les joueurs qui y sont presents(Qu'ils soient humains ou IA)
 * @author Eduardo Dom
 *
 */
public class Board implements Serializable
{
	public static Case[][] tableau = new Case[9][9];
	public static AGenericPlayer joueur1;
	public static AGenericPlayer joueur2;



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
	

	
	public void setTableau(Case[][] tableau){
		Board.tableau = tableau;
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