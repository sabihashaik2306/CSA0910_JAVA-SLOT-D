import java.sql.*;
public class SafeLogin{
 public static boolean check(Connection c,String id,String hash)throws SQLException{
  String sql="SELECT 1 FROM users WHERE id=? AND pwd_hash=?";
  try(PreparedStatement p=c.prepareStatement(sql)){
   p.setString(1,id);p.setString(2,hash);
   try(ResultSet r=p.executeQuery()){return r.next();}
  }
 }
}
