# A2 – Buffered I/O

A 50 MiB file contains 52,428,800 bytes, so byte-at-a-time code performs about 52.4 million read operations at the Java API level (and potentially many underlying calls if not buffered). This is slow due to call overhead. BufferedInputStream fills a buffer (for example 8 KiB) and serves many byte reads from memory; approximate refill count is 52,428,800 / 8,192 = 6,400.

**Buffered counter:** wrap FileInputStream in BufferedInputStream, read chunks/bytes and increment line count for '\n'. Buffering reduces expensive lower-level reads. It provides little benefit when the source is already memory-backed or the stream is already buffered.

Files.readAllLines() is convenient but retains all lines in memory: O(file size + line count) memory. Streaming buffered reading uses O(buffer size) extra memory. Both are O(n) time; actual time must be measured on the same machine and file.
