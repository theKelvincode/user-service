//package com.oyas.user.service.domain.bootstrap;
//
//import com.github.javafaker.Faker;
//import com.oyas.user.service.domain.dao.UserDAO;
//import com.oyas.user.service.domain.entity.User;
//import com.oyas.user.service.role.domain.entity.UserRole;
//import jakarta.transaction.Transactional;
//import org.springframework.boot.CommandLineRunner;
////import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import java.util.ArrayList;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//




// THIS IS NOT NEEDED FOR NOW AS THERE ARE USERS IN THE DB


//@Component
//public class UserSeeder  implements CommandLineRunner {
//
//    private final UserDAO userDAO;
//
//    private final Faker faker = new Faker();
//
//    private final PasswordEncoder passwordEncoder;
//
//    public UserSeeder(UserDAO userDAO, PasswordEncoder passwordEncoder) {
//        this.userDAO = userDAO;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//
//        if (userDAO.count() <= 0) {
//
//            List<User> dummyUsers = new ArrayList<>();
//            UserRole defaultRole = new UserRole();
//            defaultRole.setId(1);
//
//            Set<String> usedEmails = new HashSet<>();
//
//            // load 5k users into the db
//            for (int i = 0; i < 5000; i++) {
//                String firstName = faker.name().firstName();
//                String lastName = faker.name().lastName();
//                String username = faker.name().username();
//
//                //ensure email is unique
//                String email;
//                do {
//                    email = faker.internet().emailAddress();
//                } while (!usedEmails.add(email)); // loop until a unique email is found
//
//                firstName = firstName.substring(0, Math.min(firstName.length(), 20));
//                lastName = lastName.substring(0, Math.min(lastName.length(), 20));
//                username = username.substring(0, Math.min(username.length(), 20));
//                email = email.substring(0, Math.min(email.length(), 100));
//
//                dummyUsers.add(new User(
//                        firstName,
//                        lastName,
//                        username,
//                        email,
//                        passwordEncoder.encode("password123"),
//                        defaultRole
//                ));
//
//                System.out.println("created new user.");
//            }
//
//            System.out.println("Seeding users now..." + dummyUsers.size());
//
//            try {
//                userDAO.saveAll(dummyUsers);
//                System.out.println("Users seeded successfully!");
//            } catch (Exception e) {
//                System.err.println("Error seeding users: " + e.getMessage());
//                e.printStackTrace(); // Add this to see full error details
//            }
//
//            // create admins on the fly and add them
//            UserRole adminRole = new UserRole();
//            adminRole.setId(2);
//
//            UserRole superAdminRole = new UserRole();
//            superAdminRole.setId(3);
//
//            List<User> dummyAdmins = List.of(
//                    // super admins
//                    new User("Kelvin", "Iseh", "kelvinIseh", "thekelvincode@gmail.com", passwordEncoder.encode("password123"), superAdminRole),
//                    new User("Jane", "Smith", "janesmith", "jane@example.com", passwordEncoder.encode("password123"), superAdminRole),
//                    //admins
//                    new User("John", "Doe", "johndoe", "john@example.com", passwordEncoder.encode("password123"), adminRole),
//                    new User("Alice", "Brown", "alicebrown", "alice@example.com", passwordEncoder.encode("password123"), adminRole),
//                    new User("Bob", "Johnson", "bobjohnson", "bob@example.com", passwordEncoder.encode("password123"), adminRole),
//                    new User("Charlie", "Davis", "charliedavis", "charlie@example.com", passwordEncoder.encode("password123"), adminRole)
//            );
//
//            try {
//                userDAO.saveAll(dummyAdmins);
//                System.out.println("admins seeded successfully!");
//            } catch (Exception e) {
//                System.err.println("Error seeding admins: " + e.getMessage());
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
