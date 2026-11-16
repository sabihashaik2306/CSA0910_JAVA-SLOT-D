import java.util.*;
public class Task02 {
    static long time(Runnable r) {
        long s = System.nanoTime(); r.run(); return System.nanoTime() - s;
    }
    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000};
        for (int n : sizes) {
            System.out.println("\\nPlaylist size: " + n);
            long aEnd = time(() -> { ArrayList<Integer> x = new ArrayList<>(); for(int i=0;i<n;i++) x.add(i); });
            long lEnd = time(() -> { LinkedList<Integer> x = new LinkedList<>(); for(int i=0;i<n;i++) x.add(i); });
            ArrayList<Integer> a = new ArrayList<>(); LinkedList<Integer> l = new LinkedList<>();
            for(int i=0;i<n;i++){a.add(i);l.add(i);}
            long aFront = time(() -> { for(int i=0;i<1000;i++) a.add(0, i); });
            long lFront = time(() -> { for(int i=0;i<1000;i++) l.add(0, i); });
            long aGet = time(() -> { long z=0; for(int i=0;i<10000;i++) z += a.get(i % a.size()); });
            long lGet = time(() -> { long z=0; for(int i=0;i<10000;i++) z += l.get(i % l.size()); });
            System.out.println("Add-at-end ArrayList ns: " + aEnd + " | LinkedList ns: " + lEnd);
            System.out.println("Front insert x1000 ArrayList ns: " + aFront + " | LinkedList ns: " + lFront);
            System.out.println("Random get x10000 ArrayList ns: " + aGet + " | LinkedList ns: " + lGet);
        }
        ArrayDeque<Integer> upNext = new ArrayDeque<>();
        upNext.addFirst(2); upNext.addLast(3); upNext.addFirst(1);
        System.out.println("\\nArrayDeque Up Next order: " + upNext);
        System.out.println("Complexity: ArrayList get O(1), middle/front insert O(n); LinkedList indexed get O(n), end operations O(1); ArrayDeque end operations O(1) amortized.");
    }
}
