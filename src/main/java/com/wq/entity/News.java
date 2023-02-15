package com.wq.entity;

import javax.annotation.Resource;

@Resource
public class News {
    private String id;
    private String label;
    private String author;
    private Integer categoryId;
    private String region;
    private Integer roleId;
    private Integer auditState;
    private Integer publishState;
    private Integer view;
    private Integer star;
    private String createTime;
    private String publishTime;
    private String content;
    /**
     * 联表 category
     */
    private Category category;
    /**
     * 联表 role
     */
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public Integer getAuditState() {
        return auditState;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    public Integer getPublishState() {
        return publishState;
    }

    public void setPublishState(Integer publishState) {
        this.publishState = publishState;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime == null ? null : publishTime.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", author='" + author + '\'' +
                ", categoryId=" + categoryId +
                ", region='" + region + '\'' +
                ", roleId=" + roleId +
                ", auditState=" + auditState +
                ", publishState=" + publishState +
                ", view=" + view +
                ", star=" + star +
                ", createTime='" + createTime + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", content='" + content + '\'' +
                ", category=" + category +
                ", role=" + role +
                '}';
    }
}