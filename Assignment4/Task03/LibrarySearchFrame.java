import javax.swing.*; import java.awt.*;
public class LibrarySearchFrame extends JFrame {
  LibrarySearchFrame(){
    super("Campus Library Search");setLayout(new BorderLayout(8,8));
    JLabel title=new JLabel("Library Search",SwingConstants.CENTER);add(title,BorderLayout.NORTH);
    JTextField query=new JTextField(18);JComboBox<String> category=new JComboBox<>(new String[]{"All","Books","Journals"});
    JButton search=new JButton("Search");JPanel top=new JPanel(new FlowLayout());top.add(query);top.add(category);top.add(search);
    JPanel north=new JPanel(new BorderLayout());north.add(title,BorderLayout.NORTH);north.add(top,BorderLayout.SOUTH);add(north,BorderLayout.NORTH);
    JTable results=new JTable(new Object[][]{{"Java Basics","Book","Available"},{"Database Systems","Book","Issued"}},new String[]{"Title","Type","Status"});
    add(new JScrollPane(results),BorderLayout.CENTER);
    JPanel bottom=new JPanel(new FlowLayout());bottom.add(new JButton("Prev"));bottom.add(new JButton("Next"));bottom.add(new JButton("Reserve"));bottom.add(new JLabel("Ready"));add(bottom,BorderLayout.SOUTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);setSize(650,400);setLocationRelativeTo(null);
  }
  public static void main(String[] a){SwingUtilities.invokeLater(()->new LibrarySearchFrame().setVisible(true));}
}
