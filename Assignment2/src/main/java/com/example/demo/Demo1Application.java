package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {

       // SpringApplication.run(Demo1Application.class, args);

        // Email
        NotificationService email =
                NotificationProvider.createNotification("EMAIL");
        email.send("Welcome via Email");
        email.receive("Email: Your order has been shipped");
        email.display();

        System.out.println("------------------------------");

        // SMS
        NotificationService sms =
                NotificationProvider.createNotification("SMS");
        sms.send("OTP sent via SMS");
        sms.receive("SMS: Your OTP is 123456");
        sms.display();

        System.out.println("------------------------------");

        // Push
        NotificationService push =
                NotificationProvider.createNotification("PUSH");
        push.send("New update available");
        push.receive("Push: App update downloaded");
        push.display();
    }
}
