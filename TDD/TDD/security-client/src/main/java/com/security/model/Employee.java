package com.security.model;


public class Employee {

    String id;
    String name;
    
    public String getId() {
        return id;
    }

    public void setId(String empId) {
        this.id = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee [empId=" + id + ", empName=" + name + "]";
    }
    //String designation;
    //Integer salary;
}