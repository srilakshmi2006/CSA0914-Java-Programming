import java.util.*;
public class Main {
    static String buildPlus(int fields) {
        String s = "";
        for (int i = 1; i <= fields; i++) s += "FIELD" + i + "=VALUE" + i + ";";
        return s;
    }
    static String buildBuilder(int fields) {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= fields; i++) s.append("FIELD").append(i).append("=VALUE").append(i).append(';');
        return s.toString();
    }
    static String buildBuffer(int fields) {
        StringBuffer s = new StringBuffer();
        for (int i = 1; i <= fields; i++) s.append("FIELD").append(i).append("=VALUE").append(i).append(';');
        return s.toString();
    }
    static long time(Runnable r, int reps) {
        long start = System.nanoTime();
        for (int i=0;i<reps;i++) r.run();
        return (System.nanoTime()-start)/reps;
    }
    public static void main(String[] args) {
        int[][] workloads = {{6,1},{50,10000},{100,3000}};
        System.out.println("Q1 String / StringBuilder / StringBuffer comparison");
        for (int[] w : workloads) {
            int fields=w[0], tickets=w[1];
            Runnable a=()->{for(int i=0;i<tickets;i++) buildPlus(fields);};
            Runnable b=()->{for(int i=0;i<tickets;i++) buildBuilder(fields);};
            Runnable c=()->{for(int i=0;i<tickets;i++) buildBuffer(fields);};
            a.run(); b.run(); c.run(); // warm-up
            System.out.printf("fields=%d tickets=%d | plus=%d ns | builder=%d ns | buffer=%d ns%n",
                fields,tickets,time(a,3),time(b,3),time(c,3));
        }
        System.out.println("Per-ticket recommendation: StringBuilder. Shared concurrent log: synchronize access or use a thread-safe logging mechanism; StringBuffer alone does not make a multi-step log operation atomic.");
        System.out.println("Estimated intermediate concatenation strings for repeated + grow with fields; builders use one mutable buffer plus final String.");
    }
}