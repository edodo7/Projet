package pathFinding;
import java.util.ArrayList;

import board.Board;
import board.Case;
import mainAndRules.Rules;
/**
 *Cette classe permet de savoir s'il y a un chemin de la position du Joueur1 vers la sortie et,si oui, d'obtenir le chemin que doit emprunter le joueur pour y arriver
 * @author Eduardo Dom
 * @author Pierre Zielinski
 */
public class PathFindingFirstPlayer {
	
	protected Case startingCase;
	protected boolean findPath;
	public Case[][] tableau;
	protected ArrayList<PathFindingCase> lookedElements;//on crée une arraylist des element où l'on est passé
	protected ArrayList<Case> way;//liste du chemin possible à partir de la case 0 si c'est pas possible de trouver une sortie de là
	protected PathFindingCase exit;//case de sortie
	
	public PathFindingFirstPlayer(Case startingCase){
		this.startingCase = startingCase;
		this.tableau = Board.getTableau();
		this.exit = new PathFindingCase(null,-1,null);//on initialise la case de sortie à -1,-1 pour la methode way au cas où l'on demande la sortie alors qu'on n'a pas utiliser la methode isExit()
		this.way = new ArrayList<Case>();//on initialise l'arraylist au cas où on la demande directement
	}
	
	
	public void setStartingCase(Case newStartingCase){
		this.startingCase = newStartingCase;
	}
	
	public void setTabeau(Case[][] newTab){
		this.tableau = newTab;
	}
	protected  ArrayList<PathFindingCase> loopFindingPath(ArrayList<PathFindingCase> liste){
		ArrayList<PathFindingCase> al = new ArrayList<PathFindingCase>();
		ArrayList<Case> lookCase;
		for (int i = 0; i < liste.size();i++){
			lookCase  = Rules.canMove(liste.get(i).getCase());
			for (int j = 0;j < lookCase.size();j++){
				//System.out.println(lookCase.get(j));
				Case new_case = lookCase.get(j);
				PathFindingCase new_PathFindingCase = new PathFindingCase(new_case,liste.get(i).getDepth(),liste.get(i));
				if (!isIn(new_PathFindingCase,lookedElements)){
					al.add(new_PathFindingCase);
					lookedElements.add(new_PathFindingCase);
					if( new_case.getX() == 8){
						findPath =true;
						exit = new_PathFindingCase;
					}
				}
			}
			/*if (lookCase.getX()-1 >= 0 ){
				Case new_case =  tableau[lookCase.getX() - 1][lookCase.getY()];
				PathFindingCase new_PathFindingCase = new PathFindingCase(new_case,liste.get(i).getDepth(),liste.get(i));
				if (Rules.canMove(lookCase, new_case) &&(!isIn(new_PathFindingCase,lookedElements))){//on verifie si l'élément n'est pas par où on est déjà passé
					al.add(new_PathFindingCase);
					lookedElements.add(new_PathFindingCase);
				}
			}
			if (lookCase.getX()+1 < 9 ){
				Case new_case = tableau[lookCase.getX() +1][lookCase.getY()];
				PathFindingCase new_PathFindingCase = new PathFindingCase(new_case,liste.get(i).getDepth(),liste.get(i));
				if (Rules.canMove(lookCase, new_case) && (!isIn(new_PathFindingCase,lookedElements))){//on verifie si l'élément n'est pas par où on est déjà passé

					al.add(new_PathFindingCase);
					lookedElements.add(new_PathFindingCase);
				
					if (new_case.getX() == 8){
						findPath = true;
						exit = new_PathFindingCase;//on donne la case de sortie
					}
				}
			}
			if (lookCase.getY()-1 >= 0 ){
				Case new_case =  tableau[lookCase.getX() ][lookCase.getY()-1];
				PathFindingCase new_PathFindingCase = new PathFindingCase(new_case,liste.get(i).getDepth(),liste.get(i));
				if (Rules.canMove(lookCase, new_case) && (!isIn(new_PathFindingCase,lookedElements))){//on verifie si l'élément n'est pas par où on est déjà passé
					al.add(new_PathFindingCase);
					lookedElements.add(new_PathFindingCase);
				}
			}
			if (lookCase.getY()+1 < 9 ){
				Case new_case =  tableau[lookCase.getX() ][lookCase.getY()+1];
				PathFindingCase new_PathFindingCase = new PathFindingCase(new_case,liste.get(i).getDepth(),liste.get(i));
				if (Rules.canMove(lookCase, new_case) && (!isIn(new_PathFindingCase,lookedElements))){//on verifie si l'élément n'est pas par où on est déjà passé
					al.add(new_PathFindingCase);
					lookedElements.add(new_PathFindingCase);
				}
			}*/	
		}
		return al;
	}
	
	
	protected boolean isIn(PathFindingCase pathfindcase,ArrayList<PathFindingCase> al){
		for (int i = 0 ; i < al.size(); i++){
			if (al.get(i).getCase().equals(pathfindcase.getCase())){//on ne vérifie que les case car un pathfinding case contient aussi l'ancienne case
				return true;
			}
		}
		return false;
	}
	
	public boolean isExit(){
		findPath = false;//on initialise findPath là pour pouvoir réutiliser tout le pathfinding quand on place un nouveau mur
		ArrayList<PathFindingCase> al = new ArrayList<PathFindingCase>();
		al.add(new PathFindingCase(startingCase, 0, null));
		lookedElements = new ArrayList<PathFindingCase>();//on initialise l'arraylist
		while(al.size() != 0 && !findPath){
			ArrayList<PathFindingCase> new_al =loopFindingPath(al);
			al = new_al;
		}
		return findPath;
	}
	
	public void way(){//la méthode va ajouter dans way le chemin, le dernier elements sera l element d'ou on part donc l avant dernier sera la case où l'on doit se deplace ,..., et le premier sera la case de sortie
		way = new ArrayList<Case>();//on reinitialise l arraylist
		if(!exit.getCase().equals(null)){
			way.add(exit.getCase());
			PathFindingCase nextPathFindingCase = exit.getLastPFC();
			while(nextPathFindingCase != null){
				way.add(nextPathFindingCase.getCase());
				nextPathFindingCase = nextPathFindingCase.getLastPFC();
			}
		}
		ArrayList<Case> new_way = new ArrayList<Case>();
		for (int i = this.way.size()-1;i >= 0;i--){
			new_way.add(this.way.get(i));
		}
		this.way = new_way;
	}
	
	public ArrayList<Case> getWay(){
		return way;
	}

}
