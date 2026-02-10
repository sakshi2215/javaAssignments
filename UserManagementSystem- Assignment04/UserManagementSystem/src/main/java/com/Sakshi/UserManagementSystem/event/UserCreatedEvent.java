package com.Sakshi.UserManagementSystem.event;
import com.Sakshi.UserManagementSystem.model.Users;

public class UserCreatedEvent {

    private final Users user;

    public UserCreatedEvent(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }
}

