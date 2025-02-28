package com.oyas.user.service.user.service;

import com.oyas.user.service.generics.service.IGenericService;
import com.oyas.user.service.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IGenericService genericService;

    public List<User> getAllUsers() {
        return (List<User>) this.genericService.fetchAllObjects(User.class);
    }

    public User getUserById(Long id) {
        return this.genericService.findObjectById(User.class, id);
    }
}