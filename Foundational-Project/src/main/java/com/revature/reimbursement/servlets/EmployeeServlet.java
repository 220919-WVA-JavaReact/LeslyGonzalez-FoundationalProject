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
import javax.servlet.http.HttpSession;
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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{

        HttpSession session = req.getSession(false);
        //Call the database to get the reimbursement ticket list
        if (session != null) {
            Employee loggedIn = (Employee) session.getAttribute("auth-user");

            if(loggedIn.getAdmin()) {
                List<Employee> tickets = ed.getAllEmployees();

                String resPayload = objMapper.writeValueAsString(tickets);
                resp.setContentType("application/json");
                resp.getWriter().write(resPayload);
            }else{
                resp.setStatus(400);
                resp.setContentType("application/json");
                resp.getWriter().write("Message: Login as a manager to view this features.");
            }
        }else {
            resp.setStatus(400);
            resp.setContentType("application/json");
            resp.getWriter().write("Message: Login to view this features.");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            HashMap<String, Object> credentials = objMapper.readValue(req.getInputStream(), HashMap.class);

            String providedFirst = (String) credentials.get("first");
            String providedLast = (String) credentials.get("last");
            String providedUser = (String) credentials.get("username");
            String providedPass = (String) credentials.get("password");

            Employee user = es.register(providedFirst, providedLast, providedUser, providedPass);
            String payload = objMapper.writeValueAsString(user);


            if (!payload.equals("null")) {
                resp.setContentType("application/json");
                resp.getWriter().write(payload);
            } else {
                resp.setStatus(400);
                resp.setContentType("application/json");
                resp.getWriter().write("Message: Username already in use.");
            }
        }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            Employee loggedIn = (Employee) session.getAttribute("auth-user");

            if (loggedIn.getAdmin()) {
                    HashMap<String, Object> updateDeny = objMapper.readValue(req.getInputStream(), HashMap.class);

                    boolean approvedAdmin = (boolean) updateDeny.get("admin");
                    int providedUser = (int) updateDeny.get("employee_id");


                    Employee user = es.updateManager(approvedAdmin, providedUser);

                    String payload = objMapper.writeValueAsString(user);

                    if (payload != null) {
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        resp.getWriter().write("Message: Your role was updated. \n ");
                        resp.getWriter().write(payload);

                    } else {
                        resp.setStatus(400);
                        resp.setContentType("application/json");
                        resp.getWriter().write("Unable to update column");
                    }
            }else{
                resp.setStatus(400);
                resp.setContentType("application/json");
                resp.getWriter().write("Login as a manager to update or downgrade a role");
            }
        }
    }
}



