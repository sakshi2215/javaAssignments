package com.Sakshi.UserManagementSystem.service;
import com.Sakshi.UserManagementSystem.event.UserCreatedEvent;
import com.Sakshi.UserManagementSystem.event.UserDeletedEvent;
import com.Sakshi.UserManagementSystem.event.UserUpdatedEvent;
import com.Sakshi.UserManagementSystem.model.Users;
import com.Sakshi.UserManagementSystem.repository.UserRepo;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepo repo;
    private final ApplicationEventPublisher eventPublisher;


    public UserService(UserRepo repo, ApplicationEventPublisher eventPublisher) {
        this.repo = repo;
        this.eventPublisher = eventPublisher;
    }

    public String createUser(Users user) {
        Users savedUser = repo.save(user);

        eventPublisher.publishEvent(
                new UserCreatedEvent(savedUser)
        );

        return "User saved successfully";
    }

    public List<Users> getAllUser() {
        return repo.findByActiveTrue();
    }

    public Users getUserByID(Long id){
        return repo.findById(id).orElse(null);
    }


    public Users updateUser(@NonNull Users user, Long id) {

        Users existingUser = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id not found"));

        Map<String, Object> changes = new HashMap<>();

        // MAP CONTAINS -
        //ex: firstname, Sakshi -> UpdatedSakshi
        //changes.put("firstName",
        //                    existingUser.getFirstName() + " → " + user.getFirstName());

        if (user.getFirstName() != null &&
                !user.getFirstName().equals(existingUser.getFirstName())) {
            changes.put("firstName",
                    existingUser.getFirstName() + " → " + user.getFirstName());
            existingUser.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null &&
                !user.getLastName().equals(existingUser.getLastName())) {
            changes.put("lastName",
                    existingUser.getLastName() + " → " + user.getLastName());
            existingUser.setLastName(user.getLastName());
        }

        if (user.getEmail() != null &&
                !user.getEmail().equals(existingUser.getEmail())) {
            changes.put("email",
                    existingUser.getEmail() + " → " + user.getEmail());
            existingUser.setEmail(user.getEmail());
        }

        if (user.getRole() != null &&
                !user.getRole().equals(existingUser.getRole())) {
            changes.put("role",
                    existingUser.getRole() + " → " + user.getRole());
            existingUser.setRole(user.getRole());
        }

        Users updatedUser = repo.save(existingUser);

        if (!changes.isEmpty()) {
            eventPublisher.publishEvent(
                    new UserUpdatedEvent(updatedUser, changes)
            );
        }

        return updatedUser;
    }


    public void deleteUser(Long id) {
        Users user = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id not found"));

        user.setActive(false);
        repo.save(user);

        eventPublisher.publishEvent(
                new UserDeletedEvent(user)
        );
    }

}
