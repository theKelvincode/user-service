package com.oyas.user.service.bootstrap;

import com.oyas.user.service.auth.dto.RegisterUserDto;
import com.oyas.user.service.role.domain.dao.RoleDAO;
import com.oyas.user.service.role.domain.entity.RoleTypeEnum;
import com.oyas.user.service.role.domain.entity.UserRole;
import com.oyas.user.service.user.domain.dao.UserDAO;
import com.oyas.user.service.user.domain.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final RoleDAO roleDAO;
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(RoleDAO roleDAO, UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.roleDAO = roleDAO;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // create two super admins
        List<RegisterUserDto> superAdmins = List.of(
                new RegisterUserDto("Kelvin", "Iseh", "kelvinIseh", "thekelvincode@gmail.com", passwordEncoder.encode("password123")),
                new RegisterUserDto("Jane", "Smith", "janesmith", "jane@example.com", passwordEncoder.encode("password123"))
        );
        createAdministrators(superAdmins,RoleTypeEnum.ROLE_SUPER_ADMIN);

        // create 4 admins
        List<RegisterUserDto> admins = List.of(
                new RegisterUserDto("John", "Doe", "johndoe", "john@example.com", passwordEncoder.encode("password123")),
                new RegisterUserDto("Alice", "Brown", "alicebrown", "alice@example.com", passwordEncoder.encode("password123")),
                new RegisterUserDto("Bob", "Johnson", "bobjohnson", "bob@example.com", passwordEncoder.encode("password123")),
                new RegisterUserDto("Charlie", "Davis", "charliedavis", "charlie@example.com", passwordEncoder.encode("password123"))
        );
        createAdministrators(admins, RoleTypeEnum.ROLE_ADMIN);
    }

    private void createAdministrators(List<RegisterUserDto> admins, RoleTypeEnum adminRole) {

        Optional<UserRole> optionalRole = roleDAO.findByName(adminRole);

        List<User> newAdmins = new ArrayList<>();
        for (RegisterUserDto newAdmin :  admins) {

            Optional<User> optionalUser = userDAO.findByEmail(newAdmin.email());

            if (optionalRole.isEmpty() || optionalUser.isPresent()) {
                continue;
            }

            newAdmins.add(new User(
                    newAdmin.firstName(),
                    newAdmin.lastName(),
                    newAdmin.userName(),
                    newAdmin.email(),
                    newAdmin.password(),
                    optionalRole.get()
            ));
        }

        userDAO.saveAll(newAdmins);
        System.out.println("dummy admins created successfully");
    }

}
