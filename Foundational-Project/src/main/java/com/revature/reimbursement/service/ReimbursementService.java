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

//    public void createReimbursement(Employee employee){
//
//        System.out.println("Enter amount for reimbursement");
//        double amount = Double.parseDouble(sc.nextLine());
//
//        System.out.println("Enter reimbursement description");
//        String description = sc.nextLine();
//
//        boolean successful = rd.createReimbursement(amount, description, reimbursementType, employee);
//
//        if(successful){
//            System.out.println("You created a reimbursement ticket!");
//        }else{
//            System.out.println("Something went wrong can not create ticket!");
//        }
//    }

    public Reimbursement createReimbursement(double amount, String description, String reimbursementType, int employee){
        Reimbursement ticket = rd.createReimbursement(amount, description, reimbursementType, employee);
        return ticket;
    }
    public void getAllReimbursement(){
        System.out.println("List of created Tickets: ");

        List<Reimbursement> reimbursementList = rd.getAllReimbursement();

        for(Reimbursement ticket: reimbursementList){
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------+");
            System.out.println(" Ticket ID: " + ticket.getReimbursementId() + " | Approval Status: PENDING!" + " | Price: $" + ticket.getAmount() + " | Description: " +  ticket.getDescription() + " | Employee ID: " + ticket.getEmployeeId());
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------+");
        }
    }


    public void getAllPending(){
        System.out.println("List of created Tickets: ");

        List<Reimbursement> pendingList = rd.getAllPending();

        for(Reimbursement ticket: pendingList){
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------+");
            System.out.println(" Ticket ID: " + ticket.getReimbursementId() + " | Approval Status: PENDING!" + " | Price: $" + ticket.getAmount() + " | Description: " +  ticket.getDescription() + " | Employee ID: " + ticket.getEmployeeId());
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------+");
        }
    }

    public void getReimbursementByEmployee(Employee employee){

        List<Reimbursement> reimbursements = rd.getReimbursementByEmployee(employee.getEmployeeId());


        for(Reimbursement ticket: reimbursements){
            System.out.println("+---------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println(" Ticket ID: " + ticket.getReimbursementId() + " | Price: $" + ticket.getAmount() + " | Description: " + ticket.getDescription());
            System.out.println("+---------------------------------------------------------------------------------------------------------------------------------+");
        }
    }

    public Reimbursement getReimbursementByEmployee(int employee_id){
        return (Reimbursement) rd.getReimbursementByEmployee(employee_id);
    }



    public Reimbursement updateReimbursementDeny(int employee_id){

        return rd.updateReimbursementDeny(employee_id);

//        System.out.println("Enter Reimbursement Number you'll like to approve");
//        int reimbursementId = sc.nextInt();


//        if( reimbursement.equals(true)){
//            System.out.println("You updated a reimbursement ticket!");
//        }else{
//            System.out.println("Something went wrong can not update ticket!");
//        }
    }


    public Reimbursement reimbursementApproval(int id){
        return rd.reimbursementApproval(id);
    }

    public Reimbursement getReimbursementById(int providedTicket) {
        return rd.getReimbursementById(providedTicket);
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


