package entity;

import java.io.Serializable;

public class Classes implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String classNo;
    private String className;
    private int majorId;
    private String majorName;
    private String grade;

    public Classes() {}
    public Classes(int id, String classNo, String className, int majorId, String grade) {
        this.id = id; this.classNo = classNo; this.className = className;
        this.majorId = majorId; this.grade = grade;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getClassNo() { return classNo; }
    public void setClassNo(String classNo) { this.classNo = classNo; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public int getMajorId() { return majorId; }
    public void setMajorId(int majorId) { this.majorId = majorId; }
    public String getMajorName() { return majorName; }
    public void setMajorName(String majorName) { this.majorName = majorName; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}
