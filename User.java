package entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private String role;
    private int roleId;
    private int status;

    public User() {}
    public User(int id, String username, String password, String role, int roleId, int status) {
        this.id = id; this.username = username; this.password = password;
        this.role = role; this.roleId = roleId; this.status = status;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}
