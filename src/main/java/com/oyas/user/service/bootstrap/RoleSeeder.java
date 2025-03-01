package com.oyas.user.service.bootstrap;

import com.oyas.user.service.role.domain.dao.RoleDAO;
import com.oyas.user.service.role.domain.entity.RoleTypeEnum;
import com.oyas.user.service.role.domain.entity.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
@Order(1)
public class RoleSeeder implements CommandLineRunner {

    private final RoleDAO roleDAO;

    public RoleSeeder(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        loadRoles();
    }

    private void loadRoles() {

        RoleTypeEnum[] roleNames = {
                RoleTypeEnum.ROLE_USER, RoleTypeEnum.ROLE_ADMIN, RoleTypeEnum.ROLE_SUPER_ADMIN
        };

        Map<RoleTypeEnum, String> roleDescriptionMap = Map.of(
                RoleTypeEnum.ROLE_USER, "Default user role",
                RoleTypeEnum.ROLE_ADMIN, "Administrator role",
                RoleTypeEnum.ROLE_SUPER_ADMIN, "Super Administrator role"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<UserRole> optionalRole = roleDAO.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                UserRole roleToCreate = new UserRole();

                roleToCreate.setName(roleName);
                roleToCreate.setDescription(roleDescriptionMap.get(roleName));

                roleDAO.save(roleToCreate);
            });
        });
    }

}
