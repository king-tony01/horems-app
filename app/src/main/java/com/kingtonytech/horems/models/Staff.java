package com.kingtonytech.horems.models;

public class Staff {
    private int id;
    private String staff_name;
    private String staff_role;
    private String shift_time;
    private String created_at;

    public int getId() { return id; }
    public String getStaff_name() { return staff_name; }
    public String getStaff_role() { return staff_role; }
    public String getShift_time() { return shift_time; }

    public void setId(int id) { this.id = id; }
    public void setStaff_name(String staff_name) { this.staff_name = staff_name; }
    public void setStaff_role(String staff_role) { this.staff_role = staff_role; }
    public void setShift_time(String shift_time) { this.shift_time = shift_time; }
    public String getCreated_at() { return created_at; }
    public void setCreated_at(String created_at) { this.created_at = created_at; }
}
