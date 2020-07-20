package com.example.amazon.reviews.service.impl;

import com.example.amazon.reviews.model.Role;
import com.example.amazon.reviews.repository.RoleRepository;
import com.example.amazon.reviews.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByRoleName(Role.RoleName roleName) {
        return roleRepository.getByRoleName(roleName);
    }
}
