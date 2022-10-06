package com.revature.reimbursement.models;
import java.util.Objects;

public class Reimbursement {

    int reimbursementId;
    double amount;
    String description;
    boolean approvalStatus;
    boolean completed;
    int date;
    int employeeId;

    public Reimbursement(int reimbursementId, double amount, String description, boolean approvalStatus, boolean completed, int date, int employeeId) {
        this.reimbursementId = reimbursementId;
        this.amount = amount;
        this.description = description;
        this.approvalStatus = approvalStatus;
        this.completed = completed;
        this.date = date;
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getEmployeeIdId() {
        return employeeId;
    }

    public void setEmployeeIdId(int employeeId) {
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
        return reimbursementId == that.reimbursementId && Double.compare(that.amount, amount) == 0 && approvalStatus == that.approvalStatus && completed == that.completed && date == that.date && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbursementId, amount, description, approvalStatus, completed, date);
    }
}
