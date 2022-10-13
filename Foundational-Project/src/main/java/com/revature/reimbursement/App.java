package com.revature.reimbursement;

import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.service.EmployeeService;
import com.revature.reimbursement.service.ReimbursementService;

import java.util.Scanner;

public class App {

    public static EmployeeService es = new EmployeeService();

    public static ReimbursementService rs = new ReimbursementService();

    public static void main(String[] args) {

        System.out.println("Press 1 to Login. Press 2 to Register");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();


        Employee loggedInEmployee = null;

        if(input.equals("1")){

            loggedInEmployee = es.login();

        }else if(input.equals("2")){

            loggedInEmployee = es.register();
        } /*else if (input.equals("3")) {
            es.getAllEmployees();
        }*/ else{
            System.out.println("invalid input");
        }

        while(loggedInEmployee != null) {
            String ticketChoice = null;
            if (loggedInEmployee.getAdmin()) {

                System.out.println("Press 1 to update a ticket, 2 view all tickets pending, 3 view all tickets ");

                ticketChoice = sc.nextLine();

                if (ticketChoice.equals("1")) {
                    System.out.println("updated ticket");
                    rs.updateReimbursement();
                } else if(ticketChoice.equals("2")){
                    rs.getAllPending();
                }else if (ticketChoice.equals("3")) {
                    rs.getAllReimbursement();
                } else {
                    System.out.println("invalid input");
                }
            } else if (!loggedInEmployee.getAdmin()) {

                System.out.println("Press 1 to create a ticket, 2 view all your tickets ");
                ticketChoice = sc.nextLine();

                if (ticketChoice.equals("1")) {
                    rs.createReimbursement(loggedInEmployee);
                } else if (ticketChoice.equals("2")){
                    rs.getReimbursementByEmployee(loggedInEmployee);
                } else {
                    System.out.println("invalid input");
                }
            }
        }
    }
}


