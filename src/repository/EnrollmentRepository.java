package repository;

import model.Enrollment;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository implements GenericFileRepository<Enrollment> {
    private final String filePath = "data/enrollments.txt";

    @Override
    public List<Enrollment> loadAll() {
        List<Enrollment> enrollments = new ArrayList<>();
        List<String> lines = FileUtils.readLines(filePath);

        for (String line : lines) {
            //studentId,subjectId,semester,status
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                Enrollment enrollment = new Enrollment(
                    parts[0].trim(),
                    parts[1].trim(),
                    parts[2].trim(),
                    parts[3].trim()
                );
                enrollments.add(enrollment);
            }
        }
        return enrollments;
    }

    @Override
    public void saveAll(List<Enrollment> items) {
        List<String> lines = new ArrayList<>();
        for (Enrollment enrollment : items) {
            lines.add(enrollment.toString());
        }
        FileUtils.writeLines(filePath, lines);
    }
}
