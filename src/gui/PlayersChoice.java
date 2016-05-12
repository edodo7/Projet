package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import players.HardAI;
import players.HumanPlayer;
import players.RandomAI;

/**
 * Fenetre demandant le type de partie
 * @author Eduardo
 *
 */
public class PlayersChoice extends JFrame implements Serializable{
	
	private JButton humanVShuman = new JButton("joueur humain vs joueur humain");
	private JButton humanVSrandomAI = new JButton("joueur humain vs IA facile");
	private JButton humanVShardAI = new JButton("joueur humain vs IA difficile");
	private JButton randomAIVSrandomAI = new JButton("IA facile vs IA facile");
	private JButton randomAIVShardAI = new JButton("IA facile vs IA difficile");
	private JButton hardAIVShardAI = new JButton("IA difficile vs IA difficile");
	private JButton charger = new JButton("Charger partie");
	public Save save;
	public Lock lock;
	public Condition done;
	
	public PlayersChoice(){
		GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice []  gs= ge.getScreenDevices();
		GraphicsConfiguration gc= gs[0]. getDefaultConfiguration();
		Rectangle bounds= gc. getBounds();
		this.setSize(bounds.width, bounds.height);
		this.setTitle("Choisissez le type de joueurs");
		this.setLocationRelativeTo(null);
		lock = new ReentrantLock();
		done = lock.newCondition();
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
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		save = null;
	}
	
	/**
	 * Permet d'attendre jusqu'a ce qu'un choix soit effectue
	 * @throws InterruptedException
	 */
	public void Wait() throws InterruptedException{
		lock.lock();
		done.await();
		lock.unlock();
	}
	
	/**
	 * Permet d'instancier le type de joueurs selectiones
	 * @author Eduardo
	 *
	 */
	private class PlayerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			lock.lock();
			try {
				if (e.getSource() == humanVShuman){
					Main.joueur1 = new HumanPlayer(true);
					Main.joueur2 = new HumanPlayer(false);
					done.signal();
				}
				else if (e.getSource() == humanVSrandomAI){
					Main.joueur1 = new RandomAI(true);
					Main.joueur2 = new HumanPlayer(false);
					done.signal();
				}
				else if(e.getSource() == humanVShardAI){
					Main.joueur1 = new HardAI(true);
					Main.joueur2 = new HumanPlayer(false);
					done.signal();
				}
				else if (e.getSource() == randomAIVSrandomAI){
					Main.joueur1 = new RandomAI(true);
					Main.joueur2 = new RandomAI(false);
					done.signal();
				}
				else if(e.getSource() == randomAIVShardAI){
					Main.joueur1 = new HardAI(true);
					Main.joueur2 = new RandomAI(false);
					done.signal();
				}
				else if (e.getSource() == hardAIVShardAI){
					Main.joueur1 = new HardAI(true);
					Main.joueur2 = new HardAI(false);
					done.signal();
				}
			}
			finally{
				lock.unlock();
			}
		}
	}
	
	/**
	 * Permet de recuperer une partie sauvegardee
	 * @author Eduardo
	 *
	 */
	public class LoadListener implements ActionListener{
		public void actionPerformed(ActionEvent e){	
			try {
				lock.lock();
				save = Save.load();
				done.signal();
				lock.unlock();
			} 
			catch (ClassNotFoundException | IOException a) {
				a.printStackTrace();
			}
		}
	}
}
