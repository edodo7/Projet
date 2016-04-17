package GUI;
import mainAndRules.*;
import board.*;
import pathFinding.*;
import players.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import board.Board;
public class Panneau extends JPanel{
	public void paintComponent(Graphics g){
		try{
			Image background = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/vault boy.jpg"));
			/*Image square = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/blue.png"));
			Image square2 = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/maj.png"));
			Image wallV = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/wallV.png"));
			Image wallH = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/wallH.png"));*/
			g.drawImage(background,0, 0, this.getWidth(), this.getHeight(),this);
			int xDepart = this.getWidth()/8;
			int yDepart = this.getHeight()/8;
			for (int i =0; i <9;i++){
				if (i != 0){
					yDepart += 80; 
				}
				for(int j =0;j < 9; j++){
					if (j != 0){
						xDepart += 80;
					}
					else{
						xDepart = this.getWidth()/8;
					}
					Color brown = new java.awt.Color(51,0,0);
					if(!Board.tableau[i][j].isEmpty()){
						if(i == Board.getFirstPlayer().getX() && j== Board.getFirstPlayer().getY()){
							g.setColor(brown);
							g.fillRect(xDepart, yDepart, 60, 60);
							g.setColor(Color.red);
							g.fillOval(xDepart+2, yDepart+2, 55, 55);
						}
						else{
							g.setColor(brown);
							g.fillRect(xDepart, yDepart, 60, 60);
							g.setColor(Color.green);
							g.fillOval(xDepart+2, yDepart+2, 55, 55);
						}
					}
					else{
						g.setColor(brown);
						g.fillRect(xDepart, yDepart, 60, 60);
					}
					if (Board.tableau[i][j].isEdgeDownBegin()){
						g.setColor(Color.BLACK);
						g.fillRect(xDepart, yDepart+65, 140, 10);
					}
					else if(Board.tableau[i][j].isEdgeLeftBegin()){
						g.setColor(Color.BLACK);
						g.fillRect(xDepart-15, yDepart, 10, 140);
					}
					else if(Board.tableau[i][j].isEdgeRightBegin()){
						g.setColor(Color.BLACK);
						g.fillRect(xDepart +65, yDepart, 10, 140);
					}
					else if(Board.tableau[i][j].isEdgeUpBegin()){
						g.setColor(Color.BLACK);
						g.fillRect(xDepart, yDepart-15, 140, 10);
					}
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
