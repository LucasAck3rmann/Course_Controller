package service;

import model.Course;
import repository.CourseRepository;

import java.util.List;

public class CourseService {
    private CourseRepository repository;

    public CourseService() {
        this.repository = new CourseRepository();
    }

    public void createCourse(Course course) {
        repository.save(course);
    }

    public List<Course> getCoursesByName(String name) {
        return repository.findByName(name);
    }

    public List<Course> getAllCourses() {
        return repository.findAll();
    }
}
