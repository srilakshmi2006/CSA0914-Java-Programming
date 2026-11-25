import java.io.*;
public class BufferedLineCounter {
  static long count(String path)throws IOException{
    long lines=0;try(BufferedInputStream in=new BufferedInputStream(new FileInputStream(path),8192)){
      int b;while((b=in.read())!=-1)if(b=='\n')lines++;
    }return lines;
  }
  public static void main(String[] a)throws Exception{
    if(a.length==0){System.out.println("Usage: java BufferedLineCounter file");return;}
    long start=System.nanoTime();long n=count(a[0]);long elapsed=System.nanoTime()-start;
    System.out.println("Lines="+n+", time(ns)="+elapsed);
  }
}
