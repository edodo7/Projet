package mains;
import board.Board;
import players.AGenericPlayer;
import players.HardAI;
import players.HumanPlayer;
import players.RandomAI;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
/**
 * Permet de jouer au Quoridor en mode console
 * @author Eduardo
 */
public class ConsoleMain {
	private static Board board;
	private AGenericPlayer joueur1;
	private AGenericPlayer joueur2;
	private boolean tourJoueur1;
	public static void main(String[] args){
		ConsoleMain consoleMain = new ConsoleMain();
		consoleMain.play();
	}
	
	public ConsoleMain() {
		System.out.println("Quelle genre de partie voulez vous?");
		System.out.println("1 : IA vs joueur humain");
		System.out.println("2 : joueur humain vs joueur humain");
		System.out.println("3 : IA vs IA");
		System.out.println("Entrez le numero correspondant a votre choix.");
		Scanner question = new Scanner(System.in);
		int reponse = question.nextInt();
		if (reponse == 1){
			System.out.println("Quel genre d'IA voulez vous?");
			System.out.println("1 : Aleatoire");
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
			System.out.println("1 : IA aleatoire vs IA aleatoire");
			System.out.println("2 : IA difficile vs IA aleatoire");
			System.out.println("3 : IA difficile vs IA difficile");
			System.out.println("Entrez le numero correspondant a votre choix.");
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
		board = new Board(joueur1,joueur2);
	}
	
	
	/**
	 * 
	 */
	public void play(){
		Random choice = new Random();
		int whoStarts = choice.nextInt(2);
		int nbreCoupsJ1 = 0;
		int nbreCoupsJ2 = 0;
		if(whoStarts == 1){
			tourJoueur1 = true;
		}
		else{
			tourJoueur1 = false;
		}
		boolean continuer = true;
		while(continuer){
			try{
				if (tourJoueur1 && continuer){
					System.out.println(board);
					System.out.println("C'est au tour du Joueur 1");
					joueur1.play();
					nbreCoupsJ1++;
					tourJoueur1 = false;
					if (joueur1.getX() == 8){
						System.out.println("Le Joueur 1 a gagne la partie en "+nbreCoupsJ1+" coups!");
						System.out.println(board);
						continuer = false;
					}
				}
				if(!tourJoueur1 && continuer){
					System.out.println(board);
					System.out.println("C'est au tour du Joueur 2");
					joueur2.play();
					nbreCoupsJ2++;
					tourJoueur1 = true;
					if (joueur2.getX() == 0){
						System.out.println("Le Joueur 2 a gagne la partie en "+nbreCoupsJ2+" coups!");
						System.out.println(board);
						continuer = false;
					}
				}
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static Board getBoard(){
		return board;
	}
}