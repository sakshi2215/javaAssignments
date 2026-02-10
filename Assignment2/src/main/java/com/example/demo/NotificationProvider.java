package com.example.demo;

public class NotificationProvider {

    public static NotificationService createNotification(String type) {

        if (type == null) {
            throw new IllegalArgumentException("Notification type is required");
        }

        switch (type.toUpperCase()) {
            case "EMAIL":
                return new EmailService();
            case "SMS":
                return new SmsService();
            case "PUSH":
                return new PushService();
            default:
                throw new IllegalArgumentException("Invalid notification type");
        }
    }
}

