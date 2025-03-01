package com.oyas.user.service.auth.controllers;

import com.oyas.user.service.auth.domain.LoginResponse;
import com.oyas.user.service.auth.dto.LoginUserDto;
import com.oyas.user.service.auth.dto.RegisterUserDto;
import com.oyas.user.service.auth.service.AuthService;
import com.oyas.user.service.auth.service.JwtService;
import com.oyas.user.service.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) throws Exception {
        User registeredUser = authService.signUp(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(LoginUserDto loginUserDto) {

        User authenticatedUser = authService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }








}