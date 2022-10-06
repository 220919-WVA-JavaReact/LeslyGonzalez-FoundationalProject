package com.revature.reimbursement.dao;

import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.models.Reimbursement;
import com.revature.reimbursement.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
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

        Connection conn = ConnectionUtil.getConnection();

        List<Reimbursement> reimbursements = new ArrayList<>();

        try{
            Statement stat = conn.createStatement();

            String sql = "SELECT * FROM reimbursement WHERE completed = false";

            ResultSet rs = stat.executeQuery(sql);

            while(rs.next()){

                int id = rs.getInt("reimbursement_id");
                double amount = rs.getDouble("amount");
                String description = rs.getString("description");
                boolean approval_status = rs.getBoolean("approval_status");
                boolean completed = rs.getBoolean("completed");
                String date = rs.getString("created_at");
                int employee_id = rs.getInt("employee_id");

                Reimbursement ticket = new Reimbursement(id, amount, description, approval_status, completed, date, employee_id);

                reimbursements.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return reimbursements;
    }

    @Override
    public List<Reimbursement> getReimbursementByEmployee(int id) {
        System.out.println("Get reimbursement by employee");


        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection();){

            String sql = "SELECT * FROM reimbursement WHERE employee_id = ?";

            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setInt(1, id);

            ResultSet rs = stat.executeQuery();

            if((rs = stat.executeQuery()) != null) {

                while (rs.next()) {

                    int ticketId = rs.getInt("reimbursement_id");
                    double amount = rs.getDouble("amount");
                    String description = rs.getString("description");
                    boolean approved = rs.getBoolean("approval_status");
                    boolean completed = rs.getBoolean("completed");
                    String date = rs.getString("created_at");

                    Reimbursement ticket = new Reimbursement(ticketId, amount, description, approved, completed, date, id);

                    reimbursements.add(ticket);
                }
            }
            } catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public boolean updateReimbursement(Reimbursement reimbursement) {
        System.out.println("update method");
        return false;
    }
}
