# Task 4 – Event-Handling Forensics

The handler may not run because the registered listener method name/signature is wrong or the listener was never registered. The label may not update because the code changes a local variable rather than the label, or updates it from the wrong event path. Drawing directly inside mouseClicked is temporary: minimize/restore causes repaint and erases pixels.

Store every click as a Point in a List<Point>, update the count label, then call repaint(). Override paintComponent(Graphics) and redraw all stored points. `@Override` makes the compiler detect a misspelled or incorrectly parameterized method. For an AWT Canvas use paint(Graphics); for Swing JPanel use paintComponent(Graphics).

Point objects are simple and easy to undo/edit, using O(k) memory for k points. An off-screen BufferedImage stores rendered pixels and can be efficient for complex drawings, but consumes O(width × height) pixel memory and is less convenient to edit individual points.
