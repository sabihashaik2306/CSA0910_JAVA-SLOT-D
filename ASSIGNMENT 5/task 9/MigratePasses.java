import java.io.*;import java.nio.file.*;import java.sql.*;import java.util.*;
public class MigratePasses{
 static final String URL="jdbc:mysql://localhost:3306/buspass_test",USER="root",PASSWORD="YOUR_MYSQL_PASSWORD";
 public static void main(String[]a)throws Exception{
  Path f=Paths.get(a.length>0?a[0]:"passes.csv");List<String[]> good=new ArrayList<>();List<String> bad=new ArrayList<>();
  try(BufferedReader r=Files.newBufferedReader(f)){String l;int n=0;while((l=r.readLine())!=null){n++;String[] x=l.split(",",-1);
   try{if(x.length!=5)throw new IllegalArgumentException("expected 5 fields");Integer.parseInt(x[3]);Double.parseDouble(x[4]);good.add(x);}
   catch(Exception e){bad.add("Line "+n+": "+e.getMessage());}
  }}
  try(Connection c=DriverManager.getConnection(URL,USER,PASSWORD)){
   c.setAutoCommit(false);String sql="INSERT INTO bus_pass(pass_id,reg_no,name,route_no,amount) VALUES(?,?,?,?,?)";
   try(PreparedStatement p=c.prepareStatement(sql)){for(String[]x:good){p.setString(1,x[0]);p.setString(2,x[1]);p.setString(3,x[2]);p.setInt(4,Integer.parseInt(x[3]));p.setDouble(5,Double.parseDouble(x[4]));p.addBatch();}p.executeBatch();c.commit();}
   catch(Exception e){c.rollback();throw e;}
   DatabaseMetaData m=c.getMetaData();System.out.println("Database: "+m.getDatabaseProductName()+" "+m.getDatabaseProductVersion());
   System.out.println("Driver: "+m.getDriverName()+" "+m.getDriverVersion());
   try(ResultSet t=m.getTables(c.getCatalog(),null,"%",new String[]{"TABLE"})){while(t.next())System.out.println("Table: "+t.getString("TABLE_NAME"));}
  }
  System.out.println("Valid migrated: "+good.size());System.out.println("Rejected: "+bad.size());for(String s:bad)System.out.println(s);
 }
}
