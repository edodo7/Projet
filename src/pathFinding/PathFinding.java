package pathFinding;
import java.io.Serializable;
import java.util.ArrayList;
import board.Board;
import board.Case;
import mainAndRules.Rules;
/**
 *Cette classe permet de savoir s'il y a un chemin de la position du Joueur1 vers la sortie et,si oui, d'obtenir le chemin que doit emprunter le joueur pour y arriver
 * @author Eduardo Dom
 * @author Pierre Zielinski
 */
public class PathFinding implements Serializable{
	
	private Case startingCase;//La case a partir de laquelle on va commencer le calcul ddu chemin.
	private boolean findPath;//Bolleen disant si un chemin a ete trouver ou pas
	public Case[][] tableau;//Plateau de jeu
	private ArrayList<PathFindingCase> lookedElements;//on cree une arraylist des element ou l'on est passe
	private ArrayList<Case> way;//liste du chemin possible a partir de la case 0 si c'est pas possible de trouver une sortie de la
	private PathFindingCase exit;//case de sortie
	private boolean FirstPlayer;
	
	private class PathFindingCase implements Serializable {
		/**
		 * Cette classe recupere une case ainsi que son parent tout au long du chemin afin de pouvoir reconstituer celui-ci.
		 * @author Eduardo Dom
		 * @author Pierre Zielinski
		 */
		private Case case1;
		private PathFindingCase lastPFC;
		
		public PathFindingCase(Case case1,PathFindingCase lastPFC){
			this.case1 = case1;
			this.lastPFC = lastPFC;
		}
		/**
		 * @return Le parent dont l'instance de Case est associee
		 */
		public PathFindingCase getLastPFC(){
			return this.lastPFC;  
		}
		
		/**
		 * @return Une instance de Case
		 */
		public Case getCase(){
			return this.case1;
		}
	}
	/**
	 * Constructeur
	 * @param startingCase la case de depart pour la recherche du cchemin
	 * @param FirstPlayer si oui,alors la  sortie est atteinte en x = 8,si non elle l'est en x = 0 
	 */
	public PathFinding(Case startingCase,boolean FirstPlayer) {
		this.startingCase = startingCase;
		this.tableau = Board.getTableau();
		this.exit = new PathFindingCase(null,null);//on initialise la case de sortie a null,null pour la methode way au cas ou l'on demande la sortie alors qu'on n'a pas utiliser la methode isExit()
		this.way = new ArrayList<Case>();//on initialise l'arraylist au cas ou on la demande directement
		this.FirstPlayer = FirstPlayer;
	}
	
	/**
	 * Permet de changer la case a partir de laquelle on commence le calcul du chemin
	 * @param newStartingCase
	 */
	public void setStartingCase(Case newStartingCase){
		this.startingCase = newStartingCase;
	}
	
	/**
	 * Permet ce changer le tableau sur lequel on fait les calculs
	 * @param newTab
	 */
	public void setTabeau(Case[][] newTab){
		this.tableau = newTab;
	}
	
	/**
	 * Permet de determiner si il existe un chemin ou pas vers la sortie.
	 * Pour chaque case contenue dans L'ArrayList,on regarde tous ses voisins.
	 * S'ils sont deja marques,on ne les ajoute pas dans la nouvelle ArrayList.
	 * Sinon,on les ajoutes et on les marques afin de ne plus revenir sur nos pas.
	 * @param liste cases avec leurs parents
	 * @return chemin ou vide s'il n'en existe pas
	 */
	protected  ArrayList<PathFindingCase> loopFindingPath(ArrayList<PathFindingCase> liste){
		ArrayList<PathFindingCase> al = new ArrayList<PathFindingCase>();
		ArrayList<Case> lookCase;
		for (int i = 0; i < liste.size();i++){
			lookCase  = Rules.canMove(liste.get(i).getCase());
			for (int j = 0;j < lookCase.size();j++){
				Case new_case = lookCase.get(j);
				PathFindingCase new_PathFindingCase = new PathFindingCase(new_case,liste.get(i));
				if (!isIn(new_PathFindingCase,lookedElements)){
					al.add(new_PathFindingCase);
					lookedElements.add(new_PathFindingCase);
					if( new_case.getX() == 8 && FirstPlayer){
						findPath =true;
						exit = new_PathFindingCase;
					}
					if (new_case.getX() == 0 && !FirstPlayer){
						findPath =true;
						exit = new_PathFindingCase;
					}
				}
			}
		}
		return al;
	}
	
	/**
	 * Permet de savoir si une case est presente dans une ArrayList de cases
	 * @param pathfindcase Une case ainsi que son parent
	 * @param al cases avec leurs parents
	 * @return si oui ou non la case se trouve dans l'ArrayList de cases
	 */
	protected boolean isIn(PathFindingCase pathfindcase,ArrayList<PathFindingCase> al){
		for (int i = 0 ; i < al.size(); i++){
			if (al.get(i).getCase().equals(pathfindcase.getCase())){//on ne verifie que les case car un pathfinding case contient aussi l'ancienne case
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @return Vrai si il existe unn chemin jusqu'a la sortie,Faux sinon
	 */
	public boolean isExit(){
		findPath = false;//on initialise findPath la pour pouvoir reutiliser tout le pathfinding quand on place un nouveau mur
		ArrayList<PathFindingCase> al = new ArrayList<PathFindingCase>();
		al.add(new PathFindingCase(startingCase, null));
		lookedElements = new ArrayList<PathFindingCase>();//on initialise l'arraylist
		while(al.size() != 0 && !findPath){
			ArrayList<PathFindingCase> new_al =loopFindingPath(al);
			al = new_al;
		}
		return findPath;
	}
	
	/**
	 * Reconsitue le chemin en partant de la case de sortie, et en remontant jusqu'a la case de depart grace aux parents
	 * 
	 */
	public void way(){//la methode va ajouter dans way le chemin, le dernier elements sera l element d'ou on part donc l avant dernier sera la case ou l'on doit se deplace ,..., et le premier sera la case de sortie
		way = new ArrayList<Case>();//on reinitialise l arraylist
		//System.out.println("Case : "+exit.getCase());
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
	/**
	 * 
	 * @return La liste des chemins reconstitues
	 */
	public ArrayList<Case> getWay(){
		return way;
	}

}
