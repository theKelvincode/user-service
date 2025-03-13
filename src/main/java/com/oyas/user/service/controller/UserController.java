package com.oyas.user.service.controller;

import com.oyas.user.service.dto.RegisterUserDto;
import com.oyas.user.service.domain.entity.User;
import com.oyas.user.service.dto.UserDto;
import com.oyas.user.service.exception.user.UserNotFoundException;
import com.oyas.user.service.role.domain.dao.RoleDAO;
import com.oyas.user.service.role.domain.entity.RoleTypeEnum;
import com.oyas.user.service.role.domain.entity.UserRole;
import com.oyas.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleDAO roleDAO;

    @GetMapping("")
    ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();

        System.out.println(allUsers);

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable Long id, @RequestBody UserDto userDto) throws Exception {

        // check if role exists.
        RoleTypeEnum roleName = RoleTypeEnum.valueOf(userDto.getRole());
        Optional<UserRole> userRole = roleDAO.findByName(roleName);

        if (userRole.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role: " + userDto.getRole() + "does not exist!.");
        }

        Optional<User> optionalUser = Optional.ofNullable(userService.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found")));

        User user = optionalUser.get();

        // Update user fields
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRole(userRole.get());
        userService.updateUser(user);

        return ResponseEntity.ok("User updated successfully.");
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = (userService.getUserById(id));

        if (optionalUser.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with Id: " + id + " not found.");

        User user = optionalUser.get();

        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().toString()
        );
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<User> optionalUser =
                Optional.ofNullable(userService.getUserById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found.")));

        User user = optionalUser.get();

        userService.deleteUser(user);

        return ResponseEntity.ok("User:  " + user.getId() + " deleted successfully.");
    }

    // auth is managed by another service, so find another way to get logged in user from db for this api.
//    @GetMapping("/me")
//    public ResponseEntity<UserDto> getLoggedInUser(@AuthenticationPrincipal UserDetails userDetails) {
//
//        // extract username from logged-in user and fetch from db and return
//        Optional<User> optionalUser = userService.findUserByEmail(userDetails.getUsername());
//
//        return optionalUser.map(user -> {
//            UserDto userDto = new UserDto(user.getId(), user.getFirstName(), user.getLastName(),
//                    user.getUsername(), user.getEmail(), user.getRole());
//            return ResponseEntity.ok(userDto);
//        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
//    }


    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) throws Exception {
        User registeredUser = userService.createUser(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }
}