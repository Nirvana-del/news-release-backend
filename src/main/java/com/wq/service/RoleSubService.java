package com.wq.service;

import com.wq.entity.RoleSubKey;

import java.util.List;

public interface RoleSubService {
    List<RoleSubKey> getRoleSubKey();

    void deleteRoleSubKey(RoleSubKey RoleSubKey);

    void addRoleSubKey(RoleSubKey RoleSubKey);
}
