# Q1 – Bank ATM Exception Handling

**Failure analysis.** Invalid numeric input causes InputMismatchException (unchecked); a negative withdrawal or amount above balance is a business-rule failure and should be represented by a clear validation result or custom InsufficientFundsException; a missing card-reader file causes checked FileNotFoundException; a closed or unreadable file can cause IOException. JVM failures such as OutOfMemoryError are Errors and should not normally be caught by application code.

**Throwable hierarchy (relevant part).**
Throwable → Error; Exception → IOException (checked), RuntimeException → InputMismatchException / IllegalArgumentException (unchecked). A custom InsufficientFundsException may extend Exception when callers must handle it explicitly.

**Catch order and finally.** Catch specific exceptions before broader ones: InputMismatchException, IOException, then a final Exception only if a recovery boundary genuinely requires it. A broad catch placed first makes later specific catches unreachable and will not compile. finally is used to close resources/reset state; try-with-resources is preferred for files.

**Test table.** Valid amount 200 with balance 1000 → success; input “abc” → InputMismatchException handled; amount -10 → validation rejection; amount 1500 → insufficient funds; missing card file → IOException/FileNotFoundException handled. In every path, the cleanup action runs. A stack trace contains exception class/message followed by method frames showing the call path.

**Alternative without exceptions.** Scanner.hasNextInt()/hasNextDouble() and explicit validation can return status codes. This makes expected user errors explicit but adds checks at each boundary. Normal validation is O(1) time and O(1) extra space; constructing/throwing an exception is more expensive because a stack trace is captured, also O(depth) time and memory proportional to stack depth.
