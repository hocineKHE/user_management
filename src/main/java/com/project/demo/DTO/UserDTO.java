package com.project.demo.DTO;

import com.project.demo.entities.Address;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    @NotBlank(message = "error.email.required")
    private String email;
    private int age;
    private Address address;
}
