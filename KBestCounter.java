import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class KBestCounter<T extends Comparable<? super T>> {

    PriorityQueue<T> heap;
    int k;
    

    public KBestCounter(int k) {
        //todo
        heap=new PriorityQueue<> ();
        this.k=k;
    }

    //inserts an element into heap in O(log k)
    //heap of size k
    public void count(T x) {
        //todo
        if(heap.size()<k){
            heap.add(x);
        }
        else if((x.compareTo(heap.peek())>0 ) && heap.size()==k ){
            heap.remove();
            heap.add(x);
        }
    }

    //returns list of the k-largest elements
    public List<T> kbest() {
        //todo
        List <T>arry=new ArrayList<>();
        int sizeOfHeap=heap.size();
        
        for(int i=0; i<sizeOfHeap;i++){
            arry.add(heap.poll());
        }
        
      
        for(int i=0;i<arry.size();i++){
            heap.add(arry.get(i));
        }
            
        return arry; // replace this
    }
    
}