import java.util.Random;
import java.util.ArrayList;


public class Grid {
	Random random = new Random();
	Node[][] array;
	public Grid(){
		array = new Node[10][10];
		for(int j = 0; j < 10; j++){
			for(int i = 0; i < 10; i++){
				if(i == 0 && j ==0){
					array[i][j] = new Node(i, j, false);
				}
				else if(i == 9 && j == 9){
					array[i][j] = new Node(i, j, false);
				}
				else {
					int rand = random.nextInt(99);
					if(rand < 30){
						array[i][j] = new Node(i, j, true);
					}
					else {
						array[i][j] = new Node(i, j, false);
					}
				}
			}
		}
	}
	
	public void printGrid(ArrayList<Node> path){
		boolean exists = false;
		if(path == null){
			for(int j = 0; j < 10; j++){
				System.out.println();
				for(int i = 0; i < 10; i++){
					if(array[i][j].getBlocked())
						System.out.print("B ");
					else
						System.out.print("U ");
				}
			}
			System.out.println();
		}else {
			for(int h = 0; h < path.size()-1; h++){
				for(int k = h+1; k < path.size(); k++){
					if(path.get(h).getX() == path.get(k).getX() && path.get(h).getY() == path.get(k).getY()){
						path.remove(k);
					}
				}
			}
			int index = 0;
			for(int j = 0; j < 10; j++){
				System.out.println();
				for(int i = 0; i < 10; i++){
					for(index = 0; index < path.size(); index++){
						if(path.get(index).getX() == i && path.get(index).getY() == j){
							exists = true;
							break;
						}	
					}
					if(exists){
						System.out.print("X");
					}
					else {
						System.out.print("-");
					}
					exists = false;
				}
			}
			System.out.println();
		}
	}
}
