package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class EmailService implements NotificationService {

    private final List<String> messages = new ArrayList<>();
    private final SystemLogger logger = SystemLogger.getInstance();

    public void send(String message) {
        logger.info("Email sent: " + message);
    }

    public void receive(String message) {
        messages.add(message);
        logger.info("Email received: " + message);
    }

    public void display() {
        logger.info("Displaying Email inbox");

        if (messages.isEmpty()) {
            logger.warning("No emails available");
            return;
        }

        for (String msg : messages) {
            logger.info("Email -> " + msg);
        }
    }
}
