package model;

import java.sql.*;
import java.util.*;
import entity.Major;
import dbutil.Dbconn;

public class MajorModel {
    public List<Major> getAllMajors() {
        List<Major> list = new ArrayList<>();
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM major ORDER BY id");
            while (rs.next()) {
                Major m = new Major();
                m.setId(rs.getInt("id")); m.setMajorNo(rs.getString("major_no"));
                m.setMajorName(rs.getString("major_name")); m.setDescription(rs.getString("description"));
                list.add(m);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, stmt, conn); }
        return list;
    }
    
    public Major getMajorById(int id) {
        Major m = null;
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("SELECT * FROM major WHERE id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                m = new Major();
                m.setId(rs.getInt("id")); m.setMajorNo(rs.getString("major_no"));
                m.setMajorName(rs.getString("major_name")); m.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, ps, conn); }
        return m;
    }
    
    public boolean addMajor(Major major) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("INSERT INTO major (major_no, major_name, description) VALUES (?,?,?)");
            ps.setString(1, major.getMajorNo()); ps.setString(2, major.getMajorName());
            ps.setString(3, major.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    public boolean updateMajor(Major major) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("UPDATE major SET major_no=?, major_name=?, description=? WHERE id=?");
            ps.setString(1, major.getMajorNo()); ps.setString(2, major.getMajorName());
            ps.setString(3, major.getDescription()); ps.setInt(4, major.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    public boolean deleteMajor(int id) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("DELETE FROM major WHERE id=?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
}
