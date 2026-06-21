package entity;

import java.io.Serializable;

public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String subjectNo;
    private String subjectName;
    private double credit;
    private int teacherId;
    private String teacherName;
    private String semester;
    private String description;

    public Subject() {}
    public Subject(int id, String subjectNo, String subjectName, double credit, int teacherId, String semester, String description) {
        this.id = id; this.subjectNo = subjectNo; this.subjectName = subjectName;
        this.credit = credit; this.teacherId = teacherId; this.semester = semester; this.description = description;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getSubjectNo() { return subjectNo; }
    public void setSubjectNo(String subjectNo) { this.subjectNo = subjectNo; }
    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
    public double getCredit() { return credit; }
    public void setCredit(double credit) { this.credit = credit; }
    public int getTeacherId() { return teacherId; }
    public void setTeacherId(int teacherId) { this.teacherId = teacherId; }
    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
