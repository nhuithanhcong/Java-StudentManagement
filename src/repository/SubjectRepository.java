
package repository;

import model.Subject;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;


public class SubjectRepository  implements GenericFileRepository<Subject> {
    private final String filePath = "data/subjects.txt";
    
    @Override
    public List<Subject> loadAll() {
        List<Subject> subjects = new ArrayList<>();
        List<String> lines = FileUtils.readLines(filePath);
        
        for (String line : lines) {
            //subjectId,subjectName,credits,prerequisiteIds
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                String id = parts[0].trim();
                String name = parts[1].trim();
                int credits = Integer.parseInt(parts[2].trim());
                
                List<String> prereqs = new ArrayList<>();
                String prereqStr = parts[3].trim();
                if (!prereqStr.equalsIgnoreCase("NONE") && !prereqStr.isEmpty()) {
                    String[] prereqArray = prereqStr.split(";");
                    for (String p : prereqArray) {
                        prereqs.add(p.trim());
                    }
                }
                Subject subject = new Subject(id, name, credits, prereqs);
                subjects.add(subject);
            }
        }
        return subjects;
    }
    
    @Override
    public void saveAll(List<Subject> items) {
        List<String> lines = new ArrayList<>();
        for (Subject subject : items) {
            lines.add(subject.toString());
        }
        FileUtils.writeLines(filePath, lines);
    }
}
