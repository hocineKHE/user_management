package com.project.demo.exception;

public class IllegalAddressException extends RuntimeException {
    /**
     * illegal Address exception handling
     */
    public IllegalAddressException(String s) {
        super(s);
    }
}
