package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import board.Board;

public class testPanel extends JPanel {
	
	private Image bluePawn;
	private Image redPawn;
	private Image square;
	private ArrayList<GuiCase> tabCases = new ArrayList();
	private board.Case[][] realTab = Board.getTableau();
	private ArrayList<GuiWall> tabWalls = new ArrayList();
	
	
	public testPanel(){
		try{
			bluePawn = ImageIO.read(new File("./ressources/blue.png"));
			redPawn = ImageIO.read(new File("./ressources/red.png"));
			square = ImageIO.read(new File("./ressources/empty.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		GridLayout gl = new GridLayout();
		gl.setColumns(16);
		gl.setRows(16);
		gl.setHgap(5);
		gl.setVgap(5);
		this.setLayout(gl);
		for (int i = 0 ;i < 16;i++){
			for (int j = 0; j< 16;j++){
				if(i % 2 == 0){
					if (j % 2 == 0){
						GuiCase bouton = new GuiCase(i/2,j/2);
						tabCases.add(bouton);
						this.add(bouton);
					}
					else{
						GuiWall VerticalWall = new GuiWall(i/2,j-1,true);
						tabWalls.add(VerticalWall);
						this.add(VerticalWall);
					}
				}
				else{
					if(j % 2 == 0){
						GuiWall HorizontalWall = new GuiWall(i-1,j/2,false);
						tabWalls.add(HorizontalWall);
						this.add(HorizontalWall);
					}
					else{
						this.add(new JLabel(""));
					}
				}
			}
		}
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(850,700));
		for (int i = 0;i < tabCases.size() ;i++){
			tabCases.get(i).actualize();
		}
		for (int i = 0;i < tabWalls.size();i++){
			tabWalls.get(i).actuaize();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i = 0;i < tabCases.size() ;i++){
			tabCases.get(i).actualize();
		}
		for (int i = 0;i < tabWalls.size();i++){
			tabWalls.get(i).actuaize();
		}
	}
	
	
}