package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import board.Board;
import players.AGenericPlayer;

public class Main implements Serializable {

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
	public static Save lastSave;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu partie = new JMenu("Partie");
	private JMenuItem sauver = new JMenuItem("Sauvegarder");
	public static Lock lock;
	public static Condition done;
	
	public Main() {
		PlayersChoice choix = new PlayersChoice();
		try {
			choix.Wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//choix.get();
		if (choix.save != null){
			this.joueur1= choix.save.joueur1;
			this.joueur2 = choix.save.joueur2;
			this.board = new Board(joueur1,joueur2) ;
			board.setTableau(choix.save.plateau);
			this.lastSave = choix.save;
		}
		else{
			board = new Board(joueur1,joueur2);
			lastSave = new Save(board);
		}
		this.frame = new MyFrame();
		sauver.addActionListener(new SaveListener());
		partie.add(sauver);
		menuBar.add(partie);
		menuBar.setOpaque(false);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
		choix.dispose();
		lock = new ReentrantLock();
		done = lock.newCondition();
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
						lastSave.shoot(board);
						tourJoueur1 = false;
						if (!joueur2.getClass().getName().equals("players.HumanPlayer")){
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					else{
						System.out.println("C'est au tour du Joueur 1");
						//ActionThread actionJoueur1 = new ActionThread();
						boolean notPlayedYet = true;
						MoveListener.notDone = true;
						WallListener.notDone = true;
						while (notPlayedYet){
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if ((MoveListener.notDone && WallListener.notDone )== false){
								notPlayedYet = false;
							}
						}
						System.out.println("Le joueur1 a joué");
						lastSave.shoot(board);
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
						lastSave.shoot(board);
						tourJoueur1 = true;
						if (!joueur1.getClass().getName().equals("players.HumanPlayer")){
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					else{
						boolean notPlayedYet = true;
						MoveListener.notDone = true;
						WallListener.notDone = true;
						while (notPlayedYet){
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if ((MoveListener.notDone && WallListener.notDone )== false){
								notPlayedYet = false;
							}
						}
						System.out.println("Le joueur2 a joué");
						nbreCoupsJ2++;
						lastSave.shoot(board);
						tourJoueur1 = true;
					}
				}
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	public class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("L'état du tableau quand je l'ai sauvé");
			System.out.println(board);
			Save.save(lastSave);
			try {
				System.out.println("Voici normalement le plateau sauvé");
				System.out.println(Save.load().board);
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	
	public static void main(String[] args){
		Main main = new Main();
		main.play();
	}
}
