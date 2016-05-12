package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.Case;
import mainAndRules.Rules;
import players.AGenericPlayer;
import players.HumanPlayer;
/**
 * Classe permettant de verifier le respect des regles 
 * @author Eduardo Dom
 */
public class RulesTest {
	private Board board;
	private AGenericPlayer joueur1;
	private AGenericPlayer joueur2;
	private Case[][] plateau;
	
	/**
	 * Initialisation pour les test
	 */
	@Before public void init(){
		joueur1 = new HumanPlayer(true);
		joueur2 = new HumanPlayer(false);
		board = new Board(joueur1,joueur2);
		plateau = board.tableau;
	}
	
	/**
	 * Teste qu'un mur en bas d'une case empeche de se deplacer en bas de celle ci
	 */
	@Test
	public void caseDownBlocked(){
		joueur1.putWallDown(plateau[joueur1.getX()][joueur1.getY()]);
		assertFalse(Rules.canMoveDown(joueur1));
	}
	
	/**
	 * Teste qu'un mur en bas d'une case empeche de se deplacer en bas de celle ci
	 */
	@Test
	public void caseUpBlocked(){
		joueur2.putWallUp(plateau[joueur2.getX()][joueur2.getY()]);
		assertFalse(Rules.canMoveUp(joueur2));
	}
	
	/**
	 *  Teste qu'un mur a gauche d'une case empeche de se deplacer a gauche de celle ci
	 */
	@Test
	public void caseLeftBlocked(){
		joueur1.putWallLeft(plateau[joueur1.getX()][joueur1.getY()]);
		assertFalse(Rules.canMoveLeft(joueur1));
	}
	
	/**
	 *  Teste qu'un mur a droite d'une case empeche de se deplacer a droite de celle ci
	 */
	@Test
	public void caseRightBlocked(){
		joueur1.putWallRight(plateau[joueur1.getX()][joueur1.getY()]);
		assertFalse(Rules.canMoveRight(joueur1));
	}
	
	/**
	 * Teste le fait de pouvoir sauter un pion vers le haut
	 */
	@Test
	public void faceToFaceForward(){
		joueur1.setX(7);
		joueur1.setY(4);
		plateau[7][4].setEmpty(false);
		assertTrue(Rules.canMove(plateau[8][4],plateau[6][4]));
	}
	/**
	 * Teste le fait de pouvoir sauter un pion vers le bas
	 */
	@Test
	public void faceToFaceBackward(){
		joueur2.setX(1);
		joueur2.setY(4);
		plateau[1][4].setEmpty(false);
		assertTrue(Rules.canMove(plateau[0][4],plateau[2][4]));
	}
	/**
	 * Teste le fait de pouvoir aller en diagonale gauche superieure lors d'un face a face
	 */
	@Test
	public void faceToFaceDiagonalLeftUp(){
		joueur1.setX(7);
		joueur1.setY(4);
		plateau[7][4].setEmpty(false);
		joueur1.putWallUp(plateau[7][4]);
		assertTrue(Rules.canMove(plateau[8][4],plateau[7][3]));
	}
	
	/**
	 * Teste le fait de pouvoir aller en diagonale droite superieure lors d'un face a face
	 */
	@Test
	public void faceToFaceDiagonalRightUp(){
		joueur1.setX(7);
		joueur1.setY(4);
		plateau[7][4].setEmpty(false);
		joueur1.putWallUp(plateau[7][4]);
		assertTrue(Rules.canMove(plateau[8][4],plateau[7][5]));
	}
	/**
	 * Teste le fait de pouvoir aller en diagonale gauche inferieure lors d'un face a face
	 */
	@Test
	public void faceToFaceDiagonalLeftDown(){
		joueur2.setX(1);
		joueur2.setY(4);
		plateau[1][4].setEmpty(false);
		joueur1.putWallDown(plateau[1][4]);
		assertTrue(Rules.canMove(plateau[0][4],plateau[1][3]));
	}
	
