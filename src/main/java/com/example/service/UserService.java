package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserService() { }


    UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;  }



    public User createUser(User user) {
        System.out.println("-----------");
        return  userRepository.save(user);
    }

    public User findUserById(int id) {
        return userRepository.findById(id).get();
    }

    public Iterable<User> findAllUser() {
        return userRepository.findAll();
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
    public void delete(User user) {
        userRepository.delete(user);
    }



}
