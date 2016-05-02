package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import board.Board;
import board.Case;

public class GuiWall extends JButton{

	private static Case[][] realTab = Board.getTableau();
	private int x;
	private int y;
	private boolean isVertical;
	public static ImageIcon HorizonalWall;
	public static ImageIcon VerticalWall;
	
	public GuiWall(int x ,int y, boolean isVertical){
		this.x = x;
		this.y = y;
		this.isVertical = isVertical;
		try{
			HorizonalWall = new ImageIcon(ImageIO.read(new File("./ressources/hWall.png")));
			VerticalWall = new ImageIcon(ImageIO.read(new File("./ressources/vWall.png")));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		if (isVertical){
			this.setPreferredSize(new Dimension(15,100));
		}
		else{
			this.setPreferredSize(new Dimension(100,15));
		}
		this.setEnabled(true);
		this.setBackground(new Color(0,0,0,0));
		this.setFocusPainted(false);
		this.setMargin(null);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setVisible(true);
	}
	
	
	public void actualize(){
		if ((realTab[this.x][this.y].isEdgeRightBegin() ||realTab[this.x][this.y].isEdgeRightEnd()&& isVertical)){
			//this.setBackground(Color.BLACK);
		}
		else if((realTab[this.x][this.y].isEdgeDownBegin() ||realTab[this.x][this.y].isEdgeDownEnd()&& !isVertical)){
			this.setBackground(Color.BLACK);
		}
		else{
			this.setBackground(new Color(0,0,0,0));;
		}
	}
}
