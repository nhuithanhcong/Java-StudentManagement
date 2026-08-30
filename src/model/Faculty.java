package model;

import java.util.ArrayList;
import java.util.List;
public class Faculty {
    private String facultyId;
    private String facultyName;
    private List<String> departments;

    public Faculty() {
        this.departments = new ArrayList<>();
    }

    public Faculty(String facultyId, String facultyName, List<String> deparments) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        
        
        if (deparments != null){
            this.departments = deparments;
        }else {
            this.departments = new ArrayList<>();
        }
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public List<String> getDeparments() {
        return departments;
    }

    public void setDeparments(List<String> deparments) {
        this.departments = deparments;
    }

    @Override
    public String toString() {
        String placeHolder = "";
        if (departments != null && !departments.isEmpty()) {
            placeHolder = String.join(";", departments);
        } else {
            placeHolder = "NONE";
        }
        
        return facultyId + "," + facultyName + "," + placeHolder;
    } 
}
