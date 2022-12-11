package com.wq.service.impl;

import com.wq.entity.RoleSubExample;
import com.wq.entity.RoleSubKey;
import com.wq.mapper.RoleSubMapper;
import com.wq.service.RoleSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleSubServiceImpl implements RoleSubService {
    @Autowired
    RoleSubMapper roleSubMapper;

    /**
     * 获取 role <==> sub_rights 映射关系
     * @return
     */
    @Override
    public List<RoleSubKey> getRoleSubKey() {
        RoleSubExample RoleSubExample = new RoleSubExample();
       return roleSubMapper.selectByExample(RoleSubExample);
    }

    /**
     * 删除 role <==> sub_rights 映射关系
     * @param RoleSubKey
     */
    @Override
    public void deleteRoleSubKey(RoleSubKey RoleSubKey) {
        roleSubMapper.deleteByPrimaryKey(RoleSubKey);
    }

    /**
     * 添加 role <==> sub_rights 映射关系
     * @param RoleSubKey
     */
    @Override
    public void addRoleSubKey(RoleSubKey RoleSubKey) {
        Integer subId = RoleSubKey.getSubId();
        Integer roleId = RoleSubKey.getRoleId();

        RoleSubExample RoleSubExample = new RoleSubExample();
        RoleSubExample.Criteria criteria = RoleSubExample.createCriteria();
        criteria.andSubIdEqualTo(subId);
        criteria.andRoleIdEqualTo(roleId);

        List<RoleSubKey> RoleSubKeys = roleSubMapper.selectByExample(RoleSubExample);
        // 先查询数据库， 如果不存在这条数据， 则插入到数据库中
        if (RoleSubKeys.size() == 0){
            roleSubMapper.insert(RoleSubKey);
        }
    }
}
