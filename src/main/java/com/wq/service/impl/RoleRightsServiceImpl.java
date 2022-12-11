package com.wq.service.impl;

import com.wq.entity.RoleRightsExample;
import com.wq.entity.RoleRightsKey;
import com.wq.mapper.RoleRightsMapper;
import com.wq.service.RoleRightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleRightsServiceImpl implements RoleRightsService {
    @Autowired
    RoleRightsMapper roleRightsMapper;

    /**
     * 获取 role <==> rights 映射关系
     * @return
     */
    @Override
    public List<RoleRightsKey> getRoleRightsKey() {
        RoleRightsExample roleRightsExample = new RoleRightsExample();
       return roleRightsMapper.selectByExample(roleRightsExample);
    }

    /**
     * 删除 role <==> rights 映射关系
     * @param roleRightsKey
     */
    @Override
    public void deleteRoleRightsKey(RoleRightsKey roleRightsKey) {
        roleRightsMapper.deleteByPrimaryKey(roleRightsKey);
    }

    /**
     * 添加 role <==> rights 映射关系
     * @param roleRightsKey
     */
    @Override
    public void addRoleRightsKey(RoleRightsKey roleRightsKey) {
        Integer rightId = roleRightsKey.getRightId();
        Integer roleId = roleRightsKey.getRoleId();

        RoleRightsExample roleRightsExample = new RoleRightsExample();
        RoleRightsExample.Criteria criteria = roleRightsExample.createCriteria();
        criteria.andRightIdEqualTo(rightId);
        criteria.andRoleIdEqualTo(roleId);
        List<RoleRightsKey> roleRightsKeys = roleRightsMapper.selectByExample(roleRightsExample);
        // 先查询数据库， 如果不存在这条数据， 则插入到数据库中
        if (roleRightsKeys.size() == 0){
            roleRightsMapper.insert(roleRightsKey);
        }
    }
}
