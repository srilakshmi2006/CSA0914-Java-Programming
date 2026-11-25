import javax.swing.*; import java.awt.*;
public class CurrencyConverter extends JFrame {
  JTextField amount=new JTextField(10); JComboBox<String> from=new JComboBox<>(new String[]{"USD","EUR","INR"}),to=new JComboBox<>(new String[]{"INR","USD","EUR"}); JLabel result=new JLabel("Result appears here");
  CurrencyConverter(){
    super("Currency Converter");setLayout(new FlowLayout());add(amount);add(from);add(to);JButton convert=new JButton("Convert");add(convert);add(result);
    Runnable action=()->{try{double v=Double.parseDouble(amount.getText().trim());if(v<0)throw new NumberFormatException();double inr=v*rate((String)from.getSelectedItem());double out=inr/rate((String)to.getSelectedItem());result.setText(String.format("%.2f %s",(Object)out,to.getSelectedItem()));}catch(Exception ex){JOptionPane.showMessageDialog(this,"Enter a valid non-negative amount");}};
    convert.addActionListener(e->action.run());amount.addActionListener(e->action.run());
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);setSize(420,140);
  }
  double rate(String c){return c.equals("INR")?1:c.equals("USD")?83:90;}
  public static void main(String[] a){SwingUtilities.invokeLater(()->new CurrencyConverter().setVisible(true));}
}
