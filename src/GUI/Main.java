package GUI;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

import board.Board;
import players.AGenericPlayer;
import players.HardAI;
import players.HumanPlayer;
import players.RandomAI;

public class Main {

	private AGenericPlayer joueur1;
	private AGenericPlayer joueur2;
	private MyFrame frame;
	private Board board;
	public static boolean tourJoueur1;
	
	public Main() {
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
		board = new Board(joueur1,joueur2);
		this.frame = new MyFrame();
	}
	
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
			System.out.println(board);
			System.out.println(joueur1.getClass().getName());
			try{
				if ((tourJoueur1)){
					if(joueur1.getClass().getName().equals("players.RandomAI")|| joueur1.getClass().getName().equals("players.HardAI")){
						System.out.println("C'est au tour du Joueur 1");				
						joueur1.play();
						nbreCoupsJ1++;
						System.out.println("Le joueur1 a joué");
						tourJoueur1 = false;
					}
					else{
						System.out.println("C'est au tour du Joueur 1");
						while(tourJoueur1){
							System.out.println("");
						}
						System.out.println("Le joueur1 a joué");
						nbreCoupsJ1++;
					}
				}
				else{
					System.out.println("C'est au tour du Joueur 2");
					if(joueur2.getClass().getName().equals("players.RandomAI")|| joueur2.getClass().getName().equals("players.HardAI")){
						joueur2.play();
						System.out.println("Le joueur2 a joué");
						nbreCoupsJ2++;
						tourJoueur1 = true;
					}
					else{
						while(!tourJoueur1){
							System.out.println("");
						}
						System.out.println("Le joueur2 a joué");
						nbreCoupsJ2++;
					}
				}
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void main(String[] args){
		Main main = new Main();
		main.play();
	}
}
