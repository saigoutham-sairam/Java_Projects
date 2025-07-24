package com.example.multithreading.controller;

public
class Singleton {

    //Lazy Initialization with Synchronization (Thread-safe but slow):
    private static Singleton instance;

    public static synchronized Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    // Best Practice – Bill Pugh Singleton (Thread-safe & Fast):
    private
    Singleton() {
        // private constructor to prevent instantiation
    }

    private static class Holder{
        // Static inner class - inner classes are not loaded until they are referenced.
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getSingletonInstance(){
        return Holder.INSTANCE;
    }



}
