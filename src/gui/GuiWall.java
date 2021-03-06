package gui;


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

/**
 * Boutons permettant de poser des murs sur le plateau graphique
 * @author Eduardo
 */
public class GuiWall extends JButton{

	private static Case[][] realTab = Board.getTableau();
	private int x;
	private int y;
	private boolean isVertical;
	
	public GuiWall(int x ,int y, boolean isVertical){
		this.x = x;
		this.y = y;
		this.isVertical = isVertical;
		if (isVertical){
			this.setPreferredSize(new Dimension(15,90));
		}
		else{
			this.setPreferredSize(new Dimension(90,15));
		}
		this.setEnabled(true);
		this.setBackground(new Color(0,0,0,0));
		this.setFocusPainted(false);
		this.setMargin(null);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.addActionListener(new WallListener(this.x,this.y,isVertical));
		this.setVisible(true);
	}
}
