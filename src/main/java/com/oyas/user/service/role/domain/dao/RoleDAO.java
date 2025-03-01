package com.oyas.user.service.role.domain.dao;

import com.oyas.user.service.role.domain.entity.RoleTypeEnum;
import com.oyas.user.service.role.domain.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDAO extends CrudRepository<UserRole, Integer> {

    Optional<UserRole> findByName(RoleTypeEnum name);
}
