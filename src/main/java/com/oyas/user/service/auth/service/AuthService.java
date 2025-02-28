package com.oyas.user.service.auth.service;

import com.oyas.user.service.auth.dto.LoginUserDto;
import com.oyas.user.service.auth.dto.RegisterUserDto;
import com.oyas.user.service.user.domain.dao.UserDAO;
import com.oyas.user.service.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDAO userDAO;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authManager;

    public User signUp(RegisterUserDto newUser) {

        User user = new User(
                newUser.firstName(),
                newUser.lastName(),
                newUser.userName(),
                newUser.email(),
                passwordEncoder.encode(newUser.password())
        );
        return userDAO.save(user);
    }

    public User authenticate(LoginUserDto loginUser) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.email(),
                        loginUser.password()
                )
        );
        return userDAO.findByEmail(loginUser.email()).orElseThrow();
    }
}
