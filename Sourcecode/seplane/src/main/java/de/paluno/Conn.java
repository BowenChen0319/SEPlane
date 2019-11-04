package de.paluno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn{
    Connection con;
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con= DriverManager.getConnection("jdbc:mysql:"+
                    "//db4free.net","seplane","seplane1920");
            System.out.println("get Mysql");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}