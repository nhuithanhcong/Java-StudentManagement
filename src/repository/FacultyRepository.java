package repository;

import model.Faculty;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class FacultyRepository implements GenericFileRepository<Faculty> {
    private final String filePath = "data/faculties.txt";

    @Override
    public List<Faculty> loadAll() {
        List<Faculty> faculties = new ArrayList<>();
        List<String> lines = FileUtils.readLines(filePath);

        for (String line : lines) {
            //facultyId,facultyName,departments
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                String id = parts[0].trim();
                String name = parts[1].trim();
                
                List<String> depts = new ArrayList<>();
                String deptStr = parts[2].trim();
                if (!deptStr.equalsIgnoreCase("NONE") && !deptStr.isEmpty()) {
                    String[] deptArray = deptStr.split(";");
                    for (String d : deptArray) {
                        depts.add(d.trim());
                    }
                }
                Faculty faculty = new Faculty(id, name, depts);
                faculties.add(faculty);
            }
        }
        return faculties;
    }

    @Override
    public void saveAll(List<Faculty> items) {
        List<String> lines = new ArrayList<>();
        for (Faculty faculty : items) {
            lines.add(faculty.toString());
        }
        FileUtils.writeLines(filePath, lines);
    }
}
