package players;
import java.io.IOException;
public interface IPlayer {
	void play()throws IOException;
	int getX();
	int getY();
}
