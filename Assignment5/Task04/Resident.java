import java.io.*;
public class Resident implements Serializable {
  private static final long serialVersionUID=1L;
  String name, registerNumber; transient String password; transient Object liveConnection;
  public Resident(String n,String r,String p){name=n;registerNumber=r;password=p;}
}
