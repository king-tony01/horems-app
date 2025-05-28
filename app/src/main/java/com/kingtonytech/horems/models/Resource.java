package com.kingtonytech.horems.models;

public class Resource {
    private int id;
    private String resource_type;
    private int available;
    private int quantity;

    public int getId() { return id; }
    public String getResource_type() { return resource_type; }
    public boolean isAvailable() { return available == 1; }
    public int getQuantity() { return quantity; }

    public void setId(int id) { this.id = id; }
    public void setResource_type(String resource_type) { this.resource_type = resource_type; }
    public void setAvailable(int available) { this.available = available; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
