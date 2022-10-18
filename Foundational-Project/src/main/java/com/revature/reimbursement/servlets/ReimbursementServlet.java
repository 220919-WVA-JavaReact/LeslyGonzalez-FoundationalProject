package com.revature.reimbursement.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.dao.ReimbursementDAO;
import com.revature.reimbursement.models.Reimbursement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {

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
}