	/**
	 * Teste le fait de pouvoir aller en diagonale droite inferieure lors d'un face a face
	 */
	@Test
	public void faceToFaceDiagonalRightDown(){
		joueur2.setX(1);
		joueur2.setY(4);
		plateau[1][4].setEmpty(false);
		joueur1.putWallDown(plateau[1][4]);
		assertTrue(Rules.canMove(plateau[0][4],plateau[1][5]));
	}
	
	/**
	 * Teste le fait qu'on ne peut pas bloquer le chemin d'au moins un des joueurs
	 */
	@Test
	public void cannotBlockPath(){
		joueur1.putWallDown(plateau[3][0]);
		joueur1.putWallDown(plateau[3][2]);
		joueur1.putWallDown(plateau[3][4]);
		joueur1.putWallDown(plateau[3][6]);
		joueur1.putWallRight(plateau[3][7]);
		assertFalse(Rules.canReallyPutWallDown(plateau[4][7]));
		
	}
	/**
	 * Teste le fait qu'on ne peut pas couper verticalement un mur horizontal
	 */
	@Test
	public void cannotCrossHorizontalWall(){
		joueur1.putWallDown(plateau[3][0]);
		assertFalse(Rules.canPutWallRight(plateau[3][0]));
	}
	
	/**
	 * Teste le fait qu'on peut placer un mur vertical entre 2 murs horizontaux
	 */
	@Test
	public void verticalWallBetweenTwoHorizontals(){
		joueur1.putWallDown(plateau[3][0]);
		joueur1.putWallDown(plateau[3][2]);
		assertTrue(Rules.canPutWallRight(plateau[3][1]));
	}
	
	/**
	 * Teste le fait qu'on ne peut pas se deplacer en haut en etant sur la premiere ligne du plateau
	 */
	@Test
	public void cannotMoveUpBeingEdgeUp(){
		assertFalse(Rules.canMoveUp(joueur1));
	}
	
	/**
	 * Teste le fait qu'on ne peut pas se deplacer en bas en etant sur la derniere ligne du plateau
	 */
	@Test
	public void cannotMoveDownBeingEdgeDown(){
		assertFalse(Rules.canMoveDown(joueur2));
	}
	
	/**
	 * Teste le fait qu'on ne peut pas se deplacer a gauche en etant sur la premiere colonne du plateau
	 */
	@Test
	public void cannotMoveLeftBeingEdgeLeft(){
		joueur2.setX(1);
		joueur2.setY(0);
		plateau[1][0].setEmpty(false);
		assertFalse(Rules.canMoveLeft(joueur2));
	}
	/**
	 * Teste le fait qu'on ne peut pas se deplacer a droite en etant sur la derniere colonne du plateau
	 */
	@Test
	public void cannotMoveRightBeingEdgeRight(){
		joueur2.setX(1);
		joueur2.setY(8);
		plateau[1][8].setEmpty(false);
		assertFalse(Rules.canMoveRight(joueur2));
	}
	
	/**
	 * Teste le fait qu'on ne peut pas placer un mur en haut d'une case  se trouvant sur la premiere ligne 
	 */
	@Test
	public void cannotPutWallUpBeingEdgeUp(){
		assertFalse(Rules.canPutWallUp(plateau[0][4]));
	}
	
	/**
	 * Teste le fait qu'on ne peut pas placer un mur en bas d'une case se trouvant sur la derniere ligne
	 */
	@Test
	public void cannotPutWallDownBeingEdgeDown(){
		assertFalse(Rules.canPutWallDown(plateau[8][4]));
	}
	
	/**
	 * Teste le fait qu'on ne peut pas placer un mur a gauche d'une case se trouvant sur la premiere colonne
	 */
	@Test
	public void cannotPutWallLeftBeingEdgeLeft(){
		assertFalse(Rules.canPutWallLeft(plateau[8][0]));
	}
	
	/**
	 * Teste le fait qu'on ne peut pas placer un mur a droite d'une case se trouvant sur la derniere colonne
	 */
	@Test
	public void cannotPutWallRightBeingEdgeRight(){
		assertFalse(Rules.canPutWallRight(plateau[8][8]));
	}
	
}
