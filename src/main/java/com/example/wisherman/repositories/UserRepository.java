package com.example.wisherman.repositories;

import com.example.wisherman.model.User;
import com.example.wisherman.utlility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    Connection conn = DBConnection.getConnection();
    List<User> listOfUsers;

    public List<User> getAllUsers() {
        listOfUsers = new ArrayList<>();

        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("SELECT * FROM sql11448423.users");
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                User currentUser = new User();
                        currentUser.setUserID(resultSet.getInt("id"));
                        currentUser.setUsername(resultSet.getString("username"));
                        currentUser.setPassword(resultSet.getString("password"));
                        currentUser.setFirstName(resultSet.getString("first_name"));
                        currentUser.setLastName(resultSet.getString("last_name"));
                        currentUser.setEmail(resultSet.getString("email"));
                listOfUsers.add(currentUser);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listOfUsers;
    }

    public User getUser(int id) {
        User currentUser = new User();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("SELECT * FROM sql11448423.users WHERE id = (?)");
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                        currentUser.setUserID(resultSet.getInt("id"));
                        currentUser.setUsername(resultSet.getString("username"));
                        currentUser.setPassword(resultSet.getString("password"));
                        currentUser.setFirstName(resultSet.getString("first_name"));
                        currentUser.setLastName(resultSet.getString("last_name"));
                        currentUser.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return currentUser;
    }

    public int getUserIdLogin(String username, String password) {

        PreparedStatement pstmt = null;
        int a = 2;

        try {
            pstmt = conn.prepareStatement("SELECT users.id FROM users WHERE username = ? AND users.password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();


            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                a = resultSet.getInt("id");
            }


            return a;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return a;
    }

    public boolean addUserToDB(User user) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("INSERT INTO sql11448423.users (username, password, first_name, last_name, email) VALUES (?, ?, ?, ?, ?)");


            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFirstName());
            pstmt.setString(4, user.getLastName());
            pstmt.setString(5, user.getEmail());

            return pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
