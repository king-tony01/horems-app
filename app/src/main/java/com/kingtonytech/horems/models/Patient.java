package com.kingtonytech.horems.models;

public class Patient {
    private int id;
    private String patient_name;
    private int age;
    private String patient_condition;
    private String created_at;

    // Getters and Setters
    public int getId() { return id; }
    public String getPatient_name() { return patient_name; }
    public int getAge() { return age; }
    public String getPatient_condition() { return patient_condition; }
    public String getCreated_at() { return created_at; }

    public void setId(int id) { this.id = id; }
    public void setPatient_name(String patient_name) { this.patient_name = patient_name; }
    public void setAge(int age) { this.age = age; }
    public void setPatient_condition(String patient_condition) { this.patient_condition = patient_condition; }
    public void setCreated_at(String created_at) { this.created_at = created_at; }
}
