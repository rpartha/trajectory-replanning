import java.util.ArrayList;
import java.util.Collections;

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
					node.setG(node.getX()+node.getY());
					node.setH((9-node.getX()) + (9-node.getY()));	
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
						continue;
					}
					openList.insert(node);
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
				Node heapRoot = new Node(0,0,0,0);
				heapRoot.setX(openList.heap.get(0).getX());
				heapRoot.setY(openList.heap.get(0).getY());
				Node temp = root;
				while(temp.getChild() != null){
					temp = temp.getChild();
				}
				if((temp.getX() == heapRoot.getX()+1 && temp.getY() == heapRoot.getY()) || (temp.getX() == heapRoot.getX() && temp.getY() == heapRoot.getY()+1) || 
				(temp.getX() == heapRoot.getX()-1 && temp.getY() == heapRoot.getY()) || (temp.getX() == heapRoot.getX() && temp.getY() == heapRoot.getY()-1)){
					temp.setChild(heapRoot);
				}
				for(Node node : closedList){
					//System.out.println("Closed List Node(X , Y): " + node.getX() + " , " + node.getY());
				}
				//System.out.println("Open List Root(X , Y): " + openList.heap.get(0).getX() + " , " + openList.heap.get(0).getY());
				Node nextNode = openList.delete();
				current.setX(nextNode.getX());
				current.setY(nextNode.getY());
				//System.out.println(current.getX() + " , " + current.getY());
				neighbors.clear();
			}
			Node temp = root;
			while(temp.getChild() != null){
				Node temp2 = temp;
				temp = temp.getChild();
				plannedRoute.add(temp2);		
			}
			plannedRoute.add(temp);
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
					node.setG(node.getX()+node.getY());
					node.setH((9-node.getX()) + (9-node.getY()));	
					node.setF(node.getG()+node.getH());
					if(!closedList.isEmpty()){
						for(int i = 0; i < openList.heap.size(); i++){
							for(int j = 0; j < closedList.size(); j++){
								if(openList.heap.get(i).getX() == closedList.get(j).getX() && openList.heap.get(i).getY() == closedList.get(j).getY()){
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
		Node current = new Node(0,0,0,0);
		if(!isForward){
			current.setX(9);
			current.setY(9);;
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
}
