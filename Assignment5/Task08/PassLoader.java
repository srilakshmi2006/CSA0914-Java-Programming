import java.sql.*;import java.util.*;
public class PassLoader {
  public static void insertBatch(Connection c,List<String[]> rows)throws SQLException{
    String sql="INSERT INTO bus_pass(pass_id,register_no,name,route_no,boarding_stop,validity_date,amount_paid) VALUES(?,?,?,?,?,?,?)";
    try(PreparedStatement ps=c.prepareStatement(sql)){
      c.setAutoCommit(false);
      try{
        int count=0;for(String[] r:rows){
          ps.setString(1,r[0]);ps.setString(2,r[1]);ps.setString(3,r[2]);ps.setInt(4,Integer.parseInt(r[3]));
          ps.setString(5,r[4]);ps.setDate(6,java.sql.Date.valueOf(r[5]));ps.setBigDecimal(7,new java.math.BigDecimal(r[6]));
          ps.addBatch();if(++count%1000==0)ps.executeBatch();
        }
        ps.executeBatch();c.commit();
      }catch(Exception e){c.rollback();if(e instanceof SQLException)throw (SQLException)e;throw new SQLException("Invalid pass row",e);}
      finally{c.setAutoCommit(true);}
    }
  }
}
