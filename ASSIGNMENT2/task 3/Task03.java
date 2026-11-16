import java.util.*;
public class Task03 {
    static class Student {
        final String regNo, name;
        Student(String r, String n){regNo=r;name=n;}
        public boolean equals(Object o){return o instanceof Student && regNo.equals(((Student)o).regNo);}
        public int hashCode(){return Objects.hash(regNo);}
        public String toString(){return regNo+" "+name;}
    }
    public static void main(String[] args) {
        Set<Student> set = new HashSet<>();
        set.add(new Student("192311001","Arun"));
        set.add(new Student("192311002","Divya"));
        set.add(new Student("192311001","Arun"));
        System.out.println("Corrected HashSet unique students: " + set.size());
        System.out.println("Students: " + set);
        Set<Student> tree = new TreeSet<>(Comparator.comparing(s -> s.regNo));
        tree.add(new Student("192311001","Arun"));
        tree.add(new Student("192311001","Arun duplicate"));
        tree.add(new Student("192311003","Sara"));
        System.out.println("TreeSet by registration number: " + tree.size());
        Map<String,Student> byReg = new HashMap<>();
        byReg.put("192311001", new Student("192311001","Arun"));
        byReg.put("192311001", new Student("192311001","Arun duplicate"));
        System.out.println("HashMap unique keys: " + byReg.size());
        System.out.println("Expected corrected output: Unique students = 2");
        System.out.println("Reason: equal objects must return the same hash code; changing key fields after insertion can make lookup fail.");
    }
}
