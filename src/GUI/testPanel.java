package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import board.Board;
import mainAndRules.Main;

public class testPanel extends JPanel {
	
	private Image bluePawn;
	private Image redPawn;
	private Image square;
	private GuiCase[][] tabCases = new GuiCase[9][9];
	private board.Case[][] realTab = Board.getTableau();
	private ArrayList<GuiWall> tabWalls = new ArrayList<GuiWall>();
	
	
	public testPanel(){
		try{
			bluePawn = ImageIO.read(new File("./ressources/blue.png"));
			redPawn = ImageIO.read(new File("./ressources/red.png"));
			square = ImageIO.read(new File("./ressources/empty.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		for (int i = 0; i < 9;i++){
				gbc.gridx = 0;
				if(i != 0){
					gbc.gridy += 2;
				}
			for(int j = 0; j < 9;j++){
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				GuiCase bouton = new GuiCase(i,j);
				tabCases[i][j] = bouton;
				this.add(bouton, gbc);
				GuiWall VWall = new GuiWall(i,j,true);
				GuiWall HWall = new GuiWall(i,j,false);
				tabWalls.add(VWall);
				tabWalls.add(HWall);
				gbc.gridx += 1;
				gbc.gridheight = 2;
				gbc.gridwidth = 1;
				gbc.fill = GridBagConstraints.VERTICAL;
				this.add(VWall, gbc);
				gbc.gridx -= 1;
				gbc.gridy += 1;
				gbc.gridheight = 1;
				gbc.gridwidth = 2;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				this.add(HWall, gbc);
				gbc.gridx += 2;
				gbc.gridy -= 1;
				
			}
		}
		this.setBackground(new Color(0,0,0,0));
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i = 0;i < tabCases.length ;i++){
			for (int j = 0;j < tabCases[0].length ;j++){
				tabCases[i][j].actualize();
			}
		}
		for (int i = 0;i < tabWalls.size();i++){
			tabWalls.get(i).actualize();
		}
	}
	
	
	
}