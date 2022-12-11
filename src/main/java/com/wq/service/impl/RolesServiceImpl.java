package com.wq.service.impl;

import com.wq.entity.Role;
import com.wq.entity.RoleExample;
import com.wq.mapper.RoleMapper;
import com.wq.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wq.util.Tool.htmlUnescape;

@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    RoleMapper roleMapper;

    /**
     * 根据ID获取角色信息
     * @param id
     * @return
     */
    @Override
    public Role getRole(Integer id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    /**
     * 获取角色列表
     * @return
     */
    @Override
    public List<Role> getRoleList() {
        RoleExample roleExample = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        return roleList;
    }
    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteByPrimaryKey(id);
    }
}
