package com.revature.reimbursement.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

//singleton

    //private instance connection

    private static Connection conn = null;

    //private constructor
    private ConnectionUtil(){

    }
    //public static getInstance() Method
    //method create a new connection or allow us to reuse the connection that exists

    public static Connection getConnection(){
        //check to see if there is a connection
        //if there is return it
        try {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Use previously made Connection");
                return conn;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }


        String url = System.getenv("url");
        String username = System.getenv("username");
        String password = System.getenv("password");
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Could not establish connection");
            e.printStackTrace();
        }



        return conn;
    }

}
