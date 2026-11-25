# A3 – DAO Pattern

StudentDB, CourseDB and MarksDB repeat connection/statement creation, parameter binding, execution, ResultSet mapping and exception handling. Differences are entity fields, SQL/table/column names, key type and mapping logic.

**Generic interface:** `Dao<T,K>` declares insert(T), findById(K), findAll(), update(T), delete(K). StudentDao implements Dao<Student,Integer>; maps Student.id → student_id (primary key), name → name, email → email. Each DAO owns SQL and row mapping; service classes call DAO methods rather than embedding SQL.

Generic DAO reduces duplication and supports testing/mocking. Direct SQL in GUI/service code couples presentation/business rules to storage and makes schema changes harder. Avoid over-generalizing complex queries; specialized DAO methods can coexist.
