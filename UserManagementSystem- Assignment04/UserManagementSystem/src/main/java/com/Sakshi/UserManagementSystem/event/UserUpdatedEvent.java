package com.Sakshi.UserManagementSystem.event;

import com.Sakshi.UserManagementSystem.model.Users;

import java.util.Map;

public class UserUpdatedEvent {
    private final Users user;
    private final Map<String, Object> changedFields;

    public UserUpdatedEvent(Users user, Map<String, Object> changedFields) {
        this.user = user;
        this.changedFields = changedFields;
    }

    public Users getUser() {
        return user;
    }

    public Map<String, Object> getChangedFields() {
        return changedFields;
    }
}
