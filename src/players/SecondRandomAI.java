package players;
import java.io.IOException;
import java.util.Random;

import mainAndRules.Rules;

/**
 * 
 * @author Eduardo Dom
 *
 */
public class SecondRandomAI extends FirstRandomAI{	
	public SecondRandomAI(){
		this.x = 8;
		this.y = 4;
		plateau[x][y].setEmpty(false);
		nbresMurs = 10;
	}
	
	public void play()throws IOException{
		Random whatChoice = new Random();
		int WallOrMove = whatChoice.nextInt(2);
		if (WallOrMove == 0 && nbresMurs > 0){//on place un mur 
			boolean not_done = true;
			while(not_done){
				int wall = whatChoice.nextInt(4);
				int i = whatChoice.nextInt(7)+1;
				int j = whatChoice.nextInt(7)+1;
				if (wall == 0 && Rules.canPutWallUp(plateau[i][j]) && Rules.canReallyPutWallUp(plateau[i][j])){
					putWallUp(plateau[i][j]);
					System.out.println("L'IA facile a pos� un mur au dessus de la case en position ("+i+","+j+")");
					nbresMurs--;
					not_done = false;
				}
				if (wall == 1 && Rules.canPutWallDown(plateau[i][j])&& Rules.canReallyPutWallDown(plateau[i][j])){
					putWallDown(plateau[i][j]);
					System.out.println("L'IA facile a pos� un mur en dessous de la case en position ("+i+","+j+")");
					nbresMurs--;
					not_done = false;
				}
				if (wall == 2 && Rules.canPutWallRight(plateau[i][j])&& Rules.canReallyPutWallRight(plateau[i][j])){
					putWallRight(plateau[i][j]);
					System.out.println("L'IA facile a pos� un mur � droite de la case en position ("+i+","+j+")");
					nbresMurs--;
					not_done = false;
				}
				if (wall == 3 && Rules.canPutWallLeft(plateau[i][j])&& Rules.canReallyPutWallLeft(plateau[i][j])){
					putWallLeft(plateau[i][j]);
					System.out.println("L'IA facile a pos� un mur � gauche de la case en position ("+i+","+j+")");
					nbresMurs--;
					not_done = false;
				}
			}
		}
		else{
			boolean not_done = true;
			while(not_done){
				int whatDirection = whatChoice.nextInt(4);
				if (whatDirection == 0 && Rules.canMoveUp(this)){
					moveUp();
					not_done = false;
				}
				if (whatDirection == 1 && Rules.canMoveDown(this)){
					moveDown();
					not_done = false;
				}
				if (whatDirection == 2 && Rules.canMoveLeft(this)){
					moveLeft();
					not_done = false;
				}
				if(whatDirection == 3 && Rules.canMoveRight(this)){
					moveRight();
					not_done = false;
				}
			}
		}
	}
}
