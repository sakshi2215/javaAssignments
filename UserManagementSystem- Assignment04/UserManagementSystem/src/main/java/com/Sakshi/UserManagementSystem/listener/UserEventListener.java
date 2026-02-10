package com.Sakshi.UserManagementSystem.listener;
import com.Sakshi.UserManagementSystem.event.UserCreatedEvent;
import com.Sakshi.UserManagementSystem.event.UserDeletedEvent;
import com.Sakshi.UserManagementSystem.event.UserUpdatedEvent;
import com.Sakshi.UserManagementSystem.model.Users;
import com.Sakshi.UserManagementSystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserEventListener {
    @Autowired
    private EmailService emailService;

    @Async
    @EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        Users user = event.getUser();

        try {
            Context context = new Context();
            context.setVariable("user", user);
            context.setVariable(
                    "subject",
                    "Welcome to TechCorp - Account Created"
            );

            List<String> recipients = new ArrayList<>();
            recipients.add(user.getEmail());
            recipients.addAll(emailService.getHrEmails());

            emailService.sendEmail(
                    recipients,
                    "Welcome to TechCorp - Account Created",
                    "email/user-created",
                    context
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Something happenned", e);
        }
    }
    @Async
    @EventListener
    public void handleUserUpdated(UserUpdatedEvent event) {
        Context context = new Context();
        context.setVariable("user", event.getUser());
        context.setVariable("changes", event.getChangedFields());

        emailService.sendEmail(
                emailService.getUpdateRecipients(event.getUser()),
                "Account Updated",
                "email/user-updated",
                context
        );
    }
    @Async
    @EventListener
    public void handleUserDeleted(UserDeletedEvent event) {
        Context context = new Context();
        context.setVariable("user", event.getUser());

        emailService.sendEmail(
                emailService.getDeleteRecipients(event.getUser()),
                "Account Deactivated",
                "email/user-deleted",
                context
        );
    }
}
