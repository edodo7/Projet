package GUI;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import board.Board;
import players.AGenericPlayer;
import players.HumanPlayer;

import java.io.IOException;

public class GuiCase extends JButton{
	
	public static ImageIcon bluePawn;
	public static ImageIcon redPawn;
	public static ImageIcon square;
	private int x;
	private int y;
	public static AGenericPlayer joueur1 = Board.getFirstPlayer();
	public static AGenericPlayer joueur2 = Board.getSecondPlayer();
	public GuiCase(int x,int y){
		this.x = x;
		this.y = y;
		try{
			bluePawn = new ImageIcon(ImageIO.read(new File("./ressources/blue.png")));
			redPawn = new ImageIcon(ImageIO.read(new File("./ressources/red.png")));
			square = new ImageIcon(ImageIO.read(new File("./ressources/empty.png")));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(62,62));
		this.setFocusPainted(false);
		this.setMargin(null);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.addActionListener(new MoveListener(this.x,this.y,this));
	}
	
	public GuiCase(){
		try{
			bluePawn = new ImageIcon(ImageIO.read(new File("./ressources/blue.png")));
			redPawn = new ImageIcon(ImageIO.read(new File("./ressources/red.png")));
			square = new ImageIcon(ImageIO.read(new File("./ressources/empty.png")));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		this.setIcon(square);
		this.setPreferredSize(new Dimension(62,62));
		this.setFocusPainted(false);
		this.setMargin(null);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.addActionListener(new MoveListener(this.x,this.y,this));
	}
	
	
	
	public void actualize (){
		if (this.x == joueur1.getX() && this.y == joueur1.getY() ){
			this.setIcon(redPawn);
		}
		else if (this.x == joueur2.getX() && this.y == joueur2.getY()){
			this.setIcon(bluePawn);
		}
		else{
			this.setIcon(square);
		}
	}
	
}