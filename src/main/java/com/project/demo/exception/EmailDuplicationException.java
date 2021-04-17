package com.project.demo.exception;

public class EmailDuplicationException extends RuntimeException {

    /**
     * Email duplication exception handling
     */
    public EmailDuplicationException(String s) {
        super(s);
    }
}
