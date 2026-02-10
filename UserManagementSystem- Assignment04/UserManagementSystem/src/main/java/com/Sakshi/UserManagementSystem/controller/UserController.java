package com.Sakshi.UserManagementSystem.controller;
import com.Sakshi.UserManagementSystem.model.Users;
import com.Sakshi.UserManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public String createUser(@RequestBody Users user){
        return userService.createUser(user);
    }

    @GetMapping("/getAllUser")
    public List<Users> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/getUser/{id}")
    public Users getUserByID(@PathVariable Long id){
        return userService.getUserByID(id);
    }

    @PutMapping("/updateUser/{id}")
    public Users updateUser(@RequestBody Users user, @PathVariable Long id){

        return userService.updateUser(user,id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
