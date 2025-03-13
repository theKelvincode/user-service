package com.oyas.user.service.domain.bootstrap;

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
        loadRolesIntoDB();
    }

    private void loadRolesIntoDB() {

        RoleTypeEnum[] roleNames = {
                RoleTypeEnum.USER, RoleTypeEnum.ADMIN, RoleTypeEnum.SUPER_ADMIN
        };

        Map<RoleTypeEnum, String> roleDescriptionMap = Map.of(
                RoleTypeEnum.USER, "Default user role",
                RoleTypeEnum.ADMIN, "Administrator role",
                RoleTypeEnum.SUPER_ADMIN, "Super Administrator role"
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
