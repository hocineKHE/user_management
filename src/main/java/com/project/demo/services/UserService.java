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


/**
 * UserService class is a layer between a ressources and controllers
 */
@Service
@AllArgsConstructor
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * register service used to register a user and return that user,
     * in this method, i call two methods to check the Age and the Country,
     * convert userDTO to a user with userMapper and save it in the DB after that return userDTO
     *
     * @param userDTO
     * @return UserDTO
     */
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

    /**
     * findAll service used to get all users stored in the DB
     * in this method, i call userRepository method to get all users,
     * i converted the result to Stream and every user to userDTO
     *
     * @return Set<UserDTO>
     */
    public Set<UserDTO> findAll() {
        log.debug("RESOURCE::REQUEST TO FIN ALL USERS");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toSet());
    }


    /**
     * findById service used to get user by id stored in the DB
     * in this method, i call userRepository method to get Optionnel(user),
     * i converted user to userDTO and throw an exception if user not exist
     *
     * @return UserDTO
     * @throws UserException
     */
    public UserDTO findById(String id) {
        log.debug("RESOURCE::REQUEST TO FIND USER BY ID");
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UserException("error.user.notfound"));
    }


    /**
     * checkEmailDuplication method to check email duplication in the db
     * and throw an exception if the email exist
     *
     * @param user
     * @return User
     * @throws EmailDuplicationException
     */
    private User checkEmailDuplication(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailDuplicationException("error.email.duplication");
        }
        return user;
    }

    /**
     * checkAddress method to check address.country if is France or not
     * and throw an exception if is not 'FRANCE'
     *
     * @param user
     * @return User
     * @throws IllegalAddressException
     */
    private User checkAddress(User user) {
        if (!user.getAddress().getCountry().equalsIgnoreCase("FRANCE")) {
            throw new IllegalAddressException("error.user.address.country.illegal");
        }
        return user;
    }

    /**
     * checkAge method to check Age if is more than 18
     * and throw an exception if is not
     *
     * @param user
     * @return User
     * @throws illegalAgeException
     */
    private User checkAge(User user) {
        if (user.getAge() <= 18) {
            throw new illegalAgeException("error.user.age.illegal");
        }
        return user;
    }
}
