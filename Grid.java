import java.util.Random;
import java.util.ArrayList;


public class Grid {
	Random random = new Random();
	Node[][] array;
	public Grid(){
		array = new Node[101][101];
		for(int j = 0; j < 101; j++){
			for(int i = 0; i < 101; i++){
				if(i == 0 && j ==0){
					array[i][j] = new Node(i, j, false);
				}
				else if(i == 100 && j == 100){
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
			for(int j = 0; j < 101; j++){
				System.out.println();
				for(int i = 0; i < 101; i++){
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
			int count = 0;
			for(index = 0; index < path.size(); index++){
				System.out.println();
				for(int j = 0; j < 101; j++){
					for(int i = 0; i < 101; i++){
						for(int k = 0; k <= index; k++){
							if(path.get(k).getX() == i && path.get(k).getY() == j){
								exists = true;
								break;
							}
						}
						if(exists) {
							System.out.print("X");
						}
						else {
							System.out.print("-");
						}
						exists = false;
					}
					System.out.println();
				}
				System.out.println();
			}
		}
	}
}
