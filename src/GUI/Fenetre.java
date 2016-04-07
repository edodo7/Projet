package GUI;
import java.awt.*;
import javax.swing.*;
public class Fenetre extends JFrame {
	public Fenetre(){
		this.setTitle("Fenêtre d'Eduardo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280,720);
		this.setLocationRelativeTo(null);
		this.setContentPane(new Panneau());
		this.setVisible(true);
	}
}
