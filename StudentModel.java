package model;

import java.sql.*;
import java.util.*;
import entity.Student;
import dbutil.Dbconn;

public class StudentModel {
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT s.*, m.major_name, c.class_name FROM student s LEFT JOIN major m ON s.major_id=m.id LEFT JOIN classes c ON s.class_id=c.id ORDER BY s.id");
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id")); s.setStuNo(rs.getString("stu_no")); s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender")); s.setAge(rs.getInt("age"));
                s.setMajorId(rs.getInt("major_id")); s.setMajorName(rs.getString("major_name"));
                s.setClassId(rs.getInt("class_id")); s.setClassName(rs.getString("class_name"));
                s.setPhone(rs.getString("phone")); s.setEmail(rs.getString("email"));
                list.add(s);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, stmt, conn); }
        return list;
    }
    
    public List<Student> getStudentsByClassId(int classId) {
        List<Student> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("SELECT s.*, m.major_name, c.class_name FROM student s LEFT JOIN major m ON s.major_id=m.id LEFT JOIN classes c ON s.class_id=c.id WHERE s.class_id=? ORDER BY s.id");
            ps.setInt(1, classId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id")); s.setStuNo(rs.getString("stu_no")); s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender")); s.setAge(rs.getInt("age"));
                s.setMajorId(rs.getInt("major_id")); s.setMajorName(rs.getString("major_name"));
                s.setClassId(rs.getInt("class_id")); s.setClassName(rs.getString("class_name"));
                s.setPhone(rs.getString("phone")); s.setEmail(rs.getString("email"));
                list.add(s);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, ps, conn); }
        return list;
    }
    
    public Student getStudentById(int id) {
        Student s = null;
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("SELECT s.*, m.major_name, c.class_name FROM student s LEFT JOIN major m ON s.major_id=m.id LEFT JOIN classes c ON s.class_id=c.id WHERE s.id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                s = new Student();
                s.setId(rs.getInt("id")); s.setStuNo(rs.getString("stu_no")); s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender")); s.setAge(rs.getInt("age"));
                s.setMajorId(rs.getInt("major_id")); s.setMajorName(rs.getString("major_name"));
                s.setClassId(rs.getInt("class_id")); s.setClassName(rs.getString("class_name"));
                s.setPhone(rs.getString("phone")); s.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, ps, conn); }
        return s;
    }
    
    public boolean addStudent(Student student) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("INSERT INTO student (stu_no, name, gender, age, major_id, class_id, phone, email) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, student.getStuNo()); ps.setString(2, student.getName());
            ps.setString(3, student.getGender()); ps.setInt(4, student.getAge());
            ps.setInt(5, student.getMajorId()); ps.setInt(6, student.getClassId());
            ps.setString(7, student.getPhone()); ps.setString(8, student.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    public boolean updateStudent(Student student) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("UPDATE student SET stu_no=?, name=?, gender=?, age=?, major_id=?, class_id=?, phone=?, email=? WHERE id=?");
            ps.setString(1, student.getStuNo()); ps.setString(2, student.getName());
            ps.setString(3, student.getGender()); ps.setInt(4, student.getAge());
            ps.setInt(5, student.getMajorId()); ps.setInt(6, student.getClassId());
            ps.setString(7, student.getPhone()); ps.setString(8, student.getEmail());
            ps.setInt(9, student.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    public boolean deleteStudent(int id) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("DELETE FROM student WHERE id=?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
}
