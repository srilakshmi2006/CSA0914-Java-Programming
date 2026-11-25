public class ScannerStations {
  static class Station implements Runnable {
    private final int id, scripts;
    Station(int id,int scripts){this.id=id;this.scripts=scripts;}
    public void run(){
      try { Thread.sleep(100L*id); System.out.println("Station "+id+" scanned "+scripts); }
      catch(InterruptedException e){Thread.currentThread().interrupt();}
    }
  }
  public static void main(String[] args) throws InterruptedException {
    Thread[] ts=new Thread[4];
    for(int i=0;i<4;i++){ts[i]=new Thread(new Station(i+1,25));ts[i].start();}
    for(Thread t:ts)t.join();
    System.out.println("All stations completed");
  }
}
