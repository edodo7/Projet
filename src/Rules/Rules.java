package rules;

import board.Board;
import board.Case;
import pathFinding.PathFinding;

import players.AGenericPlayer;
import java.util.ArrayList;
/**
 * Cette classe regroupe un ensemble de methodes permettant de dire ce qu'on a le droit de faire ou pas
 * @author Eduardo Dom
 *
 */
public class Rules{
		public static Case[][] tableau = Board.getTableau();
		public static AGenericPlayer joueur1= Board.getFirstPlayer();
		public static AGenericPlayer joueur2= Board.getSecondPlayer();
		private static PathFinding pathFirstPlayer = new PathFinding(tableau[joueur1.getX()][joueur1.getY()],true);
		private static PathFinding pathSecondPlayer = new PathFinding(tableau[joueur2.getX()][joueur2.getY()],false);
		
		
		/**
		 * Permet de dire si on peut se deplacer d'une case vers une autre
		 * @param case1 ,la case de depart
		 * @param case2 , la case d'arrivee
		 * @return Vrai si on peut se deplacer depuis la case de depart vers la case d'arrivee,Faux sinon
		 */
		public static  boolean canMove(Case case1,Case case2){
				if (case1.getX() - case2.getX() == 1 && case1.getY() - case2.getY() == 0 ){//On regarde si la case2 est en haut de la case1
						return !(case2.isEdgeDownBegin() || case2.isEdgeDownEnd()|| !case2.isEmpty());
				}
				else if (case1.getX() - case2.getX() == -1 && case1.getY() - case2.getY() == 0 ){//On regarde si la case2 est en bas de la case1
						return !(case2.isEdgeUpBegin() || case2.isEdgeUpEnd()|| !case2.isEmpty());
				}
				else if (case1.getX() - case2.getX() == 0 && case1.getY() - case2.getY() == -1 ){//On regarde si la case2 est � droite de la case1
						return !(case2.isEdgeLeftBegin() || case2.isEdgeLeftEnd()|| !case2.isEmpty());
				}
				else if (case1.getX() - case2.getX() == 0 && case1.getY() - case2.getY() == 1){//On regarde si la case2 est � gauche de la case1
					 return !(case2.isEdgeRightBegin() || case2.isEdgeRightEnd()|| !case2.isEmpty());
				}
				else{//Sinon il faut voir si on peut atteindre la case2 en sautant le pion adverse ou en allant en diagonale
						if(case1.getX()-1 == case2.getX() && case1.getY()+1 == case2.getY()){//Diagonale Haut/Droite
							 return ( !case2.isEdgeLeftBegin() &&
												!case2.isEdgeLeftEnd()   &&
												!case2.isEdgeDownBegin() &&
												!case2.isEdgeDownEnd()   &&
												!tableau[case1.getX()-1][case1.getY()].isEmpty() );
						}
						else if(case1.getX()-1 == case2.getX() && case1.getY() -1 == case2.getY()){//Diagonale Haut/Gauche
								return ( !case2.isEdgeRightBegin() &&
												 !case2.isEdgeRightEnd()   &&
												 !case2.isEdgeDownBegin()  &&
												 !case2.isEdgeDownEnd()    &&
												 !tableau[case1.getX()-1][case1.getY()].isEmpty() );
						}
						else if(case1.getX()+1 == case2.getX() && case1.getY() -1 == case2.getY()){//Diagonale Bas/Gauche
								return ( !case2.isEdgeRightBegin() &&
												 !case2.isEdgeRightEnd()   &&
												 !case2.isEdgeUpBegin()    &&
												 !case2.isEdgeUpEnd()      &&
												 !tableau[case1.getX()+1][case1.getY()].isEmpty() );
						}
						else if(case1.getX()+1 == case2.getX() && case1.getY() +1 == case2.getY()) {//Diagonale Bas/Droite
								return (  !case2.isEdgeLeftBegin() &&
													!case2.isEdgeLeftEnd()   &&
													!case2.isEdgeUpBegin()   &&
													!case2.isEdgeUpEnd()     &&
													!tableau[case1.getX()+1][case1.getY()].isEmpty() );
						}
						else if(case1.getX()+2 == case2.getX() && case1.getY() == case2.getY()){//On tente de sauter un pion vers le bas
							return ( !tableau[case1.getX()+1][case1.getY()].isEdgeUpBegin()   &&
												 !tableau[case1.getX()+1][case1.getY()].isEdgeUpEnd()     &&
												 !tableau[case1.getX()+1][case1.getY()].isEdgeDownBegin() &&
												 !tableau[case1.getX()+1][case1.getY()].isEdgeDownEnd()   &&
												 !tableau[case1.getX()+1][case1.getY()].isEmpty() );
						}
						else if(case1.getX()-2 == case2.getX() && case1.getY() == case2.getY()){//On tente de sauter un pion vers le haut
							return ( !tableau[case1.getX()-1][case1.getY()].isEdgeUpBegin()   &&
												 !tableau[case1.getX()-1][case1.getY()].isEdgeUpEnd()     &&
												 !tableau[case1.getX()-1][case1.getY()].isEdgeDownBegin() &&
												 !tableau[case1.getX()-1][case1.getY()].isEdgeDownEnd()   &&
												 !tableau[case1.getX()-1][case1.getY()].isEmpty() );
						}
						else if(case1.getX() == case2.getX() && case1.getY()+2 == case2.getY()){//On tente de sauter un  pion vers la droite 
							return ( !tableau[case1.getX()][case1.getY()+1].isEdgeLeftBegin()  &&
												 !tableau[case1.getX()][case1.getY()+1].isEdgeLeftEnd()    &&
												 !tableau[case1.getX()][case1.getY()+1].isEdgeRightBegin() &&
												 !tableau[case1.getX()][case1.getY()+1].isEdgeRightEnd()   &&
												 !tableau[case1.getX()][case1.getY()+1].isEmpty() );
						}
						else if(case1.getX() == case2.getX() && case1.getY()-2 == case2.getY()){//On tente de sauter un pion vers la gauche 
							return ( !tableau[case1.getX()][case1.getY()-1].isEdgeLeftBegin()  &&
												 !tableau[case1.getX()][case1.getY()-1].isEdgeLeftEnd()    &&
												 !tableau[case1.getX()][case1.getY()-1].isEdgeRightBegin() &&
												 !tableau[case1.getX()][case1.getY()-1].isEdgeRightEnd()   &&
												 !tableau[case1.getX()][case1.getY()-1].isEmpty() );
						}
						else{
							return false;
						}
				}
		}
		/**
		 * Permet de savoir si on peut placer un mur a droite d'une case sans tenir compte du chemin
		 * @param case1  
		 * @return Vrai si on peut poser un mur a droite,Faux sinon
		 */
		public static boolean canPutWallRight(Case case1){
				int i = case1.getX();
				int j = case1.getY();
				if (i !=8){
						return !( case1.isEdgeRightBegin()  ||
											case1.isEdgeRightEnd()    ||
											case1.isEdgeDownBegin()   ||
											case1.getY() == 8         ||
											tableau[i+1][j].isEdgeRightBegin() );
				}
				else{
						return !( case1.isEdgeRightEnd()   ||
											case1.isEdgeUpBegin()    ||
											case1.getY() == 8        ||
											tableau[i-1][j].isEdgeRightEnd() );
				}
		}
		/**
		 * Donne tout les cases voisines atteignables depuis une case de depart
		 * @param case1 , la case de depart
		 * @return Une ArrayList contenant toutes les cas atteignables
		 */
		public static ArrayList<Case> canMove(Case case1){
				int x = case1.getX();
				int y = case1.getY();
				ArrayList<Case> testAl = new ArrayList<Case>();
				if (x == 0 && y == 0){//Coin sup�rieur gauche
						testAl.add(tableau[x][y+1]);
						testAl.add(tableau[x+1][y]);
						testAl.add(tableau[x+1][y+1]);
						testAl.add(tableau[x][y+2]);
						testAl.add(tableau[x+2][y]);
				}
				else if(x == 0 && y != 0){//Si on se trouve su la premi�re ligne
						if(y != 8){
								testAl.add(tableau[x][y-1]);
								testAl.add(tableau[x][y+1]);
								testAl.add(tableau[x+1][y]);
								testAl.add(tableau[x+1][y-1]);
								testAl.add(tableau[x+1][y+1]);
								testAl.add(tableau[x+2][y]);
								if (y-2 >= 0){
									testAl.add(tableau[x][y-2]);
								}
								if(y+2 <9){
									testAl.add(tableau[x][y+2]);
								}
						}
						else{//Coin sup�rieur droit
								testAl.add(tableau[x][y-1]);
								testAl.add(tableau[x+1][y]);
								testAl.add(tableau[x+1][y-1]);
								testAl.add(tableau[x][y-2]);
								testAl.add(tableau[x+2][y]);
						}
				}
				else if(x == 8 && y == 8){//Coin inf�rieur droit
						testAl.add(tableau[x][y-1]);
						testAl.add(tableau[x-1][y]);
						testAl.add(tableau[x-1][y-1]);
						testAl.add(tableau[x-2][y]);
						testAl.add(tableau[x][y-2]);
				}
				else if(x == 8 && y != 8){
						if (y == 0){//Coin inf�rieur gauche
								testAl.add(tableau[x-1][y]);
								testAl.add(tableau[x][y+1]);
								testAl.add(tableau[x-1][y+1]);
								testAl.add(tableau[x-2][y]);
								testAl.add(tableau[x][y+2]);
						}
						else{//On est sur la derni�re ligne
								testAl.add(tableau[x][y-1]);
								testAl.add(tableau[x-1][y]);
								testAl.add(tableau[x][y+1]);
								testAl.add(tableau[x-1][y-1]);
								testAl.add(tableau[x-1][y+1]);
								testAl.add(tableau[x-2][y]);
								if( y - 2 >= 0){
									testAl.add(tableau[x][y-2]);
								}
								if(y+2 < 9){
									testAl.add(tableau[x][y+2]);
								}
						}
				}
				else if(y == 0 && x != 0){
					if(x==8){//Coin inf�rieur gauche
						testAl.add(tableau[x-1][y]);
						testAl.add(tableau[x][y+1]); 
						testAl.add(tableau[x-1][y+1]);
						testAl.add(tableau[x-2][y]);
								testAl.add(tableau[x][y+2]);
					}
					else{
						testAl.add(tableau[x-1][y]);
						testAl.add(tableau[x+1][y]);
						testAl.add(tableau[x][y+1]);
						testAl.add(tableau[x-1][y+1]);
						testAl.add(tableau[x+1][y+1]);
						testAl.add(tableau[x][y+2]);
						if (x-2 >= 0){
							testAl.add(tableau[x-2][y]);
						}
						if (x+2 < 9){
							testAl.add(tableau[x+2][y]);
						}
					}
				}
				else if(y == 8){
					if (x == 0){
						testAl.add(tableau[x][y-1]);
						testAl.add(tableau[x+1][y]);
						testAl.add(tableau[x+1][y-1]);
						testAl.add(tableau[x][y-2]);
								testAl.add(tableau[x+2][y]);
					}
					else{
						testAl.add(tableau[x-1][y]);
						testAl.add(tableau[x+1][y]);
						testAl.add(tableau[x][y-1]);
						testAl.add(tableau[x][y-2]);
						if(x- 2 >= 0){
							testAl.add(tableau[x-2][y]);
						}
						if(x+2 < 9){
							testAl.add(tableau[x+2][y]);
						}
					}
				}
				else{
						testAl.add(tableau[x][y-1]);
						testAl.add(tableau[x-1][y]);
						testAl.add(tableau[x][y+1]);
						testAl.add(tableau[x+1][y]);
						testAl.add(tableau[x+1][y-1]);
						testAl.add(tableau[x+1][y+1]);
						testAl.add(tableau[x-1][y-1]);
						testAl.add(tableau[x-1][y+1]);
						if(x- 2 >= 0){
					testAl.add(tableau[x-2][y]);
				}
				if(x+2 < 9){
					testAl.add(tableau[x+2][y]);
				}
				if( y - 2 >= 0){
							testAl.add(tableau[x][y-2]);
						}
						if(y+2 < 9){
							testAl.add(tableau[x][y+2]);
						}
				}
				ArrayList<Case> realCase = new ArrayList<Case>();
				for (int i = 0; i < testAl.size();i++){
						if(Rules.canMove(tableau[x][y], testAl.get(i))){
								realCase.add(testAl.get(i));
						}
				}
				return realCase;
		}
		
