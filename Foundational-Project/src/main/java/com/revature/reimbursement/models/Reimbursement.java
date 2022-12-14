package com.revature.reimbursement.models;
import java.util.Objects;

public class Reimbursement {

    int reimbursementId;
    double amount;
    String description;

    String reimbursementType;
    boolean approvalStatus;
    boolean completed;
    String date;
    int employeeId;

    public Reimbursement(int reimbursementId, double amount, String description, String reimbursementType , boolean approvalStatus, boolean completed, String date, int employeeId) {
        this.reimbursementId = reimbursementId;
        this.amount = amount;
        this.description = description;
        this.reimbursementType = reimbursementType;
        this.approvalStatus = approvalStatus;
        this.completed = completed;
        this.date = date;
        this.employeeId = employeeId;
    }

    public Reimbursement(double amount, String description, String reimbursementType, int employeeId) {
        this.amount = amount;
        this.description = description;
        this.reimbursementType = reimbursementType;
        this.employeeId = employeeId;
    }


    public Reimbursement(double amount, String description, int employeeId) {
        this.amount = amount;
        this.description = description;
        this.employeeId = employeeId;

    }

    public Reimbursement(){
    }

    public Reimbursement(String reimbursementId, int employeeId) {
        this.reimbursementId = Integer.parseInt(reimbursementId);
        this.employeeId = employeeId;
    }


    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDate() {
        return date;
    }

    public String getReimbursementType() {
        return reimbursementType;
    }

    public void setReimbursementType(String reimbursementType) {
        this.reimbursementType = reimbursementType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", approvalStatus=" + approvalStatus +
                ", completed=" + completed +
                ", date=" + date +
                ", employeeId=" + employeeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return reimbursementId == that.reimbursementId && Double.compare(that.amount, amount) == 0 && approvalStatus == that.approvalStatus && completed == that.completed && employeeId == that.employeeId && description.equals(that.description) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbursementId, amount, description, approvalStatus, completed, date, employeeId);
    }
}


