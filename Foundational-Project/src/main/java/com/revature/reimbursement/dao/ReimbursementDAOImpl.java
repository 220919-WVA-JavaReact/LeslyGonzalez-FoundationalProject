package com.revature.reimbursement.dao;

import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.models.Reimbursement;
import com.revature.reimbursement.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO {

    @Override
    public boolean createReimbursement(double amount, String description, Employee employee) {
        System.out.println("Created ticked Method");


        Reimbursement reimbursement = new Reimbursement();

        try(Connection conn = ConnectionUtil.getConnection()){


            String sql = "INSERT INTO reimbursement (amount, description, employee_id) VALUES (?,?,?)";

            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setDouble(1, amount);
            stat.setString(2, description);
            stat.setInt(3, employee.getEmployeeId());

            int rowsUpdated = stat.executeUpdate();

            if(rowsUpdated == 1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Unable to add course");
        }
        return false;
    }


    @Override
    public List<Reimbursement> getAllReimbursement() {
        System.out.println("get all the reimbursement method");
        return null;
    }

    @Override
    public List<Reimbursement> getReimbursementByEmployee(int id) {
        System.out.println("Get reimbursement by employee");
        return null;
    }

    @Override
    public boolean updateReimbursement(Reimbursement reimbursement) {
        System.out.println("update method");
        return false;
    }
}
