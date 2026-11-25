# Task 6 – Campus Event Registration System

**Users:** students register; committee members view, filter and export registrations. **Screens:** login (optional), student registration form, committee registration table, filter dialog and export confirmation. **Data:** Student, Event and Registration. **Rules:** required fields, valid email, event exists, no duplicate (student_id,event_id), capacity not exceeded.

**Layers:** Swing UI → RegistrationService (validation/duplicate policy) → RegistrationDao (PreparedStatement/JDBC) → MySQL. Add a unique constraint on (student_id,event_id) because application-only checks can race. Use transactions for registration. SQL pseudocode: validate fields; begin transaction; insert registration; handle duplicate-key exception; commit; otherwise rollback.

Use SwingWorker for DB loads and exports so work does not block the Event Dispatch Thread. JTable displays results; filter by event. Export selected data to CSV with proper escaping.

Loading 100,000 rows at once uses O(n) memory and can freeze rendering. Pagination with LIMIT/OFFSET bounds page memory to O(pageSize), improves initial display and responsiveness; large offsets can become slower, so keyset pagination may be considered. SQL schema and Java starter are in Task06.
