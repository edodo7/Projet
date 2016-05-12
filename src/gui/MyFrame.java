package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * La fenetre sur laquelle on joue
 * @author Eduardo
 *
 */
public class MyFrame extends JFrame {
	private JButton newGame;
	private JButton loadGame;
	private JPanel gamePanel = background();
	private JPanel start = new Start();
	private Container contentPane = this.getContentPane();
	
	public MyFrame() {
		this.setTitle("Quoridor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280,1280);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(1000,1000));
		contentPane.setLayout(new BorderLayout());
		contentPane.add(gamePanel,BorderLayout.CENTER);
		Timer timer = new Timer(96,new Repaint());
		timer.start();
	}
	
	
	private class Repaint  implements ActionListener{
		public void actionPerformed(ActionEvent e){
			MyFrame.this.repaint();
		}
	}
	
	/**
	 * Permet d'obtenir un JPanel sur lequel on va pouvoir jouer
	 * @return Un JPanel
	 */
	public static JPanel background() {
		JPanel background = new MyPannel();
		background.setLayout(new BorderLayout());
		JPanel nordEst = new JPanel();
		nordEst.setOpaque(false);
		nordEst.setLayout(new BorderLayout());
		nordEst.add(new GamePanel(),BorderLayout.WEST);
		background.add(nordEst,BorderLayout.NORTH);
		JPanel pan = new JPanel();
		pan.setOpaque(false);
		background.add(pan,BorderLayout.EAST);
		return background;
	}
	
}
