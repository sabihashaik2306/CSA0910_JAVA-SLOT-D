import java.util.*;
public class Task07 {
    static long measureArrayList(int n) {
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<n;i++) list.add(i);
        long s=System.nanoTime();
        for(int i=0;i<Math.min(n,50000);i++) list.add(0,i);
        return System.nanoTime()-s;
    }
    static long measureArrayDeque(int n) {
        ArrayDeque<Integer> deque=new ArrayDeque<>();
        for(int i=0;i<n;i++) deque.addLast(i);
        long s=System.nanoTime();
        for(int i=0;i<Math.min(n,50000);i++) deque.addFirst(i);
        return System.nanoTime()-s;
    }
    public static void main(String[] args) {
        System.out.println("Q7 redesign: Up Next queue front insertion bottleneck");
        for(int n : new int[]{1000,5000,10000}) {
            System.out.println("n="+n+" | ArrayList front inserts ns="+measureArrayList(n)
                +" | ArrayDeque front inserts ns="+measureArrayDeque(n));
        }
        System.out.println("Diagnosis: ArrayList shifts existing elements for front insertion (O(n) per insert).");
        System.out.println("Redesign: ArrayDeque supports addFirst/addLast in amortized O(1), avoiding repeated shifts.");
        System.out.println("Trade-off: ArrayDeque does not provide efficient indexed access; use it for deque/queue workloads.");
    }
}
