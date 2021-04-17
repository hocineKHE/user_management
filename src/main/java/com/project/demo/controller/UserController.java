package com.project.demo.controller;

import com.project.demo.DTO.UserDTO;
import com.project.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    /**
     * register API end point to register a user and return that user
     *
     * @param userDTO
     * @return ResponseEntity<UserDTO>
     */
    @PostMapping
    private ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
        log.debug("RESOURCE::REQUEST TO REGISTER USER");
        UserDTO result = userService.register(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * findAll API end point to get all users
     *
     * @return ResponseEntity<Set < UserDTO>>
     */
    @GetMapping
    private ResponseEntity<Set<UserDTO>> findAll() {
        log.debug("RESOURCE::REQUEST TO FIN ALL USERS");
        Set<UserDTO> result = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * findAll API end point to get users by id
     *
     * @param id  'user id'
     * @return ResponseEntity<UserDTO>
     */
    @GetMapping("/{id}")
    private ResponseEntity<UserDTO> findById(@PathVariable("id") String id) {
        log.debug("RESOURCE::REQUEST TO FIND USER BY ID");
        UserDTO result = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
