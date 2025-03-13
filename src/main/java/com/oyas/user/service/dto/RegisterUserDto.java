package com.oyas.user.service.dto;

public record RegisterUserDto(String firstName,
                              String lastName,
                              String userName,
                              String email,
                              String password) {
}
