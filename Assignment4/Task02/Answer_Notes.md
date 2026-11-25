# Task 2 – Hospital Appointment Event Design

**Event table:** Department JComboBox / ItemEvent / ItemListener / refresh doctor choices; Book JButton / ActionEvent / ActionListener / validate and save appointment; Clear JButton / ActionEvent / ActionListener / clear fields; Window / WindowEvent / WindowListener / confirm close; Date chooser / property change / appropriate listener / validate date.

**Containment tree:** JFrame → BorderLayout; NORTH JPanel (GridLayout) with patient name JTextField, department JComboBox, doctor JComboBox and date field; CENTER JPanel with appointment details; SOUTH JPanel with Book, Clear and status JLabel.

**Department change pseudocode:** Read selected department; query doctors for department; clear doctor combo; add returned doctors; show “No doctors available” if empty. Cache doctor lists in Map<Department,List<Doctor>> when data changes infrequently; invalidate cache when doctors are updated.

**Booking pseudocode:** Validate patient name, department, doctor and future appointment date; check slot availability; insert appointment using PreparedStatement in a transaction; show confirmation or friendly error.

**Window trace:** main → SwingUtilities.invokeLater → construct frame → create components → register listeners → pack → setVisible → user event → listener callback → service → DAO → update view → windowClosing → dispose. Applet init/start/paint/stop/destroy are lifecycle callbacks controlled by an applet container; JFrame uses construction, visibility, event callbacks and disposal rather than applet lifecycle methods.

Loading doctors every change gives fresh data but incurs repeated DB latency. Loading once into a Map reduces queries and improves responsiveness but needs cache invalidation and memory O(d).
