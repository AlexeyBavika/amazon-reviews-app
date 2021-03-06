package com.example.amazon.reviews.controller;

import com.example.amazon.reviews.model.Role;
import com.example.amazon.reviews.model.User;
import com.example.amazon.reviews.service.RoleService;
import com.example.amazon.reviews.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InjectController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public InjectController(UserService userService, RoleService roleService,
                            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void injectData() {
        injectRoles();
        injectAdmin();
    }

    private void injectRoles() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.saveRole(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.saveRole(userRole);
    }

    private void injectAdmin() {
        User admin = new User();
        admin.setProfileName("Admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setRoles(Set.of(roleService.getByRoleName(Role.RoleName.ADMIN)));
        userService.save(admin);
    }
}
