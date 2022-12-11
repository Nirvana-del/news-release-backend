package com.wq.entity;

import javax.annotation.Resource;

@Resource
public class SubRights {
    private Integer id;

    private String label;

    private Integer rightId;

    private String path;

    private Integer pagePermission;

    private Integer routePermission;

    private Integer grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getPagePermission() {
        return pagePermission;
    }

    public void setPagePermission(Integer pagePermission) {
        this.pagePermission = pagePermission;
    }

    public Integer getRoutePermission() {
        return routePermission;
    }

    public void setRoutePermission(Integer routePermission) {
        this.routePermission = routePermission;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "SubRights{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", rightId=" + rightId +
                ", path='" + path + '\'' +
                ", pagePermission=" + pagePermission +
                ", routePermission=" + routePermission +
                ", grade=" + grade +
                '}';
    }
}