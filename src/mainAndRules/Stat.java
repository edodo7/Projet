package mainAndRules;

import java.io.IOException;

import board.Board;
import players.AGenericPlayer;
import players.HardAI;
import players.RandomAI;

public class Stat {
	private AGenericPlayer joueur1;
	private AGenericPlayer joueur2;
	private int nbresCoupsJ1;
	private int nbresCoupsJ2;
	
	public Stat(AGenericPlayer joueur1,AGenericPlayer joueur2){
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
	}
	
	public int play(boolean tourJoueur1) throws IOException{
		while(true){
			if (tourJoueur1){
				joueur1.play();
				nbresCoupsJ1++;
				tourJoueur1 = false;
				if(joueur1.getX() == 8){
					return 1;
				}
			}
			else{
				joueur2.play();
				nbresCoupsJ2++;
				tourJoueur1 = true;
				if (joueur2.getX() == 0){
					return 2;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		AGenericPlayer J1 = new RandomAI(true);
		AGenericPlayer J2 = new HardAI(false);
		int victoiresJ1 = 0;
		int victoiresJ2 = 0;
		boolean tourJ1 = true;
		for (int i = 0;i < 100;i++){
			Board board = new Board(J1,J2);
			Stat stat = new Stat(J1,J2);
			int whoWins = stat.play(tourJ1);
			if (whoWins == 1){
				victoiresJ1++;
			}
			else{
				victoiresJ2++;
			}
			tourJ1 = !tourJ1;
		}
		String str = "Le pourcentage de victoire du joueur1 est de "+((victoiresJ1/30.0)*100)+"%"+"\n"+"Le pourcentage de victoire du joueur2 est de "+((victoiresJ2/30.0)*100)+"%";
		System.out.println(str);
	}
}
