package com.hostelsphere.app.service.impl;

import org.springframework.stereotype.Service;

import com.hostelsphere.app.entity.Role;
import com.hostelsphere.app.repository.RoleRepository;
import com.hostelsphere.app.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
