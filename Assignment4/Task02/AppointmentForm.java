import javax.swing.*; import java.awt.*;
public class AppointmentForm extends JFrame {
  JComboBox<String> dept=new JComboBox<>(new String[]{"Cardiology","ENT","Orthopaedics"});
  JComboBox<String> doctor=new JComboBox<>(); JTextField patient=new JTextField(14); JLabel status=new JLabel("Choose department");
  AppointmentForm(){
    super("Hospital Appointment");setLayout(new GridLayout(0,2,6,6));
    add(new JLabel("Patient"));add(patient);add(new JLabel("Department"));add(dept);add(new JLabel("Doctor"));add(doctor);
    JButton book=new JButton("Book");add(book);add(status);
    dept.addActionListener(e->{doctor.removeAllItems();String d=(String)dept.getSelectedItem();if(d.equals("Cardiology"))doctor.addItem("Dr Rao");else if(d.equals("ENT"))doctor.addItem("Dr Kumar");else doctor.addItem("Dr Meena");});
    book.addActionListener(e->{if(patient.getText().isBlank()||doctor.getSelectedItem()==null)status.setText("Enter patient and doctor");else status.setText("Appointment request saved");});
    dept.setSelectedIndex(0);setDefaultCloseOperation(EXIT_ON_CLOSE);pack();
  }
  public static void main(String[] a){SwingUtilities.invokeLater(()->new AppointmentForm().setVisible(true));}
}
