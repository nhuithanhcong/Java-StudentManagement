package model;

public class Grade {
    private String subjectId;
    private double score;

    public Grade() {
    }

    public Grade(String subjectId, double score) {
        this.subjectId = subjectId;
        this.score = score;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return subjectId + "," + score;
    }
}
