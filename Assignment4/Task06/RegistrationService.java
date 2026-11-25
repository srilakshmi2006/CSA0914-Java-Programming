import java.sql.*;
public class RegistrationService {
  private final Connection connection;
  public RegistrationService(Connection c){connection=c;}
  public void register(String studentId,int eventId)throws SQLException{
    if(studentId==null||studentId.isBlank()||eventId<=0)throw new IllegalArgumentException("Invalid registration details");
    String sql="INSERT INTO registrations(student_id,event_id) VALUES(?,?)";
    try(PreparedStatement ps=connection.prepareStatement(sql)){ps.setString(1,studentId);ps.setInt(2,eventId);ps.executeUpdate();}
  }
}
