package model;

import java.sql.*;
import java.util.*;
import entity.User;
import dbutil.Dbconn;

public class UserModel {
    
    public User login(String username, String password) {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=? AND status=1");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setRoleId(rs.getInt("role_id"));
                user.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbconn.closeAll(rs, ps, conn);
        }
        return user;
    }
    
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users ORDER BY id");
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                u.setRoleId(rs.getInt("role_id"));
                u.setStatus(rs.getInt("status"));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbconn.closeAll(rs, stmt, conn);
        }
        return list;
    }
    
    public boolean addUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("INSERT INTO users (username, password, role, role_id, status) VALUES (?,?,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setInt(4, user.getRoleId());
            ps.setInt(5, user.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbconn.closeAll(null, ps, conn);
        }
        return false;
    }
    
    public boolean updateUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("UPDATE users SET password=?, role=?, status=? WHERE id=?");
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getRole());
            ps.setInt(3, user.getStatus());
            ps.setInt(4, user.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbconn.closeAll(null, ps, conn);
        }
        return false;
    }
    
    public boolean deleteUser(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("DELETE FROM users WHERE id=?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbconn.closeAll(null, ps, conn);
        }
        return false;
    }
    
    public User getUserById(int id) {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Dbconn.getConnection();
            ps = conn.prepareStatement("SELECT * FROM users WHERE id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setRoleId(rs.getInt("role_id"));
                user.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbconn.closeAll(rs, ps, conn);
        }
        return user;
    }
}
