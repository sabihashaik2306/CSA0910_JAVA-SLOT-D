import java.util.*;
public class Task06 {
    static class Patient {
        final String name; final int severity; final long arrival;
        Patient(String n,int s,long a){name=n;severity=s;arrival=a;}
        public String toString(){return name+"(S"+severity+")";}
    }
    static class Triage {
        long counter=0;
        PriorityQueue<Patient> queue = new PriorityQueue<>(
            Comparator.comparingInt((Patient p)->p.severity).thenComparingLong(p->p.arrival));
        void admit(String name,int severity){queue.add(new Patient(name,severity,counter++));}
        Patient next(){return queue.poll();}
    }
    public static void main(String[] args) {
        Triage er=new Triage();
        er.admit("Ravi",3); er.admit("Meena",1); er.admit("John",3);
        er.admit("Asha",5); er.admit("Kumar",1); er.admit("Lata",2);
        StringBuilder order=new StringBuilder(); Patient p;
        while((p=er.next())!=null) order.append(p).append(' ');
        System.out.println(order.toString().trim());
        System.out.println("PriorityQueue add/poll: O(log n); peek: O(1). Arrival counter preserves FIFO among equal severity.");
        System.out.println("Fairness improvement: increase priority after a defined waiting-time threshold to reduce starvation.");
    }
}
