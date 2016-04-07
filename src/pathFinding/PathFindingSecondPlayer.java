package pathFinding;
import java.util.ArrayList;

import board.Case;
import mainAndRules.Rules;
/**
 *Cette classe permet de savoir s'il y a un chemin de la position du Joueur1 vers la sortie et,si oui, d'obtenir le chemin que doit emprunter le joueur pour y arriver
 * @author Eduardo Dom
 * @author Pierre Zielinski
 */
public class PathFindingSecondPlayer extends PathFindingFirstPlayer {
	
	public PathFindingSecondPlayer(Case startingCase){
		super(startingCase);
	}
	
	
	public void setStartingCase(Case newStartingCase){
		this.startingCase = newStartingCase;
	}
	protected  ArrayList<PathFindingCase> loopFindingPath(ArrayList<PathFindingCase> liste){
		ArrayList<PathFindingCase> al = new ArrayList<PathFindingCase>();
		for (int i = 0; i < liste.size();i++){
			Case lookCase = liste.get(i).getCase();
			if (lookCase.getX()-1 >= 0 ){
				Case new_case =  tableau[lookCase.getX() - 1][lookCase.getY()];
				PathFindingCase new_PathFindingCase = new PathFindingCase(new_case,liste.get(i).getDepth(),liste.get(i));
				if (new_case.getX() == 0){//On regarde ici si c'est la case de sortie
					findPath = true;
					exit = new_PathFindingCase;//on donne la case de sortie
				}
				else{
					if (Rules.canMove(lookCase, new_case) &&(!isIn(new_PathFindingCase,lookedElements))){//on verifie si l'élément n'est pas par où on est déjà passé
						al.add(new_PathFindingCase);
						lookedElements.add(new_PathFindingCase);
					}
				}
			}
			if (lookCase.getX()+1 < 9 ){
				Case new_case = tableau[lookCase.getX() +1][lookCase.getY()];
				PathFindingCase new_PathFindingCase = new PathFindingCase(new_case,liste.get(i).getDepth(),liste.get(i));
				if (Rules.canMove(lookCase, new_case) && (!isIn(new_PathFindingCase,lookedElements))){//on verifie si l'élément n'est pas par où on est déjà passé

					al.add(new_PathFindingCase);
					lookedElements.add(new_PathFindingCase);
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
			}	
		}
		return al;
	}
}
