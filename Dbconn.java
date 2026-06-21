// src/main/java/dbutil/Dbconn.java
package dbutil;

import java.sql.*;

public class Dbconn {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/university?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "xsq!@123";
    
    static {
        try {
            Class.forName(DRIVER);
            System.out.println("MySQL驱动加载成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL驱动加载失败！");
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    public static void closeConnection(Connection conn) {
        if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
    }
    
    public static void closeStatement(Statement stmt) {
        if (stmt != null) { try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
    }
    
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
    }
    
    public static void closeAll(ResultSet rs, Statement stmt, Connection conn) {
        closeResultSet(rs);
        closeStatement(stmt);
        closeConnection(conn);
    }
}
