package repository;

import model.Course;
import model.Enrollment;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {

    // Creat Enrollments
    public void save(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, enrollment.getStudent().getId());
            stmt.setLong(2, enrollment.getCourse().getId());
            stmt.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List Enrollments
    public List<Enrollment> findAll() {
        String sql = """
                SELECT e.id, e.enrollment_date, 
                       s.id AS student_id, s.name AS student_name, s.email, s.birth_date, 
                       c.id AS course_id, c.name AS course_name, c.description, c.workload
                FROM enrollment e
                JOIN student s ON e.student_id = s.id
                JOIN course c ON e.course_id = c.id
                """;

        List<Enrollment> enrollments = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getLong("student_id"),
                        rs.getString("student_name"),
                        rs.getString("email"),
                        rs.getDate("birth_date").toLocalDate()
                );

                Course course = new Course(
                        rs.getLong("course_id"),
                        rs.getString("course_name"),
                        rs.getString("description"),
                        rs.getInt("workload")
                );

                Enrollment enrollment = new Enrollment(
                        rs.getLong("id"),
                        student,
                        course,
                        rs.getDate("enrollment_date").toLocalDate()
                );

                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }
}
