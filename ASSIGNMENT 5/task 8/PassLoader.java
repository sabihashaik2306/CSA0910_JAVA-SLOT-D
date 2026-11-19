import java.sql.*;import java.util.*;
public class PassLoader{
 static final String SQL="INSERT INTO bus_pass(pass_id,reg_no,name,route_no,amount) VALUES(?,?,?,?,?)";
 public static int loadAll(Connection c,List<String[]> rows)throws SQLException{
  boolean old=c.getAutoCommit();c.setAutoCommit(false);int n=0;
  try(PreparedStatement p=c.prepareStatement(SQL)){
   for(String[] r:rows){p.setString(1,r[0]);p.setString(2,r[1]);p.setString(3,r[2]);
    p.setInt(4,Integer.parseInt(r[3]));p.setDouble(5,Double.parseDouble(r[4]));p.addBatch();n++;
    if(n%1000==0)p.executeBatch();
   }p.executeBatch();c.commit();return n;
  }catch(SQLException|RuntimeException e){c.rollback();throw e;}
  finally{c.setAutoCommit(old);}
 }
 public static void main(String[]a)throws Exception{
  try(Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/buspass_test","root","YOUR_MYSQL_PASSWORD")){
   List<String[]> rows=new ArrayList<>();
   for(int i=1;i<=1000;i++)rows.add(new String[]{String.format("P%06d",i),""+(192420000+i),"Student"+i,""+(i%20+1),"4500.00"});
   System.out.println("Rows loaded: "+loadAll(c,rows));
  }
 }
}
