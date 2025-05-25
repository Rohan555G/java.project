import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnrollmentService {
    private HashMap<String, List<String>> enrollmentMap;
    private StudentCourseDAO dao;

    public EnrollmentService() {
        dao = new StudentCourseDAO();
        try {
            enrollmentMap = dao.load();
        } catch (IOException | ClassNotFoundException e) {
            enrollmentMap = new HashMap<>();
        }
    }

    public boolean addCourse(String studentId, String course) {
        List<String> courses = enrollmentMap.getOrDefault(studentId, new ArrayList<>());
        if (courses.contains(course)) {
            return false;
        }
        courses.add(course);
        enrollmentMap.put(studentId, courses);
        save();
        return true;
    }

    public boolean dropCourse(String studentId, String course) {
        List<String> courses = enrollmentMap.get(studentId);
        if (courses == null || !courses.remove(course)) {
            return false;
        }
        if (courses.isEmpty()) {
            enrollmentMap.remove(studentId);
        } else {
            enrollmentMap.put(studentId, courses);
        }
        save();
        return true;
    }

    public List<String> searchCourses(String studentId) {
        return enrollmentMap.getOrDefault(studentId, new ArrayList<>());
    }

    private void save() {
        try {
            dao.save(enrollmentMap);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
