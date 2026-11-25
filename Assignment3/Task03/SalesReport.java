import java.io.*;
import java.nio.file.*;
public class SalesReport {
  public static void main(String[] args) {
    Path output = Paths.get("summary.txt");
    try (BufferedWriter w = Files.newBufferedWriter(output)) {
      for (String name : new String[]{"store1.txt","store2.txt"}) {
        try (BufferedReader r = Files.newBufferedReader(Paths.get(name))) {
          String line; long total=0;
          while ((line=r.readLine())!=null) total += Long.parseLong(line.trim());
          w.write(name + " total=" + total); w.newLine();
        } catch (IOException | NumberFormatException e) {
          System.err.println("Failed file " + name + ": " + e.getMessage());
        }
      }
    } catch (IOException e) { System.err.println("Cannot write summary: "+e.getMessage()); }
  }
}
