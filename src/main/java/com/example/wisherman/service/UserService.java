package com.example.wisherman.service;

import com.example.wisherman.model.User;
import com.example.wisherman.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserService {
    UserRepository userRepository = new UserRepository();

    public User makeUser(String username, String password, String firstName, String lastName, String email){
        return new User(username,password,firstName,lastName,email);
    }

    public void saveUser(User user){
        userRepository.addUserToDB(user);
    }

    public boolean userIsValid(User user){
        return user.userIsFilled(user) && !userIsDuplicate(user);
    }

    public boolean userIsDuplicate(User currentUser){
        List<User> userList = userRepository.getAllUsers();
        for(User user : userList){
            if(currentUser.getUsername().equals(user.getUsername()) || currentUser.getEmail().equals(user.getEmail())){
                return true;
            }
        }
        return false;
    }

    public int loginApproved(String username, String password){
        return userRepository.getUserIdLogin(username, password);
    }

    /*
    public int loginApproved(String username, String password){
        List<User> userList = userRepository.getAllUsers();
        for(User user : userList){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user.getUserID();
            }
        }
        return -1;
    }
    */

    public User approvedUser(int userID){
        return userRepository.getUser(userID);
        }

    public int getUserSessionID(HttpSession session){
        if (session.getAttribute("user") == null || session.isNew()) {

            System.out.println("SESSION NOT SET. NULL!! Default to 1");

            session.setAttribute("user", userRepository.getUser(2));

            return 2;
        }
        return ((User)session.getAttribute("user")).getUserID();
    }


}

