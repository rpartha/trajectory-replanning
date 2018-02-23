import java.util.ArrayList;

public class Driver {
	public static void main(String[] args){

		long beg = Ssytem.nanoTime();

		Grid grid = new Grid();
		grid.printGrid(null);
		ArrayList<Node> al = RepeatedAStar.executePath(true, grid, true);

		long end =System.nanoTime();

		if(al == null){
			System.out.println("This puzzle is impossible!");
			return;
		}
		/*for(Node node : al){
			System.out.println(node.getX() + " , " + node.getY());
		}*/
		grid.printGrid(al);


		long total = end - beg;

		System.out.println(total);

	}
}
