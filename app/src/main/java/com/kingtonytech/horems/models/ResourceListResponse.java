package com.kingtonytech.horems.models;

import java.util.List;

public class ResourceListResponse {
    private boolean success;
    private String message;
    private List<Resource> data;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public List<Resource> getData() { return data; }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<Resource> data) {
        this.data = data;
    }
}
