package GUI;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import board.Board;
import players.AGenericPlayer;
import players.HumanPlayer;

import java.io.IOException;

public class Case extends JButton implements ActionListener{
	private Image player1 ;
	private Image player2;
	private Image square;
	public int x;
	public int y;
	public board.Case[][] plateau = Board.getTableau();
	public Case(Icon img){
		super(img);
	}
	
	public void actionPerformed(ActionEvent e){
		HumanPlayer joueur1 = (HumanPlayer)Board.getFirstPlayer();
		joueur1.move(x, y);
		getRootPane().repaint();
	}
	
}
