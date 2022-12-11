package com.wq.entity;

import javax.annotation.Resource;

@Resource
public class Category {
    private Integer id;

    private String label;

    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}