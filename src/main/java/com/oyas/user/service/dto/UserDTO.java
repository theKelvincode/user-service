package com.oyas.user.service.dto;

import com.oyas.user.service.domain.entity.UserRole;

import java.util.Set;

public record UserDTO(long id, String firstName, String lastName, String username, String email, String password, Set<UserRole> roles ) {
}
