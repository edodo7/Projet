package players;
/**
 * @author Eduardo
 * Cette classe modélise L'IA Difficile
 */
import java.io.IOException;
import java.util.ArrayList;

import board.Board;
import board.Case;
import mainAndRules.Rules;
import pathFinding.PathFinding;
import pathFinding.*;
public class HardAI extends AGenericPlayer{
	
	public static AGenericPlayer joueur2;
	private PathFinding AIpath;
	private  PathFinding SecondPlayerpath;
	private int nbresMurs;
	private boolean tour1;
	private boolean tour2;
	private boolean tour3;
	private boolean isFirstPlayer;
	private Case[][] plateau;
	//Je veux que pendant les 3 premiers tours de jeu L'IA essaye de placer un mur à l'adversaire
	
	public HardAI(boolean isFirstPlayer){
		this.isFirstPlayer = isFirstPlayer;
		if(isFirstPlayer){
			this.x = 0;
			this.y = 4;
		}
		else{
			this.x = 8;
			this.y = 4;
		}
		nbresMurs = 10;
		tour1 = true;
		tour2 = false;
		tour3 = false;
		this.plateau = Board.getTableau();
		AIpath = new PathFinding(plateau[x][y],isFirstPlayer);
	}
	
	public boolean move(int i,int j){
		plateau[x][y].setEmpty(true);
		x = i;
		y = j;
		plateau[x][y].setEmpty(false);
		return true;
	}
	/**
	 * Cette méthode permet de bloquer le chemin de l'adversaire si c'est possible
	 * @param CasesPath L'ArrayList contenant le plus court chemin de l'adversaire
	 * @return true  si un mur a été placé et false sinon
	 */
	public boolean blockPath(ArrayList<Case> CasesPath){
		Case lastCase;
		Case followingCase;
		for (int i = 0;i < CasesPath.size();i++){
			if (i != CasesPath.size() -1){
				lastCase = CasesPath.get(i);
				followingCase = CasesPath.get(i+1);
				if ((lastCase.getX() - followingCase.getX() == 0) && (lastCase.getY() - followingCase.getY()== -1)){//la case suivante se trouve à droite
					if(Rules.canPutWallRight(lastCase) && Rules.canReallyPutWallRight(lastCase)){
						putWallRight(lastCase);
						return true;
					}
				}
				else if ((lastCase.getX() - followingCase.getX() == 0) && (lastCase.getY() - followingCase.getY()== 1)){//la case se trouve à gauche
					if(Rules.canPutWallLeft(lastCase) && Rules.canReallyPutWallLeft(lastCase)){
						putWallLeft(lastCase);
						return true;
					}
				}
				else if((lastCase.getX() - followingCase.getX() == -1) && (lastCase.getY() - followingCase.getY()== 0)){//la case se trouve en bas
					if(Rules.canPutWallDown(lastCase) && Rules.canReallyPutWallDown(lastCase)){
						putWallDown(lastCase);
						return true;
					}	
				}
				else if((lastCase.getX() - followingCase.getX() == 1) && (lastCase.getY() - followingCase.getY()== 0)){
					if(Rules.canPutWallUp(lastCase) && Rules.canReallyPutWallUp(lastCase)){
						putWallUp(lastCase);
						return true;
					}	
				}
			}
		}
		return false;
	}
	
	public void play() throws IOException{
		joueur2 = Board.getSecondPlayer();
		AIpath.setStartingCase(plateau[x][y]);
		SecondPlayerpath = new PathFinding(plateau[joueur2.getX()][joueur2.getY()],!isFirstPlayer);
		AIpath.isExit();
		SecondPlayerpath.isExit();
		AIpath.way();
		SecondPlayerpath.way();
		ArrayList<Case> AIListPath = AIpath.getWay();
		ArrayList<Case> SecondPlayerListPath = SecondPlayerpath.getWay();
		if (nbresMurs > 0){
			if (tour1){
				if (blockPath(SecondPlayerListPath)){
					tour1 = false;
					tour2 = true;
					nbresMurs--;
				}
				else{
					move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
				}
			}
			else if (tour2){
				if(blockPath(SecondPlayerListPath)){
					tour2 = false;
					tour3 = true;
					nbresMurs--;
				}
				else{
					move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
				}
			}
			else if (tour3){
				if (blockPath(SecondPlayerListPath)){
					tour3 = false;
					nbresMurs--;
				}
				else{
					move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
				}
			}
			else{
				if (SecondPlayerListPath.size()< AIListPath.size()){
					if (blockPath(SecondPlayerListPath)){
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
			if (AIListPath.get(1).getX() == joueur2.getX() && AIListPath.get(1).getY() == joueur2.getY()){
				move(AIListPath.get(2).getX(),AIListPath.get(2).getY());
			}
			else{
				move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
			}
		}
	}
}
