package command;

import exception.RegistrationException;
import model.Enrollment;
import repository.EnrollmentRepository;

import java.util.List;

public class RegisterCourseCommand implements Command {
    private final List<Enrollment> enrollments;
    private final Enrollment enrollment;
    private final EnrollmentRepository repo;

    public RegisterCourseCommand(List<Enrollment> enrollments, Enrollment enrollment, EnrollmentRepository repo) {
        this.enrollments = enrollments;
        this.enrollment = enrollment;
        this.repo = repo;
    }

    @Override
    public void execute() throws RegistrationException {
        enrollments.add(enrollment);
        repo.saveAll(enrollments);
    }

    @Override
    public void undo() {
        enrollments.remove(enrollment);
        repo.saveAll(enrollments);
    }
}