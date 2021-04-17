package com.project.demo.entities;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {

    /**
     * exception message model
     * used lombok to generate constructor, getter, setter and builder
     */
    private String message;
}
