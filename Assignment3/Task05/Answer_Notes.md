# Q5 – Cinema Seat Booking and Race Conditions

The critical section is the check-then-set operation: test `booked[seat]`, set it true, and increment bookingsMade. Without a lock, two threads can both observe false and both increment the count. The supplied 100-thread test competes for 10 seats; the correct result is `Seats sold: 10`.

**Completed method:** `synchronized boolean book(int seat) { if (seat < 0 || seat >= booked.length) return false; if (booked[seat]) return false; booked[seat] = true; bookingsMade++; return true; }`

A synchronized method locks the whole object for each call. A synchronized block can lock only the critical section, but must use the same lock consistently. Static synchronization locks the Class object and is only needed when coordinating static shared state across instances. For 20 screens, use one independent booking service/lock per screen or seat-level locks to avoid unnecessary cross-screen blocking.

Alternatives: AtomicBoolean[] with compareAndSet gives O(1) attempt per seat and O(s) storage; ReentrantLock gives explicit lock/unlock with finally; ConcurrentHashMap.putIfAbsent gives atomic key insertion and O(s) map storage. Contention can increase waiting/retries, so correctness tests should be repeated.
