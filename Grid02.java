import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Grid02 {
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

	public Grid(String fname){
		try{
			array = new Node[101][101];

			String[][] temp = new String[101][101];
			//File file = new File();
			BufferedReader br = new BufferedReader(new FileReader("C:/Users/tillu/Documents/Trajectory_Replanning/" + fname));
			Scanner reader = new Scanner(new File("C:/Users/tillu/Documents/Trajectory_Replanning/" + fname));
			
			while(reader.hasNextLine()){
				for(int i = 0; i < 101; i++){
					temp[i] = reader.nextLine().split(",");
				}

				/*for(int i = 0; i < temp.length; i++){
					for(int j = 0; j < temp[i].length; j++){
						System.out.print(temp[i][j] + ",");
					}
					System.out.println();
				}*/
	
				for(int m =0; m < array.length; m++){
					for(int n = 0; n < array[m].length; n++){
						if(temp[m][n].equals("B")){
							array[m][n] =new Node(m,n,true);
							
						} else{
							array[m][n] = new Node(m,n,false);
							
						}
					}
				}
			}
			
			reader.close();
			
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public void writeToFile(Node[][] terrain, String fname){
		try{
			File file = new File("C:/Users/tillu/Documents/Trajectory_Replanning/" + fname);

			if(!file.exists()){
				file.createNewFile();
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(fname));

			//write contents to file
			for(int i = 0; i < array.length; i++){
				for(int j = 0; j < array[i].length; j++){
					if(array[i][j].getBlocked()){
						bw.write("B" + ((j == array[i].length-1) ? "" : ",")); 
					} else{
						bw.write("U" + ((j == array[i].length-1) ? "" : ","));
					}
				}

				bw.newLine();
			}

			bw.close();

		} catch(IOException e){
			e.printStackTrace();
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
