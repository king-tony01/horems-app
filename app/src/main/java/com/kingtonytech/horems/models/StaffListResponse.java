package com.kingtonytech.horems.models;

import java.util.List;

public class StaffListResponse {
    private boolean success;
    private String message;
    private List<Staff> data;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public List<Staff> getData() { return data; }

    public void setSuccess(boolean success) { this.success = success; }
    public void setMessage(String message) { this.message = message; }
    public void setData(List<Staff> data) { this.data = data; }
}