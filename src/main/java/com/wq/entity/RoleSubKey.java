package com.wq.entity;

import javax.annotation.Resource;

@Resource
public class RoleSubKey {
    private Integer roleId;

    private Integer subId;

    public RoleSubKey(Integer roleId, Integer subId) {
        this.roleId = roleId;
        this.subId = subId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    @Override
    public String toString() {
        return "RoleSubKey{" +
                "roleId=" + roleId +
                ", subId=" + subId +
                '}';
    }
}