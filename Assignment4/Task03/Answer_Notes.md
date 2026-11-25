# Task 3 – Campus Library Search Layout

**Layout choice:** JFrame uses BorderLayout. NORTH has a title JLabel and search JPanel with JTextField, JComboBox/Choice and Search JButton using FlowLayout. CENTER contains a JTable in JScrollPane and grows with the window. SOUTH has Prev, Next, Reserve buttons and a status JLabel in FlowLayout. Avoid absolute positioning because it does not adapt to font, platform or window size.

**Containment tree:** JFrame(BorderLayout) → NORTH JPanel(BorderLayout) → title JLabel(NORTH), searchPanel(FlowLayout,CENTER); CENTER JScrollPane(JTable); SOUTH JPanel(BorderLayout) → navigationPanel(FlowLayout,CENTER), status JLabel(SOUTH).

**Implementation:** see Task03/LibrarySearchFrame.java. Test at small, medium and large sizes; the center scroll pane should expand while north/south controls retain preferred sizes. Record actual screenshots after running locally.

Nested layouts are easy to reason about and responsive. GridBagLayout offers finer control but is more verbose. Absolute positioning has low initial coding effort but breaks on resize and different display scaling.
