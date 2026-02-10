package com.Sakshi.UserManagementSystem.event;

import com.Sakshi.UserManagementSystem.model.Users;

public class UserDeletedEvent {
    private final Users user;

    public UserDeletedEvent(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }
}

