# Q6 – Deadlock and Producer–Consumer

Deadlock requires mutual exclusion, hold-and-wait, no pre-emption and circular wait. Two transfers can deadlock when thread A locks wallet X then Y while thread B locks Y then X. Prevent it by locking accounts in a stable order (for example ascending account ID), so circular wait is impossible. A timed tryLock is another option; on failure release acquired locks and retry or report failure.

For the bounded token buffer of capacity five, `put` waits while full, writes at `in`, advances modulo five, increments count and calls notifyAll. `take` waits while empty, reads at `out`, advances modulo five, decrements count, calls notifyAll and returns the token. Both methods must be synchronized and use while loops because of spurious wakeups and because another thread may consume the condition before a woken thread reacquires the lock. wait releases the monitor; sleep does not.

**Completed put/take core:**
`while (count == buffer.length) wait(); buffer[in] = token; in = (in + 1) % buffer.length; count++; notifyAll();`
`while (count == 0) wait(); int token = buffer[out]; out = (out + 1) % buffer.length; count--; notifyAll(); return token;`

Expected sum of tokens 1..20 is 210. ArrayBlockingQueue<Integer>(5) is a simpler, less error-prone bounded-buffer alternative. Queue put/take are O(1) average and use O(5) buffer space. Consistent lock ordering prevents deadlock; tryLock(timeout) can avoid indefinite waiting but needs retry handling.
