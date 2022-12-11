package com.wq.service.impl;

import com.wq.entity.*;
import com.wq.mapper.RoleSubMapper;
import com.wq.mapper.SubRightsMapper;
import com.wq.service.SubRightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubRightsServiceImpl implements SubRightsService {
    @Autowired
    SubRightsMapper subRightsMapper;

    @Autowired
    RoleSubMapper roleSubMapper;

    /**
     * 根据父级ID返回二级权限
     * @param id
     * @return
     */
    @Override
    public List<SubRights> getSubRightsByParentId(Integer id) {
        SubRightsExample subRightsExample = new SubRightsExample();
        SubRightsExample.Criteria criteria = subRightsExample.createCriteria();
        // 对应父级路由 ID 为 id
        criteria.andRightIdEqualTo(id);
        List<SubRights> subRightsList = subRightsMapper.selectByExample(subRightsExample);

        return subRightsList;
    }

    /**
     * 删除二级权限
     * @param id
     */
    @Override
    public void deleteSubRights(Integer id) {
        subRightsMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新二级权限
     * @param subRights
     */
    @Override
    public void updateSubRights(SubRights subRights) {
        subRightsMapper.updateByPrimaryKeySelective(subRights);
    }

    /**
     * 二级权限列表
     * @return
     */
    @Override
    public List<SubRights> getSubRightsList() {

        SubRightsExample subRightsExample = new SubRightsExample();
        List<SubRights> subRightsList = subRightsMapper.selectByExample(subRightsExample);
        return subRightsList;
    }

    /**
     * 根据角色权限获取权限路径
     * @param roleId
     * @return
     */
    @Override
    public List<String> getSubRightsListByRole(Integer roleId) {
        // 先根据角色 ID 获取二级权限的 ID 列表
        RoleSubExample roleSubExample = new RoleSubExample();
        RoleSubExample.Criteria criteria = roleSubExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<RoleSubKey> roleSubKeys = roleSubMapper.selectByExample(roleSubExample);
        List<Integer> subRightsIdList = roleSubKeys.stream().map(item -> item.getSubId()).collect(Collectors.toList());

        // 根据 ID 列表查询 sub_rights 表的path字段并返回 path 字符串数组
        SubRightsExample subRightsExample = new SubRightsExample();
        SubRightsExample.Criteria criteria1 = subRightsExample.createCriteria();
        criteria1.andIdIn(subRightsIdList);
        List<SubRights> rightsList = subRightsMapper.selectByExample(subRightsExample);
        List<String> subRightsPathList = rightsList.stream().map(item -> item.getPath()).collect(Collectors.toList());

        return subRightsPathList;
    }
}
