import java.util.ArrayList;

public class Driver {
	public static void main(String[] args){
		Grid grid = new Grid();
		grid.printGrid(null);
		System.out.println("begin exec");
		ArrayList<Node> al = RepeatedAStar.executePath(true, grid);
		
		System.out.println("end exec");
		/*if(al == null){
			System.out.println("Impossible puzzle!!");
		}else{
			System.out.println("not null");
		}*/
		for(Node node : al){
			System.out.println(node.getX() + " , " + node.getY());
		}
		//grid.printGrid(al);
		
		
	}
}
