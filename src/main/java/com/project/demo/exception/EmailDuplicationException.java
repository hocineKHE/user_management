package com.project.demo.exception;

public class EmailDuplicationException extends RuntimeException {
    public EmailDuplicationException(String s) {
        super(s);
    }
}
