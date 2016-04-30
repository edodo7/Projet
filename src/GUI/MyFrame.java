package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import board.Board;

public class MyFrame extends JFrame {
	public MyFrame() {
		this.setTitle("Quoridor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280,1280);
		this.setLocationRelativeTo(null);
		MyPannel background = new MyPannel();
		background.setLayout(new BorderLayout());
		background.add(new testPanel(),BorderLayout.WEST);
		JPanel pan = new JPanel();
		pan.setOpaque(false);
		pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));
		pan.add(new JButton("Move"));
		pan.add(new JButton("Wall"));
		background.add(pan,BorderLayout.EAST);
		this.setMinimumSize(new Dimension(1000,1000));
		this.setContentPane(background);
		this.setVisible(true);
		Timer timer = new Timer(96,new Repaint());
		timer.start();
	}
	
	private class Repaint  implements ActionListener{
		public void actionPerformed(ActionEvent e){
			MyFrame.this.repaint();
		}
	}
}
