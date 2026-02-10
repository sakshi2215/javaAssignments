package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class SmsService implements NotificationService {

    private final List<String> messages = new ArrayList<>();
    private final SystemLogger logger = SystemLogger.getInstance();

    @Override
    public void send(String message) {
        logger.info("SMS sent: " + message);
    }

    @Override
    public void receive(String message) {
        messages.add(message);
        logger.info("SMS received: " + message);
    }

    @Override
    public void display() {
        logger.info("Displaying SMS inbox");

        if (messages.isEmpty()) {
            logger.warning("No SMS available");
            return;
        }

        for (String msg : messages) {
            logger.info("SMS -> " + msg);
        }
    }
}
