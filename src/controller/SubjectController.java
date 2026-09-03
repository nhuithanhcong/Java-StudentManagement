package controller;

import exception.ValidationException;
import model.Subject;
import repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

public class SubjectController {
    private final SubjectRepository subjectRepo;
    private final List<Subject> subjectList;
    
    public SubjectController() {
        this.subjectRepo = new SubjectRepository();
        this.subjectList = subjectRepo.loadAll();
    }
    
    public List<Subject> getSubjects() {
        return subjectList;
    }
    
    public void addSubject(String subjectId, String subjectName, int) {
        
    }
}
