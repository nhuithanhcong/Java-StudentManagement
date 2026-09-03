package controller;

import exception.ValidationException;
import model.Student;
import repository.StudentRepository;
import utils.SortingUtils;
import utils.ValidationUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentController {
    private final StudentRepository studentRepo;
    private final List<Student> studentList;
    
    public StudentController() {
        this.studentRepo = new StudentRepository();
        this.studentList = studentRepo.loadAll();
    }
    
    public List<Student> getAllStudents() {
        return studentList;
    }
    
    public void addStudent(String id, String name, String dob, String email, String facultyId) throws ValidationException {
        if (id == null || id.trim().isEmpty()) {
            throw new ValidationException("The student ID must not be blank!");
        }
        if (!ValidationUtils.isVEmail(email)) {
            throw new ValidationException("Invalid email format!");
        }
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(id)) {
                throw new ValidationException("Student ID already exists!");
            }
        }
        
        Student student = new Student(id, name, dob, email, facultyId);
        studentList.add(student);
        studentRepo.saveAll(studentList);
    }
    
    public Student findById(String id) {
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }
    
    public List<Student> searchByName(String keyword) {
        List<Student> result = new ArrayList();
        for (Student s : studentList) {
            if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }
    public void sortByName() {
        SortingUtils.sortStudents(studentList, Comparator.comparing(Student::getName));
    }
}
