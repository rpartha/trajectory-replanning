import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class RepeatedAStar{

	private RepeatedAStar(){

	}

	public static ArrayList<Node> planning(Node current, boolean isForward, int[][] blocked){
		if(!isForward){
			current.setX(9);	
			current.setY(9);
		}
		boolean impossible = false;
		boolean duplicate = false;
		Node topNeighbor = new Node(0,0,0,0);	
		Node bottomNeighbor = new Node(0,0,0,0);	
		Node leftNeighbor = new Node(0,0,0,0);	
		Node rightNeighbor = new Node(0,0,0,0);
		Node start = new Node(current.getX(),current.getY(),current.getG(),current.getH());
		Node dest = new Node(9,9,0,0);
		Node root = new Node(0,0,0,0);
		ArrayList<Node> plannedRoute = new ArrayList<Node>();
		ArrayList<Node> closedList = new ArrayList<Node>();
		ArrayList<Node> neighbors = new ArrayList<Node>();
		boolean inClosedList = false;
		MinHeap openList = new MinHeap();
		if(isForward){
			while(current.getX() != 9 || current.getY() != 9){
				//System.out.println("Current Node(X , Y): " + current.getX() + " , " + current.getY());
				duplicate = false;
				if(current.getX() -1 < 0 && current.getY()-1 < 0){
					bottomNeighbor.setX(current.getX());
					bottomNeighbor.setY(current.getY()+1);
					neighbors.add(bottomNeighbor);
					rightNeighbor.setX(current.getX()+1);
					rightNeighbor.setY(current.getY());
					neighbors.add(rightNeighbor);							
				}
				else if(current.getX()+1 > 9 && current.getY() + 1 > 9){
					topNeighbor.setX(current.getX());
					topNeighbor.setY(current.getY()-1);
					neighbors.add(topNeighbor);
					leftNeighbor.setX(current.getX()-1);
					leftNeighbor.setY(current.getY());
					neighbors.add(leftNeighbor);
				}
				else if(current.getX()-1 < 0 && current.getY()+1 > 9){
					topNeighbor.setX(current.getX());
					topNeighbor.setY(current.getY()-1);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(current.getX()+1);
					rightNeighbor.setY(current.getY());
					neighbors.add(rightNeighbor);
				}
				else if(current.getX()+1 > 9 && current.getY()-1 < 0){
					leftNeighbor.setX(current.getX()-1);
					leftNeighbor.setY(current.getY());
					neighbors.add(leftNeighbor);
					bottomNeighbor.setX(current.getX());
					bottomNeighbor.setY(current.getY()+1);
					neighbors.add(bottomNeighbor);
				}
				else if(current.getX()-1 < 0 && current.getY()-1 >= 0){
					topNeighbor.setX(current.getX());
					topNeighbor.setY(current.getY()-1);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(current.getX()+1);
					rightNeighbor.setY(current.getY());
					neighbors.add(rightNeighbor);
					bottomNeighbor.setX(current.getX());
					bottomNeighbor.setY(current.getY()+1);
					neighbors.add(bottomNeighbor);
				}
				else if(current.getX()-1 >= 0 && current.getY()-1 < 0){
					leftNeighbor.setX(current.getX()-1);
					leftNeighbor.setY(current.getY());
					neighbors.add(leftNeighbor);
					bottomNeighbor.setX(current.getX());
					bottomNeighbor.setY(current.getY()+1);
					neighbors.add(bottomNeighbor);
					rightNeighbor.setX(current.getX()+1);
					rightNeighbor.setY(current.getY());
					neighbors.add(rightNeighbor);
				}
				else if(current.getX()+1 > 9 && current.getY() + 1 <= 9){
					topNeighbor.setX(current.getX());
					topNeighbor.setY(current.getY()-1);
					neighbors.add(topNeighbor);
					leftNeighbor.setX(current.getX()-1);
					leftNeighbor.setY(current.getY());
					neighbors.add(leftNeighbor);
					bottomNeighbor.setX(current.getX());
					bottomNeighbor.setY(current.getY()+1);
					neighbors.add(bottomNeighbor);
				}
				else if(current.getX()+1 <=9 && current.getY()+1 > 9){
					leftNeighbor.setX(current.getX()-1);
					leftNeighbor.setY(current.getY());
					neighbors.add(leftNeighbor);
					topNeighbor.setX(current.getX());
					topNeighbor.setY(current.getY()-1);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(current.getX()+1);
					rightNeighbor.setY(current.getY());
					neighbors.add(rightNeighbor);
				}
				else {
					leftNeighbor.setX(current.getX()-1);
					leftNeighbor.setY(current.getY());
					neighbors.add(leftNeighbor);
					topNeighbor.setX(current.getX());
					topNeighbor.setY(current.getY()-1);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(current.getX()+1);
					rightNeighbor.setY(current.getY());
					neighbors.add(rightNeighbor);
					bottomNeighbor.setX(current.getX());
					bottomNeighbor.setY(current.getY()+1);
					neighbors.add(bottomNeighbor);
				}
				for(Node node : neighbors){	
					inClosedList = false;
					node.setG(computeGOrH(start, node));
					node.setH(computeGOrH(node,dest));	
					node.setF(node.getG()+node.getH());
					for(Node closedListNode : closedList){
						if(node.getX() == closedListNode.getX() && node.getY() == closedListNode.getY()){
							inClosedList = true;
							break;
						}	
					}
					if(inClosedList){
						continue;
					}
					//System.out.println("NeighborNode(X , Y): " + node.getX() + " , " + node.getY());
					if(blocked[node.getX()][node.getY()] == 1){
						//System.out.println("blocked");
						continue;
					}
					openList.insert(node);
				}		
				for(Node node : closedList){
					//System.out.println("Closed List(X , Y): " + node.getX() + " , " + node.getY());
				}
				if(openList.heap.size() == 0){
					return null;
				}
				//System.out.println("Current Node X value: " + current.getX());
				//System.out.println("Current Node Y value: " + current.getY());
				Node newNode = new Node(0,0,0,0);
				newNode.setX(current.getX());
				newNode.setY(current.getY());
				newNode.setG(current.getG());
				newNode.setH(current.getH());
				closedList.add(newNode);
				inClosedList = false;
				Node temp = root;
				while(temp.getChild() != null){
					temp = temp.getChild();
				}
				Node heapRoot = new Node(openList.heap.get(0).getX(),openList.heap.get(0).getY(),openList.heap.get(0).getG(),openList.heap.get(0).getH());
				if((newNode.getX() == heapRoot.getX()+1 && newNode.getY() == heapRoot.getY()) || (newNode.getX() == heapRoot.getX() && newNode.getY() == heapRoot.getY()+1) || 
				(newNode.getX() == heapRoot.getX()-1 && newNode.getY() == heapRoot.getY()) || (newNode.getX() == heapRoot.getX() && newNode.getY() == heapRoot.getY()-1)){
					temp.setChild(heapRoot);
				}
				for(Node node : closedList){
					//System.out.println("Closed List Node(X , Y): " + node.getX() + " , " + node.getY());
				}
				//System.out.println("Open List Root(X , Y): " + openList.heap.get(0).getX() + " , " + openList.heap.get(0).getY());
				current.setX(openList.heap.get(0).getX());
				current.setY(openList.heap.get(0).getY());
				start = openList.delete();
				//System.out.println(current.getX() + " , " + current.getY());
				neighbors.clear();
			}
			Node temp = root;
			while(temp.getChild() != null){
				plannedRoute.add(temp);
				temp = temp.getChild();
			}
			plannedRoute.add(temp);
			openList.heap.clear();
		}
		else {
			while(current.getX() != 0 && current.getY() != 0){
				if(current.getX() -1 < 0 && current.getY()-1 < 0){
					bottomNeighbor.setX(current.getX());
					bottomNeighbor.setY(current.getY()+1);
					neighbors.add(bottomNeighbor);
					rightNeighbor.setX(current.getX()+1);
					rightNeighbor.setY(current.getY());
					neighbors.add(rightNeighbor);
				}
				else if(current.getX() -1 < 0 && current.getY()-1 >= 0){
					topNeighbor.setX(current.getX());
					topNeighbor.setY(current.getY()-1);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(current.getX()+1);
					rightNeighbor.setY(current.getY());
					neighbors.add(rightNeighbor);
					bottomNeighbor.setX(current.getX());
					bottomNeighbor.setY(current.getY()+1);
					neighbors.add(bottomNeighbor);
				}
				else if(current.getX() -1 >= 0 && current.getY()-1 < 0){
					leftNeighbor.setX(current.getX()-1);
					leftNeighbor.setY(current.getY());
					neighbors.add(leftNeighbor);
					bottomNeighbor.setX(current.getX());
					bottomNeighbor.setY(current.getY()+1);
					neighbors.add(bottomNeighbor);
					rightNeighbor.setX(current.getX()+1);
					rightNeighbor.setY(current.getY());
					neighbors.add(rightNeighbor);
				}
				else if(current.getX()+1 > 9 && current.getY() + 1 > 9){
					topNeighbor.setX(current.getX());
					topNeighbor.setY(current.getY()-1);
					neighbors.add(topNeighbor);
					leftNeighbor.setX(current.getX()-1);
					leftNeighbor.setY(current.getY());
					neighbors.add(leftNeighbor);
				}
				else if(current.getX()+1 > 9 && current.getY() + 1 <= 9){
					topNeighbor.setX(current.getX());
					topNeighbor.setY(current.getY()-1);
					neighbors.add(topNeighbor);
					leftNeighbor.setX(current.getX()-1);
					leftNeighbor.setY(current.getY());
					neighbors.add(leftNeighbor);
					bottomNeighbor.setX(current.getX());
					bottomNeighbor.setY(current.getY()+1);
					neighbors.add(bottomNeighbor);
				}
				else if(current.getX()+1 <=9 && current.getY()+1 > 9){
					leftNeighbor.setX(current.getX()-1);
					leftNeighbor.setY(current.getY());
					neighbors.add(leftNeighbor);
					topNeighbor.setX(current.getX());
					topNeighbor.setY(current.getY()-1);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(current.getX()+1);
					rightNeighbor.setY(current.getY());
					neighbors.add(rightNeighbor);
				}
				else if(current.getX()-1 < 0 && current.getY()+1 > 9){
					topNeighbor.setX(current.getX());
					topNeighbor.setY(current.getY()-1);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(current.getX()+1);
					rightNeighbor.setY(current.getY());
					neighbors.add(rightNeighbor);
				}
				else if(current.getX()+1 > 9 && current.getY()-1 < 0){
					leftNeighbor.setX(current.getX()-1);
					leftNeighbor.setY(current.getY());
					neighbors.add(leftNeighbor);
					bottomNeighbor.setX(current.getX());
					bottomNeighbor.setY(current.getY()+1);
					neighbors.add(bottomNeighbor);
				}
				else {
					leftNeighbor.setX(current.getX()-1);
					leftNeighbor.setY(current.getY());
					neighbors.add(leftNeighbor);
					topNeighbor.setX(current.getX());
					topNeighbor.setY(current.getY()-1);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(current.getX()+1);
					rightNeighbor.setY(current.getY());
					neighbors.add(rightNeighbor);
					bottomNeighbor.setX(current.getX());
					bottomNeighbor.setY(current.getY()+1);
					neighbors.add(bottomNeighbor);
				}
				for(Node node : neighbors){	
					node.setG(node.getX() + node.getY());
					node.setH((9-node.getX()) + (9-node.getY()));	
					node.setF(node.getG()+node.getH());
					if(!closedList.isEmpty()){
						for(int i = 0; i < openList.heap.size(); i++){
							for(int j = 0; j < closedList.size(); j++){
								if(openList.heap.get(i).getX() == closedList.get(j).getX() && openList.heap.get(i).getY() == closedList.get(j).getY()){
									System.out.println("in closed list");
									continue;
								}	
								if(blocked[openList.heap.get(i).getX()][openList.heap.get(i).getY()] == 1){
									continue;
								}
							}
						}		
					}	
					openList.insert(node);
				}		
				if(openList.heap.size() == 0){
					return null;
				}
				openList.heap.get(0).setParent(current);
				closedList.add(current);
				current = openList.delete();
			}
			while(current.getParent() != null){
				plannedRoute.add(current);		
				current = current.getParent();
			}
			plannedRoute.add(current);
			Collections.reverse(plannedRoute);	
		}
		return plannedRoute;
	}
	
	public static ArrayList<Node> executePath(boolean isForward, Grid grid){
		boolean ranIntoBlock = false;
		int[][] blocked = new int[10][10]; //1 is blocked, 0 is unblocked
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				blocked[i][j] = 0;
			}
		}
		Node current = new Node(0,0,0,0);
		if(!isForward){
			current.setX(9);
			current.setY(9);
		}
		ArrayList<Node> plannedRoute = planning(current, isForward, blocked);
		//System.out.println("After first planned route");
		//Initializing nodes of plannedRoute to Blocked or Unblocked
		for(Node node : plannedRoute){
			System.out.println("Planned Route(X , Y): " + node.getX() + " , " + node.getY());
		}
		for(int i = 0; i < plannedRoute.size(); i++){
			if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()].getBlocked()){
				plannedRoute.get(i).setBlocked(true);
			}
		}
		
		int i = 0;
		
		//Actually traversing the grid
		while(true){
			for(i = 0; i < plannedRoute.size(); i++){
				if(plannedRoute.get(i).getX() -1 < 0 && plannedRoute.get(i).getY()-1 < 0){
					if(grid.array[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()].getBlocked()){
						blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
						blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
					}
				}
				else if(plannedRoute.get(i).getX()+1 > 9 && plannedRoute.get(i).getY() + 1 > 9){
					if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
						blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
						blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
					}
				}
				else if(plannedRoute.get(i).getX()-1 < 0 && plannedRoute.get(i).getY()+1 > 9){
					if(grid.array[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()].getBlocked()){
						blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
						blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
					}
				}
				else if(plannedRoute.get(i).getX()+1 > 9 && plannedRoute.get(i).getY()-1 < 0){
					if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
						blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
						blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
					}
				}
				else if(plannedRoute.get(i).getX()-1 < 0 && plannedRoute.get(i).getY()-1 >= 0){
					if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
						blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()].getBlocked()){
						blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
						blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
					}
				}
				else if(plannedRoute.get(i).getX()-1 >= 0 && plannedRoute.get(i).getY()-1 < 0){
					if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
						blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()].getBlocked()){
						blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
						blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
					}
				}
				else if(plannedRoute.get(i).getX()+1 > 9 && plannedRoute.get(i).getY() + 1 <= 9){
					if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
						blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
						blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
						blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
					}
				}
				else if(plannedRoute.get(i).getX()+1 <=9 && plannedRoute.get(i).getY()+1 > 9){
					if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
						blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()].getBlocked()){
						blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
						blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
					}
				}
				else {
					if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
						blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()].getBlocked()){
						blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
					}
					if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
						blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
					}
				}
				
				if(plannedRoute.get(i).getBlocked()){
					blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()] = 1;
					ranIntoBlock = true;
					break;
				}
			}
			if(ranIntoBlock == false){
				break;
			}
			if(i == 0){
				//System.out.println("blah");
				plannedRoute = planning(plannedRoute.get(i),isForward, blocked);
			}
			else {
				//System.out.println("blah2");
				plannedRoute = planning(plannedRoute.get(i-1),isForward, blocked);
			}
			//System.out.println("After second planned route");
			if(plannedRoute == null){
				return null;
			}
		}
		return plannedRoute;
	}
	public static int computeGOrH(Node start, Node dest){
		int result = Math.abs(dest.getX() - start.getX()) + Math.abs(dest.getY() - start.getY());
		return result;
	}
}
