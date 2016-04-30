package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import board.Board;

public class testPanel extends JPanel {
	
	Image bluePawn;
	Image redPawn;
	Image square;
	GuiCase[][] tabCases = new GuiCase[9][9];
	board.Case[][] realTab = Board.getTableau();
	
	
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
		gl.setColumns(9);
		gl.setRows(9);
		gl.setHgap(20);
		gl.setVgap(20);
		this.setLayout(gl);
		for (int i = 0 ;i < 9;i++){
			for (int j = 0; j< 9;j++){
				GuiCase bouton =new GuiCase(i,j);
				tabCases[i][j] =bouton;
				this.add(bouton);
			}
		}
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(850,700));
		for (int i = 0;i < 9 ;i++){
			for (int j = 0; j < 9;j++){
				tabCases[i][j].actualize();
			}
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i = 0;i < 9 ;i++){
			for (int j = 0; j < 9;j++){
				tabCases[i][j].actualize();
			}
		}
	}
	
	
}