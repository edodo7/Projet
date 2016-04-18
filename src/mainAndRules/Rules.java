package mainAndRules;

import board.Board;
import board.Case;
import pathFinding.PathFinding;

import players.AGenericPlayer;
import java.util.ArrayList;
/**
 * Cette classe regroupe un ensemble de méthodes permettant de dire ce qu'on a le droit de faire ou pas
 * @author Eduardo Dom
 *
 */
public class Rules{
    public static Case[][] tableau = Board.getTableau();
    public static AGenericPlayer joueur1= Board.getFirstPlayer();
    public static AGenericPlayer joueur2= Board.getSecondPlayer();
    private static PathFinding pathFirstPlayer = new PathFinding(tableau[joueur1.getX()][joueur1.getY()],true);
    private static PathFinding pathSecondPlayer = new PathFinding(tableau[joueur2.getX()][joueur2.getY()],false);
    
    
    
    public static  boolean canMove(Case case1,Case case2){
        if (case1.getX() - case2.getX() == 1 && case1.getY() - case2.getY() == 0 ){//On regarde si la case2 est en haut de la case1
            if (case2.isEdgeDownBegin() || case2.isEdgeDownEnd()){
                return false;
            }
            else{
                return true;
            }
        }
        else if (case1.getX() - case2.getX() == -1 && case1.getY() - case2.getY() == 0 ){//On regarde si la case2 est en bas de la case1
            if(case2.isEdgeUpBegin() || case2.isEdgeUpEnd()){
                return false;
            }
            else{
                return true;
            }
        }
        else if (case1.getX() - case2.getX() == 0 && case1.getY() - case2.getY() == -1 ){//On regarde si la case2 est à droite de la case1
            if (case2.isEdgeLeftBegin() || case2.isEdgeLeftEnd()){
                return false;
            }
            else{
                return true;
            }
        }
        else if (case1.getX() - case2.getX() == 0 && case1.getY() - case2.getY() == 1){//On regarde si la case2 est à gauche de la case1
            if (case2.isEdgeRightBegin() || case2.isEdgeRightEnd()){
                return false;
            }
            else{
                return true;
            }
        }
        else{//Sinon il faut voir si on peut atteindre la case2 en sautant le pion adverse ou en allant en diagonale
            if(case1.getX()-1 == case2.getX() && case1.getY()+1 == case2.getY()){//Diagonale Haut/Droite
                if(!case2.isEdgeLeftBegin() && !case2.isEdgeLeftEnd() && !case2.isEdgeDownBegin() && !case2.isEdgeDownEnd() && !tableau[case1.getX()-1][case1.getY()].isEmpty()){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if(case1.getX()-1 == case2.getX() && case1.getY() -1 == case2.getY()){//Diagonale Haut/Gauche
                if(!case2.isEdgeRightBegin() && !case2.isEdgeRightEnd() && !case2.isEdgeDownBegin() && !case2.isEdgeDownEnd() && !tableau[case1.getX()-1][case1.getY()].isEmpty()){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if(case1.getX()+1 == case2.getX() && case1.getY() -1 == case2.getY()){//Diagonale Bas/Gauche
                if(!case2.isEdgeRightBegin() && !case2.isEdgeRightEnd() && !case2.isEdgeUpBegin() && !case2.isEdgeUpEnd() && !tableau[case1.getX()+1][case1.getY()].isEmpty()){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if(case1.getX()+1 == case2.getX() && case1.getY() +1 == case2.getY()) {//Diagonale Bas/Droite
                if (!case2.isEdgeLeftBegin() && !case2.isEdgeLeftEnd() && !case2.isEdgeUpBegin() && !case2.isEdgeUpEnd() && !tableau[case1.getX()+1][case1.getY()].isEmpty()){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if(case1.getX()+2 == case2.getX() && case1.getY() == case2.getY()){//On tente de sauter un pion vers le bas
            	if(!tableau[case1.getX()+1][case1.getY()].isEdgeUpBegin() && !tableau[case1.getX()+1][case1.getY()].isEdgeUpEnd() && !tableau[case1.getX()+1][case1.getY()].isEdgeDownBegin() && !tableau[case1.getX()+1][case1.getY()].isEdgeDownEnd() && !tableau[case1.getX()+1][case1.getY()].isEmpty()){
            		return true;
            	}
            	else{
            		return false;
            	}
            }
            else if(case1.getX()-2 == case2.getX() && case1.getY() == case2.getY()){//On tente de sauter un pion vers le haut
            	if(!tableau[case1.getX()-1][case1.getY()].isEdgeUpBegin() && !tableau[case1.getX()-1][case1.getY()].isEdgeUpEnd() && !tableau[case1.getX()-1][case1.getY()].isEdgeDownBegin() && !tableau[case1.getX()-1][case1.getY()].isEdgeDownEnd() && !tableau[case1.getX()-1][case1.getY()].isEmpty()){
            		return true;
            	}
            	else{
            		return false;
            	}
            }
            else if(case1.getX() == case2.getX() && case1.getY()+2 == case2.getY()){//On tente de sauter un  pion vers la droite 
            	if(!tableau[case1.getX()][case1.getY()+1].isEdgeLeftBegin() && !tableau[case1.getX()][case1.getY()+1].isEdgeLeftEnd() && !tableau[case1.getX()][case1.getY()+1].isEdgeRightBegin() && !tableau[case1.getX()][case1.getY()+1].isEdgeRightEnd() && !tableau[case1.getX()][case1.getY()+1].isEmpty()){
            		return true;
            	}
            	else{
            		return false;
            	}
            }
            else if(case1.getX() == case2.getX() && case1.getY()-2 == case2.getY()){//On tente de sauter un pion vers la gauche 
            	if(!tableau[case1.getX()][case1.getY()-1].isEdgeLeftBegin() && !tableau[case1.getX()][case1.getY()-1].isEdgeLeftEnd() && !tableau[case1.getX()][case1.getY()-1].isEdgeRightBegin() && !tableau[case1.getX()][case1.getY()-1].isEdgeRightEnd() && !tableau[case1.getX()][case1.getY()-1].isEmpty()){
            		return true;
            	}
            	else{
            		return false;
            	}
            }
            else{
            	return false;
            }
        }
    }
    
    public static boolean canPutWallRight(Case case1){
        int i = case1.getX();
        int j = case1.getY();
        if (i !=8){
            if(case1.isEdgeRightBegin() || case1.isEdgeRightEnd() || case1.isEdgeDownBegin() || case1.getY() == 8 || tableau[i+1][j].isEdgeRightBegin()){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            if(case1.isEdgeRightBegin() || case1.isEdgeRightEnd() || case1.isEdgeDownBegin() || case1.getY() == 8 || tableau[i-1][j].isEdgeUpEnd()){
                return false;
            }
            else{
                return true;
            }
        }
    }
    
    public static ArrayList<Case> canMove(Case case1){
        int x = case1.getX();
        int y = case1.getY();
        ArrayList<Case> testAl = new ArrayList<Case>();
        if (x == 0 && y == 0){//Coin supérieur gauche
            testAl.add(tableau[x][y+1]);
            testAl.add(tableau[x+1][y]);
            testAl.add(tableau[x+1][y+1]);
            testAl.add(tableau[x][y+2]);
            testAl.add(tableau[x+2][y]);
        }
        else if(x == 0 && y != 0){//Si on se trouve su la première ligne
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
            else{//Coin supérieur droit
                testAl.add(tableau[x][y-1]);
                testAl.add(tableau[x+1][y]);
                testAl.add(tableau[x+1][y-1]);
                testAl.add(tableau[x][y-2]);
                testAl.add(tableau[x+2][y]);
            }
        }
        else if(x == 8 && y == 8){//Coin inférieur droit
            testAl.add(tableau[x][y-1]);
            testAl.add(tableau[x-1][y]);
            testAl.add(tableau[x-1][y-1]);
            testAl.add(tableau[x-2][y]);
            testAl.add(tableau[x][y-2]);
        }
        else if(x == 8 && y != 8){
            if (y == 0){//Coin inférieur gauche
                testAl.add(tableau[x-1][y]);
                testAl.add(tableau[x][y+1]);
                testAl.add(tableau[x-1][y+1]);
                testAl.add(tableau[x-2][y]);
                testAl.add(tableau[x][y+2]);
            }
            else{//On est sur la dernière ligne
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
        	if(x==8){//Coin inférieur gauche
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
    
    public static boolean canPutWallLeft(Case case1){
        int i = case1.getX();
        int j = case1.getY();
        if (i != 8){
            if (case1.isEdgeLeftBegin() || case1.isEdgeLeftEnd() || case1.isEdgeDownEnd() || case1.getY() == 0 || tableau[i+1][j].isEdgeLeftBegin()){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            if (case1.isEdgeLeftBegin() || case1.isEdgeLeftEnd() || case1.isEdgeDownEnd() || case1.getY() == 0 || tableau[i-1][j].isEdgeLeftEnd()){
                return false;
            }
            else{
                return true;
            }
        }
    }
    
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
    
    public static boolean canPutWallUp(Case case1){
        int i = case1.getX();
        int j = case1.getY();
        if (j != 8){
            if (case1.isEdgeUpBegin() || case1.isEdgeUpEnd() || case1.getX() == 0 || tableau[i][j+1].isEdgeUpBegin()){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            if (case1.isEdgeUpBegin() || case1.isEdgeUpEnd() || case1.getX() == 0 || tableau[i][j-1].isEdgeUpEnd()){
                return false;
            }
            else{
                return true;
            }
        }
    }
    
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
    
    public static boolean canPutWallDown(Case case1){
        int i = case1.getX();
        int j = case1.getY();
        if (j != 8){
            if (case1.isEdgeDownBegin() || case1.isEdgeDownEnd() || case1.getX() == 8 || tableau[i][j+1].isEdgeDownBegin()){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            if (case1.isEdgeDownBegin() || case1.isEdgeDownEnd() || case1.getX() == 8 || tableau[i][j-1].isEdgeDownEnd()){
                return false;
            }
            else{
                return true;
            }
        }
    }
    
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
    
    
    public static boolean canMoveLeft(AGenericPlayer joueur){
        int x = joueur.getX();
        int y = joueur.getY();
        if (y == 0 || tableau[x][y-1].isEdgeRightBegin() || tableau[x][y-1].isEdgeRightEnd()|| tableau[x][y-1].isEmpty() == false){
            return false;
        }
        else{
            return true;
        }   
    }
    
    public static boolean canMoveRight(AGenericPlayer joueur){
        int x = joueur.getX();
        int y = joueur.getY();
        if (y == 8 || tableau[x][y+1].isEdgeLeftBegin() || tableau[x][y+1].isEdgeLeftEnd() || tableau[x][y+1].isEmpty() == false){
            return false;
        }
        else{
            return true;
        }
    }
    
    public static boolean canMoveUp(AGenericPlayer joueur){
        int x = joueur.getX();
        int y = joueur.getY();
        if (x == 0 || tableau[x-1][y].isEdgeDownBegin() || tableau[x-1][y].isEdgeDownEnd()|| tableau[x-1][y].isEmpty() == false){
            return false;
        }
        else{
            return true;
        }
    }
    
    public static boolean canMoveDown(AGenericPlayer joueur){
        int x = joueur.getX();
        int y = joueur.getY();
        if (x == 8 || tableau[x+1][y].isEdgeUpBegin() || tableau[x+1][y].isEdgeUpEnd() || tableau[x+1][y].isEmpty() == false){
            return false;
        }
        else{
            return true;
        }
    }
}
