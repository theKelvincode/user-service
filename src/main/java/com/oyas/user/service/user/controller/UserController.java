package com.oyas.user.service.user.controller;


import com.oyas.user.service.user.domain.entity.User;
import com.oyas.user.service.user.dto.UserDTO;
import com.oyas.user.service.user.dto.UserDto;
import com.oyas.user.service.user.exception.UserNotFoundException;
import com.oyas.user.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.oyas.user.service.generics.service.IGenericService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = (List<User>) this.userService.getAllUsers();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = Optional.ofNullable(userService.getUserById(id).
                orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found")));

        User user = optionalUser.get();

        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable Long id, @RequestBody UserDto userDto) {

        Optional<User> optionalUser = Optional.ofNullable(userService.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found")));

        User user = optionalUser.get();

        // Update user fields
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());

        userService.updateUser(user);

        return ResponseEntity.ok("User updated successfully");
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<UserDto> getLoggedInUser(@AuthenticationPrincipal UserDetails userDetails) {

        // extract username from logged-in user and fetch from db and return
        Optional<User> optionalUser = userService.findUserByEmail(userDetails.getUsername());

        return optionalUser.map(user -> {
            UserDto userDto = new UserDto(user.getId(), user.getFirstName(), user.getLastName(),
                    user.getUsername(), user.getEmail(), user.getRole());
            return ResponseEntity.ok(userDto);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<User> optionalUser =
                Optional.ofNullable(userService.getUserById(id).orElseThrow(() -> new UserNotFoundException("USer with ID " + id + " not found")));

        User user = optionalUser.get();

        userService.deleteUser(user);

        return ResponseEntity.ok("User updated successfully");
    }
}