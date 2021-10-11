package com.alvarado.models;

public class Departments {

    // Instance Variables

    private Integer departmentId;
    private Integer staffId;
    private String department;

    // Constructors

    public Departments() {
    }

    public Departments(Integer departmentId, Integer staffId, String department) {
        this.departmentId = departmentId;
        this.staffId = staffId;
        this.department = department;
    }

    // Getters and Setters: a way to interact with private fields within a class

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    //Override the toString method here

    @Override
    public String toString() {
        return "Departments{" +
                "departmentId=" + departmentId +
                ", staffId=" + staffId +
                ", department='" + department + '\'' +
                '}';
    }
}
