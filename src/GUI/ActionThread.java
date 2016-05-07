package GUI;

public class ActionThread implements Runnable {
	
	
	public void run(){
		WallListener.notDone = true;
		MoveListener.notDone = true;
		while(WallListener.notDone && MoveListener.notDone){
			System.out.println("");
		}
	}
}
