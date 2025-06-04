package service;

import model.Enrollment;
import repository.EnrollmentRepository;

import java.util.List;

public class EnrollmentService {
    private EnrollmentRepository repository;

    public EnrollmentService() {
        this.repository = new EnrollmentRepository();
    }

    public void createEnrollment(Enrollment enrollment) {
        repository.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return repository.findAll();
    }
}
