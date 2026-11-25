# Q4 – Thread Creation and Life Cycle

Extending Thread combines task and execution object and prevents extending another class. Implementing Runnable separates the task from the Thread and is usually preferred; one Runnable can be run by different execution mechanisms. Call start() to create a new thread; calling run() directly is an ordinary method call on the current thread. join() makes the main thread wait until a worker terminates; isAlive() checks whether it is still active; sleep() pauses the current thread but does not release held monitors.

Lifecycle: NEW → RUNNABLE → (BLOCKED / WAITING / TIMED_WAITING as applicable) → TERMINATED. Four scanner stations can finish in different orders because scheduling and I/O timing vary. Thread priority is only a scheduling hint; it does not guarantee order. A daemon thread does not keep the JVM alive after all user threads finish.

A fixed ExecutorService pool bounds concurrency and reuses workers. For n scripts and p workers, ideal work time is approximately O(n/p) when work is balanced; total work remains O(n). Thread-stack memory is O(p × stack size), compared with manually creating one thread per station. Always shut down the executor.
