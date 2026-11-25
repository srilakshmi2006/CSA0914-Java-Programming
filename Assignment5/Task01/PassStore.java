import java.io.*;import java.nio.file.*;import java.util.*;
public class PassStore {
  private final Path file; public PassStore(Path file){this.file=file;}
  public void add(Pass p)throws IOException{Files.writeString(file,p.toLine()+System.lineSeparator(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);}
  public Pass find(String id)throws IOException{
    if(!Files.exists(file))return null;
    try(BufferedReader r=Files.newBufferedReader(file)){String line;while((line=r.readLine())!=null){try{Pass p=Pass.parse(line);if(p.id.equals(id))return p;}catch(RuntimeException bad){System.err.println("Skipping damaged row: "+line);}}}
    return null;
  }
  public List<Pass> listRoute(int route)throws IOException{
    List<Pass> out=new ArrayList<>();if(!Files.exists(file))return out;
    try(BufferedReader r=Files.newBufferedReader(file)){String line;while((line=r.readLine())!=null)try{Pass p=Pass.parse(line);if(p.route==route)out.add(p);}catch(RuntimeException bad){System.err.println("Skipping damaged row");}}
    return out;
  }
  public static void main(String[] a)throws Exception{
    PassStore s=new PassStore(Paths.get("passes.txt"));
    s.add(new Pass("P1003","REG03","Anu",12,"Central","2027-03-31",500));
    System.out.println(s.find("P1003"));System.out.println(s.find("P9999"));
  }
}
