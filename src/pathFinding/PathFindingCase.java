package pathFinding;
import board.Case;

/**
 * @author Eduardo Dom
 * @author Pierre Zielinski
 */
public class PathFindingCase{
	private Case case1;
	private int depth;
	private PathFindingCase lastPFC;
	
	public PathFindingCase(Case case1,int depth,PathFindingCase lastPFC){
		this.case1 = case1;
		this.depth = depth;
		this.lastPFC = lastPFC;
	}
	
	public PathFindingCase getLastPFC(){
		return this.lastPFC;  
	}
	
	public Case getCase(){
		return this.case1;
	}
	
	public int getDepth(){
		return this.depth;
	}
}