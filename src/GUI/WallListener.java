package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WallListener implements ActionListener {   
	
	private int x;
	private int y;
	
	public WallListener(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("x : "+this.x+"\n"+"y : "+this.y);
	}

}
