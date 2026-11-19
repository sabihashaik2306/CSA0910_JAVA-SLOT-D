# TASK 03 – A3: Data-Access Design Pattern (5 marks)
Repeated DAO steps: get connection, prepare SQL, bind values, execute, map ResultSet to objects, handle SQLException and close resources. Differences are entity/table, SQL and row mapping. Pattern: Data Access Object (DAO).
Generic interface:
```java
interface Dao<T,K> {
 void insert(T value) throws SQLException;
 Optional<T> findById(K id) throws SQLException;
 List<T> findAll() throws SQLException;
 void update(T value) throws SQLException;
 void delete(K id) throws SQLException;
}
```
StudentDao implements Dao<Student,Integer> and contains student-specific SQL/mapping. Mapping: Student.id -> student_id PRIMARY KEY; name -> student_name; email -> email; department -> department.
If SQL is scattered in GUI/service classes, column renames require edits in many places. DAO centralizes schema changes. Per-query time complexity is still determined by database/index/result size; abstraction adds only constant overhead.
