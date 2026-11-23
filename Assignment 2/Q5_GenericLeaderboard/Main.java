import java.util.*;
class Team {
    final String name; final int points, goalDiff;
    Team(String n,int p,int g){name=n;points=p;goalDiff=g;}
    public String toString(){return name+"("+points+","+goalDiff+")";}
}
class Leaderboard<T> {
    private final List<T> items=new ArrayList<>();
    private final Comparator<? super T> order;
    Leaderboard(Comparator<? super T> order){this.order=order;}
    void add(T item){items.add(item);}
    void addAll(List<? extends T> values){items.addAll(values);}
    List<T> ranking(){List<T> copy=new ArrayList<>(items);copy.sort(order);return copy;}
}
public class Main {
    public static void main(String[] args){
        Comparator<Team> ranking=Comparator.comparingInt((Team x)->x.points).reversed()
            .thenComparing(Comparator.comparingInt((Team x)->x.goalDiff).reversed())
            .thenComparing(x->x.name);
        Leaderboard<Team> board=new Leaderboard<>(ranking);
        board.addAll(Arrays.asList(new Team("Falcons",18,12),new Team("Tigers",18,15),new Team("Eagles",15,8),new Team("Wolves",18,15)));
        System.out.println("Leaderboard: "+board.ranking());
        System.out.println("Comparator contract: transitive, consistent ordering; name is final tie-breaker.");
        System.out.println("Sorting n items: O(n log n) time; copied list O(n) extra space.");
        System.out.println("Comparator is reusable for different types/rules; Comparable defines one natural order for a type.");
    }
}