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

//        String url = System.getenv("url");
//        String username = System.getenv("username");
//        String password = System.getenv("password");


        String url = "";
        String username = "";
        String password = "";

        Properties prop = new Properties();



        try{
            prop.load(new FileReader("src/main/resources/application.properties"));

            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");


            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Established connection to database!");

        } catch (IOException e){
            System.out.println("Property file not found!");
            throw new RuntimeException(e);
        }catch (SQLException e){
            System.out.println("Could not establish connection");
            throw new RuntimeException(e);
        }

        return conn;
    }


    //Static block for Driver

    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load PostgreSQL driver");
            throw new RuntimeException(e);
        }
    }


}