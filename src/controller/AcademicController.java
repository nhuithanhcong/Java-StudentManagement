package controller;

import model.Grade;
import model.Student;
import model.Subject;

import java.util.List;

public class AcademicController {
    private final SubjectController subjectController;
    
    public AcademicController(SubjectController subjectController1) {
        this.subjectController = subjectController1;
    }
    
    //GPA he 10
    public double calculateGPA(Student student) {
        List<Grade> grades = student.getGrades();
        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }

        double totalPoints = 0;
        int totalCredits = 0;

        for (Grade grade : grades) {
            Subject subject = subjectController.findById(grade.getSubjectId());
            if (subject != null) {
                totalPoints += grade.getScore() * subject.getCredits();
                totalCredits += subject.getCredits();
            }
        }

        return totalCredits == 0 ? 0.0 : (double) Math.round((totalPoints / totalCredits) * 100) / 100;
    }

    // Tính tổng số tín chỉ tích lũy (Chỉ tính các môn điểm >= 4.0)
    public int calculateAccumulatedCredits(Student student) {
        List<Grade> grades = student.getGrades();
        if (grades == null) return 0;

        int credits = 0;
        for (Grade grade : grades) {
            if (grade.getScore() >= 4.0) { // Điểm qua môn
                Subject subject = subjectController.findById(grade.getSubjectId());
                if (subject != null) {
                    credits += subject.getCredits();
                }
            }
        }
        return credits;
    }
}
