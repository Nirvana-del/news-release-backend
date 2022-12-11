package com.wq.util;

import com.wq.entity.*;
import com.wq.service.impl.CategoriesServiceImpl;
import com.wq.service.impl.RightsServiceImpl;
import com.wq.service.impl.RolesServiceImpl;
import com.wq.service.impl.SubRightsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Component
public class Tool {
    private static Tool tool;

    @PostConstruct
    private void init() {
        tool = this;
        tool.categoriesServiceImpl = this.categoriesServiceImpl;
        tool.rolesServiceImpl = this.rolesServiceImpl;
        tool.rightsServiceImpl = this.rightsServiceImpl;
        tool.subRightsServiceImpl = this.subRightsServiceImpl;
    }
    @Autowired
    CategoriesServiceImpl categoriesServiceImpl;
    @Autowired
    RolesServiceImpl rolesServiceImpl;
    @Autowired
    RightsServiceImpl rightsServiceImpl;
    @Autowired
    SubRightsServiceImpl subRightsServiceImpl;

    /**
     * 联表 ：category,news
     * 新闻列表
     * @param newsList
     */
    public static void newsExpandCategory(List<News> newsList) {
        for(News news : newsList){
            Integer id = news.getCategoryId();
            Category category = tool.categoriesServiceImpl.getCategory(id);
            news.setCategory(category);
        }
    }

    /**
     * 联表 ：category,news
     * 新闻列表
     * @param news
     */
    public static void newsExpandCategory(News news) {
            Integer id = news.getCategoryId();
            Category category = tool.categoriesServiceImpl.getCategory(id);
            news.setCategory(category);
    }

    /**
     * 联表 ：role,news
     * 新闻列表
     * @param newsList
     */
    public static void newsExpandRole(List<News> newsList) {
        for(News news : newsList){
            Integer id = news.getRoleId();
            Role role = tool.rolesServiceImpl.getRole(id);
            news.setRole(role);
        }

    }

    /**
     * 联表 ：role,news
     * 单条新闻
     * @param news
     */
    public static void newsExpandRole(News news) {
        Integer id = news.getRoleId();
            Role role = tool.rolesServiceImpl.getRole(id);
        news.setRole(role);
    }

 /**
     * 联表 ：role,user
     * 用户列表
     * @param userList
     */
    public static void userExpandRole(List<User> userList) {
        for(User user : userList){
            Integer id = user.getRoleId();
            Role role = tool.rolesServiceImpl.getRole(id);
            user.setRole(role);
        }

    }

    /**
     * 联表 ：role,news
     * 单条新闻
     * @param user
     */
    public static void userExpandRole(User user) {
        Integer id = user.getRoleId();
        Role role = tool.rolesServiceImpl.getRole(id);
        user.setRole(role);
    }

    /**
     * 联表 ：rights, sub_rights
     * 单条一级权限
     * @param rights
     */
    public static void expandSubRights(Rights rights) {
            Integer id = rights.getId();
            List<SubRights> subRightsList = tool.subRightsServiceImpl.getSubRightsByParentId(id);
        rights.setChildren(subRightsList);
    }

   /**
     * 联表 ：rights, sub_rights
     * 一级权限列表
     * @param rightsList
     */
    public static void expandSubRights(List<Rights> rightsList) {
        for (Rights rights : rightsList) {
            Integer id = rights.getId();
            List<SubRights> subRightsList = tool.subRightsServiceImpl.getSubRightsByParentId(id);
            rights.setChildren(subRightsList);
        }
    }

    /**
     * 联表 ：role, rights, sub_rights
     * 角色列表
     * @param roleList
     */
    public static void roleExpandAllRights(List<Role> roleList) {
            Integer roleId;
        for (Role role : roleList) {
            roleId = role.getId();
            System.out.println(roleId);
            // 一级权限 path 列表
            List<String> rightsPathList = tool.rightsServiceImpl.getRightsListByRole(roleId);
            // 二级权限 path 列表
            List<String> subRightsPathList = tool.subRightsServiceImpl.getSubRightsListByRole(roleId);
            List<String> pathList = new ArrayList<>();
            pathList.addAll(rightsPathList);
            pathList.addAll(subRightsPathList);

            role.setPathList(pathList);
        }
    }

    /**
     * 联表 ：role, rights, sub_rights
     * 角色列表
     * @param role
     */
    public static void roleExpandAllRights(Role role) {
            Integer roleId = role.getId();
            // 一级权限 path 列表
            List<String> rightsPathList = tool.rightsServiceImpl.getRightsListByRole(roleId);
            // 二级权限 path 列表
            List<String> subRightsPathList = tool.subRightsServiceImpl.getSubRightsListByRole(roleId);
            List<String> pathList = new ArrayList<>();
            pathList.addAll(rightsPathList);
            pathList.addAll(subRightsPathList);

            role.setPathList(pathList);
    }

    /**
     * 解析富文本
     * 单条新闻
     * @param news
     */
    public static void htmlUnescape(News news) {
        String content = news.getContent();
        String returnHtml = HtmlUtils.htmlUnescape(content);
        news.setContent(returnHtml);
    }

    /**
     * 解析富文本
     * 新闻列表
     * @param newsList
     */
    public static void htmlUnescape(List<News> newsList) {
        for(News news : newsList){
            String content = news.getContent();
            String returnHtml = HtmlUtils.htmlUnescape(content);
            news.setContent(returnHtml);
        }
    }
}
