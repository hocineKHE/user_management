package com.project.demo.exception;

import com.project.demo.entities.ExceptionMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class GlobalException {

    private final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler({UserException.class})
    public ResponseEntity<ExceptionMessage> handleUserException(UserException e) {
        logger.debug("ERROR::USER EXCEPTION {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage
                        .builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler({EmailDuplicationException.class})
    public ResponseEntity<ExceptionMessage> handleEmailException(EmailDuplicationException e) {
        logger.debug("ERROR::EMAIL EXCEPTION {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage
                        .builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler({IllegalAddressException.class})
    public ResponseEntity<ExceptionMessage> handleAddressException(IllegalAddressException e) {
        logger.debug("ERROR::ADDRESS EXCEPTION {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage
                        .builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler({illegalAgeException.class})
    public ResponseEntity<ExceptionMessage> handleAgeException(illegalAgeException e) {
        logger.debug("ERROR::ADDRESS EXCEPTION {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage
                        .builder()
                        .message(e.getMessage())
                        .build());
    }
}
