# Q3 – Resource Safety and Debugging

Manual close() in a finally block can leak a resource if opening a later resource fails before execution reaches the corresponding close, or if close logic itself is skipped by a faulty control path. Use try-with-resources for every reader/writer/stream. Resources close in reverse order of declaration. If the body throws and close also throws, the close exception is recorded as a suppressed exception and can be inspected with getSuppressed().

The report generator should process each store file independently, log its filename, continue after a recoverable file failure, and write a clearly marked summary only after processing. A single corrupt file must not silently produce a success report. try-with-resources guarantees closing, but does not validate content, make writes atomic, or recover a partially written output file; write to a temporary file and rename after successful completion where appropriate.

For f files containing a total of N records, processing is O(N). With one file open at a time, maximum open handles are O(1); opening all f files together uses O(f) handles. No-close code can exhaust the operating-system limit.
