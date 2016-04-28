package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import board.Board;

public class testPanel extends JPanel {
	
	Image bluePawn;
	Image redPawn;
	Image square;
	Case[][] tabCases = new Case[9][9];
	board.Case[][] realTab = Board.getTableau();
	
	
	public testPanel(){
		try{
			bluePawn = ImageIO.read(new File("/home/umons.ac.be/161974/Projet/ressources/blue.png"));
			redPawn = ImageIO.read(new File("/home/umons.ac.be/161974/Projet/ressources/red.png"));
			square = ImageIO.read(new File("/home/umons.ac.be/161974/Projet/ressources/empty.png"));
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
				Case bouton =new Case(new ImageIcon(square));
				tabCases[i][j] =bouton;
				this.add(bouton);
			}
		}
		this.setPreferredSize(new Dimension(850,700));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i =0;i < 9;i++){
			for (int j = 0 ; j < 9;j++){
				if (!realTab[i][j].isEmpty()){
					System.out.println("Je suis occupe");
					if (board.Board.getFirstPlayer().getX() == i && board.Board.getFirstPlayer().getY()== j){
						tabCases[i][j].setIcon(new ImageIcon(redPawn));
					}
					else{
						tabCases[i][j].setIcon(new ImageIcon(bluePawn));
					}
				}
				else{
					tabCases[i][j].setIcon(new ImageIcon(square));
				}
			}
		}
	}
}