		/**
		 * Supprime le mur a droite de la case
		 * @param case1
		 */
		protected static void eraseWallRight(Case case1){
				int i = case1.getX();
				int j = case1.getY();
				if (j != 8 && i != 8){
						tableau[i][j].setEdgeRightBegin(false);
						tableau[i+1][j].setEdgeRightEnd(false);
						tableau[i][j+1].setEdgeLeftBegin(false);
						tableau[i+1][j+1].setEdgeLeftEnd(false);
				}
				else{
						tableau[i][j].setEdgeRightEnd(false);
						tableau[i-1][j].setEdgeRightBegin(false);
						tableau[i][j+1].setEdgeLeftEnd(false);
						tableau[i-1][j-1].setEdgeLeftBegin(false);
				}
		}
		
		/**
		 * Permet de savoir si on peut placer un mur a droite d'une case sans bloquer le chemin
		 * @param case1
		 * @return Vrai si peut placer un mur a droite d'une case sans bloquer le chemin,Faux sinon
		 */
		public static boolean canReallyPutWallRight(Case case1){
				pathFirstPlayer.setStartingCase(tableau[joueur1.getX()][joueur1.getY()]);
				pathSecondPlayer.setStartingCase(tableau[joueur2.getX()][joueur2.getY()]);
				joueur1.putWallRight(case1);
				if (pathFirstPlayer.isExit() && pathSecondPlayer.isExit()){
						Rules.eraseWallRight(case1);
						return true;
				}
				else{
						Rules.eraseWallRight(case1);
						return false;
				}
		}
		
