# Q7 – Concurrent Fault-Tolerant Result Processor

**Requirements.** Process 10,000 independent score files; validate each; calculate a result; continue after missing/corrupt files; and report exact success/failure counts. Parallel work is file reading, validation and calculation. Shared data is the result/failure summary.

**Design.** Use ExecutorService with a fixed pool, Callable<Result> per file and Future<Result> to collect outcomes. Each task catches expected per-file IOException/validation exceptions and returns a structured success/failure result, so one bad file does not cancel the batch. Store outcomes in a ConcurrentLinkedQueue or collect Futures in the main thread. Always shut down the executor.

**Pseudocode.** Create fixed pool p; submit one Callable per path; each Callable reads, validates and computes or returns failure; iterate Futures and update counts; write summary; shutdown. A class structure can contain ScoreFileTask, Result, ResultProcessor and Summary.

Measure pools of 1, 2, 4 and 8 using System.nanoTime on the same dataset and record raw measurements on the execution machine. More threads may not help when disk I/O is saturated or task overhead dominates. Correctness is verified against a small hand-checked set. A limitation is that a machine failure can interrupt the whole run unless progress is checkpointed.

Parallel streams use the common ForkJoinPool and offer concise code but less explicit pool control. One thread per file can create excessive stacks and scheduling overhead. For n files and p workers, work is O(n), ideal elapsed processing approaches O(n/p), and worker-stack memory is O(p × stack size).
