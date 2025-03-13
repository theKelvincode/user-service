package com.oyas.user.service.domain.dao;

import com.oyas.user.service.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}