		/**
		 * Permet de savoir si on peut placer un mur a gauche d'une case sans tenir compte du chemin
		 * @param case1  
		 * @return Vrai si on peut poser un mur a gauche,Faux sinon
		 */
		public static boolean canPutWallLeft(Case case1){
				int i = case1.getX();
				int j = case1.getY();
				if (i != 8){
						return !(case1.isEdgeLeftBegin()  ||
										 case1.isEdgeLeftEnd()    ||
										 case1.isEdgeDownEnd()    ||
										 case1.getY() == 0        ||
										 tableau[i+1][j].isEdgeLeftBegin());
				}
				else{
						return !( case1.isEdgeLeftEnd() ||
											case1.isEdgeUpEnd()   ||
											case1.getY() == 0     ||
											tableau[i-1][j].isEdgeLeftEnd());
				}
		}
		/**
		 * Supprime le mur a gauche de la case
		 * @param case1
		 */
		protected static void eraseWallLeft(Case case1){
				int i = case1.getX();
				int j = case1.getY();
				if (j != 0 && i != 8){
						tableau[i][j].setEdgeLeftBegin(false);
						tableau[i+1][j].setEdgeLeftEnd(false);
						tableau[i][j-1].setEdgeRightBegin(false);
						tableau[i+1][j-1].setEdgeRightEnd(false);
				}
				else{
						tableau[i][j].setEdgeLeftEnd(false);
						tableau[i-1][j].setEdgeLeftBegin(false);
						tableau[i][j-1].setEdgeRightEnd(false);
						tableau[i-1][j-1].setEdgeRightBegin(false);
				}
		}
		
