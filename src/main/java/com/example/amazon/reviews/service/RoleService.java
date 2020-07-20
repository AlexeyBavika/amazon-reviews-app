package com.example.amazon.reviews.service;

import com.example.amazon.reviews.model.Role;

public interface RoleService {

    Role saveRole(Role role);

    Role getByRoleName(Role.RoleName roleName);
}
