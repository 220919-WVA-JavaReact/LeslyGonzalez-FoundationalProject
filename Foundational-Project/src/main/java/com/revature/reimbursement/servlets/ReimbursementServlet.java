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
import java.time.LocalDateTime;
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


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        HttpSession session = req.getSession(false);
        //Call the database to get the reimbursement ticket list
        if (session != null) {
            Employee loggedIn = (Employee) session.getAttribute("auth-user");

            if(req.getParameter("action").equals("pending")){//if pending then get pending tickets

                if(loggedIn.getAdmin()) {
                    List<Reimbursement> tickets = rd.getAllPending();

                    String resPayload = objMapper.writeValueAsString(tickets);
                    resp.setContentType("application/json");
                    resp.getWriter().write(resPayload);
                }else{
                    resp.setStatus(400);
                    resp.setContentType("application/json");
                    resp.getWriter().write("Message: Login as manager to proceed.");
                }
            }else if(req.getParameter("action").equals("byEmployee")){//tickets by employee sign in
                List<Reimbursement> tickets = rd.getReimbursementByEmployee(loggedIn.getEmployeeId());

                String resPayload = objMapper.writeValueAsString(tickets);
                resp.setContentType("application/json");
                resp.getWriter().write(resPayload);
            } else if (req.getParameter("action").equals("allTickets")) {
                if(loggedIn.getAdmin()) {
                    List<Reimbursement> tickets = rd.getAllReimbursement();//All tickets

                    String resPayload = objMapper.writeValueAsString(tickets);
                    resp.setContentType("application/json");
                    resp.getWriter().write(resPayload);
                }else{
                    resp.getWriter().write("Manager feature! Can not be accessed by Employees.");
                }
            }
        }else{
            resp.setStatus(400);
            resp.setContentType("application/json");
            resp.getWriter().write("Message:  Login to view this features.");
        }
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

            if (providedAmount <= 0.00) {
                resp.getWriter().write("Message: Reimbursement ticket amount can not be equal to zero! \n        Please enter a valid amount.");
            } else if (providedDescription.equals("")) {
                resp.getWriter().write("Message: Reimbursement ticket description can not be empty! \n        Please enter a valid description.");
            } else if (providedType.equals("")) {
                resp.getWriter().write("Message: Reimbursement ticket reimbursement type can not be empty! \n        Please enter a valid type.");
            } else {
                Reimbursement ticket = rs.createReimbursement(providedAmount, providedDescription, providedType, loggedIn.getEmployeeId());

                String payload = objMapper.writeValueAsString(ticket);

                if (!payload.equals("null")) {
                    resp.setContentType("application/json");
                    resp.getWriter().write(payload);
                } else {
                    resp.setStatus(400);
                    resp.setContentType("application/json");
                    resp.getWriter().write("Unable to create reimbursement ticket.");
                }
            }

        }else {
            resp.setStatus(400);
            resp.setContentType("application/json");
            resp.getWriter().write("Message: Login to view this features");
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession(false);

        if(session != null){
            Employee loggedIn = (Employee) session.getAttribute("auth-user");

            if(loggedIn.getAdmin()){

                if(req.getParameter("action").equals("denyTicket")){

                    HashMap<String, Object> updateDeny = objMapper.readValue(req.getInputStream(), HashMap.class);

                    int ProvidedTicket = (int) updateDeny.get("reimbursement_id");
                    System.out.println(ProvidedTicket);

                    Reimbursement getTicket = rs.getReimbursementById(ProvidedTicket);

                    if (!getTicket.isApprovalStatus() && !getTicket.isCompleted()) {

                        Reimbursement ticket = rs.updateReimbursementDeny(ProvidedTicket);

                        String payload = objMapper.writeValueAsString(ticket);

                        if (payload != null) {
                            resp.setStatus(200);
                            resp.setContentType("application/json");
                            resp.getWriter().write("Message: Your ticket was Denied. \n         Might not be a work related cause. \n");
                            resp.getWriter().write(payload);

                        } else {
                            resp.setStatus(400);
                            resp.setContentType("application/json");
                            resp.getWriter().write("Unable to update ticket reimbursement status.");
                        }
                    }else{
                        resp.getWriter().write("You can not approve tickets that have already been approved or denied.");
                    }

                } else if (req.getParameter("action").equals("approvedTicket")) {

                    HashMap<String, Object> approvedTicket = objMapper.readValue(req.getInputStream(), HashMap.class);

                    int providedTicket = (int) approvedTicket.get("reimbursement_id");

                    Reimbursement getTicket = rs.getReimbursementById(providedTicket);

                    if (!getTicket.isApprovalStatus() && !getTicket.isCompleted()) {

                        Reimbursement ticket = rs.reimbursementApproval(providedTicket);

                        String payload = objMapper.writeValueAsString(ticket);

                        if (payload != null) {
                            resp.setStatus(200);
                            resp.setContentType("application/json");
                            resp.getWriter().write("Message: Your ticket was Approved. \n         Enough proof of work related cause. \n");
                            resp.getWriter().write(payload);

                        } else {
                            resp.setStatus(400);
                            resp.setContentType("application/json");
                            resp.getWriter().write("Unable to update ticket reimbursement status.");
                        }
                    }else{
                        resp.getWriter().write("You can not approve tickets that have already been approved or denied.");
                    }
                }
            }else { //check this section
                resp.setStatus(400);
                resp.setContentType("application/json");
                resp.getWriter().write("Message: Login as manager to proceed");
            }
        } else { //check this section
            resp.setStatus(400);
            resp.setContentType("application/json");
            resp.getWriter().write("Message: Login to view features");
        }

    }

}