		/**
		 * Permet de savoir si on peut placer un mur a gauche d'une case sans bloquer le chemin
		 * @param case1
		 * @return Vrai si peut placer un mur a gauche d'une case sans bloquer le chemin,Faux sinon
		 */
		public static boolean canReallyPutWallLeft(Case case1){
				pathFirstPlayer.setStartingCase(tableau[joueur1.getX()][joueur1.getY()]);
				pathSecondPlayer.setStartingCase(tableau[joueur2.getX()][joueur2.getY()]);
				joueur1.putWallLeft(case1); 
				if (pathFirstPlayer.isExit() && pathSecondPlayer.isExit()){
						Rules.eraseWallLeft(case1);
						return true;
				}
				else{
						Rules.eraseWallLeft(case1);
						return false;
				}
		}
		
		/**
		 * Permet de savoir si on peut placer un mur en haut d'une case sans tenir compte du chemin
		 * @param case1  
		 * @return Vrai si on peut poser un mur en haut,Faux sinon
		 */
		public static boolean canPutWallUp(Case case1){
				int i = case1.getX();
				int j = case1.getY();
				if (j != 8){
						return !(case1.isEdgeUpBegin() || case1.isEdgeUpEnd() || case1.getX() == 0 || tableau[i][j+1].isEdgeUpBegin()|| case1.isEdgeRightEnd());
				}
				else{
						return !(case1.isEdgeUpEnd() || case1.getX() == 0 || tableau[i][j-1].isEdgeUpEnd()|| case1.isEdgeLeftEnd());
				}
		}
		/**
		 * Supprime le mur en haut de la case
		 * @param case1
		 */
		protected static void eraseWallUp(Case case1){
				int i = case1.getX();
				int j = case1.getY();
				if ( i != 0 && j != 8){
						tableau[i][j].setEdgeUpBegin(false);
						tableau[i][j+1].setEdgeUpEnd(false);
						tableau[i-1][j].setEdgeDownBegin(false);
						tableau[i-1][j+1].setEdgeDownEnd(false);
				}
				else{
						tableau[i][j].setEdgeUpEnd(false);
						tableau[i][j-1].setEdgeUpBegin(false);
						tableau[i-1][j].setEdgeDownEnd(false);
						tableau[i-1][j-1].setEdgeDownBegin(false);
				}
		}
		/**
		 * Permet de savoir si on peut placer un mur en haut d'une case sans bloquer le chemin
		 * @param case1
		 * @return Vrai si peut placer un mur en haut d'une case sans bloquer le chemin,Faux sinon
		 */
		public static boolean canReallyPutWallUp(Case case1){
				pathFirstPlayer.setStartingCase(tableau[joueur1.getX()][joueur1.getY()]);
				pathSecondPlayer.setStartingCase(tableau[joueur2.getX()][joueur2.getY()]);
				joueur1.putWallUp(case1);   
				if (pathFirstPlayer.isExit() && pathSecondPlayer.isExit()){
						Rules.eraseWallUp(case1);
						return true;
				}
				else{
						Rules.eraseWallUp(case1);
						return false;
				}
		}
		
