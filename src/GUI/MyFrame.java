package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import board.Board;
import mainAndRules.Main;

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
		this.setVisible(true);
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
	
	private JPanel background() {
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
