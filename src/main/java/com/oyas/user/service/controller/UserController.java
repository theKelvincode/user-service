package com.oyas.user.service.controller;


import com.oyas.user.service.domain.entity.User;
import com.oyas.user.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oyas.user.service.service.IGenericService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users/")
public class UserController {

    private final IGenericService genericService;

    @Autowired
    public UserController(IGenericService genericService) {
        this.genericService = genericService;
    }

    // fetch all users
    @GetMapping("")
    ResponseEntity<List<User>> fetchAllUsers() {
        List<User> allUsers = (List<User>) this.genericService.fetchAllObjects(User.class);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = this.genericService.findObjectById(User.class, id);

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
}