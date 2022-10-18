package com.revature.reimbursement.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


public class LoginServlet extends HttpServlet {


    EmployeeService es = new EmployeeService();

    private final ObjectMapper objMapper;


    public LoginServlet(ObjectMapper objMapper) {
        this.objMapper = objMapper;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HashMap<String, Object> credentials = objMapper.readValue(req.getInputStream(), HashMap.class);

        String providedUser = (String) credentials.get("username");
        String providePassword = (String) credentials.get("password");


        Employee user = es.login(providedUser, providePassword);
        String payload = objMapper.writeValueAsString(user);

        if(!payload.equals("null")){
            resp.getWriter().write(payload);
            return;
        }else {
            resp.getWriter().write("Invalid credentials");
        }


        System.out.println(providedUser);
        System.out.println(providePassword);
    }

}
