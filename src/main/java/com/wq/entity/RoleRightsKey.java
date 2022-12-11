package com.wq.entity;

import javax.annotation.Resource;

@Resource
public class RoleRightsKey {
    private Integer roleId;

    private Integer rightId;

    public RoleRightsKey(Integer roleId, Integer rightId) {
        this.roleId = roleId;
        this.rightId = rightId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    @Override
    public String toString() {
        return "RoleRightsKey{" +
                "roleId=" + roleId +
                ", rightId=" + rightId +
                '}';
    }
}