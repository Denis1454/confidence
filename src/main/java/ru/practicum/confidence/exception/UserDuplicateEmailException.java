package ru.practicum.confidence.exception;

public class UserDuplicateEmailException extends RuntimeException{
    public UserDuplicateEmailException(String message) {
        super(message);
    }
}
