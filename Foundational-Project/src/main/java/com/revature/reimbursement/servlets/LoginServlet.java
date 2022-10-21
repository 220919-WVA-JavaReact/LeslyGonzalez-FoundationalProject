package com.revature.reimbursement.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;


public class LoginServlet extends HttpServlet {


    EmployeeService es = new EmployeeService();

    private final ObjectMapper objMapper;


    public LoginServlet(ObjectMapper objMapper) {
        this.objMapper = objMapper;
    }

    public void init() throws ServletException{
        System.out.println("[LOG}  - LoginServlet Instantiated!");
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
            HttpSession session = req.getSession();
            session.setAttribute("auth-user", user);
            return;
        }else {
            resp.setStatus(400);
            resp.setContentType("application/json");

            HashMap<String, Object> errorMessage = new HashMap<>();

            errorMessage.put("Status code", 400);
            errorMessage.put("Message" , "No user found with provided credentials");
            errorMessage.put("TimeStamp" , LocalDateTime.now().toString());

            resp.getWriter().write(objMapper.writeValueAsString(errorMessage));

        }


        System.out.println(providedUser);
        System.out.println(providePassword);
    }

@Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        HttpSession session = req.getSession(false);

        if(session != null){
            session.invalidate();
            resp.getWriter().write("Logged out");
        }else{
            resp.setStatus(201);
        }
    }

}
