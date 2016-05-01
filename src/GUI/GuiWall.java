package GUI;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
		this.setVisible(true);
	}
	
	
	public void actualize(){
		if (realTab[x][y].isEdgeRightBegin() ){
			this.setSize(20, 140);
			this.setText("Yolo");
		}
		else if(realTab[x][y].isEdgeDownBegin() ){
			this.setSize(140, 15);
			this.setText("Yolo2");
		}
	}
}
