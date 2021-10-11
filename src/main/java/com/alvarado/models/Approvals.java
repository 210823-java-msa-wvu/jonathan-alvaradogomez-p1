package com.alvarado.models;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Approvals {

    //Instance variables

    private Integer approvalId;
    private Integer staffId;
    private String  staffMoreInfo;
    private String finalGrade;

    private Boolean superMoreInfo;
    private Boolean superDecision;
    private String superReasoning;

    private Boolean headMoreInfo;
    private Boolean headDecision;
    private String headReasoning;

    private Boolean bencoMoreInfo;
    private Boolean bencoDecision;
    private String bencoReasoning;
    // Constructors

    public Approvals() {
    }

    public Approvals(Integer approvalId,
                     Integer staffId, String staffMoreInfo, String finalGrade,
                     Boolean superMoreInfo, Boolean superDecision, String superReasoning,
                     Boolean headMoreInfo, Boolean headDecision, String headReasoning,
                     Boolean bencoMoreInfo, Boolean bencoDecision, String bencoReasoning) {
        this.approvalId = approvalId;
        this.staffId = staffId;
        this.staffMoreInfo = staffMoreInfo;
        this.finalGrade = finalGrade;
        this.superMoreInfo = superMoreInfo;
        this.superDecision = superDecision;
        this.superReasoning = superReasoning;
        this.headMoreInfo = headMoreInfo;
        this.headDecision = headDecision;
        this.headReasoning = headReasoning;
        this.bencoMoreInfo = bencoMoreInfo;
        this.bencoDecision = bencoDecision;
        this.bencoReasoning = bencoReasoning;
    }

    // Getters and Setters: a way for us to interact with private fields in a class

    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffMoreInfo() {
        return staffMoreInfo;
    }

    public void setStaffMoreInfo(String staffMoreInfo) {
        this.staffMoreInfo = staffMoreInfo;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Boolean getSuperMoreInfo() {
        return superMoreInfo;
    }

    public void setSuperMoreInfo(Boolean superMoreInfo) {
        this.superMoreInfo = superMoreInfo;
    }

    public Boolean getSuperDecision() {
        return superDecision;
    }

    public void setSuperDecision(Boolean superDecision) {
        this.superDecision = superDecision;
    }

    public String getSuperReasoning() {
        return superReasoning;
    }

    public void setSuperReasoning(String superReasoning) {
        this.superReasoning = superReasoning;
    }

    public Boolean getHeadMoreInfo() {
        return headMoreInfo;
    }

    public void setHeadMoreInfo(Boolean headMoreInfo) {
        this.headMoreInfo = headMoreInfo;
    }

    public Boolean getHeadDecision() {
        return headDecision;
    }

    public void setHeadDecision(Boolean headDecision) {
        this.headDecision = headDecision;
    }

    public String getHeadReasoning() {
        return headReasoning;
    }

    public void setHeadReasoning(String headReasoning) {
        this.headReasoning = headReasoning;
    }

    public Boolean getBencoMoreInfo() {
        return bencoMoreInfo;
    }

    public void setBencoMoreInfo(Boolean bencoMoreInfo) {
        this.bencoMoreInfo = bencoMoreInfo;
    }

    public Boolean getBencoDecision() {
        return bencoDecision;
    }

    public void setBencoDecision(Boolean bencoDecision) {
        this.bencoDecision = bencoDecision;
    }

    public String getBencoReasoning() {
        return bencoReasoning;
    }

    public void setBencoReasoning(String bencoReasoning) {
        this.bencoReasoning = bencoReasoning;
    }


    //Override the toString method here


    @Override
    public String toString() {
        return "Approvals{" +
                "approvalId=" + approvalId +
                ", staffId=" + staffId +
                ", staffMoreInfo='" + staffMoreInfo + '\'' +
                ", finalGrade='" + finalGrade + '\'' +
                ", superMoreInfo=" + superMoreInfo +
                ", superDecision=" + superDecision +
                ", superReasoning='" + superReasoning + '\'' +
                ", headMoreInfo=" + headMoreInfo +
                ", headDecision=" + headDecision +
                ", headReasoning='" + headReasoning + '\'' +
                ", bencoMoreInfo=" + bencoMoreInfo +
                ", bencoDecision=" + bencoDecision +
                ", bencoReasoning='" + bencoReasoning + '\'' +
                '}';
    }
}
