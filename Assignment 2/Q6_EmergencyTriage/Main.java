import java.util.*;
class Patient {
    final String name; final int severity; final long arrival;
    Patient(String n,int s,long a){name=n;severity=s;arrival=a;}
    public String toString(){return name+"(S"+severity+")";}
}
public class Main {
    private long counter=0;
    // TODO 1 completed: lower severity number first; FIFO for equal severity.
    private final PriorityQueue<Patient> queue=new PriorityQueue<>(
        Comparator.comparingInt((Patient p)->p.severity).thenComparingLong(p->p.arrival));
    void admit(String name,int severity){if(severity<1||severity>5)throw new IllegalArgumentException("Severity must be 1..5");queue.add(new Patient(name,severity,counter++));}
    Patient next(){return queue.poll();}
    public static void main(String[] args){
        Main triage=new Main();
        triage.admit("Asha",3);triage.admit("Bala",1);triage.admit("Charan",2);triage.admit("Divya",1);
        triage.admit("Esha",5);triage.admit("Farhan",3);triage.admit("Gita",4);triage.admit("Hari",2);
        System.out.println("Treatment order (poll repeatedly):");
        Patient p;while((p=triage.next())!=null)System.out.println(p);
        System.out.println("PriorityQueue add/poll: O(log n); peek: O(1); storage O(n).");
        System.out.println("Starvation risk: continuous critical arrivals can delay minor cases. Apply waiting-time ageing or a maximum-wait escalation policy.");
    }
}