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
    
    public void addSubject(String subjectId, String subjectName, int credits, List<String> prereqs) throws ValidationException{
        if (subjectId == null || subjectId.trim().isEmpty()) {
            throw new ValidationException("The course code must not be blank!");
        }
        if (credits <= 0) {
            throw new ValidationException("The number of credits must be greater than 0!");
        }
        for (Subject s : subjectList) {
            if (s.getSubjectId().equalsIgnoreCase(subjectId)) {
                throw new ValidationException("The course code already exists!");
            }
        }
        
        Subject subject = new Subject(subjectId, subjectName, credits, prereqs);
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
