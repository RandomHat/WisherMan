package com.example.wisherman.controller;

import com.example.wisherman.utlility.DBConnection;

import java.sql.Connection;

public class Controller {

    Connection conn = DBConnection.getConnection();
}
