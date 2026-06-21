package model;

import java.sql.*;
import java.util.*;
import entity.Teacher;
import dbutil.Dbconn;

public class TeacherModel {
    public List<Teacher> getAllTeachers() {
        List<Teacher> list = new ArrayList<>();
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM teacher ORDER BY id");
            while (rs.next()) {
                Teacher t = new Teacher();
                t.setId(rs.getInt("id")); t.setTeacherNo(rs.getString("teacher_no"));
                t.setName(rs.getString("name")); t.setGender(rs.getString("gender"));
                t.setTitle(rs.getString("title")); t.setPhone(rs.getString("phone"));
                t.setEmail(rs.getString("email"));
                list.add(t);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, stmt, conn); }
        return list;
    }
    
    public Teacher getTeacherById(int id) {
        Teacher t = null;
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("SELECT * FROM teacher WHERE id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                t = new Teacher();
                t.setId(rs.getInt("id")); t.setTeacherNo(rs.getString("teacher_no"));
                t.setName(rs.getString("name")); t.setGender(rs.getString("gender"));
                t.setTitle(rs.getString("title")); t.setPhone(rs.getString("phone"));
                t.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, ps, conn); }
        return t;
    }
    
    public boolean addTeacher(Teacher teacher) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("INSERT INTO teacher (teacher_no, name, gender, title, phone, email) VALUES (?,?,?,?,?,?)");
            ps.setString(1, teacher.getTeacherNo()); ps.setString(2, teacher.getName());
            ps.setString(3, teacher.getGender()); ps.setString(4, teacher.getTitle());
            ps.setString(5, teacher.getPhone()); ps.setString(6, teacher.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    public boolean updateTeacher(Teacher teacher) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("UPDATE teacher SET teacher_no=?, name=?, gender=?, title=?, phone=?, email=? WHERE id=?");
            ps.setString(1, teacher.getTeacherNo()); ps.setString(2, teacher.getName());
            ps.setString(3, teacher.getGender()); ps.setString(4, teacher.getTitle());
            ps.setString(5, teacher.getPhone()); ps.setString(6, teacher.getEmail());
            ps.setInt(7, teacher.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    public boolean deleteTeacher(int id) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("DELETE FROM teacher WHERE id=?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
}
