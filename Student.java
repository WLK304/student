package entity;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String stuNo;
    private String name;
    private String gender;
    private int age;
    private int majorId;
    private String majorName;
    private int classId;
    private String className;
    private String phone;
    private String email;

    public Student() {}
    public Student(int id, String stuNo, String name, String gender, int age, int majorId, int classId, String phone, String email) {
        this.id = id; this.stuNo = stuNo; this.name = name; this.gender = gender;
        this.age = age; this.majorId = majorId; this.classId = classId;
        this.phone = phone; this.email = email;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getStuNo() { return stuNo; }
    public void setStuNo(String stuNo) { this.stuNo = stuNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public int getMajorId() { return majorId; }
    public void setMajorId(int majorId) { this.majorId = majorId; }
    public String getMajorName() { return majorName; }
    public void setMajorName(String majorName) { this.majorName = majorName; }
    public int getClassId() { return classId; }
    public void setClassId(int classId) { this.classId = classId; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
