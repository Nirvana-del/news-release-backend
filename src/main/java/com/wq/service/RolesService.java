package com.wq.service;

import com.wq.entity.Role;

import java.util.List;

public interface RolesService {
    Role getRole(Integer id);

    List<Role> getRoleList();

    void deleteRole(Integer id);
}
