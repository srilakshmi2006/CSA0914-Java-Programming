# B1 – File Store Implementation and Measurement

Implement add/find/list with validation and robust parsing. `find` reads line-by-line, checks the exact field count, parses required numeric/date fields, skips damaged lines and returns the matching record. A malformed P1002 row must not prevent a later P1003 row from being found; expected supplied example is P1003 record followed by null for damaged P1002.

For q searches among n records, scanning each time is O(q×n) and O(1) extra memory. Loading once into HashMap is O(n+q) expected time and O(n) memory. Use a benchmark table for file sizes 1, 10 and 50 MB and record wall-clock measurements locally; do not invent timing numbers. Search at 1,000, 10,000 and 100,000 records and interpret scaling.
