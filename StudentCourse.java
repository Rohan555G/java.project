package model;

import java.io.Serializable;
import java.util.List;

public class StudentCourse implements Serializable {
    private String studentId;
    private List<String> courses;

    public StudentCourse(String studentId, List<String> courses) {
        this.studentId = studentId;
        this.courses = courses;
    }

    public String getStudentId() {
        return studentId;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
