package com.revature.reimbursement.service;

import com.revature.reimbursement.dao.ReimbursementDAO;
import com.revature.reimbursement.dao.ReimbursementDAOImpl;
import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.models.Reimbursement;

import java.util.List;
import java.util.Scanner;

public class ReimbursementService {

    Scanner sc = new Scanner(System.in);

    ReimbursementDAO rd = new ReimbursementDAOImpl();

    public void createReimbursement(Employee employee){

        System.out.println("Enter amount for reimbursement");
        double amount = Double.parseDouble(sc.nextLine());

        System.out.println("Enter reimbursement description");
        String description = sc.nextLine();

        boolean successful = rd.createReimbursement(amount, description, employee);

        if(successful){
            System.out.println("You created a reimbursement ticket!");
        }else{
            System.out.println("Something went wrong can not create ticket!");
        }
    }

    public void getAllReimbursement(){
        System.out.println("List of created Tickets: ");

        List<Reimbursement> reimbursementList = rd.getAllReimbursement();

        for(Reimbursement ticket: reimbursementList){
            System.out.println(ticket);
        }
    }

    public void getReimbursementByEmployee(Employee employee){
        System.out.println("tickets pending");

        List<Reimbursement> reimbursements = rd.getReimbursementByEmployee(employee.getEmployeeId());


        for(Reimbursement ticket: reimbursements){
            System.out.println(ticket);
        }
    }

    //need to refactor and store in user information somewhere

//    public void assignReimbursement(int employeeId){
//
//        System.out.println("Enter Reimbursement Number");
//        String reimbursementId = sc.nextLine();
//
//        Reimbursement makeTicket = new Reimbursement(reimbursementId, employeeId);
//
//        rd.updateReimbursement(makeTicket);
//    }

}
