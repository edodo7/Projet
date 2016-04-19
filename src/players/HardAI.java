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
		//plateau[x][y].setEmpty(false);
		//AIpath = new PathFinding(plateau[x][y],isFirstPlayer);
		nbresMurs = 10;
		tour1 = true;
		tour2 = false;
		tour3 = false;
		this.plateau = Board.getTableau();
		AIpath = new PathFinding(plateau[x][y],isFirstPlayer);
	}
	
	protected void move(int i,int j){
		plateau[x][y].setEmpty(true);
		x = i;
		y = j;
		plateau[x][y].setEmpty(false);
	}
	/**
	 * Cette méthode permet de bloquer le chemin de l'adversaire si c'est possible
	 * @param CasesPath L'ArrayList contenant le plus court chemin de l'adversaire
	 * @return true  si un mur a été placé et false sinon
	 */
	protected boolean blockPath(ArrayList<Case> CasesPath){
		Case lastCase = null;
		boolean sameLine = false;
		int casesSameLine = 0;
		int casesSameColumn = 0;
		for (int i = 0;i < CasesPath.size();i++){//La boucle parcours la liste de chemin de l'adversaire et regarde si à un moment il y a un changement de direction(changement de ligne ou de colonne)
			if (i == 0){
				lastCase = CasesPath.get(i);
			}
			else if (i == 1){
				if(CasesPath.get(i).getX() == lastCase.getX()){
					sameLine = true;
					casesSameLine++;
					lastCase = CasesPath.get(i);
				}
				else{
					sameLine = false;
					casesSameColumn++;
					lastCase = CasesPath.get(i);
				}
			}
			else{
				if (sameLine){
					if (CasesPath.get(i).getX() == lastCase.getX()){
						casesSameLine++;
						lastCase = CasesPath.get(i);
					}
					else{//Il y a un changement de direction sur le chemin de l'adversaire
						sameLine = false;
						casesSameColumn++;
						lastCase = CasesPath.get(i);
					}
				}
				else{
					if (CasesPath.get(i).getY() == lastCase.getY()){
						casesSameColumn++;
						lastCase = CasesPath.get(i);
					}
					else{//Il y a un changement de direction sur le chemin de l'adversaire
						sameLine = true;
						casesSameLine++;
						lastCase = CasesPath.get(i);
					}
				}
			}
		}
		if (casesSameColumn >= casesSameLine){//Il y a plus de cases sur la même ligne que de cases sur la même colonne,c'est donc plus contraignant pour l'adversaire de bloquer horizontaalement 
			for (int i = 1; i < CasesPath.size();i++){
				if (Rules.canPutWallDown(plateau[CasesPath.get(i).getX()][CasesPath.get(i).getY()]) && Rules.canReallyPutWallDown(plateau[CasesPath.get(i).getX()][CasesPath.get(i).getY()])){
					putWallDown(plateau[CasesPath.get(i).getX()][CasesPath.get(i).getY()]);
					//afficher l'endroit où on a placé un mur
					System.out.println("L'IA difficile a placé un mur en bas de la case en position ("+CasesPath.get(i).getX()+","+ CasesPath.get(i).getY()+")");
					return true;
				}
			}
			return false;
		}
		else{
			for (int i = 1; i < CasesPath.size();i++){
				if(Rules.canPutWallLeft(plateau[CasesPath.get(i).getX()][CasesPath.get(i).getY()])&& Rules.canReallyPutWallLeft(plateau[CasesPath.get(i).getX()][CasesPath.get(i).getY()])){
					putWallLeft(plateau[CasesPath.get(i).getX()][CasesPath.get(i).getY()]);
					System.out.println("L'IA difficile a placé un mur à gauche de la case en position ("+CasesPath.get(i).getX()+","+ CasesPath.get(i).getY()+")");
					return true;
				}
			}
			return false;
		}
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
				if (SecondPlayerListPath.size() < AIListPath.size()){
					if (blockPath(SecondPlayerListPath)){
						nbresMurs--;
					}
					else{
						if (AIListPath.get(1).getX() == joueur2.getX() && AIListPath.get(1).getY() == joueur2.getY()){
							move(AIListPath.get(2).getX(),AIListPath.get(2).getY());
						}
						else{
							move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
						}
					}
				}
				else{
					if (AIListPath.get(1).getX() == joueur2.getX() && AIListPath.get(1).getY() == joueur2.getY()){
						move(AIListPath.get(2).getX(),AIListPath.get(2).getY());
					}
					else{
						move(AIListPath.get(1).getX(),AIListPath.get(1).getY());
					}
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
