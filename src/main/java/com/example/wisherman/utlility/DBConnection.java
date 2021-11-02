package com.example.wisherman.utlility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static String user;
    private static String pw;
    private static String url;
    private static Connection conn = null;

    private DBConnection() {}

    public static Connection getConnection() {
        if(conn != null){
            return conn;
        }
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            pw = properties.getProperty("pw");
            conn = DriverManager.getConnection(url, user, pw);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException | IOException e) {
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
