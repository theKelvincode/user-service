package com.oyas.user.service.domain.dao;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDAO {

    private long id;

    private String username;

    private String email;

    private String password;

    private String roles;
}
