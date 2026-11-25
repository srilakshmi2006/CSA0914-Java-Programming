import java.io.*;
import java.util.*;
public class ATMExceptionDemo {
  static void withdraw(double balance, double amount) throws IOException {
    if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
    if (amount > balance) throw new IllegalStateException("Insufficient funds");
    System.out.println("Dispensed: " + amount);
  }
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      System.out.print("Enter withdrawal amount: ");
      double amount = sc.nextDouble();
      withdraw(1000, amount);
    } catch (InputMismatchException e) {
      System.out.println("Invalid numeric input");
    } catch (IllegalArgumentException | IllegalStateException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Unable to complete withdrawal");
    } finally {
      System.out.println("ATM session completed");
    }
  }
}
