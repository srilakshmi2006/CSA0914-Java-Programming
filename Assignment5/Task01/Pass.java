public class Pass {
  public final String id, regNo, name, stop, validity; public final int route; public final double amount;
  public Pass(String id,String regNo,String name,int route,String stop,String validity,double amount){
    this.id=id;this.regNo=regNo;this.name=name;this.route=route;this.stop=stop;this.validity=validity;this.amount=amount;
  }
  public String toLine(){return String.join("|",id,regNo,name,Integer.toString(route),stop,validity,Double.toString(amount));}
  public static Pass parse(String line){
    String[] p=line.split("\\|",-1); if(p.length!=7)throw new IllegalArgumentException("Wrong field count");
    return new Pass(p[0],p[1],p[2],Integer.parseInt(p[3]),p[4],p[5],Double.parseDouble(p[6]));
  }
  public String toString(){return toLine();}
}
