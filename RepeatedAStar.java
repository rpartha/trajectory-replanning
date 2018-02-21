import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class RepeatedAStar{

	private RepeatedAStar(){

	}

	public static ArrayList<Node> planning(Node current, boolean isForward, int[][] blocked){
		if(!isForward && current.getX() == 0 && current.getY() == 0){
			current.setX(9);	
			current.setY(9);
		}
		boolean impossible = false;
		boolean duplicate = false;
		boolean isBlocked = false;
		Node start = new Node(current.getX(),current.getY(),current.getG(),current.getH());
		Node dest = new Node(9,9,0,0);
		if(!isForward){
			dest.setX(0);
			dest.setY(0);
		}
		Node root = new Node(current.getX(),current.getY(),current.getG(),current.getH());
		ArrayList<Node> plannedRoute = new ArrayList<Node>();
		ArrayList<Node> closedList = new ArrayList<Node>();
		ArrayList<Node> neighbors = new ArrayList<Node>();
		boolean inClosedList = false;
		MinHeap openList = new MinHeap();
		if(isForward){
			while(current.getX() != 9 || current.getY() != 9){
				Node topNeighbor = new Node(0,0,0,0);	
				Node bottomNeighbor = new Node(0,0,0,0);	
				Node leftNeighbor = new Node(0,0,0,0);	
				Node rightNeighbor = new Node(0,0,0,0);
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
				else if(current.getX()-1 < 0){
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
				else if(current.getY()-1 < 0){
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
				else if(current.getX()+1 > 9){
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
				else if(current.getY()+1 > 9){
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
				for(int i = 0; i < neighbors.size(); i++){	
					inClosedList = false;
					isBlocked = false;
					neighbors.get(i).setG(computeGOrH(start, neighbors.get(i)));
					neighbors.get(i).setH(computeGOrH(neighbors.get(i),dest));	
					neighbors.get(i).setF(neighbors.get(i).getG()+neighbors.get(i).getH());
					for(Node closedListNode : closedList){
						if(neighbors.get(i).getX() == closedListNode.getX() && neighbors.get(i).getY() == closedListNode.getY()){
							inClosedList = true;
							break;
						}	
					}
					if(blocked[neighbors.get(i).getX()][neighbors.get(i).getY()] == 1){
						isBlocked = true;
					}
					if(!inClosedList && !isBlocked){
						openList.insert(neighbors.get(i));
					}
				}		
				if(openList.heap.size() == 0){
					return null;
				}
				Node newNode = new Node(0,0,0,0);
				newNode.setX(current.getX());
				newNode.setY(current.getY());
				newNode.setG(current.getG());
				newNode.setH(current.getH());
				closedList.add(newNode);
				inClosedList = false;
				isBlocked = false;
				Node temp = root;
				while(temp.getChild() != null){
					temp = temp.getChild();
				}
				Node heapRoot = new Node(openList.heap.get(0).getX(),openList.heap.get(0).getY(),openList.heap.get(0).getG(),openList.heap.get(0).getH());
				if((newNode.getX() == heapRoot.getX()+1 && newNode.getY() == heapRoot.getY()) || (newNode.getX() == heapRoot.getX() && newNode.getY() == heapRoot.getY()+1) || 
				(newNode.getX() == heapRoot.getX()-1 && newNode.getY() == heapRoot.getY()) || (newNode.getX() == heapRoot.getX() && newNode.getY() == heapRoot.getY()-1)){
					temp.setChild(heapRoot);
				}
				current.setX(openList.heap.get(0).getX());
				current.setY(openList.heap.get(0).getY());
				start = openList.delete();
				neighbors.clear();
			}
			Node temp = root;
			while(temp.getChild() != null){
				plannedRoute.add(temp);
				temp = temp.getChild();
			}
			plannedRoute.add(temp);
		}
		else {
			while(current.getX() != 0 || current.getY() != 0){
				Node topNeighbor = new Node(0,0,0,0);	
				Node bottomNeighbor = new Node(0,0,0,0);	
				Node leftNeighbor = new Node(0,0,0,0);	
				Node rightNeighbor = new Node(0,0,0,0);
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
				else if(current.getX()-1 < 0){
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
				else if(current.getY()-1 < 0){
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
				else if(current.getX()+1 > 9){
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
				else if(current.getY()+1 > 9){
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
				for(int i = 0; i < neighbors.size(); i++){	
					inClosedList = false;
					isBlocked = false;
					neighbors.get(i).setG(computeGOrH(start, neighbors.get(i)));
					neighbors.get(i).setH(computeGOrH(neighbors.get(i),dest));	
					neighbors.get(i).setF(neighbors.get(i).getG()+neighbors.get(i).getH());
					for(Node closedListNode : closedList){
						if(neighbors.get(i).getX() == closedListNode.getX() && neighbors.get(i).getY() == closedListNode.getY()){
							inClosedList = true;
							break;
						}	
					}
					if(blocked[neighbors.get(i).getX()][neighbors.get(i).getY()] == 1){
						isBlocked = true;
					}
					if(!inClosedList && !isBlocked){
						openList.insert(neighbors.get(i));
					}
				}		
				if(openList.heap.size() == 0){
					return null;
				}
				Node newNode = new Node(0,0,0,0);
				newNode.setX(current.getX());
				newNode.setY(current.getY());
				newNode.setG(current.getG());
				newNode.setH(current.getH());
				closedList.add(newNode);
				inClosedList = false;
				isBlocked = false;
				Node temp = root;
				while(temp.getChild() != null){
					temp = temp.getChild();
				}
				Node heapRoot = new Node(openList.heap.get(0).getX(),openList.heap.get(0).getY(),openList.heap.get(0).getG(),openList.heap.get(0).getH());
				if((newNode.getX() == heapRoot.getX()+1 && newNode.getY() == heapRoot.getY()) || (newNode.getX() == heapRoot.getX() && newNode.getY() == heapRoot.getY()+1) || 
				(newNode.getX() == heapRoot.getX()-1 && newNode.getY() == heapRoot.getY()) || (newNode.getX() == heapRoot.getX() && newNode.getY() == heapRoot.getY()-1)){
					temp.setChild(heapRoot);
				}
				current.setX(openList.heap.get(0).getX());
				current.setY(openList.heap.get(0).getY());
				start = openList.delete();
				neighbors.clear();
			}
			Node temp = root;
			while(temp.getChild() != null){
				plannedRoute.add(temp);
				temp = temp.getChild();
			}
			plannedRoute.add(temp);
		}
		return plannedRoute;
	}
	
	public static ArrayList<Node> executePath(boolean isForward, Grid grid){
		int count = 0;
		ArrayList<Node> previousRoute = new ArrayList<Node>();
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
		if(plannedRoute == null){
			return null;
		}
		//Initializing nodes of plannedRoute to Blocked or Unblocked
		for(int i = 0; i < plannedRoute.size(); i++){
			if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()].getBlocked()){
				plannedRoute.get(i).setBlocked(true);
			}
		}
		
		int i = 0;
		
		//Actually traversing the grid
		while(true){
			i = 0;
			for(int k = 0; k < plannedRoute.size(); k++){
				if(grid.array[plannedRoute.get(k).getX()][plannedRoute.get(k).getY()].getBlocked()){
					plannedRoute.get(k).setBlocked(true);
				}
			}
			for(i = 0; i < plannedRoute.size(); i++){
				if(!plannedRoute.get(i).getBlocked()){
					if(plannedRoute.get(i).getX() == 0 && plannedRoute.get(i).getY() == 0){
						if(grid.array[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()].getBlocked()){
							blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
						}
					}
					else if(plannedRoute.get(i).getX() == 9 && plannedRoute.get(i).getY() == 9){
						if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
							blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
						}
					}
					else if(plannedRoute.get(i).getX() == 0 && plannedRoute.get(i).getY() == 9){
						if(grid.array[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()].getBlocked()){
							blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
						}
					}
					else if(plannedRoute.get(i).getX() == 9 && plannedRoute.get(i).getY() == 0){
						if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
							blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
						}
					}
					else if(plannedRoute.get(i).getX() == 0){
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
					else if(plannedRoute.get(i).getY() == 0){
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
					else if(plannedRoute.get(i).getX() == 9){
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
					else if(plannedRoute.get(i).getY() == 9){
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
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
						}
					}
				}
				if(plannedRoute.get(i).getBlocked()){
					blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()] = 1;
					if(isForward){
						if(plannedRoute.get(0).getX() == 0 && plannedRoute.get(0).getY() == 0){
							for(int j = 0; j < i; j++){
								previousRoute.add(plannedRoute.get(j));
							}
						}
						else {
							for(int j = 1; j < i; j++){
								previousRoute.add(plannedRoute.get(j));
							}
						}
					}
					else{
						if(plannedRoute.get(0).getX() == 9 && plannedRoute.get(0).getY() == 9){
							for(int j = 0; j < i; j++){
								previousRoute.add(plannedRoute.get(j));
							}
						}
						else {
							for(int j = 1; j < i; j++){
								previousRoute.add(plannedRoute.get(j));
							}
						}
					}
					ranIntoBlock = true;
					break;
				}
			}
			if(ranIntoBlock == false){
				break;
			}
			Node newNode = new Node(plannedRoute.get(i-1).getX(),plannedRoute.get(i-1).getY(),0,0);
			plannedRoute = planning(newNode,isForward, blocked);
			ranIntoBlock = false;
			if(plannedRoute == null){
				return null;
			}
		}
		for(Node node : plannedRoute){
			previousRoute.add(node);
		}
		return previousRoute;
	}
	public static int computeGOrH(Node start, Node dest){
		int result = Math.abs(dest.getX() - start.getX()) + Math.abs(dest.getY() - start.getY());
		return result;
	}
}
