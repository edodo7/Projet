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

public class Case extends JButton{
	private Image player1 ;
	private Image player2;
	private Image square;
	public int x;
	public int y;
	public board.Case[][] plateau = Board.getTableau();
	public Case(Icon img){
		super(img);
	}
	
}
