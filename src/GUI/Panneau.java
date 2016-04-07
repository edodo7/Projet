package GUI;
import java.awt.*;
import javax.swing.*;
public class Panneau extends JPanel{
	public void paintComponent(Graphics g){
		System.out.println("hauteur : "+this.getWidth()+"\nlargeur : "+this.getHeight());
	}
}
