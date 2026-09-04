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
    
    public void addSubject(String subjectId, String subjectName, int creadits, List<String> prereqs) throws ValidationException{
        if (subjectId == null || subjectId.trim().isEmpty()) {
            thorw new ValidationException("");
        }
        if (credits <= 0) {
            thorw new ValidationException("");
        }
        for (Subject s : subjectList) {
            if (s.getSubjectId().equalsIgnoreCase(subjectId)) {
                throw new ValidationException("");
            }
        }
        
        Subject subject = new Subject(subjectId, subjectName, creadits, prereqs);
        subjectList.add(subject);
        subjectRepo.saveAll(subjectList);
    }
    
    public Subject findById(String subjectId) {
        for (Subject s : subjectList) {
            if (s.getSubjectId().equalsIgnoreCase(subjectId)) {
                return s;
            }
        }
        return null;
    }
}
