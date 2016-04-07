package mainAndRules;
import java.io.IOException;
import java.util.Scanner;

import board.Board;
import players.AGenericPlayer;
import players.IPlayerAbility;
/**
 * @author Eduardo
 */
public class Main {
	private static Board board;
	private IPlayerAbility joueur1;
	private IPlayerAbility joueur2;
	private boolean tourJoueur1;
	public static void main(String[] args){
		Main main = new Main();
		main.play();
	}
	
	public Main() {
		Main.board = new Board();
		board.whatKindOfPlayers();
		joueur1 = Board.getFirstPlayer();
		joueur2 = Board.getSecondPlayer();
	}
	
	
	
	public void play(){
		Scanner question = new Scanner(System.in);
		System.out.println("Le Joueur 1 doit il jouer le premier?[O]/[N]");
		String reponse = question.nextLine();
		int nbreCoupsJ1 =0;
		int nbreCoupsJ2 = 0;
		if(reponse.equals("O")){
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
						System.out.println("Le Joueur 1 a gagné la partie en "+nbreCoupsJ1+" coups!");
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
						System.out.println("Le Joueur 2 a gagné la partie en "+nbreCoupsJ2+" coups!");
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