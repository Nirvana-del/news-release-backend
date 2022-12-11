package com.wq.entity;

import javax.annotation.Resource;
import java.util.List;
@Resource
public class Rights {
    private Integer id;

    private String label;

    private String path;

    private Integer pagePermission;

    private Integer grade;

    private List<SubRights> children;

    public List<SubRights> getChildren() {
        return children;
    }

    public void setChildren(List<SubRights> children) {
        this.children = children;
    }

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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Rights{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", path='" + path + '\'' +
                ", pagePermission=" + pagePermission +
                ", grade=" + grade +
                ", children=" + children +
                '}';
    }
}