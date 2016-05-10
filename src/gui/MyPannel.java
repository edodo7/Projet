package gui;
import mainAndRules.*;
import board.*;
import pathFinding.*;
import players.*;
import java.awt.Dimension.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import board.Board;
public class MyPannel extends JPanel {
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		try{
			Image background = ImageIO.read(new File("./ressources/vador.jpg"));
			g.drawImage(background,0, 0, this.getWidth(), this.getHeight(),this);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
