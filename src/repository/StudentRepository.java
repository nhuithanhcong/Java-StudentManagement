package repository;

import model.Student;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements GenericFileRepository<Student> {
    private final String filePath = "data/students.txt";
    
    @Override
    public List<Student> loadAll() {
        List<Student> students = new ArrayList<>();
        List<String> lines = FileUtils.readLines(filePath);
        
        for (String line : lines) {
            //id,name,dob,email,facultyId
            String[] parts = line.split(",");
            if (parts.length >= 5) {
                Student student = new Student(
                    parts[0].trim(),
                    parts[1].trim(),
                    parts[2].trim(),
                    parts[3].trim(),
                    parts[4].trim()
                );
                students.add(student);
            }
        }
        return students;
    }
    
    @Override
    public void saveAll(List<Student> items) {
        List<String> lines = new ArrayList<>();
        for (Student student : items) {
            lines.add(student.toString());
        }
        FileUtils.writeLines(filePath, lines);
    }
}

