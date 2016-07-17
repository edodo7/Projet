package gui;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Fond d'ecran lors d'une partie
 * @author Eduardo
 *
 */
public class MyPannel extends JPanel {
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		try{
			Image background = ImageIO.read(new File("./ressources/vador.jpg"));
			g.drawImage(background,0, 0, this.getWidth(), this.getHeight(),this);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
