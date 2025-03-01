package com.oyas.user.service.user.service;

import com.oyas.user.service.auth.dto.RegisterUserDto;
import com.oyas.user.service.generics.service.IGenericService;
import com.oyas.user.service.role.domain.dao.RoleDAO;
import com.oyas.user.service.role.domain.entity.RoleTypeEnum;
import com.oyas.user.service.role.domain.entity.UserRole;
import com.oyas.user.service.user.domain.dao.UserDAO;
import com.oyas.user.service.user.domain.entity.User;
import com.oyas.user.service.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IGenericService genericService;

    private final RoleDAO roleDAO;

    private final UserDAO userDAO;

    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return (List<User>) this.genericService.fetchAllObjects(User.class);
    }

    public Optional<User> findUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(this.genericService.findObjectById(User.class, id)).orElseThrow(
                () -> new UserNotFoundException("User with ID " + id + "not found"));
    }

    public User createAdministrator(RegisterUserDto newUser) throws Exception{

        Optional<UserRole> optionalRole = roleDAO.findByName(RoleTypeEnum.ROLE_ADMIN);

        if (optionalRole.isEmpty()) {
            throw new Exception("User role not found");
        }

        var user = new User(
                newUser.firstName(),
                newUser.lastName(),
                newUser.userName(),
                newUser.email(),
                passwordEncoder.encode(newUser.password()),
                optionalRole.get()
        );

        return this.genericService.saveObject(user);
    }

    public void updateUser(User user) {
         genericService.updateObject(user);
    }

    public void deleteUser(User user) {
        genericService.deleteObject(user);
    }
}