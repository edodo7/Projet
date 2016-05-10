package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Start extends JPanel {
	
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLUE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        g.setFont(new Font("Accueil",Font.BOLD,50));
        g.drawString("Bienvenue dans le magnifique Quoridor d'Eduardo", this.getWidth()/9,this.getHeight()/3);
	}
}
