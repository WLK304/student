package entity;

import java.io.Serializable;
import java.util.*;

public class SubjectStats implements Serializable {
    private static final long serialVersionUID = 1L;
    private int subjectId;
    private String subjectNo;
    private String subjectName;
    private String teacherName;
    private String semester;
    private int studentCount;
    private double avgScore;
    private double maxScore;
    private double minScore;
    private double passRate;
    // 各分数段人数: < 60, 60-69, 70-79, 80-89, >= 90
    private int[] distribution = new int[5];

    public SubjectStats() {}
    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }
    public String getSubjectNo() { return subjectNo; }
    public void setSubjectNo(String subjectNo) { this.subjectNo = subjectNo; }
    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    public int getStudentCount() { return studentCount; }
    public void setStudentCount(int studentCount) { this.studentCount = studentCount; }
    public double getAvgScore() { return avgScore; }
    public void setAvgScore(double avgScore) { this.avgScore = avgScore; }
    public double getMaxScore() { return maxScore; }
    public void setMaxScore(double maxScore) { this.maxScore = maxScore; }
    public double getMinScore() { return minScore; }
    public void setMinScore(double minScore) { this.minScore = minScore; }
    public double getPassRate() { return passRate; }
    public void setPassRate(double passRate) { this.passRate = passRate; }
    public int[] getDistribution() { return distribution; }
    public void setDistribution(int[] distribution) { this.distribution = distribution; }
    public int getD0() { return distribution[0]; } // < 60
    public int getD1() { return distribution[1]; } // 60-69
    public int getD2() { return distribution[2]; } // 70-79
    public int getD3() { return distribution[3]; } // 80-89
    public int getD4() { return distribution[4]; } // >= 90
}