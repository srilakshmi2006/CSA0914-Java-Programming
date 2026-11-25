# B2 – Adversarial Input

Use a local disposable test database only. Compare unsafe SQL concatenation against PreparedStatement with the same test input. A classic test username is `' OR '1'='1' -- ` with a dummy password; the unsafe query may authenticate incorrectly if quotes/comments are assembled into SQL, while PreparedStatement treats the entire string as a literal username and rejects it.

Create damaged records with wrong field count, non-numeric amount and an empty line. The parser should reject/quarantine each invalid record, log the line number/reason, and continue. Do not run tests against a production database.
