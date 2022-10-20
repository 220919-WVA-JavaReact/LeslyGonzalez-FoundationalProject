package com.revature.reimbursement.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.dao.EmployeeDAO;
import com.revature.reimbursement.dao.EmployeeDoaImplPostgres;
import com.revature.reimbursement.dao.ReimbursementDAO;
import com.revature.reimbursement.dao.ReimbursementDAOImpl;
import com.revature.reimbursement.servlets.EmployeeServlet;
import com.revature.reimbursement.servlets.LoginServlet;
import com.revature.reimbursement.servlets.ReimbursementServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import java.time.LocalDateTime;

public class ContextLoaderListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce){
        System.out.println("[LOG] - The servlet context was initialized at " + LocalDateTime.now());

        ObjectMapper objMapper = new ObjectMapper();
        EmployeeDAO ed = new EmployeeDoaImplPostgres();
        EmployeeServlet employeeServlet = new EmployeeServlet(objMapper, ed);
        ReimbursementDAO rd = new ReimbursementDAOImpl();
        ReimbursementServlet reimbursementServlet = new ReimbursementServlet(objMapper, rd);
        LoginServlet loginServlet = new LoginServlet(objMapper);



        ServletContext context = sce.getServletContext();

        ServletRegistration.Dynamic registeredServlet = context.addServlet("LoginServlet", loginServlet);
        registeredServlet.addMapping("/login");
        registeredServlet.setLoadOnStartup(1);
        registeredServlet.setInitParameter("employee-servlet-key", "employee-servlet-value");

        ServletRegistration.Dynamic registeredServlet1 = context.addServlet("EmployeeServlet", employeeServlet);
        registeredServlet1.addMapping("/employee");
        registeredServlet1.setLoadOnStartup(2);
        registeredServlet1.setInitParameter("employee-servlet-key", "employee-servlet-value");


        ServletRegistration.Dynamic registeredServlet2 = context.addServlet("ReimbursementServlet", reimbursementServlet);
        registeredServlet2.addMapping("/reimbursements/*");
        registeredServlet2.setLoadOnStartup(3);



    }

    public void contextDestroyed(ServletContextEvent sce){
        System.out.println("[LOG] - The servlet context was destroyed at " + LocalDateTime.now());
    }

}
