package com.project.demo.services;

import com.project.demo.DTO.UserDTO;
import com.project.demo.DTO.UserMapper;
import com.project.demo.entities.User;
import com.project.demo.exception.EmailDuplicationException;
import com.project.demo.exception.IllegalAddressException;
import com.project.demo.exception.UserException;
import com.project.demo.exception.illegalAgeException;
import com.project.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO register(UserDTO userDTO) {
        log.debug("SERVICE::REQUEST TO REGISTER USER");
        return Optional.of(userDTO)
                .map(userMapper::toModel)
                .map(this::checkEmailDuplication)
                .map(this::checkAge)
                .map(this::checkAddress)
                .map(userRepository::save)
                .map(userMapper::toDTO)
                .orElse(null);

    }

    public Set<UserDTO> findAll() {
        log.debug("RESOURCE::REQUEST TO FIN ALL USERS");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public UserDTO findById(String id) {
        log.debug("RESOURCE::REQUEST TO FIND USER BY ID");
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UserException("error.user.notfound"));
    }


    private User checkEmailDuplication(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailDuplicationException("error.email.duplication");
        }
        return user;
    }

    private User checkAddress(User user) {
        if (!user.getAddress().getCountry().equalsIgnoreCase("FRANCE")) {
            throw new IllegalAddressException("error.user.address.country.illegal");
        }
        return user;
    }

    private User checkAge(User user) {
        if (user.getAge() <= 18) {
            throw new illegalAgeException("error.user.age.illegal");
        }
        return user;
    }
}
