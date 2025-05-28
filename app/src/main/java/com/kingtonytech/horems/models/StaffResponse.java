package com.kingtonytech.horems.models;

import java.util.List;

public class StaffResponse {
    private boolean success;
    private String message;
    private Staff data;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Staff getData() { return data; }

    public void setSuccess(boolean success) { this.success = success; }
    public void setMessage(String message) { this.message = message; }
    public void setData(Staff data) { this.data = data; }
}