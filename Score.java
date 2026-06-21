package entity;

import java.io.Serializable;

public class Score implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int studentId;
    private String stuNo;
    private String studentName;
    private int subjectId;
    private String subjectNo;
    private String subjectName;
    private double credit;
    private double score;
    private String semester;
    private String className;

    public Score() {}
    public Score(int id, int studentId, int subjectId, double score, String semester) {
        this.id = id; this.studentId = studentId; this.subjectId = subjectId;
        this.score = score; this.semester = semester;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getStuNo() { return stuNo; }
    public void setStuNo(String stuNo) { this.stuNo = stuNo; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }
    public String getSubjectNo() { return subjectNo; }
    public void setSubjectNo(String subjectNo) { this.subjectNo = subjectNo; }
    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
    public double getCredit() { return credit; }
    public void setCredit(double credit) { this.credit = credit; }
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
}
