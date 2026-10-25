package studentmanagement;

public class Student {

    // Instance variables
    private String studentID;
    private String name;
    private int age;
    private String course;
    private String grade;

    // Constructor
    public Student(String studentID, String name, int age, String course, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.course = course;
        this.grade = grade;
    }

    // Getters
    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public String getGrade() {
        return grade;
    }

    // Setters
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}