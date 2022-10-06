package com.revature.reimbursement.service;

import com.revature.reimbursement.dao.EmployeeDAO;
import com.revature.reimbursement.dao.EmployeeDoaImplPostgres;
import com.revature.reimbursement.models.Employee;

import java.util.List;
import java.util.Scanner;

public class EmployeeService {

EmployeeDAO ed = new EmployeeDoaImplPostgres();

Scanner sc = new Scanner(System.in);


    public Employee login() {

    //taking user info

    System.out.println("Please enter your username");
    String username = sc.nextLine();
    System.out.println("Please enter your password");
    String password = sc.nextLine();


    Employee user = ed.getByUsername(username);

    if (user.getPassword().equals(password)){
        System.out.println("You are now logged in!");
        System.out.println(user);
        return user;
    } else{
                System.out.println("Invalid login.");
                return null;
            }
    }

    public Employee register(){
        System.out.println("Please enter your First Name");
        String first = sc.nextLine();
        System.out.println("Please enter your Last Name");
        String last = sc.nextLine();
        System.out.println("Please enter your username");
        String username = sc.nextLine();
        System.out.println("Please enter your password");
        String password = sc.nextLine();
        System.out.println("Are you a manager?");
        System.out.println("Type true for yes and false for no");
        String admin = sc.nextLine();


        Employee employee = ed.createEmployee(first,last,username,password, Boolean.parseBoolean(admin));


        return employee;
    }

    public void getAllEmployees(){
        System.out.println("Using the database to return all of our employee objects");

        List<Employee> employeeList = ed.getAllEmployees();

        // Print each value of the list
        for(Employee user: employeeList){
            System.out.println(user);
        }
    }

}
