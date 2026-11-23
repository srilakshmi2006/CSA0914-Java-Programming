import java.util.*;
public class Main {
    static class Medicine { String code,name; int stock; Medicine(String c,String n,int s){code=c;name=n;stock=s;} public String toString(){return code+":"+name+"("+stock+")";} }
    public static void main(String[] args){
        for(int n:new int[]{1000,10000,50000}){
            Map<String,Medicine> hash=new HashMap<>(), linked=new LinkedHashMap<>(), tree=new TreeMap<>();
            for(int i=n-1;i>=0;i--){Medicine m=new Medicine(String.format("M%06d",i),"Medicine"+i,100+i);hash.put(m.code,m);linked.put(m.code,m);tree.put(m.code,m);}
            long s=System.nanoTime();for(int i=0;i<100000;i++)hash.get(String.format("M%06d",i%n));long h=System.nanoTime()-s;
            s=System.nanoTime();for(int i=0;i<100000;i++)tree.get(String.format("M%06d",i%n));long t=System.nanoTime()-s;
            s=System.nanoTime();new TreeMap<>(hash);long sorted=System.nanoTime()-s;
            s=System.nanoTime();for(Medicine m:tree.values()){}long treeReport=System.nanoTime()-s;
            System.out.printf("n=%d | 100k HashMap lookups=%d ns | TreeMap lookups=%d ns | sort HashMap report=%d ns | TreeMap ordered report=%d ns%n",n,h,t,sorted,treeReport);
        }
        Map<String,String> insertionOrder=new LinkedHashMap<>();insertionOrder.put("M2","Added first");insertionOrder.put("M1","Added second");
        System.out.println("Insertion order (LinkedHashMap): "+insertionOrder.keySet());
        System.out.println("Code order (TreeMap): "+new TreeMap<>(Map.of("M2","x","M1","y")).keySet());
        System.out.println("Recommendation: HashMap for fastest lookup, TreeMap for continuously sorted code report, LinkedHashMap for insertion-order report. Hashtable is legacy synchronized API; modern alternatives are clearer.");
    }
}