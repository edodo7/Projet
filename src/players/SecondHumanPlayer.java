package players;
/**
 * 
 * @author Eduardo Dom
 *
 */
public class SecondHumanPlayer extends FirstHumanPlayer {
	public SecondHumanPlayer(){
		plateau[8][4].setEmpty(false);
		this.x = 8;
		this.y = 4;
	}
}