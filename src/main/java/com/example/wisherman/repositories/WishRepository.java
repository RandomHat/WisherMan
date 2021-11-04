package com.example.wisherman.repositories;

import com.example.wisherman.model.Wish;
import com.example.wisherman.model.WishList;
import com.example.wisherman.utlility.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class WishRepository {
    List<Wish> listOfWishes;

    Connection conn = DBConnection.getConnection();

    public List<Wish> getAllWishes() {
        listOfWishes = new ArrayList<>();
        PreparedStatement pstmt = null;

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
        PreparedStatement pstmt = null;

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


}
