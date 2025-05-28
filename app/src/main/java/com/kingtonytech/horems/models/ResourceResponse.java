package com.kingtonytech.horems.models;

import java.util.List;

public class ResourceResponse {
    private boolean success;
    private String message;
    private Resource data;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Resource getData() { return data; }

    public void setSuccess(boolean success) { this.success = success; }
    public void setMessage(String message) { this.message = message; }
    public void setData(Resource data) { this.data = data; }
}