package com.wq.entity;

import javax.annotation.Resource;

@Resource
public class User {
    private String id;

    private String username;

    private String password;

    private Boolean roleState;

    private Boolean userDefault;

    private String region;

    private Integer roleId;

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getRoleState() {
        return roleState;
    }

    public void setRoleState(Boolean roleState) {
        this.roleState = roleState;
    }

    public Boolean getUserDefault() {
        return userDefault;
    }

    public void setUserDefault(Boolean userDefault) {
        this.userDefault = userDefault;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleState=" + roleState +
                ", userDefault=" + userDefault +
                ", region='" + region + '\'' +
                ", roleId=" + roleId +
                ", role=" + role +
                '}';
    }
}