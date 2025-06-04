package service;

import model.Student;
import repository.StudentRepository;

import java.util.List;

public class StudentService {
    private StudentRepository repository;

    public StudentService() {
        this.repository = new StudentRepository();
    }

    public void createStudent(Student student) {
        repository.save(student);
    }

    public Student getStudentByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }
}
