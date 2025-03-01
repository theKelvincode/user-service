package com.oyas.user.service.user.controller;

import com.oyas.user.service.auth.dto.RegisterUserDto;
import com.oyas.user.service.user.domain.entity.User;
import com.oyas.user.service.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> createAdministrator(@RequestBody RegisterUserDto newUser) throws Exception {
        User createdAdmin = userService.createAdministrator(newUser);

        return ResponseEntity.ok(createdAdmin);
    }
}
