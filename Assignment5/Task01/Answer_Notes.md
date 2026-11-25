# A1 – Design a File Format

**Delimited record layout:** `passId|registerNo|name|routeNo|boardingStop|validityDate|amountPaid`. Escape delimiter characters in text fields or use a CSV library. Validate exact field count, numeric route and amount, date format and required fields.

**Binary layout:** DataOutputStream writes fields in a fixed documented order: UTF passId, UTF registerNo, UTF name, int routeNo, UTF boardingStop, UTF validityDate (ISO-8601), double amountPaid. Prefix records with a version/length if forward compatibility is required.

**Pseudocode:** ADD: validate; append one complete encoded record; flush; report success. FIND: open file; read records; skip malformed entries; return matching pass ID or null. LIST_ROUTE: scan valid records and output those with matching route. Missing file: create only for add; for find/list return empty result or clear error. Damaged line: log line number and skip/quarantine. Power failure: append to a temporary file or use a journal/checksum and atomic rename; never treat a partial final record as valid.

For 8,000 records, text is easy to inspect and portable but requires parsing and usually O(n) search. Fixed-length RandomAccessFile records allow direct seek/update in O(1) record positioning but need fixed field sizes, padding and careful encoding.
