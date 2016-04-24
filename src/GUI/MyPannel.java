package GUI;
import mainAndRules.*;
import board.*;
import pathFinding.*;
import players.*;
import java.awt.Dimension.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import board.Board;
public class MyPannel extends JPanel {
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		try{
			Image background = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/vault boy.jpg"));
			Image square = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/blue.png"));
			Image square2 = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/red.png"));
			Image square3 = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/empty.png"));
			/*Image wallV = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/wallV.png"));
			Image wallH = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/wallH.png"));*/
			g.drawImage(background,0, 0, this.getWidth(), this.getHeight(),this);
			int xDepart = this.getWidth()/8;
			int yDepart = this.getHeight()/8;
			JButton bouton = new JButton(new ImageIcon(square));
			bouton.setSize(60, 60);
			bouton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					System.out.println("Hello World!");
					
				}
			});
			bouton.setEnabled(false);
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
							JButton bouton2 = new Case(new ImageIcon(square));
							bouton2.setSize(60, 60);
							bouton2.setLocation(xDepart, yDepart);
							this.add(bouton2);
						}
						else{
							JButton bouton2 = new Case(new ImageIcon(square2));
							bouton2.setSize(60, 60);
							bouton2.setLocation(xDepart, yDepart);
							bouton2.setPreferredSize(new Dimension(60,60));;
							this.add(bouton2);
						}
					}
					else{
						JButton bouton2 = new Case(new ImageIcon(square3));
						bouton2.setSize(60, 60);
						bouton2.setLocation(xDepart, yDepart);
						this.add(bouton2);
					}
					if (Board.tableau[i][j].isEdgeDownBegin()){
						g.setColor(Color.BLACK);
						g.fillRect(xDepart, yDepart+65, 140, 10);
					}
					if(Board.tableau[i][j].isEdgeLeftBegin()){
						g.setColor(Color.BLACK);
						g.fillRect(xDepart-15, yDepart, 10, 140);
					}
					if(Board.tableau[i][j].isEdgeRightBegin()){
						g.setColor(Color.BLACK);
						g.fillRect(xDepart +65, yDepart, 10, 140);
					}
					if(Board.tableau[i][j].isEdgeUpBegin()){
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
