import java.util.*;
public class Main {
    static volatile long sink;
    static long measure(Runnable task) { long s=System.nanoTime(); task.run(); return System.nanoTime()-s; }
    public static void main(String[] args) {
        int[] sizes={1000,10000,50000};
        System.out.println("Q2 ArrayList vs LinkedList (nanoseconds; one local run)");
        for(int n:sizes) {
            List<Integer> a=new ArrayList<>(), l=new LinkedList<>();
            for(int i=0;i<n;i++){a.add(i);l.add(i);}
            long ar=measure(()->{long x=0; for(int i=0;i<20000;i++) x+=a.get(i%n); sink=x;});
            long lr=measure(()->{long x=0; for(int i=0;i<20000;i++) x+=l.get(i%n); sink=x;});
            long ai=measure(()->{for(int i=0;i<1000;i++) a.add(0,-i);});
            long li=measure(()->{for(int i=0;i<1000;i++) l.add(0,-i);});
            long at=measure(()->{long x=0;for(int v:a)x+=v;sink=x;});
            long lt=measure(()->{long x=0;for(int v:l)x+=v;sink=x;});
            System.out.printf("n=%d | random access A=%d L=%d | front insert A=%d L=%d | iterate A=%d L=%d%n",n,ar,lr,ai,li,at,lt);
        }
        Deque<String> upNext=new ArrayDeque<>();
        upNext.addFirst("Urgent"); upNext.addLast("Next"); upNext.removeFirst();
        System.out.println("Up Next ArrayDeque example: "+upNext);
        System.out.println("Conclusion: ArrayList suits frequent indexed reads and iteration; LinkedList only when frequent edits through an already-held iterator/node justify its overhead. ArrayDeque suits double-ended queues.");
    }
}