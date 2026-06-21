package model;

import java.sql.*;
import java.util.*;
import entity.Subject;
import dbutil.Dbconn;

public class SubjectModel {
    public List<Subject> getAllSubjects() {
        List<Subject> list = new ArrayList<>();
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT s.*, t.name AS teacher_name FROM subject s LEFT JOIN teacher t ON s.teacher_id=t.id ORDER BY s.id");
            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getInt("id")); s.setSubjectNo(rs.getString("subject_no"));
                s.setSubjectName(rs.getString("subject_name")); s.setCredit(rs.getDouble("credit"));
                s.setTeacherId(rs.getInt("teacher_id")); s.setTeacherName(rs.getString("teacher_name"));
                s.setSemester(rs.getString("semester")); s.setDescription(rs.getString("description"));
                list.add(s);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, stmt, conn); }
        return list;
    }
    
    public List<Subject> getSubjectsByTeacherId(int teacherId) {
        List<Subject> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("SELECT s.*, t.name AS teacher_name FROM subject s LEFT JOIN teacher t ON s.teacher_id=t.id WHERE s.teacher_id=?");
            ps.setInt(1, teacherId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getInt("id")); s.setSubjectNo(rs.getString("subject_no"));
                s.setSubjectName(rs.getString("subject_name")); s.setCredit(rs.getDouble("credit"));
                s.setTeacherId(rs.getInt("teacher_id")); s.setTeacherName(rs.getString("teacher_name"));
                s.setSemester(rs.getString("semester")); s.setDescription(rs.getString("description"));
                list.add(s);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, ps, conn); }
        return list;
    }
    
    public List<Subject> getSubjectsByClassId(int classId) {
        List<Subject> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("SELECT s.*, t.name AS teacher_name FROM subject s LEFT JOIN teacher t ON s.teacher_id=t.id INNER JOIN class_subject cs ON s.id=cs.subject_id WHERE cs.class_id=?");
            ps.setInt(1, classId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getInt("id")); s.setSubjectNo(rs.getString("subject_no"));
                s.setSubjectName(rs.getString("subject_name")); s.setCredit(rs.getDouble("credit"));
                s.setTeacherId(rs.getInt("teacher_id")); s.setTeacherName(rs.getString("teacher_name"));
                s.setSemester(rs.getString("semester")); s.setDescription(rs.getString("description"));
                list.add(s);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, ps, conn); }
        return list;
    }
    
    public Subject getSubjectById(int id) {
        Subject s = null;
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("SELECT s.*, t.name AS teacher_name FROM subject s LEFT JOIN teacher t ON s.teacher_id=t.id WHERE s.id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                s = new Subject();
                s.setId(rs.getInt("id")); s.setSubjectNo(rs.getString("subject_no"));
                s.setSubjectName(rs.getString("subject_name")); s.setCredit(rs.getDouble("credit"));
                s.setTeacherId(rs.getInt("teacher_id")); s.setTeacherName(rs.getString("teacher_name"));
                s.setSemester(rs.getString("semester")); s.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, ps, conn); }
        return s;
    }
    
    public boolean addSubject(Subject subject) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("INSERT INTO subject (subject_no, subject_name, credit, teacher_id, semester, description) VALUES (?,?,?,?,?,?)");
            ps.setString(1, subject.getSubjectNo()); ps.setString(2, subject.getSubjectName());
            ps.setDouble(3, subject.getCredit()); ps.setInt(4, subject.getTeacherId());
            ps.setString(5, subject.getSemester()); ps.setString(6, subject.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    public boolean updateSubject(Subject subject) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("UPDATE subject SET subject_no=?, subject_name=?, credit=?, teacher_id=?, semester=?, description=? WHERE id=?");
            ps.setString(1, subject.getSubjectNo()); ps.setString(2, subject.getSubjectName());
            ps.setDouble(3, subject.getCredit()); ps.setInt(4, subject.getTeacherId());
            ps.setString(5, subject.getSemester()); ps.setString(6, subject.getDescription());
            ps.setInt(7, subject.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    public boolean deleteSubject(int id) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("DELETE FROM subject WHERE id=?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    // 排课
    public boolean assignClassSubject(int classId, int subjectId) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("INSERT IGNORE INTO class_subject (class_id, subject_id) VALUES (?,?)");
            ps.setInt(1, classId); ps.setInt(2, subjectId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    public boolean removeClassSubject(int classId, int subjectId) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("DELETE FROM class_subject WHERE class_id=? AND subject_id=?");
            ps.setInt(1, classId); ps.setInt(2, subjectId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
}
