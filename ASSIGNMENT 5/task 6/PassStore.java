import java.io.*; import java.nio.file.*; import java.util.*;
public class PassStore {
 private final Path file; public PassStore(Path p){file=p;}
 public void add(String id,String reg,String name,int route,double amount)throws IOException{
  try(BufferedWriter w=Files.newBufferedWriter(file,StandardOpenOption.CREATE,StandardOpenOption.APPEND)){
   w.write(String.join(",",id,reg,name,""+route,""+amount)); w.newLine();
  }
 }
 public String[] find(String id)throws IOException{
  if(!Files.exists(file))return null;
  try(BufferedReader r=Files.newBufferedReader(file)){
   String line; while((line=r.readLine())!=null){
    String[] f=line.split(",",-1); if(f.length!=5)continue;
    try{Integer.parseInt(f[3]);Double.parseDouble(f[4]);}catch(NumberFormatException e){continue;}
    if(f[0].equals(id))return f;
   }
  } return null;
 }
 public List<String[]> listByRoute(int route)throws IOException{
  List<String[]> out=new ArrayList<>(); if(!Files.exists(file))return out;
  try(BufferedReader r=Files.newBufferedReader(file)){String l;while((l=r.readLine())!=null){
   String[] f=l.split(",",-1);if(f.length!=5)continue;
   try{if(Integer.parseInt(f[3])==route){Double.parseDouble(f[4]);out.add(f);}}catch(Exception ignored){}
  }} return out;
 }
 public static void main(String[]x)throws Exception{
  Path p=Files.createTempFile("passes",".csv");PassStore s=new PassStore(p);
  s.add("P1001","192311001","Arun",12,4500);
  Files.writeString(p,"P1002,DAMAGED LINE\nP1004,reg,WrongRoute,xx,500\n",StandardOpenOption.APPEND);
  s.add("P1003","192311003","Divya",7,3800);
  System.out.println("Find P1003: "+Arrays.toString(s.find("P1003")));
  System.out.println("Find damaged P1002: "+s.find("P1002"));
  System.out.println("Find missing P9999: "+s.find("P9999"));
  System.out.println("Route 12 records: "+s.listByRoute(12).size());Files.delete(p);
 }
}
