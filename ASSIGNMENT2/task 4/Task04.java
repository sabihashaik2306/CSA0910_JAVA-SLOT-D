import java.util.*;
public class Task04 {
    public static void main(String[] args) {
        String[] codes = {"MED103","MED101","MED105","MED102","MED104"};
        Map<String,Integer> hash = new HashMap<>();
        Map<String,Integer> tree = new TreeMap<>();
        Map<String,Integer> linked = new LinkedHashMap<>();
        for(int i=0;i<codes.length;i++){hash.put(codes[i], 100+i); tree.put(codes[i],100+i); linked.put(codes[i],100+i);}
        System.out.println("HashMap (order unspecified): " + hash);
        System.out.println("TreeMap (code order): " + tree);
        System.out.println("LinkedHashMap (insertion order): " + linked);
        long start = System.nanoTime();
        int checksum=0;
        for(int i=0;i<100000;i++) checksum += hash.get(codes[i%codes.length]);
        System.out.println("100000 HashMap lookups ns: " + (System.nanoTime()-start));
        System.out.println("Lookup checksum: " + checksum);
        System.out.println("Complexity: HashMap put/get O(1) average; TreeMap O(log n); LinkedHashMap O(1) average with order maintenance.");
    }
}
