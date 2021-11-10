package com.example.wisherman.repositories;

import com.example.wisherman.model.User;
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


    public List<WishList> getUserWishLists(int id) {
        wishListList = new ArrayList<>();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("SELECT * FROM sql11448423.wishlists WHERE user_id = (?)");
            pstmt.setInt(1, id);
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

    public WishList getWishList(int id) {
        WishList wishList = new WishList();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("SELECT * FROM sql11448423.wishlists WHERE idwishlists = (?)");
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                wishList.setIdwishlist(resultSet.getInt("idwishlists"));
                wishList.setListName(resultSet.getString("list_name"));
                wishList.setUserid(resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return wishList;
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

    public boolean addWishListToDB(WishList wishlist) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("INSERT INTO sql11448423.wishlists (list_name, user_id) VALUES (?, ?)");

            pstmt.setString(1, wishlist.getListName());
            pstmt.setInt(2, wishlist.getUserid());

            return pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
