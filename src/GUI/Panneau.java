package GUI;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class Panneau extends JPanel{
	public void paintComponent(Graphics g){
		try{
			Image background = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/vault boy.jpg"));
			Image square = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/blue.png"));
			Image square2 = ImageIO.read(new File("C:/Users/Eduardo/Desktop/ressources/maj.png"));
			g.drawImage(background,0, 0, this.getWidth(), this.getHeight(),this);
			g.drawImage(square,20,20,this);
			g.drawImage(square2,75,75,this);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
