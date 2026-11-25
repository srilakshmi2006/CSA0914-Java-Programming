# Task 1 – GUI Design Review

**Problems and impact:** (1) UI and payment/database logic in one handler violates separation of concerns and makes testing difficult. (2) Comparing Strings with `==` compares references; use `equals`. (3) Missing validation permits blank, malformed or negative amounts. (4) Direct SQL concatenation enables SQL injection; use PreparedStatement. (5) Calling sleep() on the Event Dispatch Thread freezes the interface. (6) No clear feedback leaves the user unsure whether payment succeeded. (7) Receipt action is mixed with payment processing. (8) Resources may remain open if not closed in finally/try-with-resources. (9) Exceptions are swallowed or shown as raw stack traces.

**Redesign:** FeePaymentFrame collects inputs and displays messages; FeeService validates and processes; FeeDao performs JDBC work; ReceiptService generates receipt. Event handler reads values, calls service, then updates UI. Long database work should run in SwingWorker.

**Example handler:** `payButton.addActionListener(e -> { try { feeService.pay(studentIdField.getText().trim(), amountField.getText().trim()); status.setText("Payment successful"); } catch (Exception ex) { status.setText("Payment failed: " + ex.getMessage()); } });`

**Design defence (under 100 words):** The frame handles presentation only, FeeService owns validation and business rules, and FeeDao owns database access. This separation improves testing, reuse and maintenance. PreparedStatement protects SQL input, try-with-resources closes JDBC resources, and background work keeps the interface responsive. Separate listeners or Swing Action objects make each command easier to understand than one large shared handler.
