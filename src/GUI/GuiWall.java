package GUI;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import board.Board;
import board.Case;

public class GuiWall extends JButton {

	private boolean isVertical;
	private int x;
	private int y;
	public static Case[][] realTab = Board.getTableau();
	public static ImageIcon HorizontalWall;
	public static ImageIcon VerticalWall;
	
	public GuiWall(int x,int t,boolean isVertical){
		this.x =x;
		this.y = y;
		this.isVertical = isVertical;
		try{
			HorizontalWall = new ImageIcon(ImageIO.read(new File("./ressources/hWall.png")));
			VerticalWall = new ImageIcon(ImageIO.read(new File("./ressources/vWall.png")));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public void actuaize(){
		System.out.println("x :"+ this.x+ "\n" +" y: "+this.y);
		if(realTab[this.x][this.y].isEdgeRightBegin() && isVertical){
			this.setIcon(VerticalWall);
			this.setVisible(true);
		}
		else if (realTab[this.x][this.y].isEdgeDownBegin() && !isVertical){
			this.setIcon(HorizontalWall);
			this.setVisible(true);
		}
		else{
			this.setVisible(false);
		}
	}
}