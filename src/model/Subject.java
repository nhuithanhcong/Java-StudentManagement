package model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String subjectId;
    private String subjectName;
    private int credits;
    private List<String> prerequisiteIds;

    public Subject() {
        this.prerequisiteIds = new ArrayList<>();
    }
    
    public Subject(String subjectId, String subjectName, int credits, List<String> prerequisiteIds) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credits = credits;
        
        if (prerequisiteIds != null){
            this.prerequisiteIds = prerequisiteIds;
        }else {
            this.prerequisiteIds = new ArrayList<>();
        }
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<String> getPrerequisiteIds() {
        return prerequisiteIds;
    }

    public void setPrerequisiteIds(List<String> prerequisiteIds) {
        this.prerequisiteIds = prerequisiteIds;
    }
    
    public void addPrerequisiteId(String code) {
        if (this.prerequisiteIds == null) {
            this.prerequisiteIds = new ArrayList<>();
        }
        this.prerequisiteIds.add(code);
    }
    
    @Override
    public String toString() {
        String placeHolder = "";
        if (prerequisiteIds != null && !prerequisiteIds.isEmpty()) {
            placeHolder = String.join(";", prerequisiteIds);
        } else {
            placeHolder = "NONE";
        }
        
        return subjectId + "," + subjectName + "," +
               credits + "," + placeHolder;
    }
    
    
}
