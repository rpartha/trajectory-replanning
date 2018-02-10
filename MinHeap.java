import java.util.ArrayList;

public class MinHeap{

    // our heap has a branching factor of 2 //

    private static int idx = 0; //heap index
    public Node[] heap;

    public MinHeap(){
        heap = new Node[5000]; //doesn't matter if it's hard-coded for now
    }

    /* insert element into heap */
    public void insert(Node x){
        heap[++idx] = x;
        siftUp(idx);
    }
    
    /* remove element from heap */
    public void delete(int x){
        heap[x] = heap[idx];
        idx--;
        siftUp(x);
        siftDown(x);
    }

    private void siftUp(int k){
        int p = k/2;
        Node nk = heap[k];

        if(k > 1 && nk.getF() < heap[p].getF()){
            while(k > 1 && nk.getG() > heap[p].getG()){
                heap[k] = heap[p];
                k = p;
                p = k/2;
            }
        }

        while(k > 1 && nk.getF() < heap[p].getF()){
             heap[k] = heap[p];
             k = p;
             p = k/2;
        }

        heap[k] = nk;
    }

    private void siftDown(int k){
        int left = 2*k;
        int right = (2*k) + 1;

        Node nk = heap[k];

        while(left <= idx){
            if(right <= idx){
                if((nk.getF() > heap[right].getF()) && (heap[left].getF() > heap[right].getF())){
                    heap[k] = heap[right];
                    k = right;
                }

                else if((nk.getF() > heap[left].getF()) && (heap[right].getF() > heap[left].getF())){
                    heap[k] = heap[left];
                    k = left;
                }

                else{
                    break;
                }
            }

            else if(nk.getF() > heap[left].getF()){
                heap[k] = heap[left];
                k = left;
            }

            else{
                break;
            }

            left = 2*k;
            right = (2*k) + 1;
        }

        heap[k] = nk;
    }

    /*private int swap(int a, int b){
        int t = a;
        a = b;
        b = t;
    }*/

    public void reset(){
        idx = 0;
    }

    public int contains(Node n){
        int j = 0;

        if(isEmpty()){
            return -1;
        }

        else{
            for(int i = 0; i <= idx; i++){
                if((heap[i].getX() == n.getX()) && (heap[i].getY() == n.getY())){
                    j = i;
                    break;
                }
            }
        }
        
        return j;
    }

    public boolean isEmpty(){
        return ((idx == 0) ? true : false);
    }
}