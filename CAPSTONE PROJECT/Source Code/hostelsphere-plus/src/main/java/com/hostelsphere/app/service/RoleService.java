package com.hostelsphere.app.service;

import com.hostelsphere.app.entity.Role;

public interface RoleService {

    Role saveRole(Role role);

    Role getRoleByName(String name);
}
