package com.revature.reimbursement.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.dao.EmployeeDAO;
import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.models.Reimbursement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmployeeServlet extends HttpServlet {

    private final ObjectMapper objMapper;
    private final EmployeeDAO ed;

    public EmployeeServlet(ObjectMapper objMapper, EmployeeDAO ed) {
        this.objMapper = objMapper;
        this.ed = ed;
    }


    public void init() throws ServletException{
        System.out.println("[LOG}  - EmployeeServlet Instantiated!");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{

        //Call the database to get the reimbursement ticket list

        List<Employee> tickets = ed.getAllEmployees();

        String resPayload = objMapper.writeValueAsString(tickets);
        res.setContentType("application/json");
        res.getWriter().write(resPayload);

    }
}
