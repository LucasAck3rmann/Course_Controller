package repository;

import model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    // Insert new course
    public void save(Course course) {
        String sql = "INSERT INTO course (name, description, workload) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getName());
            stmt.setString(2, course.getDescription());
            stmt.setInt(3, course.getWorkload());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Search via name
    public List<Course> findByName(String name) {
        String sql = "SELECT * FROM course WHERE name ILIKE ?";
        List<Course> courses = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Course course = new Course(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("workload")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // List All Courses
    public List<Course> findAll() {
        String sql = "SELECT * FROM course";
        List<Course> courses = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Course course = new Course(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("workload")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
