package com.revature.reimbursement.dao;

import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.models.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {

    //Create


    Reimbursement createReimbursement(double amount, String description, String reimbursementType, int employee);

    //Read
    List<Reimbursement> getAllReimbursement();

    List<Reimbursement> getAllPending();

    List<Reimbursement> getReimbursementByEmployee(int id);


    //Update

    //int updateReimbursement(Reimbursement reimbursement);

    Reimbursement updateReimbursementDeny(int id);

    Reimbursement reimbursementApproval(int id);

    Reimbursement getReimbursementById(int providedTicket);
}

