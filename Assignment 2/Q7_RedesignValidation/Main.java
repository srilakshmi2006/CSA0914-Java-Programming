import java.util.*;
public class Main {
    static class Medicine {final String code; Medicine(String c){code=c;}}
    static long baseline(int n){
        Map<String,Medicine> map=new HashMap<>();for(int i=n-1;i>=0;i--)map.put(String.format("M%07d",i),new Medicine("M"+i));
        long s=System.nanoTime();List<String> report=new ArrayList<>(map.keySet());Collections.sort(report);return System.nanoTime()-s;
    }
    static long redesigned(int n){
        NavigableMap<String,Medicine> sorted=new TreeMap<>();Map<String,Medicine> insertion=new LinkedHashMap<>();
        for(int i=n-1;i>=0;i--){String code=String.format("M%07d",i);Medicine m=new Medicine(code);sorted.put(code,m);insertion.put(code,m);}
        long s=System.nanoTime();for(String key:sorted.keySet()){}return System.nanoTime()-s;
    }
    public static void main(String[] args){
        System.out.println("Q7 redesign: repeated sorting of HashMap keys vs maintaining TreeMap order");
        for(int n:new int[]{1000,10000,50000}){
            long old=baseline(n), revised=redesigned(n);
            System.out.printf("n=%d | baseline sort report=%d ns | maintained sorted traversal=%d ns%n",n,old,revised);
        }
        System.out.println("Diagnosis: sorting all keys costs O(n log n) each report.");
        System.out.println("Objective: keep code order continuously, so report traversal is O(n); TreeMap insertion/lookup is O(log n), with O(n) storage.");
        System.out.println("Trade-off: TreeMap costs more per update than HashMap. For rare reports and many updates, sorting a HashMap on demand may remain preferable.");
    }
}