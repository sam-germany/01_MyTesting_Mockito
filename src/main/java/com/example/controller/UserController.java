package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value= "/create")
    public User createUser(@RequestBody User user){
      return   userService.createUser(user);
    }

    @GetMapping
    public List<User> getUser() {

      Iterable<User> x =  userService.findAllUser();
      List<User> users = null;

      for( User u: x  ){ users.add(u);  }
     return  users;
    }

    @GetMapping(value = "/getById")
    public User getUserById(@RequestParam int id) {
        return userService.findUserById(id);
    }

    @DeleteMapping(value= "/deleteById")
    public void deleteUserById(@RequestParam int id) {
         userService.deleteUserById(id);
    }

    @DeleteMapping
    public void delete(@RequestBody User user) {
        userService.delete(user);
    }



}
