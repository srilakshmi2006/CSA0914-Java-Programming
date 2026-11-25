import java.nio.file.*; import java.io.*; import java.util.*; import java.util.concurrent.*;
public class ResultProcessor {
  static class Outcome { final boolean ok; final String message; Outcome(boolean o,String m){ok=o;message=m;} }
  static Outcome process(Path p){
    try { List<String> lines=Files.readAllLines(p); if(lines.isEmpty()) throw new IOException("Empty score file");
      int total=lines.stream().mapToInt(Integer::parseInt).sum(); return new Outcome(true,p.getFileName()+": total="+total);
    } catch(Exception e){return new Outcome(false,p.getFileName()+": "+e.getMessage());}
  }
  public static void main(String[] args)throws Exception{
    List<Path> paths=new ArrayList<>(); for(String a:args)paths.add(Paths.get(a));
    ExecutorService pool=Executors.newFixedThreadPool(4); List<Future<Outcome>> fs=new ArrayList<>();
    for(Path p:paths)fs.add(pool.submit(()->process(p)));
    int ok=0,failed=0;
    for(Future<Outcome> f:fs){Outcome o=f.get();System.out.println(o.message);if(o.ok)ok++;else failed++;}
    pool.shutdown(); System.out.println("Success="+ok+", Failed="+failed);
  }
}
