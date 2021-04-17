package com.project.demo;

import com.project.demo.DTO.UserDTO;
import com.project.demo.DTO.UserMapper;
import com.project.demo.entities.Address;
import com.project.demo.entities.User;
import com.project.demo.repository.UserRepository;
import com.project.demo.services.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Test
    void should_register_user() {

        UserDTO userDTO = getUserDTO();
        User user = getUser();

        //mocking the save of the user
        BDDMockito.given(userRepository.save(user))
                .willReturn(user);

        /**
         * Mocking the user mapper : BEGIN
         */
        BDDMockito.given(userMapper.toModel(userDTO))
                .willReturn(user);

        BDDMockito.given(userMapper.toDTO(user))
                .willReturn(userDTO);
        /**
         * Mocking the user mapper : END
         */

        UserDTO register = userService.register(userDTO);

        //check the nullability
        Assertions.assertThat(register).isNotNull();

        //check the equalization
        assertEquals(userDTO.getFirstName(), user.getFirstName());

        //verify that user repository is invoked
        Mockito.verify(userRepository).save(user);

    }

    @Test
    void should_return_all_users() {

        User user = getUser();

        List<User> users = new ArrayList<>();

        users.add(user);

        //mocking the save of the user
        BDDMockito.given(userRepository.findAll())
                .willReturn(users);

        Set<UserDTO> usersSet = userService.findAll();

        //check the nullability
        Assertions.assertThat(usersSet).isNotNull();

        //check the size
        assertEquals(usersSet.size(), 1);


        //verify that user repository is invoked
        Mockito.verify(userRepository).findAll();
    }

    @Test
    void should_find_by_id() {
        User user = getUser();

        BDDMockito.given(userRepository.findById(Mockito.anyString()))
                .willReturn(Optional.of(user));

        //check the nullability
        Assertions.assertThat(user).isNotNull();

        //check the validate
        assertEquals(user.getEmail(), "hocine@gmail.com");
        assertEquals(user.getAge(), 25);

    }

    private UserDTO getUserDTO() {
        return  UserDTO.builder()
                .age(21)
                .firstName("Hocine")
                .lastName("Kheddadji")
                .email("hocine@gmail.com")
                .address(Address.builder().street("7 run pierre curie").country("FRANCE").build())
                .build();
    }

    private User getUser() {
        return User.builder()
                .age(25)
                .firstName("Hocine")
                .lastName("Kheddadji")
                .email("hocine@gmail.com")
                .address(Address.builder().street("7 run pierre curie").country("FRANCE").build())
                .build();
    }
}
