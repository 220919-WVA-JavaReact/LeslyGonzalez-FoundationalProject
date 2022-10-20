package com.revature.reimbursement.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.dao.ReimbursementDAO;
import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.models.Reimbursement;
import com.revature.reimbursement.service.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {

    ReimbursementService rs = new ReimbursementService();
    private final ObjectMapper objMapper;
    private final ReimbursementDAO rd;




    public ReimbursementServlet(ObjectMapper objMapper, ReimbursementDAO rd) {
        this.objMapper = objMapper;
        this.rd = rd;
    }

    @Override
    public void init() throws ServletException{
        System.out.println("[LOG}  - ReimbursementServlet Instantiated!");
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{

        //Call the database to get the reimbursement ticket list

        List<Reimbursement> tickets = rd.getAllReimbursement();

        String resPayload = objMapper.writeValueAsString(tickets);
        res.setContentType("application/json");
        res.getWriter().write(resPayload);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            Employee loggedIn = (Employee) session.getAttribute("auth-user");

            HashMap<String, Object> credentials = objMapper.readValue(req.getInputStream(), HashMap.class);

            double providedAmount = (double) credentials.get("amount");
            String providedDescription = (String) credentials.get("description");
            String providedType = (String) credentials.get("reimbursement_type");


            Reimbursement ticket = rs.createReimbursement(providedAmount, providedDescription, providedType, loggedIn.getEmployeeId());

            String payload = objMapper.writeValueAsString(ticket);

            if (!payload.equals("null")) {
                resp.setContentType("application/json");
                resp.getWriter().write(payload);
            } else {
                resp.getWriter().write("no bueno");

            }

        }
    }

}
