package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import board.Board;
import players.AGenericPlayer;
import players.HardAI;
import players.HumanPlayer;
import players.RandomAI;

public class Main {

	public static AGenericPlayer joueur1;
	public static AGenericPlayer joueur2;
	private MyFrame frame;
	public static Board board;
	private JFrame menuFrame;
	private JButton humanVShuman = new JButton("joueur humain vs joueur humain");
	private JButton humanVSrandomAI = new JButton("joueur humain vs IA facile");
	private JButton humanVShardAI = new JButton("joueur humain vs IA difficile");
	private JButton randomAIVSrandomAI = new JButton("IA facile vs IA facile");
	private JButton randomAIVShardAI = new JButton("IA facile vs IA difficile");
	private JButton hardAIVShardAI = new JButton("IA difficile vs IA difficile");
	public static boolean tourJoueur1;
	
	public Main() {
		PlayersChoice choix = new PlayersChoice();
		choix.Wait();
		board = new Board(joueur1,joueur2);
		this.frame = new MyFrame();
		choix.dispose();
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
						ActionThread actionJoueur1 = new ActionThread();
						Thread t = new Thread(actionJoueur1);
						t.start();
						try {
							t.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("Le joueur1 a joué");
						tourJoueur1 = false;
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
						ActionThread actionJoueur1 = new ActionThread();
						Thread t = new Thread(actionJoueur1);
						t.start();
						try {
							t.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("Le joueur2 a joué");
						nbreCoupsJ2++;
						tourJoueur1 = true;
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
