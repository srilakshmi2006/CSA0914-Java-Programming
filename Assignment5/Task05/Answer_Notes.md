# A5 – Transactions and SQL Injection

**Transfer pseudocode:** setAutoCommit(false); debit source wallet with `UPDATE wallet SET balance=balance-? WHERE id=? AND balance>=?`; verify exactly one row updated; credit destination; verify one row; commit. On any SQLException or validation failure, rollback; restore auto-commit in finally. This prevents a partial debit/credit and uses database transaction isolation.

Unsafe query concatenates username/password into SQL. Crafted input such as username `' OR '1'='1` can alter the WHERE condition and bypass authentication if the query is constructed directly. PreparedStatement uses placeholders and setString so input remains data, not SQL syntax. Passwords should be stored as salted password hashes, not plaintext.

One transaction makes both updates atomic. Two auto-commit updates can commit the debit and then fail before credit, losing money. Transactional correctness is more important than small overhead.
