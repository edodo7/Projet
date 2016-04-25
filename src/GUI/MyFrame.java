package GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import board.Board;

public class MyFrame extends JFrame {
	public MyFrame() {
		this.setTitle("Fenêtre d'Eduardo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280,1280);
		this.setLocationRelativeTo(null);
		this.setContentPane(new MyPannel());
		this.setMinimumSize(new Dimension(1000,1000));
		this.setVisible(true);
	}
}
