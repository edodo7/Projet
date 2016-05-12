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
import javax.swing.JOptionPane;

import board.Board;
import players.AGenericPlayer;
/**
 * Classe principale,c'est ici que tout les differents composants graphique se coordonent afin de pouvoir jouer une partie entre 2 joueurs
 * @author Eduardo
 *
 */
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
	/**
	 * Constructeur, il permet de donner le choix au joueur de commmencer une nouvelle partie ou de charger une partie precedement sauvee
	 */
	public Main() {
		PlayersChoice choix = new PlayersChoice();
		try {
			choix.Wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (choix.save != null){
			this.joueur1= choix.save.joueur1;
			this.joueur2 = choix.save.joueur2;
			this.board = new Board(joueur1,joueur2) ;
			board.setTableau(choix.save.plateau);
			joueur1.plateau = choix.save.plateau;
			joueur2.plateau = choix.save.plateau;
			tourJoueur1 = choix.save.tourJoueur1;
		}
		else{
			board = new Board(joueur1,joueur2);
			Random choice = new Random();
			int whoStarts = choice.nextInt(2);
			if(whoStarts == 1){
				tourJoueur1 = true;
			}
			else{
				tourJoueur1 = false;
			}
		}
		lastSave = new Save(board);
		this.frame = new MyFrame();
		sauver.addActionListener(new SaveListener());
		partie.add(sauver);
		menuBar.add(partie);
		menuBar.setOpaque(false);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
		choix.dispose();
	}
	/**
	 * Methode de jeu,elle permet a chaque joueur de jouer chacun a son tour et verifie les conditions de victoire
	 */
	public void play(){
		JOptionPane victory = new JOptionPane();
		int nbreCoupsJ1 = 0;
		int nbreCoupsJ2 = 0;
		boolean continuer = true;
		while(continuer){
			try{
				if ((tourJoueur1)){
					if(joueur1.getClass().getName().equals("players.RandomAI")|| joueur1.getClass().getName().equals("players.HardAI")){				
						if (!joueur2.getClass().getName().equals("players.HumanPlayer")){
							try {
								Thread.sleep(1000);
								
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						joueur1.play();
						nbreCoupsJ1++;
						lastSave.shoot(board);
						tourJoueur1 = false;
					}
					else{
						lastSave.shoot(board);
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
						lastSave.shoot(board);
						tourJoueur1 = false;
						nbreCoupsJ1++;
					}
					if (joueur1.getX() == 8){
						victory.showMessageDialog(null, "Le joueur 1 est le vainqueur!");
						continuer = false;
					}
				}
				else{
					if(joueur2.getClass().getName().equals("players.RandomAI")|| joueur2.getClass().getName().equals("players.HardAI")){
						joueur2.play();
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
						lastSave.shoot(board);
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
						nbreCoupsJ2++;
						lastSave.shoot(board);
						tourJoueur1 = true;
					}
					if (joueur2.getX() == 0){
						victory.showMessageDialog(null, "Le joueur 2 est le vainqueur!");
						continuer = false;
					}
				}
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
		frame.dispose();
	}
	/**
	 * Permet de detecter quand le joueur veut asuvegarder la partie en cours
	 * @author Eduardo Dom
	 */
	public class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Save.save(lastSave);
		}
	}
	
	
	
	public static void main(String[] args){
		Main main = new Main();
		while(true){
			main.play();
			main = new Main();
		}
	}
}
