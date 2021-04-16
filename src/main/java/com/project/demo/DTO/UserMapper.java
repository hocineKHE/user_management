package com.project.demo.DTO;

import com.project.demo.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(UserDTO userDTO);

    UserDTO toDTO(User user);
}
