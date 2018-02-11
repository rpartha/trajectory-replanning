import java.util.ArrayList;

public class MinHeap{

    private int size;
    private ArrayList<Node> heap;

    public MinHeap(){
        this.heap = new ArrayList<Node>();
        this.size = -1;
        constructHeap();
    }

    public void constructHeap(){
        if(this.heap.size() > 1){
            for(int i = this.heap.size()/2; i >= 0; --i){
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
		bh.insert(new Node(3, 5, Integer.MAX_VALUE, 0));
		//bh.print();
        bh.insert(new Node(6, 8, 55, 10));
        bh.insert(new Node(1, 2, 22, 5));
        bh.insert(new Node(0, 0, 15, 2));
        bh.delete();
        bh.print();
        //bh.delete(0);
        //bh.print();
	}*/
}