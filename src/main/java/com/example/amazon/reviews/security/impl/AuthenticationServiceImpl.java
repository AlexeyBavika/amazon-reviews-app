package com.example.amazon.reviews.security.impl;

import com.example.amazon.reviews.model.Role;
import com.example.amazon.reviews.model.User;
import com.example.amazon.reviews.security.AuthenticationService;
import com.example.amazon.reviews.service.RoleService;
import com.example.amazon.reviews.service.UserService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     PasswordEncoder passwordEncoder,
                                     RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User register(String profileName, String password) {
        User user = new User();
        user.setProfileName(profileName);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of(roleService.getByRoleName(Role.RoleName.USER)));
        userService.save(user);
        return user;
    }
}
