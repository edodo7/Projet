package GUI;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import board.Board;
import players.AGenericPlayer;
import players.HumanPlayer;

import java.io.IOException;

public class Case extends JButton{
	public Case(Icon img){
		super(img);
		this.setSize(60, 60);
		this.setPreferredSize(new Dimension(60,60));
		this.setFocusPainted(false);
		this.setMargin(null);
		this.setBorder(BorderFactory.createEmptyBorder()); 
		this.setContentAreaFilled(false); 
	}
	
}
