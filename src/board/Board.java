package board;
import java.util.Scanner;

import players.AGenericPlayer;
import players.RandomAI;
import players.HardAI;
import players.HumanPlayer;

/**
 * Cette classe permet de modéliser le plateau de jeu ainsi que les joueurs qui y sont présents(Qu'ils soient humains ou IA)
 * @author Eduardo Dom
 *
 */
public class Board
{
	public static Case[][] tableau = new Case[9][9];
	private static AGenericPlayer joueur1;
	private static AGenericPlayer joueur2;



	public Board ()
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				tableau[i][j] = new Case(i,j);
			}
		}
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

	
	public void whatKindOfPlayers(){
		System.out.println("Quelle genre de partie voulez vous?");
		System.out.println("1 : IA vs joueur humain");
		System.out.println("2 : joueur humain vs joueur humain");
		System.out.println("3 : IA vs IA");
		System.out.println("Entrez le numéro correspondant à votre choix.");
		Scanner question = new Scanner(System.in);
		int reponse = question.nextInt();
		if (reponse == 1){
			System.out.println("Quel genre d'IA voulez vous?");
			System.out.println("1 : Aléatoire");
			System.out.println("2 : Difficile");
			System.out.println("Entrez le numéro correspondant à votre choix.");
			int reponse2 = question.nextInt();
			if (reponse2 == 1){
				joueur1 = new RandomAI(true);
				joueur2 = new HumanPlayer(false);
			}
			else{
				joueur1 = new HardAI(true);
				joueur2 = new HumanPlayer(false);
			}
		}
		if (reponse == 2){
			joueur1 = new HumanPlayer(true);
			joueur2 = new HumanPlayer(false);
		}
		if (reponse == 3){
			System.out.println("Quelle genre de partie voulez vous?");
			System.out.println("1 : IA aléatoire vs IA aléatoire");
			System.out.println("2 : IA difficile vs IA aléatoire");
			System.out.println("3 : IA difficile vs IA difficile");
			System.out.println("Entrez le numéro correspondant à votre choix.");
			int reponse3 = question.nextInt();
			if (reponse3 == 1){
				joueur1 = new RandomAI(true);
				joueur2 = new RandomAI(false);
			}
			else if (reponse3 == 2){
				joueur1 = new HardAI(true);
				joueur2 = new RandomAI(false);
			}
			else{
				joueur1 = new HardAI(true);
				joueur2 = new HardAI(false);
			}
		}
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