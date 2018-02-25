import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args){



		Scanner scanner = new Scanner(System.in); 
		System.out.print("READ (1) or WRITE (2): ");

		int option = Integer.parseInt(scanner.nextLine());

		if(option == 1){
			System.out.print("Enter file name you wish to READ: ");
			String fname = scanner.nextLine();

			long beg = System.nanoTime();

			Grid grid = new Grid(fname);
			grid.printGrid(null);

			ArrayList<Node> al = RepeatedAStar.executePath(true, grid, true);

			long end =System.nanoTime();

			if(al == null){
				System.out.println("This puzzle is impossible!");
				scanner.close();
				return;
			} 
			grid.printGrid(al);
			//grid.printGrid(null);

			long total = end - beg;

			System.out.println(total);
			scanner.close();
		} else if(option == 2){
			System.out.print("Enter file name you wish to WRITE to: ");
			String fname = scanner.nextLine();

			//long beg = System.nanoTime();

			Grid grid = new Grid();
			grid.printGrid(null);

			ArrayList<Node> al = RepeatedAStar.executePath(true, grid, true);

			//long end =System.nanoTime();

			if(al == null){
				System.out.println("This puzzle is impossible!");
				scanner.close();
				return;
			} else{
				grid.writeToFile(grid.array, fname);
				scanner.close();
			}
		} else{
			System.out.println("Invalid option!");
			scanner.close();
			System.exit(0);
		}
	}
}