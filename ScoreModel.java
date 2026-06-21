package model;

import java.sql.*;
import java.util.*;
import entity.Score;
import entity.SubjectStats;
import dbutil.Dbconn;

public class ScoreModel {
    public List<Score> getAllScores() {
        List<Score> list = new ArrayList<>();
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT sc.*, st.stu_no, st.name AS student_name, su.subject_no, su.subject_name, su.credit, c.class_name FROM score sc LEFT JOIN student st ON sc.student_id=st.id LEFT JOIN subject su ON sc.subject_id=su.id LEFT JOIN classes c ON st.class_id=c.id ORDER BY sc.id");
            while (rs.next()) {
                Score sc = new Score();
                sc.setId(rs.getInt("id")); sc.setStudentId(rs.getInt("student_id"));
                sc.setStuNo(rs.getString("stu_no")); sc.setStudentName(rs.getString("student_name"));
                sc.setSubjectId(rs.getInt("subject_id")); sc.setSubjectNo(rs.getString("subject_no"));
                sc.setSubjectName(rs.getString("subject_name")); sc.setCredit(rs.getDouble("credit"));
                sc.setScore(rs.getDouble("score")); sc.setSemester(rs.getString("semester"));
                sc.setClassName(rs.getString("class_name"));
                list.add(sc);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, stmt, conn); }
        return list;
    }
    
    public List<Score> getScoresByStudentId(int studentId) {
        List<Score> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("SELECT sc.*, st.stu_no, st.name AS student_name, su.subject_no, su.subject_name, su.credit, c.class_name FROM score sc LEFT JOIN student st ON sc.student_id=st.id LEFT JOIN subject su ON sc.subject_id=su.id LEFT JOIN classes c ON st.class_id=c.id WHERE sc.student_id=? ORDER BY sc.id");
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Score sc = new Score();
                sc.setId(rs.getInt("id")); sc.setStudentId(rs.getInt("student_id"));
                sc.setStuNo(rs.getString("stu_no")); sc.setStudentName(rs.getString("student_name"));
                sc.setSubjectId(rs.getInt("subject_id")); sc.setSubjectNo(rs.getString("subject_no"));
                sc.setSubjectName(rs.getString("subject_name")); sc.setCredit(rs.getDouble("credit"));
                sc.setScore(rs.getDouble("score")); sc.setSemester(rs.getString("semester"));
                sc.setClassName(rs.getString("class_name"));
                list.add(sc);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, ps, conn); }
        return list;
    }
    
    public List<Score> getScoresBySubjectId(int subjectId) {
        List<Score> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("SELECT sc.*, st.stu_no, st.name AS student_name, su.subject_no, su.subject_name, su.credit, c.class_name FROM score sc LEFT JOIN student st ON sc.student_id=st.id LEFT JOIN subject su ON sc.subject_id=su.id LEFT JOIN classes c ON st.class_id=c.id WHERE sc.subject_id=? ORDER BY sc.id");
            ps.setInt(1, subjectId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Score sc = new Score();
                sc.setId(rs.getInt("id")); sc.setStudentId(rs.getInt("student_id"));
                sc.setStuNo(rs.getString("stu_no")); sc.setStudentName(rs.getString("student_name"));
                sc.setSubjectId(rs.getInt("subject_id")); sc.setSubjectNo(rs.getString("subject_no"));
                sc.setSubjectName(rs.getString("subject_name")); sc.setCredit(rs.getDouble("credit"));
                sc.setScore(rs.getDouble("score")); sc.setSemester(rs.getString("semester"));
                sc.setClassName(rs.getString("class_name"));
                list.add(sc);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, ps, conn); }
        return list;
    }
    
    public boolean addScore(Score score) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("INSERT INTO score (student_id, subject_id, score, semester) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE score=?, semester=?");
            ps.setInt(1, score.getStudentId()); ps.setInt(2, score.getSubjectId());
            ps.setDouble(3, score.getScore()); ps.setString(4, score.getSemester());
            ps.setDouble(5, score.getScore()); ps.setString(6, score.getSemester());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    public boolean updateScore(Score score) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("UPDATE score SET score=?, semester=? WHERE id=?");
            ps.setDouble(1, score.getScore()); ps.setString(2, score.getSemester());
            ps.setInt(3, score.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    public boolean deleteScore(int id) {
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("DELETE FROM score WHERE id=?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(null, ps, conn); }
        return false;
    }
    
    // ========== NEW: Statistics ==========
    public List<SubjectStats> getAllSubjectStats() {
        List<SubjectStats> list = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        String sql = "SELECT su.id, su.subject_no, su.subject_name, su.semester, t.name AS teacher_name, " +
            "COUNT(sc.id) AS cnt, ROUND(AVG(sc.score),1) AS avg_score, " +
            "ROUND(MAX(sc.score),1) AS max_score, ROUND(MIN(sc.score),1) AS min_score, " +
            "ROUND(SUM(CASE WHEN sc.score >= 60 THEN 1 ELSE 0 END) * 100.0 / COUNT(sc.id), 1) AS pass_rate, " +
            "SUM(CASE WHEN sc.score < 60 THEN 1 ELSE 0 END) AS d0, " +
            "SUM(CASE WHEN sc.score >= 60 AND sc.score < 70 THEN 1 ELSE 0 END) AS d1, " +
            "SUM(CASE WHEN sc.score >= 70 AND sc.score < 80 THEN 1 ELSE 0 END) AS d2, " +
            "SUM(CASE WHEN sc.score >= 80 AND sc.score < 90 THEN 1 ELSE 0 END) AS d3, " +
            "SUM(CASE WHEN sc.score >= 90 THEN 1 ELSE 0 END) AS d4 " +
            "FROM subject su LEFT JOIN score sc ON su.id = sc.subject_id " +
            "LEFT JOIN teacher t ON su.teacher_id = t.id " +
            "GROUP BY su.id, su.subject_no, su.subject_name, su.semester, t.name " +
            "ORDER BY su.id";
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SubjectStats ss = new SubjectStats();
                ss.setSubjectId(rs.getInt("id"));
                ss.setSubjectNo(rs.getString("subject_no"));
                ss.setSubjectName(rs.getString("subject_name"));
                ss.setTeacherName(rs.getString("teacher_name"));
                ss.setSemester(rs.getString("semester"));
                ss.setStudentCount(rs.getInt("cnt"));
                ss.setAvgScore(rs.getDouble("avg_score"));
                ss.setMaxScore(rs.getDouble("max_score"));
                ss.setMinScore(rs.getDouble("min_score"));
                ss.setPassRate(rs.getDouble("pass_rate"));
                ss.setDistribution(new int[]{
                    rs.getInt("d0"), rs.getInt("d1"), rs.getInt("d2"),
                    rs.getInt("d3"), rs.getInt("d4")
                });
                list.add(ss);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, ps, conn); }
        return list;
    }

    public SubjectStats getSubjectStats(int subjectId) {
        SubjectStats ss = null;
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        String sql = "SELECT su.id, su.subject_no, su.subject_name, su.semester, t.name AS teacher_name, " +
            "COUNT(sc.id) AS cnt, ROUND(AVG(sc.score),1) AS avg_score, " +
            "ROUND(MAX(sc.score),1) AS max_score, ROUND(MIN(sc.score),1) AS min_score, " +
            "ROUND(SUM(CASE WHEN sc.score >= 60 THEN 1 ELSE 0 END) * 100.0 / COUNT(sc.id), 1) AS pass_rate, " +
            "SUM(CASE WHEN sc.score < 60 THEN 1 ELSE 0 END) AS d0, " +
            "SUM(CASE WHEN sc.score >= 60 AND sc.score < 70 THEN 1 ELSE 0 END) AS d1, " +
            "SUM(CASE WHEN sc.score >= 70 AND sc.score < 80 THEN 1 ELSE 0 END) AS d2, " +
            "SUM(CASE WHEN sc.score >= 80 AND sc.score < 90 THEN 1 ELSE 0 END) AS d3, " +
            "SUM(CASE WHEN sc.score >= 90 THEN 1 ELSE 0 END) AS d4 " +
            "FROM subject su LEFT JOIN score sc ON su.id = sc.subject_id " +
            "LEFT JOIN teacher t ON su.teacher_id = t.id " +
            "WHERE su.id = ? " +
            "GROUP BY su.id, su.subject_no, su.subject_name, su.semester, t.name";
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, subjectId);
            rs = ps.executeQuery();
            if (rs.next()) {
                ss = new SubjectStats();
                ss.setSubjectId(rs.getInt("id"));
                ss.setSubjectNo(rs.getString("subject_no"));
                ss.setSubjectName(rs.getString("subject_name"));
                ss.setTeacherName(rs.getString("teacher_name"));
                ss.setSemester(rs.getString("semester"));
                ss.setStudentCount(rs.getInt("cnt"));
                ss.setAvgScore(rs.getDouble("avg_score"));
                ss.setMaxScore(rs.getDouble("max_score"));
                ss.setMinScore(rs.getDouble("min_score"));
                ss.setPassRate(rs.getDouble("pass_rate"));
                ss.setDistribution(new int[]{
                    rs.getInt("d0"), rs.getInt("d1"), rs.getInt("d2"),
                    rs.getInt("d3"), rs.getInt("d4")
                });
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { Dbconn.closeAll(rs, ps, conn); }
        return ss;
    }
}