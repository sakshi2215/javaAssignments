package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class PushService implements NotificationService {

    private final List<String> messages = new ArrayList<>();
    private final SystemLogger logger = SystemLogger.getInstance();

    @Override
    public void send(String message) {
        logger.info("Push notification sent: " + message);
    }

    @Override
    public void receive(String message) {
        messages.add(message);
        logger.info("Push notification received: " + message);
    }

    @Override
    public void display() {
        logger.info("Displaying Push notifications");

        if (messages.isEmpty()) {
            logger.warning("No push notifications available");
            return;
        }

        for (String msg : messages) {
            logger.info("Push -> " + msg);
        }
    }
}
