package stat;

import java.io.IOException;
import java.util.Scanner;

import board.Board;
import players.AGenericPlayer;
import players.HardAI;
import players.RandomAI;

/**
 * Classe permettant d'obtenir des statistiques de plusieurs parties entre 2 IA
 * @author Eduardo
 *
 */
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
		System.out.println("Entrez le nombre de parties souhaites");
		Scanner sc = new Scanner(System.in);
		double nbresdeParties = sc.nextDouble();
		AGenericPlayer J1;
		AGenericPlayer J2;
		System.out.println("1. IA facile vs IA facile");
		System.out.println("2. IA facile vs IA difficile");
		System.out.println("3.IA difficile vs IA difficile");
		System.out.println("Entrez le numero correspondant au type de joueurs souhaites");
		int  player = sc.nextInt();
		if(player == 1){
			J1 = new RandomAI(true);
			J2 =  new RandomAI(false);
		}
		else if(player == 2){
			J1 = new RandomAI(true);
			J2 =  new HardAI(false);
		}
		else if (player == 3){
			J1 = new HardAI(true);
			J2 =  new HardAI(false);
		}
		
		else{
			throw new IOException("Mauvaise entree!");
		}
		int victoiresJ1 = 0;
		int victoiresJ2 = 0;
		boolean tourJ1 = true;
		for (int i = 0;i < nbresdeParties ;i++){
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
		String str = "\n\n\n\n\nLe pourcentage de victoire du joueur 1 est de "+((victoiresJ1/nbresdeParties)*100)+"%"+"\n"+"Le pourcentage de victoire du joueur 2 est de "+((victoiresJ2/nbresdeParties)*100)+"%";
		System.out.println(str);
	}
}
