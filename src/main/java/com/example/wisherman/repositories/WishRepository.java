package com.example.wisherman.repositories;

import com.example.wisherman.model.Wish;
import com.example.wisherman.utlility.DBConnection;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishRepository {
    List<Wish> listOfWishes;

    Connection conn = DBConnection.getConnection();

    public List<Wish> getAllWishes() {
        listOfWishes = new ArrayList<>();
        PreparedStatement pstmt;

        try {
            pstmt = conn.prepareStatement("SELECT * FROM sql11448423.wishes");
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Wish wish = new Wish(
                        resultSet.getInt("idwishes"),
                        resultSet.getString("title"),
                        resultSet.getString("link"),
                        resultSet.getString("price"),
                        resultSet.getInt("reserved") == 1,
                        resultSet.getInt("wishlist_id")
                );
                listOfWishes.add(wish);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listOfWishes;
    }

    public List<Wish> getUserWishes(int id) {

        listOfWishes = new ArrayList<>();
        PreparedStatement pstmt;

        try {
            pstmt = conn.prepareStatement("SELECT * FROM wishes WHERE wishlist_id IN (SELECT idwishlists FROM wishlists where user_id = (?))");
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Wish wish = new Wish(
                        resultSet.getInt("idwishes"),
                        resultSet.getString("title"),
                        resultSet.getString("link"),
                        resultSet.getString("price"),
                        resultSet.getInt("reserved") == 1,
                        resultSet.getInt("wishlist_id")
                );
                listOfWishes.add(wish);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listOfWishes;
    }

    public List<Wish> getWishListWishes(int listId){
        listOfWishes = new ArrayList<>();
        PreparedStatement pstmt;

        try {
            pstmt = conn.prepareStatement("SELECT * FROM wishes WHERE wishlist_id = (?)");
            pstmt.setInt(1,listId);

            ResultSet resultSet = pstmt.executeQuery();
            listOfWishes = unpackQuery(resultSet);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return listOfWishes;
    }

    public boolean addWishToWishList(Wish wish) {
        PreparedStatement pstmt;

        try {
            pstmt = conn.prepareStatement("INSERT INTO wishes (title, link, price, reserved, wishlist_id) VALUES (?, ?, ?, ?, ?)");

            pstmt.setString(1, wish.getTitle());
            pstmt.setString(2, wish.getUrl());
            pstmt.setString(3, wish.getPrice());
            pstmt.setInt(4, 0);
            pstmt.setInt(5, wish.getWishListID());

            return pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private List<Wish> unpackQuery(ResultSet resultSet) throws SQLException {
        listOfWishes = new ArrayList<>();

        while (resultSet.next()) {

            Wish wish = new Wish(
                    resultSet.getInt("idwishes"),
                    resultSet.getString("title"),
                    resultSet.getString("link"),
                    resultSet.getString("price"),
                    resultSet.getBoolean("reserved"),
                    resultSet.getInt("wishlist_id"));
            listOfWishes.add(wish);
        }
        return listOfWishes;
    }

}
