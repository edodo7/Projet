package players;
import java.io.IOException;
import java.util.ArrayList;

import board.Board;
import board.Case;
import mainAndRules.Rules;
import pathFinding.PathFindingFirstPlayer;
import pathFinding.PathFindingSecondPlayer;
/**
 * Cette classe modélise le comportement de l'IA Difficile en tant que premier joueur.
 * @author Eduardo
 *
 */
public class SecondHardAI extends FirstHardAI{
	public static AGenericPlayer joueur1;
	private PathFindingFirstPlayer FirstPlayerpath;
	
	public SecondHardAI(){
		this.x = 8;
		this.y = 4;
		plateau[x][y].setEmpty(false);
		AIpath = new PathFindingSecondPlayer(plateau[8][4]);
		nbresMurs = 10;
		tour1 = true;
		tour2 = false;
		tour3 = false;
		joueur1 = Board.getFirstPlayer();
	}	
	
	
	public void play() throws IOException{
		AIpath.setStartingCase(plateau[x][y]);
		FirstPlayerpath = new PathFindingFirstPlayer(plateau[joueur1.getX()][joueur1.getY()]);
		AIpath.isExit();
		FirstPlayerpath.isExit();
		AIpath.way();
		FirstPlayerpath.way();
		ArrayList<Case> AIListPath = AIpath.getWay();
		ArrayList<Case> FirstPlayerListPath = FirstPlayerpath.getWay();
		if (nbresMurs > 0){
			if (tour1){
				if (blockPath(FirstPlayerListPath)){
					tour1 = false;
					tour2 = true;
					nbresMurs--;
				}
				else{
					move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
				}
			}
			else if (tour2){
				if(blockPath(FirstPlayerListPath)){
					tour2 = false;
					tour3 = true;
					nbresMurs--;
				}
				else{
					move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
				}
			}
			else if (tour3){
				if (blockPath(FirstPlayerListPath)){
					tour3 = false;
					nbresMurs--;
				}
				else{
					move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
				}
			}
			else{
				if (FirstPlayerListPath.size() < AIListPath.size()){
					if (blockPath(FirstPlayerListPath)){
						nbresMurs--;
					}
					else{
						move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
					}
				}
				else{
					move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
				}
			}
		}
		else{//Il n'y a plus de mur, on est obligé de se déplacer
			move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
		}
	}
}
