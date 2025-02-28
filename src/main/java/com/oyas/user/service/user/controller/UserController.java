package com.oyas.user.service.user.controller;


import com.oyas.user.service.user.domain.entity.User;
import com.oyas.user.service.user.dto.UserDTO;
import com.oyas.user.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oyas.user.service.generics.service.IGenericService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // fetch all users
    @GetMapping("")
    ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = (List<User>) this.userService.getAllUsers();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = this.userService.getUserById(id);

        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                null,
                user.getRoles()
        );

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    //TODO: register user, update user, delete user
}

