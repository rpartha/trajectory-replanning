import java.util.ArrayList;

public class MinHeap{

    private int size;
    public ArrayList<Node> heap;

    public MinHeap(){
        this.heap = new ArrayList<Node>();
        this.size = -1;
        constructHeap();
    }

    public void constructHeap(){
        if(this.heap.size() > 1){
            for(int i = this.heap.size()/2; i >= 0; i--){
                heapify(i);
            }
        }
    }

    /* rearrange heap to maintain heap structure */
    private void heapify(int k){
        int l = (2*k) + 1; //left child
        int r = (2*k) + 2; //right child

        int min = k;

        if(l <= this.size){
            if(this.heap.get(l).getF() == this.heap.get(k).getF()){
                if(this.heap.get(l).getG() < this.heap.get(k).getG()){
                    min = l;
                } else{
                    min = k;
                }
            }

            else if(this.heap.get(l).getF() < this.heap.get(k).getF()){
                min = l;
            } 
        
            else{
                min = k;
            }
        } 

        if(r <= this.size){
            if(this.heap.get(r).getF() == this.heap.get(min).getF()){
                if(this.heap.get(r).getG() < this.heap.get(min).getG()){
                    min = r;
                }
            }

            if(this.heap.get(r).getF() < this.heap.get(min).getF()){
                min = r;
            } 
        }

        if(min != k){
            swap(min, k);
            heapify(min);
        }
    }

    /* swap values in heap */
    private void swap(int a, int b){
        Node t = this.heap.get(a);
        this.heap.set(a, this.heap.get(b));
		this.heap.set(b, t);
    }

    public Node peek(){
        return this.heap.get(0);
    }

    /* insert element into  heap */
    public void insert(Node x){
       this.heap.add(x);
       this.size++;
       constructHeap();
    }
    
    /* extracts min index from heap */
    public Node delete(){
        try{
            if(this.size < 0){
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
        } catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        Node s = this.heap.get(0);

        this.heap.set(0, this.heap.get(this.size));
		this.heap.remove(this.size);
		this.size--;

		heapify(0);

		return s;
    }

    public void print(){
		for(Node n: heap){
			System.out.println(n.getX() + ", " + n.getY());
		}
	}

	/*public static void main(String[] args){
		MinHeap bh = new MinHeap();
		Node node1 = new Node(3, 5, 0, 11);
		Node node2 = new Node(6,8,0,2);
		Node node3 = new Node(1,2,0,3);
		Node node4 = new Node(0,0,0,1);
		node1.setF(100);
		node2.setF(1000);
		node3.setF(10000);
		node4.setF(2);
		
		bh.insert(node1);
        bh.insert(node2);
        bh.insert(node3);
        bh.insert(node4);
        bh.delete();
        bh.print();
        System.out.println("-----------");
        bh.delete();
        bh.print();
        System.out.println("-----------");
        bh.delete();
        bh.print();
        System.out.println("-----------");
        bh.delete();
        bh.print();
        System.out.println("-----------");
        //bh.delete(0);
        //bh.print();
	}*/
}
