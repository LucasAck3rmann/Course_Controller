package view;

import model.Course;
import model.Enrollment;
import model.Student;
import service.CourseService;
import service.EnrollmentService;
import service.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private StudentService studentService;
    private CourseService courseService;
    private EnrollmentService enrollmentService;

    public Menu() {
        scanner = new Scanner(System.in);
        studentService = new StudentService();
        courseService = new CourseService();
        enrollmentService = new EnrollmentService();
    }

    // Main
    public void show() {
        int option = -1;
        while (option != 0) {
            System.out.println("\n=== Course Management System ===");
            System.out.println("1 - Manage Students");
            System.out.println("2 - Manage Courses");
            System.out.println("3 - Manage Enrollments");
            System.out.println("0 - Exit");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();
            scanner.nextLine(); // limpa o buffer

            switch (option) {
                case 1 -> manageStudents();
                case 2 -> manageCourses();
                case 3 -> manageEnrollments();
                case 0 -> System.out.println("Exiting the system.");
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // Menu for manage Students
    private void manageStudents() {
        int option = -1;
        while (option != 0) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1 - Add Student");
            System.out.println("2 - List Students");
            System.out.println("3 - Search Student by Email");
            System.out.println("0 - Back");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> addStudent();
                case 2 -> listStudents();
                case 3 -> searchStudentByEmail();
                case 0 -> {}
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // Menu for manage Courses
    private void manageCourses() {
        int option = -1;
        while (option != 0) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1 - Add Course");
            System.out.println("2 - List Courses");
            System.out.println("3 - Search Course by Name");
            System.out.println("0 - Back");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> addCourse();
                case 2 -> listCourses();
                case 3 -> searchCourseByName();
                case 0 -> {}
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // Menu for manage Enrollments
    private void manageEnrollments() {
        int option = -1;
        while (option != 0) {
            System.out.println("\n--- Enrollment Management ---");
            System.out.println("1 - Add Enrollment");
            System.out.println("2 - List Enrollments");
            System.out.println("0 - Back");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> addEnrollment();
                case 2 -> listEnrollments();
                case 0 -> {}
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // Assistant method for Student

    private void addStudent() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Birth Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(date);

        Student student = new Student(null, name, email, birthDate);
        studentService.createStudent(student);
        System.out.println("Student added successfully.");
    }

    private void listStudents() {
        List<Student> students = studentService.getAllStudents();
        students.forEach(System.out::println);
    }

    private void searchStudentByEmail() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Student student = studentService.getStudentByEmail(email);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    // Assistant method for Coursess

    private void addCourse() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Workload (hours): ");
        int workload = scanner.nextInt();
        scanner.nextLine();

        Course course = new Course(null, name, description, workload);
        courseService.createCourse(course);
        System.out.println("Course added successfully.");
    }

    private void listCourses() {
        List<Course> courses = courseService.getAllCourses();
        courses.forEach(System.out::println);
    }

    private void searchCourseByName() {
        System.out.print("Enter name (or part of it): ");
        String name = scanner.nextLine();

        List<Course> courses = courseService.getCoursesByName(name);
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            courses.forEach(System.out::println);
        }
    }

    // Assistant method for Enrollmemt

    private void addEnrollment() {
        System.out.print("Student ID: ");
        Long studentId = scanner.nextLong();

        System.out.print("Course ID: ");
        Long courseId = scanner.nextLong();

        scanner.nextLine();
        System.out.print("Enrollment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        LocalDate enrollmentDate = LocalDate.parse(date);

        Student student = new Student();
        student.setId(studentId);

        Course course = new Course();
        course.setId(courseId);

        Enrollment enrollment = new Enrollment(null, student, course, enrollmentDate);
        enrollmentService.createEnrollment(enrollment);
        System.out.println("Enrollment added successfully.");
    }

    private void listEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        enrollments.forEach(System.out::println);
    }
}
