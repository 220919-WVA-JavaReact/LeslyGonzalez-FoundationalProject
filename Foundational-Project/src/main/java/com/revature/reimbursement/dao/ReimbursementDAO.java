package com.revature.reimbursement.dao;

import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.models.Reimbursement;

import java.sql.SQLException;
import java.util.List;

public interface ReimbursementDAO {

    //Create


    boolean createReimbursement(double amount, String description, Employee employee);

    //Read
    List<Reimbursement> getAllReimbursement();

    List<Reimbursement> getAllPending();

    List<Reimbursement> getReimbursementByEmployee(int id);


    //Update

    //int updateReimbursement(Reimbursement reimbursement);

    Reimbursement updateReimbursement(int id);

    Reimbursement reimbursementApproval(int id);
}

