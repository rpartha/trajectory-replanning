import java.util.Random;
import java.util.ArrayList;


public class Grid {
	Random random = new Random();
	Node[][] array;
	public Grid(){
		array = new Node[10][10];
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(i == 0 && j ==0){
					array[i][j] = new Node(i, j, false);
				}
				else if(i == 9 && j == 9){
					array[i][j] = new Node(i, j, false);
				}
				else {
					array[i][j] = new Node(i, j, random.nextBoolean());
				}
			}
		}
	}
	
	public void printGrid(ArrayList<Node> path){
		if(path == null){
			for(int i = 0; i < 10; i++){
				System.out.println();
				for(int j = 0; j < 10; j++){
					if(array[i][j].getBlocked())
						System.out.print("B ");
					else
						System.out.print("U ");
				}
			}
		}else {
			int index = 0;
			for(int i = 0; i < 10; i++){
				System.out.println();
				for(int j = 0; j < 10; j++){
					if(path.get(index).getX() == i && path.get(index).getY() == j)
						System.out.print("X");
					else
						System.out.println("-");
				}
			}
		}
	}
}
