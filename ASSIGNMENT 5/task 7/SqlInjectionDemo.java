public class SqlInjectionDemo{
 static String unsafe(String id,String pwd){return "SELECT * FROM users WHERE id='"+id+"' AND pwd='"+pwd+"'";}
 public static void main(String[]a){
  String id="admin' -- ",pwd="wrong-password";
  System.out.println("Unsafe query built from crafted input:");
  System.out.println(unsafe(id,pwd));
  System.out.println("Safe query structure: SELECT 1 FROM users WHERE id=? AND pwd_hash=?");
  System.out.println("PreparedStatement binds crafted text as data; it cannot remove the password predicate.");
  System.out.println("Expected: unsafe string construction is injectable; parameterized login rejects invalid credentials.");
 }
}
