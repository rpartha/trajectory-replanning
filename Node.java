import java.util.ArrayList;

public class Node{

    /* define private instance variables */
    private int x, y;
    private int f, g, h;
    private boolean start, goal;
    private boolean blocked, traversed, visited;

    private Node parent;

    private ArrayList<Node> openList; //neighbors

    public Node(){
        this.x = 0;
        this.y = 0;
        this.blocked = false;
    }

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, boolean blocked){
        this.x = x;
        this.y = y;
        this.blocked = blocked;
        this.openList = new ArrayList<Node>();
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

    public int getF(){
        return f;
    }

    public void setF(int f){
        this.f = f;
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

    public boolean getBlocked(){
        return blocked;
    }

    public void setBlocked(boolean blocked){
        this.blocked = blocked;
    }

    public boolean getTraversed(){
        return traversed;
    }

    public void setTraversed(boolean traversed){
        this.traversed = traversed;
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

    public ArrayList<Node> getOpenList(){
        return openList;
    }

    public void setOpenList(ArrayList<Node> openList){
        this.openList = openList;
    }
}