		/**
		 * Permet de savoir si on peut placer un mur en bas d'une case sans tenir compte du chemin
		 * @param case1  
		 * @return Vrai si on peut poser un mur en bas,Faux sinon
		 */

		public static boolean canPutWallDown(Case case1){
				int i = case1.getX();
				int j = case1.getY();
				if (j != 8){
						return !( case1.isEdgeDownBegin()           ||
											case1.isEdgeDownEnd()             ||
											case1.getX() == 8                 ||
											tableau[i][j+1].isEdgeDownBegin() ||
											case1.isEdgeRightBegin() );
				}
				else{
						return  !( case1.isEdgeDownEnd()           ||
											 case1.getX() == 8               ||
											 tableau[i][j-1].isEdgeDownEnd() ||
											 case1.isEdgeLeftBegin() );
				}
		}
		
		/**
		 * Supprimme le mur en bas de la case
		 * @param case1
		 */
		protected static void eraseWallDown(Case case1){
				int i = case1.getX();
				int j = case1.getY();
				if (i != 8 && j != 8){
						tableau[i][j].setEdgeDownBegin(false);
						tableau[i][j+1].setEdgeDownEnd(false);
						tableau[i+1][j].setEdgeUpBegin(false);
						tableau[i+1][j+1].setEdgeUpEnd(false);
				}
				else{
						tableau[i][j].setEdgeDownEnd(false);
						tableau[i][j-1].setEdgeDownBegin(false);
						tableau[i+1][j].setEdgeUpEnd(false);
						tableau[i+1][j-1].setEdgeUpBegin(false);
				}
		}
		
