package com.barismutlu.todo.exception;

public class TodoNotFoundException extends RuntimeException {

    //kurucu metot, özel hata mesajların tanımlanması.
    public TodoNotFoundException(String message) {
        super(message);
    }
}
