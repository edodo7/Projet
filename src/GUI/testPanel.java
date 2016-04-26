package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class testPanel extends JPanel {
	Image bluePawn;
	Image redPawn;
	Image square;
	
	public testPanel(){
		try{
			bluePawn = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/blue.png"));
			redPawn = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/red.png"));
			square = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/empty.png"));
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
		for (int i = 0 ;i <= 80;i++){
			JButton bouton2 = new JButton(new ImageIcon(square));
			bouton2.setSize(60, 60);
			bouton2.setPreferredSize(new Dimension(60,60));;
			this.add(bouton2);
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}
