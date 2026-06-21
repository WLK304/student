package entity;

import java.io.Serializable;

public class Major implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String majorNo;
    private String majorName;
    private String description;

    public Major() {}
    public Major(int id, String majorNo, String majorName, String description) {
        this.id = id; this.majorNo = majorNo; this.majorName = majorName; this.description = description;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMajorNo() { return majorNo; }
    public void setMajorNo(String majorNo) { this.majorNo = majorNo; }
    public String getMajorName() { return majorName; }
    public void setMajorName(String majorName) { this.majorName = majorName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
