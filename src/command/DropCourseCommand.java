package command;

import model.Enrollment;
import repository.EnrollmentRepository;

import java.util.List;

public class DropCourseCommand implements Command {
    private final List<Enrollment> enrollments;
    private final Enrollment enrollment;
    private final EnrollmentRepository repo;

    public DropCourseCommand(List<Enrollment> enrollments, Enrollment enrollment, EnrollmentRepository repo) {
        this.enrollments = enrollments;
        this.enrollment = enrollment;
        this.repo = repo;
    }

    @Override
    public void execute() {
        enrollments.remove(enrollment);
        repo.saveAll(enrollments);
    }

    @Override
    public void undo() {
        enrollments.add(enrollment);
        repo.saveAll(enrollments);
    }
}