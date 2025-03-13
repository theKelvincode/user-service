package com.oyas.user.service.dto;

import com.oyas.user.service.role.domain.entity.UserRole;

public record UserDTO(long id, String firstName, String lastName, String username, String email, String password, UserRole role ) {
}
