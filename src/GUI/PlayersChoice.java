package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import players.HardAI;
import players.HumanPlayer;
import players.RandomAI;

public class PlayersChoice extends JFrame implements Serializable{
	
	private JButton humanVShuman = new JButton("joueur humain vs joueur humain");
	private JButton humanVSrandomAI = new JButton("joueur humain vs IA facile");
	private JButton humanVShardAI = new JButton("joueur humain vs IA difficile");
	private JButton randomAIVSrandomAI = new JButton("IA facile vs IA facile");
	private JButton randomAIVShardAI = new JButton("IA facile vs IA difficile");
	private JButton hardAIVShardAI = new JButton("IA difficile vs IA difficile");
	private JButton charger = new JButton("Charger partie");
	public static boolean notDone;
	public Save save;
	
	public PlayersChoice(){
		this.setSize(1280, 1280);
		this.setTitle("Choisissez le type de joueurs");
		this.setLocationRelativeTo(null);
		humanVShuman.addActionListener(new PlayerListener());
		humanVShardAI.addActionListener(new PlayerListener());
		humanVSrandomAI.addActionListener(new PlayerListener());
		randomAIVSrandomAI.addActionListener(new PlayerListener());
		randomAIVShardAI.addActionListener(new PlayerListener());
		hardAIVShardAI.addActionListener(new PlayerListener());
		charger.addActionListener(new LoadListener());
		Start contentPane = new Start();
		contentPane.setLayout(new BorderLayout());
		JPanel pan = new JPanel();
		pan.setLayout(new BoxLayout(pan,BoxLayout.LINE_AXIS));
		pan.add(humanVShuman);
		pan.add(humanVSrandomAI);
		pan.add(humanVShardAI);
		pan.add(randomAIVSrandomAI);
		pan.add(randomAIVShardAI);
		pan.add(hardAIVShardAI);
		pan.add(charger);
		pan.setOpaque(false);
		contentPane.add(pan,BorderLayout.SOUTH);
		this.setContentPane(contentPane);
		this.notDone = true;
		this.setVisible(true);
		save = null;
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
	
	public class LoadListener implements ActionListener{
		public void actionPerformed(ActionEvent e){	
			try {
				save = Save.load();
				notDone = false;
			} 
			catch (ClassNotFoundException | IOException a) {
				a.printStackTrace();
			}
		}
	}
}
