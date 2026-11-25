# B3 – JDBC Performance and Batch Processing

Create `bus_pass` with pass_id primary key, register_no, name, route_no, boarding_stop, validity_date and amount_paid DECIMAL. Insert 10 sample rows using Statement, PreparedStatement and PreparedStatement batch; verify with SELECT and ResultSetMetaData (column count, labels and SQL types).

Measure 1,000, 10,000 and 100,000 inserts for each method using System.nanoTime and identical data/database settings. Auto-commit per row generally incurs more transaction overhead; one transaction reduces commit cost. Batch processing reduces round trips and is recommended for bulk imports. Inject an invalid amount halfway: transaction rollback should leave zero rows from that batch/transaction. For nightly 100,000 import use PreparedStatement batches in a transaction; for one counter pass use a single PreparedStatement insert.

Multi-row INSERT reduces round trips; LOAD DATA is often fastest for large trusted files but requires server/file permissions and careful validation.
