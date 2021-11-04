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

@Repository
public class WishListRepository {
    List<WishList> wishListList;
    Connection conn = DBConnection.getConnection();

    public List<WishList> getAllWishLists() {
        wishListList = new ArrayList<>();

        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("SELECT * FROM sql11448423.wishlists");
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                WishList wishlist = new WishList(
                        resultSet.getInt("idwishlists"),
                        resultSet.getString("list_name"),
                        resultSet.getInt("user_id")
                );
                wishListList.add(wishlist);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return wishListList;
    }




}
