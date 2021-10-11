package com.alvarado.models;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.security.PublicKey;

public class Staff {

    // Instance Variables

    // Wrapper Class: making a primitive object-like so that we can work with it (int -->Integer)
    private Integer staffId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Boolean isEmployee;
    private Boolean isSuper;
    private Boolean isHead;
    private Boolean isBenco;


    // Constructors (Java can do this for us!)

    public Staff() {

    }

    public Staff(Integer staffId, String firstName, String lastName, String username, String password,
                 Boolean isEmployee, Boolean isSuper, Boolean isHead, Boolean isBenco) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isEmployee = isEmployee;
        this.isSuper = isSuper;
        this.isHead = isHead;
        this.isBenco = isBenco;
    }

    // Getters and Setters - methods to interact with private fields in a class

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEmployee() {
        return isEmployee;
    }

    public void setEmployee(Boolean employee) {
        isEmployee = employee;
    }

    public Boolean getSuper() {
        return isSuper;
    }

    public void setSuper(Boolean aSuper) {
        isSuper = aSuper;
    }

    public Boolean getHead() {
        return isHead;
    }

    public void setHead(Boolean head) {
        isHead = head;
    }

    public Boolean getBenco() {
        return isBenco;
    }

    public void setBenco(Boolean benco) {
        isBenco = benco;
    }
    // Override the toString method


    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isEmployee=" + isEmployee +
                ", isSuper=" + isSuper +
                ", isHead=" + isHead +
                ", isBenco=" + isBenco +
                '}';
    }
}
