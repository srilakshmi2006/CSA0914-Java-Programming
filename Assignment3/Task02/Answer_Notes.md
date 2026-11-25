# Q2 – User-Defined Exceptions and Propagation

**Exception design.** ExamException is the checked base class. ExamWindowClosedException and StudentNotRegisteredException can extend it because callers must respond to these business-rule failures. SubmissionStorageException extends ExamException and wraps low-level IOException. FileTooLargeException may be a checked domain exception. An unchecked IllegalArgumentException is suitable for programmer errors such as a null filename.

**Propagation.** Storage catches IOException and throws new SubmissionStorageException("Could not store " + fileName, cause). Service declares `throws ExamException`; Controller catches ExamException, logs the cause internally, and displays a safe message such as “Your submission could not be saved. Please try again.” The UI should not expose filesystem paths or stack traces.

**Completed TODO.** `throw new SubmissionStorageException("Could not store " + fileName, e);`

**Chaining evidence.** `getCause()` returns the original IOException (“Disk quota exceeded”). If the cause is omitted, the controller sees only the domain message and loses the root failure type, message and original stack trace. Expected output: Saved answer01.pdf; Error: Could not store answer02.pdf; Cause: Disk quota exceeded.

**Checked vs unchecked alternative.** If custom exceptions extend RuntimeException, callers are not forced to declare/catch them; signatures become shorter but the compiler no longer documents required recovery paths. Exception creation captures a stack trace with cost proportional to call-stack depth; chaining retains a reference to the cause and its trace, increasing memory use.
