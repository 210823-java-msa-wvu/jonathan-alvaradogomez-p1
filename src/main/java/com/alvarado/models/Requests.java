package com.alvarado.models;

import java.math.BigDecimal;

public class Requests {

    //Instance Variables

    private Integer requestId;
    private Integer staffId;
    private String todayDate;
    private String eventDate;
    private String time;
    private String location;
    private String description;
    private BigDecimal cost;
    private String gradingFormat;
    private String finalGrade;
    private String eventType;
    private String justification;
    private Boolean bencoChangedCost;


    //Constructors

    public Requests() {
    }

    public Requests(Integer requestId, Integer staffId, String todayDate, String eventDate, String time, String location,
                    String description, BigDecimal cost, String gradingFormat, String finalGrade,
                    String eventType, String justification, Boolean bencoChangedCost) {

        this.requestId = requestId;
        this.staffId = staffId;
        this.todayDate = todayDate;
        this.eventDate = eventDate;
        this.time = time;
        this.location = location;
        this.description = description;
        this.cost = cost;
        this.gradingFormat = gradingFormat;
        this.finalGrade = finalGrade;
        this.eventType = eventType;
        this.justification = justification;
        this.bencoChangedCost = bencoChangedCost;
    }

    //Getters and Setters - to interact with private fields in a class

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getGradingFormat() {
        return gradingFormat;
    }

    public void setGradingFormat(String gradingFormat) {
        this.gradingFormat = gradingFormat;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Boolean getBencoChangedCost() {
        return bencoChangedCost;
    }

    public void setBencoChangedCost(Boolean bencoChangedCost) {
        this.bencoChangedCost = bencoChangedCost;
    }

    // Override the toString method here


    @Override
    public String toString() {
        return "Requests{" +
                "requestId=" + requestId +
                ", staffId=" + staffId +
                ", todayDate='" + todayDate + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", gradingFormat='" + gradingFormat + '\'' +
                ", finalGrade='" + finalGrade + '\'' +
                ", eventType='" + eventType + '\'' +
                ", justification='" + justification + '\'' +
                ", bencoChangedCost=" + bencoChangedCost +
                '}';
    }
}
