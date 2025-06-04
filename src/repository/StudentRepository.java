package repository;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentRepository {

    // Insert Student
    public void save(Student student) {
        String sql = "INSERT INTO student (name, email, birth_date) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setDate(3, Date.valueOf(student.getBirthDate()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Student via Email
    public Student findByEmail(String email) {
        String sql = "SELECT * FROM student WHERE email = ?";
        Student student = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDate("birth_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    // Student List
    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDate("birth_date").toLocalDate()
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
