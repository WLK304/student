package entity;

import java.io.Serializable;

public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String teacherNo;
    private String name;
    private String gender;
    private String title;
    private String phone;
    private String email;

    public Teacher() {}
    public Teacher(int id, String teacherNo, String name, String gender, String title, String phone, String email) {
        this.id = id; this.teacherNo = teacherNo; this.name = name; this.gender = gender;
        this.title = title; this.phone = phone; this.email = email;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTeacherNo() { return teacherNo; }
    public void setTeacherNo(String teacherNo) { this.teacherNo = teacherNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
