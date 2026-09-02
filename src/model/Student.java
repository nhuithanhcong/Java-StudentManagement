package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private String dob;
    private String email;
    private String facultyId;
    private List<Grade> grades;

    public Student() {
        this.grades = new ArrayList<>();
    }

    public Student(String id, String name, String dob, String email, String facultyId) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.facultyId = facultyId;
        this.grades = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
    
    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }
    
     @Override
     public String toString() {
         return id + "," + name + "," + 
                dob + "," + email + "," +
                facultyId;
         
     }
}
