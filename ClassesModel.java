package model;

import java.sql.*;
import java.util.*;
import entity.Classes;
import dbutil.Dbconn;

public class ClassesModel {
    public List<Classes> getAllClasses() {
        List<Classes> list = new ArrayList<>();
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT c.*, m.major_name FROM classes c LEFT JOIN major m ON c.major_id=m.id ORDER BY c.id");
            while (rs.next()) {
                Classes c = new Classes();
                c.setId(rs.getInt("id")); c.setClassNo(rs.getString("class_no"));
                c.setClassName(rs.getString("class_name")); c.setMajorId(rs.getInt("major_id"));
                c.setMajorName(rs.getString("major_name")); c.setGrade(rs.getString("grade"));
                list.add(c);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, stmt, conn); }
        return list;
    }
    
    public List<Classes> getClassesByMajorId(int majorId) {
        List<Classes> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("SELECT c.*, m.major_name FROM classes c LEFT JOIN major m ON c.major_id=m.id WHERE c.major_id=?");
            ps.setInt(1, majorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Classes c = new Classes();
                c.setId(rs.getInt("id")); c.setClassNo(rs.getString("class_no"));
                c.setClassName(rs.getString("class_name")); c.setMajorId(rs.getInt("major_id"));
                c.setMajorName(rs.getString("major_name")); c.setGrade(rs.getString("grade"));
                list.add(c);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, ps, conn); }
        return list;
    }
    
    public Classes getClassById(int id) {
        Classes c = null;
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("SELECT c.*, m.major_name FROM classes c LEFT JOIN major m ON c.major_id=m.id WHERE c.id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                c = new Classes();
                c.setId(rs.getInt("id")); c.setClassNo(rs.getString("class_no"));
                c.setClassName(rs.getString("class_name")); c.setMajorId(rs.getInt("major_id"));
                c.setMajorName(rs.getString("major_name")); c.setGrade(rs.getString("grade"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, ps, conn); }
        return c;
    }
    
    public boolean addClasses(Classes classes) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("INSERT INTO classes (class_no, class_name, major_id, grade) VALUES (?,?,?,?)");
            ps.setString(1, classes.getClassNo()); ps.setString(2, classes.getClassName());
            ps.setInt(3, classes.getMajorId()); ps.setString(4, classes.getGrade());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    public boolean updateClasses(Classes classes) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("UPDATE classes SET class_no=?, class_name=?, major_id=?, grade=? WHERE id=?");
            ps.setString(1, classes.getClassNo()); ps.setString(2, classes.getClassName());
            ps.setInt(3, classes.getMajorId()); ps.setString(4, classes.getGrade());
            ps.setInt(5, classes.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    public boolean deleteClasses(int id) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("DELETE FROM classes WHERE id=?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
}
