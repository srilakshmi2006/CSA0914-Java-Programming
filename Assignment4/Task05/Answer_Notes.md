# Task 5 – Currency Converter Event Styles

Implement the same conversion using: (1) frame implements ActionListener; (2) named inner class; (3) anonymous inner class; (4) lambda expression. All versions should validate a positive numeric amount, read From/To selections, convert using a defined rate table, and show the result in a JLabel or JOptionPane. Use WindowAdapter for closing. Register the same conversion method for the button and Enter key in the amount field.

**Comparison:** frame listener is simple for tiny programs but mixes responsibilities; named inner class is reusable and readable; anonymous class is local but verbose; lambda is concise for functional interfaces but less suitable when multiple methods/state are needed. Method references work when an existing method signature matches. A shared Action is useful when the same command is used by button and menu.

Run valid amount and invalid text/negative amount tests and capture actual screenshots. See Task05/CurrencyConverter.java for a runnable lambda example.
