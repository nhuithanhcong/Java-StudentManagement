package model;

public class Enrollment {
    private String studentId;
    private String subjectId;
    private String semester;
    private String status;

    public Enrollment() {
    }

    public Enrollment(String studentId, String subjectId, String semester, String status) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.semester = semester;
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return studentId + "," + subjectId + "," + 
               semester + "," + status;
    }
  
}
