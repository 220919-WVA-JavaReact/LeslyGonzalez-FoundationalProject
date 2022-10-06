package com.revature.reimbursement.dao;

import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDoaImplPostgres implements EmployeeDAO {

    @Override
    public Employee getByUsername(String username) {
        //

        Employee user = new Employee();
        //user block to close connection
        //close resources after using them
        try(Connection conn = ConnectionUtil.getConnection()){

            //sql statement
            String sql = "SELECT * FROM employee WHERE username = ?";

            //prepare statement
            PreparedStatement stat = conn.prepareStatement(sql);

            //set values for ?s
            stat.setString(1,username);

            ResultSet rs;

            if((rs = stat.executeQuery()) != null){

                rs.next();

                int id = rs.getInt("employee_id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String receivedUser = rs.getString("username");
                String password = rs.getString("password");
                boolean admin = rs.getBoolean("admin");

                user = new Employee(id, first, last, username,password, admin);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Employee createEmployee(String first, String last, String username, String password, boolean admin) {
        Employee employee = new Employee();

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO employee(first, last, username, password, admin) VALUES (?,?,?,?,?) RETURNING *";

            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, first);
            stat.setString(2, last);
            stat.setString(3, username);
            stat.setString(4, password);
            stat.setBoolean(5, admin);

            ResultSet rs;

            if((rs = stat.executeQuery()) != null){

                rs.next();

                int id = rs.getInt("employee_id");
                String receivedFirst = rs.getString("first");
                String receivedLast = rs.getString("last");
                String receivedUser = rs.getString("username");
                String receivedPassword = rs.getString("password");
                boolean receivedAdmin = rs.getBoolean("admin");

                employee = new Employee(id, receivedFirst, receivedLast, receivedUser, receivedPassword, receivedAdmin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't register to database");
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {

        Connection conn = ConnectionUtil.getConnection();

        List<Employee> employee = new ArrayList<>();

        try{
            Statement stat = conn.createStatement();

            String sql = "SELECT * FROM employee";

            ResultSet rs = stat.executeQuery(sql);


            while(rs.next()){

                int id = rs.getInt("employee_id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean admin = rs.getBoolean("admin");

                Employee user = new Employee(id, first, last, username,password, admin);

                        employee.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
