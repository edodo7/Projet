package GUI;

public class ActionThread implements Runnable {
	
	
	public void run(){
		WallListener.notDone = true;
		MoveListener.notDone = true;
		while(WallListener.notDone && MoveListener.notDone){
			try {
			    Thread.sleep(100);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
	}
}
