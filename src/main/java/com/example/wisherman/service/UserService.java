package com.example.wisherman.service;

import com.example.wisherman.model.User;
import com.example.wisherman.repositories.UserRepository;

public class UserService {
    UserRepository userRepository = new UserRepository();

    public User makeUser(String username, String password, String firstName, String lastName, String email){
        return new User(username,password,firstName,lastName,email);
    }

    public void saveUser(User user){
        userRepository.addUserToDB(user);
    }

}
