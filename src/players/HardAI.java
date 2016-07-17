package players;

import java.io.IOException;
import java.util.ArrayList;

import board.Board;
import board.Case;
import rules.Rules;
import pathFinding.PathFinding;

/**
 * Cette classe mod�lise L'IA Difficile
 * @author Eduardo
 */
public class HardAI extends AGenericPlayer{
	
	public static AGenericPlayer joueur2;
	private PathFinding AIpath;
	private  PathFinding SecondPlayerpath;
	private boolean tour1;
	private boolean tour2;
	private boolean tour3;
	private boolean isFirstPlayer;
	private Case[][] plateau;
	//Je veux que pendant les 3 premiers tours de jeu L'IA essaye de placer un mur � l'adversaire
	
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
		tour1 = true;
		tour2 = false;
		tour3 = false;
		this.plateau = Board.getTableau();
		AIpath = new PathFinding(plateau[x][y],isFirstPlayer);
		walls = 10;
	}
	
	/**
	 * Permet a l'IA difficile de se deplacer vers une case
	 * @param j , la ligne sur laquelle se trouve la case 
	 * @param i  , la colonne sur laquelle se trouve la case
	 * @return Vrai 
	 */
	public boolean move(int i,int j){
		plateau[x][y].setEmpty(true);
		x = i;
		y = j;
		plateau[x][y].setEmpty(false);
		return true;
	}
	/**
	 * Cette m�thode permet de bloquer le plus court chemin de l'adversaire si c'est possible
	 * @param CasesPath L'ArrayList contenant le plus court chemin de l'adversaire
	 * @return true  si un mur a �t� plac� et false sinon
	 */
	public boolean blockPath(ArrayList<Case> CasesPath){
		Case lastCase;
		Case followingCase;
		for (int i = 0;i < CasesPath.size();i++){
			if (i != CasesPath.size() -1){
				lastCase = CasesPath.get(i);
				followingCase = CasesPath.get(i+1);
				if ((lastCase.getX() - followingCase.getX() == 0) && (lastCase.getY() - followingCase.getY() == -1)){//la case suivante se trouve � droite
					if(Rules.canPutWallRight(lastCase) && Rules.canReallyPutWallRight(lastCase)){
						putWallRight(lastCase);
						return true;
					}
				}
				else if ((lastCase.getX() - followingCase.getX() == 0) && (lastCase.getY() - followingCase.getY()== 1)){//la case se trouve � gauche
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
	/**
	 * Permet a l'IA de jouer
	 */
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
		if (walls > 0){
			if (tour1){
				if (blockPath(SecondPlayerListPath)){
					tour1 = false;
					tour2 = true;
					walls--;
				}
				else{
					move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
				}
			}
			else if (tour2){
				if(blockPath(SecondPlayerListPath)){
					tour2 = false;
					tour3 = true;
					walls--;
				}
				else{
					move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
				}
			}
			else if (tour3){
				if (blockPath(SecondPlayerListPath)){
					tour3 = false;
					walls--;
				}
				else{
					move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
				}
			}
			else{
				if (SecondPlayerListPath.size()+3< AIListPath.size()){
					if (blockPath(SecondPlayerListPath)){
						walls--;
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
		else{//Il n'y a plus de mur, on est oblig� de se d�placer
			if (AIListPath.get(1).getX() == joueur2.getX() && AIListPath.get(1).getY() == joueur2.getY()){
				move(AIListPath.get(2).getX(),AIListPath.get(2).getY());
			}
			else{
				move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
			}
		}
	}
}