package GUI;

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

public class MyFrame extends JFrame {
	private JButton newGame;
	private JButton loadGame;
	private JPanel gamePanel = background();
	private JPanel start = new Start();
	private Container contentPane = this.getContentPane();
	/*private JMenuBar menuBar = new JMenuBar();
	private JMenu partie = new JMenu("Partie");
	private JMenuItem sauver = new JMenuItem("Sauvegarder");
	private JMenuItem charger = new JMenuItem("Charger");*/
	
	public MyFrame() {
		this.setTitle("Quoridor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280,1280);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		/*partie.add(sauver);
		partie.add(charger);
		menuBar.add(partie);
		menuBar.setOpaque(false);
		this.setJMenuBar(menuBar);*/
		this.setMinimumSize(new Dimension(1000,1000));
		contentPane.setLayout(new BorderLayout());
		contentPane.add(gamePanel,BorderLayout.CENTER);
		Timer timer = new Timer(96,new Repaint());
		timer.start();
		//this.setVisible(true);
	}
	
	public MyFrame(JPanel gamePanel){
		this.setTitle("Quoridor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280,1280);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(1000,1000));
		this.gamePanel = gamePanel;
		contentPane.setLayout(new BorderLayout());
		contentPane.add(gamePanel,BorderLayout.CENTER);
	}
	
	
	private class Repaint  implements ActionListener{
		public void actionPerformed(ActionEvent e){
			MyFrame.this.repaint();
		}
	}
	
	private JPanel build(){
        
        JPanel pan = new JPanel();
        newGame = new JButton("Nouvelle partie");
        loadGame = new JButton("Charger partie");
        newGame.addActionListener(new LaunchListener());
        loadGame.addActionListener(new LaunchListener()); 
        pan.add(newGame);
        pan.add(loadGame);
        pan.setOpaque(false);
        return pan;
    }
	
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
	
	private class LaunchListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == newGame){
				contentPane.add(gamePanel,BorderLayout.CENTER);
				contentPane.revalidate();
				MyFrame.this.contentPane.remove(start);
			}
		}
	} 
	
}
