public class Student implements Comparable {
    public Student(String name, float gpa) {
        this.name = name;
        this.gpa = gpa;
    }
    public Student() {}
    public int compareTo(Object o) {
        if ( ((Student)o).gpa < gpa )
            return 1;
        else if ( ((Student)o).gpa > gpa )
            return -1;
        else
            return 0;
    }
    public boolean equals(Object o) {
        if (gpa == ((Student) o).gpa)
            return true;
        else
            return false;
    }
    public int hashCode() {
        return (int) (gpa*10.0);
    }
    public String getName() { return name;}
    public float getGpa() { return gpa;}
    private String name;
    private float gpa = 0.0F;  //make sure hashing works!
}


