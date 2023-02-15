package com.wq.service.impl;

import com.wq.entity.Rights;
import com.wq.entity.RightsExample;
import com.wq.entity.RoleRightsExample;
import com.wq.entity.RoleRightsKey;
import com.wq.mapper.RightsMapper;
import com.wq.mapper.RoleRightsMapper;
import com.wq.service.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RightsServiceImpl implements RightsService {
    @Autowired
    RightsMapper rightsMapper;

    @Autowired
    RoleRightsMapper roleRightsMapper;

    /**
     * 删除一级权限
     * @param id
     */
    @Override
    public void deleteRights(Integer id) {
        rightsMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新一级权限
     * @param rights
     */
    @Override
    public void updateRights(Rights rights) {
        rightsMapper.updateByPrimaryKeySelective(rights);
    }
    @Override
    public void changeRights(List<Rights> parentRights) {
        rightsMapper.notifyAll();
    }

    /**
     * 一级权限列表
     * @return
     */
    @Override
    public List<Rights> getRightsList() {
        RightsExample rightsExample = new RightsExample();
        List<Rights> rightsList = rightsMapper.selectByExample(rightsExample);
        return rightsList;
    }

    /**
     * 根据角色权限获取权限路径
     * @param roleId
     * @return
     */
    @Override
    public List<String> getRightsListByRole(Integer roleId) {
        System.out.println(roleId);
        // 先根据角色 ID 获取一级权限的 ID 列表
        RoleRightsExample roleRightsExample = new RoleRightsExample();
        RoleRightsExample.Criteria criteria = roleRightsExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<RoleRightsKey> roleRightsKeys = roleRightsMapper.selectByExample(roleRightsExample);
        System.out.println(roleRightsKeys);
        List<Integer> rightsIdList = roleRightsKeys.stream().map(RoleRightsKey::getRightId).collect(Collectors.toList());

//        System.out.println(rightsIdList);
        // 根据 ID 列表查询 rights 表的path字段并返回 path 字符串数组
        RightsExample rightsExample = new RightsExample();
        RightsExample.Criteria criteria1 = rightsExample.createCriteria();
        criteria1.andIdIn(rightsIdList);

        List<Rights> rightsList = rightsMapper.selectByExample(rightsExample);
        List<String> rightsPathList = rightsList.stream().map(Rights::getPath).collect(Collectors.toList());

        return rightsPathList;
    }


}
