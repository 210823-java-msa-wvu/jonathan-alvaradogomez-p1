package com.alvarado.models;

import java.math.BigDecimal;

public class Balance {

    // Instance Variables

    private Integer balanceId;
    private Integer staffId;
    private BigDecimal available;
    private BigDecimal pending;
    private BigDecimal awarded;


    // Constructors

    public Balance() {
    }

    public Balance(Integer balanceId, Integer staffId,
                   BigDecimal available, BigDecimal pending, BigDecimal awarded) {
        this.balanceId = balanceId;
        this.staffId = staffId;
        this.available = available;
        this.pending = pending;
        this.awarded = awarded;
    }

    // Getters and Setters - a way to interact with private fields within a class

    public Integer getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Integer balanceId) {
        this.balanceId = balanceId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public BigDecimal getPending() {
        return pending;
    }

    public void setPending(BigDecimal pending) {
        this.pending = pending;
    }

    public BigDecimal getAwarded() {
        return awarded;
    }

    public void setAwarded(BigDecimal awarded) {
        this.awarded = awarded;
    }


    // Override toString Method
    @Override
    public String toString() {
        return "Balance{" +
                "balanceId=" + balanceId +
                ", staffId=" + staffId +
                ", available=" + available +
                ", pending=" + pending +
                ", awarded=" + awarded +
                '}';
    }
}
