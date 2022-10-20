package com.revature.reimbursement.dao;

import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.models.Reimbursement;
import com.revature.reimbursement.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO {

    @Override
    public Reimbursement createReimbursement(double amount, String description, String reimbursementType, Employee employee) {
        System.out.println("Created ticked Method");


        Reimbursement reimbursement = new Reimbursement();

        try (Connection conn = ConnectionUtil.getConnection()) {


            String sql = "INSERT INTO reimbursement (amount, description, reimbursement_type, employee_id) VALUES (?,?,?)";

            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setDouble(1, amount);
            stat.setString(2, description);
            stat.setString(3, reimbursementType );
            stat.setInt(4, employee.getEmployeeId());
// ResultSet rs;
//
//            if((rs = stat.executeQuery()) != null){
            int rowsUpdated = stat.executeUpdate();

            if (rowsUpdated == 1) {
                return reimbursement;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to add course");
        }
        return null;
    }


    @Override
    public List<Reimbursement> getAllReimbursement() {

        Connection conn = ConnectionUtil.getConnection();

        List<Reimbursement> reimbursements = new ArrayList<>();

        try {
            Statement stat = conn.createStatement();

            String sql = "SELECT * FROM reimbursement";

            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("reimbursement_id");
                double amount = rs.getDouble("amount");
                String description = rs.getString("description");
                String type = rs.getString("reimbursement_type");
                boolean approval_status = rs.getBoolean("approval_status");
                boolean completed = rs.getBoolean("completed");
                String date = rs.getString("created_at");
                int employee_id = rs.getInt("employee_id");

                Reimbursement ticket = new Reimbursement(id, amount, description, type , approval_status, completed, date, employee_id);

                reimbursements.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;
    }


    @Override
    public List<Reimbursement> getAllPending() {

        Connection conn = ConnectionUtil.getConnection();

        List<Reimbursement> reimbursements = new ArrayList<>();

        try {
            Statement stat = conn.createStatement();

            String sql = "SELECT * FROM reimbursement WHERE completed = false";

            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("reimbursement_id");
                double amount = rs.getDouble("amount");
                String description = rs.getString("description");
                String type = rs.getString("reimbursement_type");
                boolean approval_status = rs.getBoolean("approval_status");
                boolean completed = rs.getBoolean("completed");
                String date = rs.getString("created_at");
                int employee_id = rs.getInt("employee_id");

                Reimbursement ticket = new Reimbursement(id, amount, description, type, approval_status, completed, date, employee_id);

                reimbursements.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public List<Reimbursement> getReimbursementByEmployee(int id) {

        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection();) {

            String sql = "SELECT * FROM reimbursement WHERE employee_id = ?";

            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setInt(1, id);

            ResultSet rs;

            if ((rs = stat.executeQuery()) != null) {

                while (rs.next()) {

                    int ticketId = rs.getInt("reimbursement_id");
                    double amount = rs.getDouble("amount");
                    String description = rs.getString("description");
                    String type = rs.getString("reimbursement_type");
                    boolean approved = rs.getBoolean("approval_status");
                    boolean completed = rs.getBoolean("completed");
                    String date = rs.getString("created_at");

                    Reimbursement ticket = new Reimbursement(ticketId, amount, description, type, approved, completed, date, id);

                    reimbursements.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public Reimbursement updateReimbursement(int id) {
        System.out.println("update method");

        Reimbursement ticket = new Reimbursement();

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "UPDATE reimbursement SET completed = true WHERE reimbursement_id = ? RETURNING *";

            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setInt(1, id);

            ResultSet rs;

            if ((rs = stat.executeQuery()) != null) {

                rs.next();

                double amount = rs.getDouble("amount");
                String description = rs.getString("description");
                String type = rs.getString("reimbursement_type");
                boolean status = rs.getBoolean("approval_status");
                boolean complete = rs.getBoolean("completed");
                String date = rs.getString("created_at");
                int employee = rs.getInt("employee_id");

                ticket = new Reimbursement(id, amount, description, type, status, complete, date, employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ticket;
    }


    @Override
    public Reimbursement reimbursementApproval(int id) {
        System.out.println("update method");

        Reimbursement ticket = new Reimbursement();

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "UPDATE reimbursement SET approval_status = true WHERE reimbursement_id = ? RETURNING *";

            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setInt(1, id);

            ResultSet rs;

            if ((rs = stat.executeQuery()) != null) {

                rs.next();

                double amount = rs.getDouble("amount");
                String description = rs.getString("description");
                String type = rs.getString("reimbursement_type");
                boolean status = rs.getBoolean("approval_status");
                boolean complete = rs.getBoolean("completed");
                String date = rs.getString("created_at");
                int employee = rs.getInt("employee_id");

                ticket = new Reimbursement(id, amount, description,  type,status, complete, date, employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ticket;
    }



}