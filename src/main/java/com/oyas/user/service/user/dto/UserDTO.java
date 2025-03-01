package com.oyas.user.service.user.dto;

import com.oyas.user.service.role.domain.entity.UserRole;

import java.util.Set;

public record UserDTO(long id, String firstName, String lastName, String username, String email, String password, UserRole role ) {
}
