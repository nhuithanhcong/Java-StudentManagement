package controller;

import exception.ValidationException;
import model.Faculty;
import repository.FacultyRepository;

import java.util.List;

public class FacultyController {
    private final FacultyRepository facultyRepo;
    private final List<Faculty> facultyList;
    
    public FacultyController() {
        this.facultyRepo = new FacultyRepository();
        this.facultyList = facultyRepo.loadAll();
    }
    
    public List<Faculty> getAllFaculties() {
        return facultyList;
    }
    
    public void addFaculty(String id, String name, List<String> departments) throws ValidationException {
        if (id == null || id.trim().isEmpty()) {
            throw new ValidationException("The department code must not be blank!");
        }
        Faculty faculty = new Faculty(id, name, departments);
        facultyList.add(faculty);
        facultyRepo.saveAll(facultyList);
    }
}
