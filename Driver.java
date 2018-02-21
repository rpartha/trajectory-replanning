import java.util.ArrayList;

public class Driver {
	public static void main(String[] args){
		Grid grid = new Grid();
		grid.printGrid(null);
		ArrayList<Node> al = RepeatedAStar.executePath(false, grid);
		if(al == null){
			System.out.println("This puzzle is impossible!");
			return;
		}
		for(Node node : al){
			System.out.println(node.getX() + " , " + node.getY());
		}
		grid.printGrid(al);
		
		
	}
}
