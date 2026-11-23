import java.util.*;
class Student {
    private final String regNo, name;
    Student(String regNo,String name){this.regNo=regNo;this.name=name;}
    public boolean equals(Object o){if(this==o)return true;if(!(o instanceof Student))return false;return Objects.equals(regNo,((Student)o).regNo);}
    // TODO 1 completed: equal registration numbers produce equal hash codes.
    public int hashCode(){return Objects.hash(regNo);}
    public String toString(){return regNo+"-"+name;}
}
public class Main {
    public static void main(String[] args){
        Set<Student> students=new HashSet<>();
        students.add(new Student("ECE101","Asha"));
        students.add(new Student("ECE101","Asha duplicate"));
        students.add(new Student("ECE102","Bala"));
        System.out.println("Corrected HashSet unique students: "+students.size()+" (expected 2)");
        System.out.println("Set: "+students);
        Set<String> scans=new HashSet<>();
        scans.add("ECE101");scans.add("ECE101");scans.add("ECE102");
        System.out.println("Three test scans, two unique IDs: "+scans.size());
        TreeSet<Student> tree=new TreeSet<>(Comparator.comparing(s->s.toString().split("-")[0]));
        tree.add(new Student("ECE101","Asha"));tree.add(new Student("ECE101","Duplicate"));tree.add(new Student("ECE102","Bala"));
        System.out.println("TreeSet unique by comparator: "+tree.size());
        Map<String,Student> byId=new HashMap<>();
        byId.put("ECE101",new Student("ECE101","Asha"));byId.put("ECE101",new Student("ECE101","Duplicate"));byId.put("ECE102",new Student("ECE102","Bala"));
        System.out.println("HashMap keys (unique IDs): "+byId.size());
        System.out.println("HashSet/HashMap average lookup O(1); TreeSet O(log n); all store O(n) unique records.");
        System.out.println("Fields used in hashCode/equals should be immutable while stored in a hash collection.");
    }
}