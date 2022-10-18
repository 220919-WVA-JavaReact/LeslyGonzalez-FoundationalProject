package com.revature.reimbursement.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.dao.EmployeeDAO;
import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.models.Reimbursement;
import com.revature.reimbursement.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class EmployeeServlet extends HttpServlet {

    private final ObjectMapper objMapper;

    EmployeeService es = new EmployeeService();

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


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String, Object> credentials = objMapper.readValue(req.getInputStream(), HashMap.class);

        String providedFirst = (String) credentials.get("first");
        String providedLast = (String) credentials.get("last");
        String providedUser = (String) credentials.get("username");
        String providedPass= (String) credentials.get("password");

        Employee user = es.register(providedFirst, providedLast, providedUser, providedPass);
        String payload = objMapper.writeValueAsString(user);


        if(!payload.equals("null")) {
            resp.getWriter().write(payload);
        } else if(providedFirst.equals("null") && providedFirst.equals("")){
            resp.getWriter().write("First name can't be empty. Please enter a first name.");
        }else if(providedLast.equals("null") && providedLast.equals("")){
            resp.getWriter().write(("Last name can't be empty. Please enter last name."));
        } else if (providedUser.equals("null") && providedUser.equals("")){
            resp.getWriter().write("Username can't be empty. Please enter username");
        } else if (!providedPass.equals("null") || !providedPass.equals("")){
            resp.getWriter().write("Password cant be empty. Please enter a password");
        }
    }
}

