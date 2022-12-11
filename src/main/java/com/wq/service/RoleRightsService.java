package com.wq.service;

import com.wq.entity.RoleRightsKey;

import java.util.List;

public interface RoleRightsService {
    List<RoleRightsKey> getRoleRightsKey();

    void deleteRoleRightsKey(RoleRightsKey roleRightsKey);

    void addRoleRightsKey(RoleRightsKey roleRightsKey);
}
