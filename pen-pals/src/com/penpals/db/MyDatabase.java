package com.penpals.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Properties;

public class MyDatabase {
    static  Connection conn = null;

    public static Connection doConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

        return conn;
    }

    public static void main(String[] args){
        try{
            System.out.println(MyDatabase.doConnection());
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

}
