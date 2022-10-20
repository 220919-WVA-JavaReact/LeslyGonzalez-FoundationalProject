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

        if (user.getPassword().equals(password)) {
            System.out.println("You are now logged in!");
            System.out.println("Employee ID: " + user.getEmployeeId() + " | First Name: " + user.getFirst() + " | Last Name: " + user.getLast() + " | Username: " + user.getUsername() + " | Admin: " + user.getAdmin());
            return user;
        } else {
            System.out.println("Invalid login.");
            return null;
        }
    }

    public Employee login(String username, String password) throws NullPointerException{
        Employee user = ed.getByUsername(username);
        if (password.equals(user.getPassword())){
            return user;
        }
        return null;
    }


    public Employee register(){
        System.out.println("Please enter your First Name");
        String first = sc.nextLine();

        while(first.equals("")){
            System.out.println("First name cant be empty. Please enter your First Name");
            first = sc.nextLine();
        }

        System.out.println("Please enter your Last Name");
        String last = sc.nextLine();

        while(last.equals("")){
            System.out.println("Last name cant be empty. Please enter your Last Name");
            last = sc.nextLine();
        }

        System.out.println("Please enter your username");
        String username = sc.nextLine();

        while(username.equals("")){
            System.out.println("Username cant be empty. Please enter a username");
            username = sc.nextLine();
        }

        System.out.println("Please enter your password");
        String password = sc.nextLine();

        while(password.equals("")){
            System.out.println("Password is needed. Please enter your password");
            password = sc.nextLine();
        }

        Employee employee = ed.createEmployee(first,last,username,password);


        return employee;
    }

    public Employee register(String first, String last, String username, String password){
        Employee employee = ed.createEmployee(first, last, username, password);
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
