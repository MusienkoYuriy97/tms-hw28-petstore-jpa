package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean save(User user){
        if (userRepository.existsByUsername(user.getUsername())){
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public boolean login(String username, String password){
        if (userRepository.existsByUsernameAndPassword(username, password)){
            return true;
        }
        return false;
    }

    public User getByUsername(String username){
        Optional<User> user = userRepository.getByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }



    public boolean delete(String username) {
        if (userRepository.existsByUsername(username)){
            User user = userRepository.getByUsername(username).get();
            userRepository.delete(user);
            return true;
        }else {
            return false;
        }
    }

    public void update(String username, User user) {
        if (userRepository.existsByUsername(username)){
            userRepository.save(user);
        }
    }

}