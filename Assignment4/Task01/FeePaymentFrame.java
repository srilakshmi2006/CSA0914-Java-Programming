import javax.swing.*; import java.awt.*;
public class FeePaymentFrame extends JFrame {
  JTextField student=new JTextField(12), amount=new JTextField(8); JLabel status=new JLabel("Enter payment details");
  FeePaymentFrame(){
    super("Fee Payment"); setLayout(new FlowLayout());
    add(new JLabel("Student ID"));add(student);add(new JLabel("Amount"));add(amount);
    JButton pay=new JButton("Pay");add(pay);add(status);
    pay.addActionListener(e->{try{double a=Double.parseDouble(amount.getText().trim());if(a<=0)throw new IllegalArgumentException("Amount must be positive");status.setText("Payment successful for "+student.getText());}catch(Exception ex){status.setText("Payment failed: "+ex.getMessage());}});
    setDefaultCloseOperation(EXIT_ON_CLOSE);pack();
  }
  public static void main(String[] a){SwingUtilities.invokeLater(()->new FeePaymentFrame().setVisible(true));}
}
