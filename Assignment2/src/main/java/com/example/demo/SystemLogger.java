package com.example.demo;

import java.time.LocalDateTime;

public class SystemLogger {

    private SystemLogger() {
    }

    //creating new instance
    private static class LoggerHolder {
        private static final SystemLogger INSTANCE = new SystemLogger();
    }
    //Function to get Instance
    public static SystemLogger getInstance() {
        return LoggerHolder.INSTANCE;
    }

    private void log(String level, String message) {
        System.out.println(
                LocalDateTime.now() + " [" + level + "] " + message
        );
    }

    public void info(String message) {
        log("INFO", message);
    }

    public void warning(String message) {
        log("WARNING", message);
    }
}
