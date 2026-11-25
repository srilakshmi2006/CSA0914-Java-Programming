# B4 – Final Engineering Decision

For 12 campuses and 100× the current volume, use a shared relational database with indexes, constraints, transactions and appropriate connection pooling. A migration program reads the legacy file, validates each row, records rejected rows with reasons, and inserts valid rows in batches inside transactions. DatabaseMetaData reports database product name, driver version and table names.

Text files are suitable for interchange and small offline exports, not concurrent multi-campus updates. Java serialization is unsuitable as the system of record because it is Java-specific, difficult to query and version, and unsafe for untrusted input. A database supports concurrent counters, indexed search, integrity constraints, access control and backups.

One shared database provides a consistent view across campuses but needs network availability, security and capacity planning. Separate serialized files isolate campuses but make cross-campus reporting, synchronization and consistency difficult. Base the final decision on recorded measurements, not guessed timings.
