import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class RepeatedAStar{

	private RepeatedAStar(){

	}

	public static ArrayList<Node> planning(Node current, boolean isForward, int[][] blocked){
		if(!isForward && current.getX() == 0 && current.getY() == 0){
			current.setX(100);	
			current.setY(100);
		}
		boolean impossible = false;
		boolean duplicate = false;
		boolean isBlocked = false;
		Node start = new Node(current.getX(),current.getY(),current.getG(),current.getH());
		Node dest = new Node(100,100,0,0);
		if(!isForward){
			dest.setX(0);
			dest.setY(0);
		}
		Node topNeighbor = new Node(0,0,0,0);	
		Node bottomNeighbor = new Node(0,0,0,0);	
		Node leftNeighbor = new Node(0,0,0,0);	
		Node rightNeighbor = new Node(0,0,0,0);
		Node root = new Node(current.getX(),current.getY(),current.getG(),current.getH());
		Node temp = root;
		ArrayList<Node> plannedRoute = new ArrayList<Node>();
		ArrayList<Node> closedList = new ArrayList<Node>();
		ArrayList<Node> neighbors = new ArrayList<Node>();
		boolean inClosedList = false;
		MinHeap openList = new MinHeap();
		openList.heap.clear();
		if(isForward){
			while(current.getX() != 100 || current.getY() != 100){
				duplicate = false;
				if(current.getX() -1 < 0 && current.getY()-1 < 0){
					int value1 = current.getX();
					int value2 = current.getY()+1;
					int value3 = current.getX()+1;
					int value4 = current.getY();
					bottomNeighbor.setX(value1);
					bottomNeighbor.setY(value2);
					neighbors.add(bottomNeighbor);
					rightNeighbor.setX(value3);
					rightNeighbor.setY(value4);
					neighbors.add(rightNeighbor);							
				}
				else if(current.getX()+1 > 100 && current.getY() + 1 > 100){
					int value1 = current.getX();
					int value2 = current.getY()-1;
					int value3 = current.getX()-1;
					int value4 = current.getY();
					topNeighbor.setX(value1);
					topNeighbor.setY(value2);
					neighbors.add(topNeighbor);
					leftNeighbor.setX(value3);
					leftNeighbor.setY(value4);
					neighbors.add(leftNeighbor);
				}
				else if(current.getX()-1 < 0 && current.getY()+1 > 100){
					int value1 = current.getX();
					int value2 = current.getY()-1;
					int value3 = current.getX()+1;
					int value4 = current.getY();
					topNeighbor.setX(value1);
					topNeighbor.setY(value2);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(value3);
					rightNeighbor.setY(value4);
					neighbors.add(rightNeighbor);
				}
				else if(current.getX()+1 > 100 && current.getY()-1 < 0){
					int value1 = current.getX();
					int value2 = current.getY()+1;
					int value3 = current.getX()-1;
					int value4 = current.getY();
					leftNeighbor.setX(value3);
					leftNeighbor.setY(value4);
					neighbors.add(leftNeighbor);
					bottomNeighbor.setX(value1);
					bottomNeighbor.setY(value2);
					neighbors.add(bottomNeighbor);
				}
				else if(current.getX() == 0){
					int value1 = current.getX();
					int value2 = current.getY()-1;
					int value3 = current.getX()+1;
					int value4 = current.getY();
					int value5 = current.getY()+1;
					topNeighbor.setX(value1);
					topNeighbor.setY(value2);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(value3);
					rightNeighbor.setY(value4);
					neighbors.add(rightNeighbor);
					bottomNeighbor.setX(value1);
					bottomNeighbor.setY(value5);
					neighbors.add(bottomNeighbor);
				}
				else if(current.getY() == 0){
					int value1 = current.getX()-1;
					int value2 = current.getY();
					int value3 = current.getX()+1;
					int value4 = current.getX();
					int value5 = current.getY()+1;
					leftNeighbor.setX(value1);
					leftNeighbor.setY(value2);
					neighbors.add(leftNeighbor);
					bottomNeighbor.setX(value4);
					bottomNeighbor.setY(value5);
					neighbors.add(bottomNeighbor);
					rightNeighbor.setX(value3);
					rightNeighbor.setY(value2);
					neighbors.add(rightNeighbor);
				}
				else if(current.getX() == 100){
					int value1 = current.getX();
					int value2 = current.getY()-1;
					int value3 = current.getX()-1;
					int value4 = current.getY();
					int value5 = current.getY()+1;
					topNeighbor.setX(value1);
					topNeighbor.setY(value2);
					neighbors.add(topNeighbor);
					leftNeighbor.setX(value3);
					leftNeighbor.setY(value4);
					neighbors.add(leftNeighbor);
					bottomNeighbor.setX(value1);
					bottomNeighbor.setY(value5);
					neighbors.add(bottomNeighbor);
				}
				else if(current.getY() == 100){
					int value1 = current.getX()-1;
					int value2 = current.getY();
					int value3 = current.getX()+1;
					int value4 = current.getX();
					int value5 = current.getY()-1;
					leftNeighbor.setX(value1);
					leftNeighbor.setY(value2);
					neighbors.add(leftNeighbor);
					topNeighbor.setX(value4);
					topNeighbor.setY(value5);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(value3);
					rightNeighbor.setY(value2);
					neighbors.add(rightNeighbor);
				}
				else {
					int value1 = current.getX();
					int value2 = current.getY()-1;
					int value3 = current.getX()+1;
					int value4 = current.getY();
					int value5 = current.getY()+1;
					int value6 = current.getX()-1;
					leftNeighbor.setX(value6);
					leftNeighbor.setY(value4);
					neighbors.add(leftNeighbor);
					topNeighbor.setX(value1);
					topNeighbor.setY(value2);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(value3);
					rightNeighbor.setY(value4);
					neighbors.add(rightNeighbor);
					bottomNeighbor.setX(value1);
					bottomNeighbor.setY(value5);
					neighbors.add(bottomNeighbor);
				}
				//System.out.println("Neighbors size: " + neighbors.size());
				for(int i = 0; i < neighbors.size(); i++){	
					//System.out.println("Neighbor(X , Y): " + neighbors.get(i).getX() + " , " + neighbors.get(i).getY());
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
					/*for(Node blockedNode : blockedNodes){
						if(neighbors.get(i).getX() == blockedNode.getX() && neighbors.get(i).getY() == blockedNode.getY()){
							isBlocked = true;
							break;
						}
					}*/
					if(blocked[neighbors.get(i).getX()][neighbors.get(i).getY()] == 1){
						//System.out.println("Blocked Nodes(X , Y): " + neighbors.get(i).getX() + " , " + neighbors.get(i).getY());
						isBlocked = true;
					}
					/*if(inClosedList || isBlocked){
						continue;
					}*/
					if(inClosedList || isBlocked){
						continue;
					}
					Node node = new Node(neighbors.get(i).getX(),neighbors.get(i).getY(),neighbors.get(i).getG(),neighbors.get(i).getH());
					node.setF(neighbors.get(i).getF());
					openList.insert(node);
				}
				//System.out.println();
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
				temp = root;
				while(temp.getChild() != null){
					temp = temp.getChild();
				}
				Node heapRoot = new Node(openList.heap.get(0).getX(),openList.heap.get(0).getY(),openList.heap.get(0).getG(),openList.heap.get(0).getH());
				if((temp.getX() == heapRoot.getX()+1 && temp.getY() == heapRoot.getY()) || (temp.getX() == heapRoot.getX() && temp.getY() == heapRoot.getY()+1) || 
				(temp.getX() == heapRoot.getX()-1 && temp.getY() == heapRoot.getY()) || (temp.getX() == heapRoot.getX() && temp.getY() == heapRoot.getY()-1)){
					//System.out.println("HeapRoot(X , Y): " + heapRoot.getX() + " , " + heapRoot.getY());
					//temp.setChild(heapRoot);
				}
				temp.setChild(heapRoot);
				current.setX(openList.heap.get(0).getX());
				current.setY(openList.heap.get(0).getY());
				start = openList.delete();
				start.setG(0);
				neighbors.clear();
			}
			//System.out.println();
			temp = root;
			while(temp.getChild() != null){
				plannedRoute.add(temp);
				temp = temp.getChild();
			}
			plannedRoute.add(temp);
			/*for(Node node : blockedNodes){
				System.out.println("Blocked Node(X , Y): " + node.getX() + " , " + node.getY());
			}*/
			//System.out.println();
			for(Node node : plannedRoute){
				//System.out.println("Planned Route(X , Y): " + node.getX() + " , " + node.getY());
			}
			//System.out.println();
		}
		else {
			while(current.getX() != 0 || current.getY() != 0){
				duplicate = false;
				if(current.getX() -1 < 0 && current.getY()-1 < 0){
					int value1 = current.getX();
					int value2 = current.getY()+1;
					int value3 = current.getX()+1;
					int value4 = current.getY();
					bottomNeighbor.setX(value1);
					bottomNeighbor.setY(value2);
					neighbors.add(bottomNeighbor);
					rightNeighbor.setX(value3);
					rightNeighbor.setY(value4);
					neighbors.add(rightNeighbor);							
				}
				else if(current.getX()+1 > 100 && current.getY() + 1 > 100){
					int value1 = current.getX();
					int value2 = current.getY()-1;
					int value3 = current.getX()-1;
					int value4 = current.getY();
					topNeighbor.setX(value1);
					topNeighbor.setY(value2);
					neighbors.add(topNeighbor);
					leftNeighbor.setX(value3);
					leftNeighbor.setY(value4);
					neighbors.add(leftNeighbor);
				}
				else if(current.getX()-1 < 0 && current.getY()+1 > 100){
					int value1 = current.getX();
					int value2 = current.getY()-1;
					int value3 = current.getX()+1;
					int value4 = current.getY();
					topNeighbor.setX(value1);
					topNeighbor.setY(value2);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(value3);
					rightNeighbor.setY(value4);
					neighbors.add(rightNeighbor);
				}
				else if(current.getX()+1 > 100 && current.getY()-1 < 0){
					int value1 = current.getX();
					int value2 = current.getY()+1;
					int value3 = current.getX()-1;
					int value4 = current.getY();
					leftNeighbor.setX(value3);
					leftNeighbor.setY(value4);
					neighbors.add(leftNeighbor);
					bottomNeighbor.setX(value1);
					bottomNeighbor.setY(value2);
					neighbors.add(bottomNeighbor);
				}
				else if(current.getX() == 0){
					int value1 = current.getX();
					int value2 = current.getY()-1;
					int value3 = current.getX()+1;
					int value4 = current.getY();
					int value5 = current.getY()+1;
					topNeighbor.setX(value1);
					topNeighbor.setY(value2);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(value3);
					rightNeighbor.setY(value4);
					neighbors.add(rightNeighbor);
					bottomNeighbor.setX(value1);
					bottomNeighbor.setY(value5);
					neighbors.add(bottomNeighbor);
				}
				else if(current.getY() == 0){
					int value1 = current.getX()-1;
					int value2 = current.getY();
					int value3 = current.getX()+1;
					int value4 = current.getX();
					int value5 = current.getY()+1;
					leftNeighbor.setX(value1);
					leftNeighbor.setY(value2);
					neighbors.add(leftNeighbor);
					bottomNeighbor.setX(value4);
					bottomNeighbor.setY(value5);
					neighbors.add(bottomNeighbor);
					rightNeighbor.setX(value3);
					rightNeighbor.setY(value2);
					neighbors.add(rightNeighbor);
				}
				else if(current.getX() == 100){
					int value1 = current.getX();
					int value2 = current.getY()-1;
					int value3 = current.getX()-1;
					int value4 = current.getY();
					int value5 = current.getY()+1;
					topNeighbor.setX(value1);
					topNeighbor.setY(value2);
					neighbors.add(topNeighbor);
					leftNeighbor.setX(value3);
					leftNeighbor.setY(value4);
					neighbors.add(leftNeighbor);
					bottomNeighbor.setX(value1);
					bottomNeighbor.setY(value5);
					neighbors.add(bottomNeighbor);
				}
				else if(current.getY() == 100){
					int value1 = current.getX()-1;
					int value2 = current.getY();
					int value3 = current.getX()+1;
					int value4 = current.getX();
					int value5 = current.getY()-1;
					leftNeighbor.setX(value1);
					leftNeighbor.setY(value2);
					neighbors.add(leftNeighbor);
					topNeighbor.setX(value4);
					topNeighbor.setY(value5);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(value3);
					rightNeighbor.setY(value2);
					neighbors.add(rightNeighbor);
				}
				else {
					int value1 = current.getX();
					int value2 = current.getY()-1;
					int value3 = current.getX()+1;
					int value4 = current.getY();
					int value5 = current.getY()+1;
					int value6 = current.getX()-1;
					leftNeighbor.setX(value6);
					leftNeighbor.setY(value4);
					neighbors.add(leftNeighbor);
					topNeighbor.setX(value1);
					topNeighbor.setY(value2);
					neighbors.add(topNeighbor);
					rightNeighbor.setX(value3);
					rightNeighbor.setY(value4);
					neighbors.add(rightNeighbor);
					bottomNeighbor.setX(value1);
					bottomNeighbor.setY(value5);
					neighbors.add(bottomNeighbor);
				}
				//System.out.println("Neighbors size: " + neighbors.size());
				for(int i = 0; i < neighbors.size(); i++){	
					//System.out.println("Neighbor(X , Y): " + neighbors.get(i).getX() + " , " + neighbors.get(i).getY());
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
					/*for(Node blockedNode : blockedNodes){
						if(neighbors.get(i).getX() == blockedNode.getX() && neighbors.get(i).getY() == blockedNode.getY()){
							isBlocked = true;
							break;
						}
					}*/
					if(blocked[neighbors.get(i).getX()][neighbors.get(i).getY()] == 1){
						//System.out.println("Blocked Nodes(X , Y): " + neighbors.get(i).getX() + " , " + neighbors.get(i).getY());
						isBlocked = true;
					}
					/*if(inClosedList || isBlocked){
						continue;
					}*/
					if(inClosedList || isBlocked){
						continue;
					}
					Node node = new Node(neighbors.get(i).getX(),neighbors.get(i).getY(),neighbors.get(i).getG(),neighbors.get(i).getH());
					node.setF(neighbors.get(i).getF());
					openList.insert(node);
				}
				//System.out.println();
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
				temp = root;
				while(temp.getChild() != null){
					temp = temp.getChild();
				}
				Node heapRoot = new Node(openList.heap.get(0).getX(),openList.heap.get(0).getY(),openList.heap.get(0).getG(),openList.heap.get(0).getH());
				if((temp.getX() == heapRoot.getX()+1 && temp.getY() == heapRoot.getY()) || (temp.getX() == heapRoot.getX() && temp.getY() == heapRoot.getY()+1) || 
				(temp.getX() == heapRoot.getX()-1 && temp.getY() == heapRoot.getY()) || (temp.getX() == heapRoot.getX() && temp.getY() == heapRoot.getY()-1)){
					//System.out.println("HeapRoot(X , Y): " + heapRoot.getX() + " , " + heapRoot.getY());
					//temp.setChild(heapRoot);
				}
				temp.setChild(heapRoot);
				current.setX(openList.heap.get(0).getX());
				current.setY(openList.heap.get(0).getY());
				start = openList.delete();
				start.setG(0);
				neighbors.clear();
			}
			//System.out.println();
			temp = root;
			while(temp.getChild() != null){
				plannedRoute.add(temp);
				temp = temp.getChild();
			}
			plannedRoute.add(temp);
			/*for(Node node : blockedNodes){
				System.out.println("Blocked Node(X , Y): " + node.getX() + " , " + node.getY());
			}*/
			//System.out.println();
			for(Node node : plannedRoute){
				//System.out.println("Planned Route(X , Y): " + node.getX() + " , " + node.getY());
			}
			//System.out.println();
		}
		return plannedRoute;
	}
	
	public static ArrayList<Node> executePath(boolean isForward, Grid grid){
		int count = 0;
		ArrayList<Node> previousRoute = new ArrayList<Node>();
		//ArrayList<Node> blockedNodes = new ArrayList<Node>();
		boolean ranIntoBlock = false;
		int[][] blocked = new int[101][101]; //1 is blocked, 0 is unblocked
		for(int i = 0; i < 101; i++){
			for(int j = 0; j < 101; j++){
				blocked[i][j] = 0;
			}
		}
		Node current = new Node(0,0,0,0);
		if(!isForward){
			current.setX(100);
			current.setY(100);
		}
		ArrayList<Node> plannedRoute = planning(current, isForward, blocked);
		if(plannedRoute == null){
			return null;
		}
		for(Node node : plannedRoute){
			//System.out.println("PlannedRoute(X , Y): " + node.getX() + " , " + node.getY());
		}
		//Initializing nodes of plannedRoute to Blocked or Unblocked
		for(int i = 0; i < plannedRoute.size(); i++){
			if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()].getBlocked()){
				plannedRoute.get(i).setBlocked(true);
			}
		}
		
		int i = 0;
		/*for(int a = 0; a < 101; a++){
			for(int b = 0; b < 101; b++){
				if(grid.array[a][b].getBlocked()){
					Node node = new Node(a,b,0,0);
					blockedNodes.add(node);
				}
			}
		}*/
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
							int value1 = plannedRoute.get(i).getX()+1;
							int value2 = plannedRoute.get(i).getY();
							blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
							int value1 = plannedRoute.get(i).getX();
							int value2 = plannedRoute.get(i).getY()+1;
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
					}
					else if(plannedRoute.get(i).getX() == 100 && plannedRoute.get(i).getY() == 100){
						if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
							int value1 = plannedRoute.get(i).getX()-1;
							int value2 = plannedRoute.get(i).getY();
							blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
							int value1 = plannedRoute.get(i).getX();
							int value2 = plannedRoute.get(i).getY()-1;
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
					}
					else if(plannedRoute.get(i).getX() == 0 && plannedRoute.get(i).getY() == 100){
						if(grid.array[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()].getBlocked()){
							int value1 = plannedRoute.get(i).getX()+1;
							int value2 = plannedRoute.get(i).getY();
							blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
							int value1 = plannedRoute.get(i).getX();
							int value2 = plannedRoute.get(i).getY()-1;
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
					}
					else if(plannedRoute.get(i).getX() == 100 && plannedRoute.get(i).getY() == 0){
						if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
							int value1 = plannedRoute.get(i).getX()-1;
							int value2 = plannedRoute.get(i).getY();
							blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
							int value1 = plannedRoute.get(i).getX();
							int value2 = plannedRoute.get(i).getY()+1;
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
					}
					else if(plannedRoute.get(i).getX()-1 < 0 && plannedRoute.get(i).getY()-1 >= 0){
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
							int value1 = plannedRoute.get(i).getX();
							int value2 = plannedRoute.get(i).getY()-1;
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()].getBlocked()){
							int value1 = plannedRoute.get(i).getX()+1;
							int value2 = plannedRoute.get(i).getY();
							blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
							int value1 = plannedRoute.get(i).getX();
							int value2 = plannedRoute.get(i).getY()+1;
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
					}
					else if(plannedRoute.get(i).getX()-1 >= 0 && plannedRoute.get(i).getY()-1 < 0){
						if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
							int value1 = plannedRoute.get(i).getX()-1;
							int value2 = plannedRoute.get(i).getY();
							blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()].getBlocked()){
							int value1 = plannedRoute.get(i).getX()+1;
							int value2 = plannedRoute.get(i).getY();
							blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
							int value1 = plannedRoute.get(i).getX();
							int value2 = plannedRoute.get(i).getY()+1;
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
					}
					else if(plannedRoute.get(i).getX()+1 > 100 && plannedRoute.get(i).getY() + 1 <= 100){
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
							int value1 = plannedRoute.get(i).getX();
							int value2 = plannedRoute.get(i).getY()-1;
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
							int value1 = plannedRoute.get(i).getX();
							int value2 = plannedRoute.get(i).getY()+1;
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
							int value1 = plannedRoute.get(i).getX()-1;
							int value2 = plannedRoute.get(i).getY();
							blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
					}
					else if(plannedRoute.get(i).getX()+1 <=100 && plannedRoute.get(i).getY()+1 > 100){
						if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
							int value1 = plannedRoute.get(i).getX()-1;
							int value2 = plannedRoute.get(i).getY();
							blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()].getBlocked()){
							int value1 = plannedRoute.get(i).getX()+1;
							int value2 = plannedRoute.get(i).getY();
							blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
							int value1 = plannedRoute.get(i).getX();
							int value2 = plannedRoute.get(i).getY()-1;
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
					}
					else {
						if(grid.array[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()].getBlocked()){
							int value1 = plannedRoute.get(i).getX()-1;
							int value2 = plannedRoute.get(i).getY();
							blocked[plannedRoute.get(i).getX()-1][plannedRoute.get(i).getY()] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()].getBlocked()){
							int value1 = plannedRoute.get(i).getX()+1;
							int value2 = plannedRoute.get(i).getY();
							blocked[plannedRoute.get(i).getX()+1][plannedRoute.get(i).getY()] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1].getBlocked()){
							int value1 = plannedRoute.get(i).getX();
							int value2 = plannedRoute.get(i).getY()+1;
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()+1] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
						if(grid.array[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1].getBlocked()){
							int value1 = plannedRoute.get(i).getX();
							int value2 = plannedRoute.get(i).getY()-1;
							blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()-1] = 1;
							Node node = new Node(value1,value2,0,0);
							//blockedNodes.add(node);
						}
					}
				}
				if(plannedRoute.get(i).getBlocked()){
					blocked[plannedRoute.get(i).getX()][plannedRoute.get(i).getY()] = 1;
					Node node = new Node(plannedRoute.get(i).getX(),plannedRoute.get(i).getY(),0,0);
					//blockedNodes.add(node);
					for(int j = 0; j < i; j++){
						previousRoute.add(plannedRoute.get(j));
					}
					/*if(isForward){
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
						if(plannedRoute.get(0).getX() == 100 && plannedRoute.get(0).getY() == 100){
							for(int j = 0; j < i; j++){
								previousRoute.add(plannedRoute.get(j));
							}
						}
						else {
							for(int j = 1; j < i; j++){
								previousRoute.add(plannedRoute.get(j));
							}
						}
					}*/
					ranIntoBlock = true;
					break;
				}
			}
			if(ranIntoBlock == false){
				break;
			}
			for(Node node : previousRoute){
				//System.out.println("Previous Route(X , Y): " + node.getX() + " , " + node.getY());
			}
			//System.out.println();
			count++;
			if(count == 20){
				//return null;
			}
			//System.out.println("blah");
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
