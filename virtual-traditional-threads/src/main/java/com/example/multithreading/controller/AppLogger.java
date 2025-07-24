package com.example.multithreading.controller;
/*
Suggested Use cases for a singleton Class
Logger class, DB configurations, general configurations, Payment processors
*/

import lombok.Synchronized;

public
class AppLogger {

    private static AppLogger instance;

    private
    AppLogger() {
        // private Constructor
    }

    private static
    class SingletonHelper {
        //This is the only time the class is getting initialised.
        private static final AppLogger INSTANCE = new AppLogger();
    }

    public
    static AppLogger getInstance() {
        return SingletonHelper.INSTANCE;
    }


    public
    void log(String msg) {
        System.out.println("AppLogger : " + msg);
    }
}