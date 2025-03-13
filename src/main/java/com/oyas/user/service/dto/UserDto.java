package com.oyas.user.service.dto;

import com.oyas.user.service.role.domain.entity.UserRole;
import lombok.Getter;


@Getter
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String role;

    public UserDto(long id, String firstName, String lastName, String username,
                   String email, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
