package com.oyas.user.service.bootstrap;

import com.github.javafaker.Faker;
import com.oyas.user.service.user.domain.dao.UserDAO;
import com.oyas.user.service.user.domain.entity.User;
import com.oyas.user.service.role.domain.entity.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserSeeder  implements CommandLineRunner {

    private final UserDAO userDAO;

    private final Faker faker = new Faker();

    public UserSeeder(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        List<User> dummyUsers = new ArrayList<>();
        UserRole defaultRole = new UserRole();
        defaultRole.setId(1);

        Set<String> usedEmails = new HashSet<>();

        // we are loading 5k users into the db
        for (int i = 0; i < 5000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String username = faker.name().username();

            //ensure email is unique
            String email;
            do {
               email = faker.internet().emailAddress();
            } while(!usedEmails.add(email)); // loop until a unique email is found

            firstName = firstName.substring(0, Math.min(firstName.length(), 20));
            lastName = lastName.substring(0, Math.min(lastName.length(), 20));
            username = username.substring(0, Math.min(username.length(), 20));
            email = email.substring(0, Math.min(email.length(), 100));
            
            dummyUsers.add(new User(
                   firstName,
                    lastName,
                    username,
                    email,
                    "password123",
                    defaultRole
            ));
        }

        userDAO.saveAll(dummyUsers);
        System.out.println("Dummy users created successfully");
    }
}

