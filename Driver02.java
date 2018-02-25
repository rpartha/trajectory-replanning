import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args){

		/*Scanner scanner = new Scanner(System.in); 
		System.out.println("Enter an output file name: ");

		String fname = scanner.nextLine();*/

		long beg = System.nanoTime();

		Grid grid = new Grid();
		grid.printGrid(null);
		
		ArrayList<Node> al = RepeatedAStar.executePath(true, grid, true);

		long end = System.nanoTime();

		if(al == null){
			System.out.println("This puzzle is impossible!");
			return;
		} /*else{
			Node[][] terrain = grid.array;
			grid.writeToFile(terrain, fname);
		}*/

		/*for(Node node : al){
			System.out.println(node.getX() + " , " + node.getY());
		}*/

		grid.printGrid(al);

		grid.printGrid(null);


		long total = end - beg;

		System.out.println(total);

	}
}
