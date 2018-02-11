import java.util.ArrayList;

public class Node implements Comparable<Node>{

    /* define private instance variables */
    private int x, y;
    private int f, g, h;
    //private boolean start, goal;
    private boolean visited;

    private Node parent;

    //private ArrayList<Node> openList; //neighbors

    public Node(int x, int y, int g, int h){
        this.parent = null;
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int x){
        this.y = y;
    }

    /*public int getF(){
        return f;
    }*

    public void setF(int f){
        this.f = f;
    }*/

    public int getF(){
        return g + h;
    }

    public int getG(){
        return g;
    }

    public void setG(int g){
        this.g = g;
    }

    public int getH(){
        return h;
    }

    public void setH(int h){
        this.h = h;
    }

    public boolean getVisited(){
        return visited;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }

    public Node getParent(){
        return parent;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    /*public ArrayList<Node> getOpenList(){
        return openList;
    }

    public void setOpenList(ArrayList<Node> openList){
        this.openList = openList;
    }*/

    @Override
    public int compareTo(Node other) {
    	if(getF() == other.getF()){
    		return Integer.compare(getG(), other.getG());
    	}
        return Integer.compare(getF(), other.getF());
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Node){
            Node other = (Node) object;

            return (x == (other.x) && y == (other.y));
        }

        return false;
    }
}