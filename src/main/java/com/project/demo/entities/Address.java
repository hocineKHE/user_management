package com.project.demo.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("address")
public class Address {
    /**
     * used lombok to generate constructor, getter, setter and builder
     */

    private String street;
    private String country;
}
