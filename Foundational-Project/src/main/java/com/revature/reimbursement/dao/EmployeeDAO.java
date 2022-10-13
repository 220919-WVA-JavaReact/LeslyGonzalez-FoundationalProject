package com.revature.reimbursement.dao;

import com.revature.reimbursement.models.Employee;

import java.util.List;

public interface EmployeeDAO {

    Employee getByUsername(String username);
  //we need a method for employee creation

    Employee createEmployee(String first, String last, String username, String password);

    //adding method
    List<Employee> getAllEmployees();
}
