package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import board.Board;
import players.HardAI;
import players.HumanPlayer;
import players.RandomAI;

public class PlayersChoice extends JFrame{
	
	private JButton humanVShuman = new JButton("joueur humain vs joueur humain");
	private JButton humanVSrandomAI = new JButton("joueur humain vs IA facile");
	private JButton humanVShardAI = new JButton("joueur humain vs IA difficile");
	private JButton randomAIVSrandomAI = new JButton("IA facile vs IA facile");
	private JButton randomAIVShardAI = new JButton("IA facile vs IA difficile");
	private JButton hardAIVShardAI = new JButton("IA difficile vs IA difficile");
	public static boolean notDone;
	
	public PlayersChoice(){
		this.setSize(300, 300);
		this.setContentPane(new JPanel());
		this.setTitle("Choisissez le type de joueurs");
		this.setLocationRelativeTo(null);
		humanVShuman.addActionListener(new PlayerListener());
		humanVShardAI.addActionListener(new PlayerListener());
		humanVSrandomAI.addActionListener(new PlayerListener());
		randomAIVSrandomAI.addActionListener(new PlayerListener());
		randomAIVShardAI.addActionListener(new PlayerListener());
		hardAIVShardAI.addActionListener(new PlayerListener());
		this.add(humanVShuman);
		this.add(humanVSrandomAI);
		this.add(humanVShardAI);
		this.add(randomAIVSrandomAI);
		this.add(randomAIVShardAI);
		this.add(hardAIVShardAI);
		this.notDone = true;
		this.setVisible(true);
	}
	
	
	public void Wait(){
		while(notDone){
			System.out.println("");
		}
	}
	
	
	private class PlayerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == humanVShuman){
				Main.joueur1 = new HumanPlayer(true);
				Main.joueur2 = new HumanPlayer(false);
				notDone = false;
			}
			else if (e.getSource() == humanVSrandomAI){
				Main.joueur1 = new RandomAI(true);
				Main.joueur2 = new HumanPlayer(false);
				notDone = false;
			}
			else if(e.getSource() == humanVShardAI){
				Main.joueur1 = new HardAI(true);
				Main.joueur2 = new HumanPlayer(false);
				PlayersChoice.notDone = false;
			}
			else if (e.getSource() == randomAIVSrandomAI){
				Main.joueur1 = new RandomAI(true);
				Main.joueur2 = new RandomAI(false);
				notDone = false;
			}
			else if(e.getSource() == randomAIVShardAI){
				Main.joueur1 = new HardAI(true);
				Main.joueur2 = new RandomAI(false);
				notDone = false;
			}
			else if (e.getSource() == hardAIVShardAI){
				Main.joueur1 = new HardAI(true);
				Main.joueur2 = new HardAI(false);
				notDone = false;
			}
		}
	}
}
