package org.example.basicproject.controller;

import org.example.basicproject.model.User;
import org.example.basicproject.service.UserManagementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserMgtController {

    @Autowired
    UserManagementService userManagementService;

    @RequestMapping("/users")
    public User[] getUserList() {
        return userManagementService.getUserList().toArray(new User[0]);
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        userManagementService.addUserList(user);
    }
}