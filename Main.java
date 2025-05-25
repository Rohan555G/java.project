import java.util.List;
import java.util.Scanner;

public class Main {
    private static EnrollmentService service = new EnrollmentService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Student Course Enrollment System ===");
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput();
            switch (choice) {
                case 1 -> enroll();
                case 2 -> drop();
                case 3 -> search();
                case 4 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please choose 1-4.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n1. Enroll in a Course");
        System.out.println("2. Drop a Course");
        System.out.println("3. Search Courses by Student ID");
        System.out.println("4. Exit");
        System.out.print("Choose option: ");
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return val;
    }

    private static void enroll() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Enter Course to enroll: ");
        String course = scanner.nextLine().trim();
        if (service.addCourse(studentId, course)) {
            System.out.println("Course enrolled successfully.");
        } else {
            System.out.println("Student already enrolled in that course.");
        }
    }

    private static void drop() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Enter Course to drop: ");
        String course = scanner.nextLine().trim();
        if (service.dropCourse(studentId, course)) {
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("Course not found for the student.");
        }
    }

    private static void search() {
        System.out.print("Enter Student ID to search: ");
        String studentId = scanner.nextLine().trim();
        List<String> courses = service.searchCourses(studentId);
        if (courses.isEmpty()) {
            System.out.println("No courses found for Student ID: " + studentId);
        } else {
            System.out.println("Courses for Student ID " + studentId + ":");
            for (String c : courses) {
                System.out.println(" - " + c);
            }
        }
    }
}