		/**
		 * Permet de savoir si on peut placer un mur en bas d'une case sans bloquer le chemin
		 * @param case1
		 * @return Vrai si peut placer un mur en bas d'une case sans bloquer le chemin,Faux sinon
		 */
		public static boolean canReallyPutWallDown(Case case1){
				pathFirstPlayer.setStartingCase(tableau[joueur1.getX()][joueur1.getY()]);
				pathSecondPlayer.setStartingCase(tableau[joueur2.getX()][joueur2.getY()]);
				joueur1.putWallDown(case1); 
				if (pathFirstPlayer.isExit() && pathSecondPlayer.isExit()){
						Rules.eraseWallDown(case1);
						return true;
				}
				else{
						Rules.eraseWallDown(case1);
						return false;
				}
		}
		
		/**
		 * Permet de savoir si le joueur peut se deplacer a gauche 
		 * @param joueur
		 * @return Vrai si le joueur peut se deplacer a gauche,Faux sinon
		 */
		public static boolean canMoveLeft(AGenericPlayer joueur){
				int x = joueur.getX();
				int y = joueur.getY();
				return !(y == 0                             ||
								 tableau[x][y-1].isEdgeRightBegin() ||
								 tableau[x][y-1].isEdgeRightEnd()   ||
								 !tableau[x][y-1].isEmpty());
		}
		
		/**
		 * Permet de savoir si le joueur peut se deplacer a droite
		 * @param joueur
		 * @return Vrai si le joueur peut se deplacer a droite,Faux sinon
		 */
		public static boolean canMoveRight(AGenericPlayer joueur){
				int x = joueur.getX();
				int y = joueur.getY();
				return !( y == 8                            ||
									tableau[x][y+1].isEdgeLeftBegin() ||
									tableau[x][y+1].isEdgeLeftEnd()   ||
								 !tableau[x][y+1].isEmpty() );
		}
		
		/**
		 * Permet de savoir si le joueur peut se deplacer en haut
		 * @param joueur
		 * @return Vrai si le joueur peut se deplacer en haut,Faux sinon
		 */
		public static boolean canMoveUp(AGenericPlayer joueur){
				int x = joueur.getX();
				int y = joueur.getY();
				return !( x == 0                            ||
									tableau[x-1][y].isEdgeDownBegin() ||
									tableau[x-1][y].isEdgeDownEnd()   ||
								 !tableau[x-1][y].isEmpty() );
		}
		
		/**
		 * Permet de savoir si le joueur peut se deplacer en bas
		 * @param joueur
		 * @return Vrai si le joueur peut se deplacer en bas,Faux sinon
		 */
		public static boolean canMoveDown(AGenericPlayer joueur){
				int x = joueur.getX();
				int y = joueur.getY();
				return !( x == 8                          ||
									tableau[x+1][y].isEdgeUpBegin() ||
									tableau[x+1][y].isEdgeUpEnd()   ||
								 !tableau[x+1][y].isEmpty() );
		}
}
