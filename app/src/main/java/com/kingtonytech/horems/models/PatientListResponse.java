package com.kingtonytech.horems.models;

import java.util.List;

public class PatientListResponse {
    private boolean success;
    private String message;
    private List<Patient> data;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public List<Patient> getData() { return data; }

    public void setSuccess(boolean success) { this.success = success; }
    public void setMessage(String message) { this.message = message; }
    public void setData(List<Patient> data) { this.data = data; }
}
