package com.wq.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wq.entity.*;
import com.wq.service.impl.*;
import com.wq.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.wq.util.Tool.roleExpandAllRights;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "*")
public class RoleController {
    @Autowired
    RolesServiceImpl rolesServiceImpl;
    @Autowired
    RoleRightsServiceImpl roleRightsServiceImpl;
    @Autowired
    RoleSubServiceImpl roleSubServiceImpl;

    /**
     * 获取角色列表
     * @return
     */
    @GetMapping("/list")
    public Result<Map<String, List<Role>>> getRolesList(){
        try {
            List<Role> roleList = rolesServiceImpl.getRoleList();
            System.out.println(roleList);
            Map<String, List<Role>> map = new HashMap<>();
            roleExpandAllRights(roleList);
            System.out.println(roleList);
            map.put("roleList", roleList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<Map<String, String>> deleteRole(@PathVariable Integer id){
        try {
            Map<String, String> map = new HashMap<>();
            rolesServiceImpl.deleteRole(id);
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 更新角色权限
     * @param roleId 角色 ID
     * @param rightsMap 权限对象
     * @return
     */
    @PostMapping("/{roleId}")
    public Result<Map<String, String>> updateRoleRights(@PathVariable Integer roleId,@RequestBody JSONObject rightsMap){
        System.out.println(roleId);
        System.out.println(rightsMap);
        try {
            Map<String, String> map = new HashMap<>();

            // 得到一级权限列表
            JSONArray parentArr = rightsMap.getJSONArray("parentRights");
            String parentStr=JSONObject.toJSONString(parentArr, SerializerFeature.WriteClassName);
            List<Rights> parentRights = JSONObject.parseArray(parentStr, Rights.class);
            System.out.println(parentRights);

            // 得到二级权限列表
            JSONArray subArr = rightsMap.getJSONArray("subRights");
            String subStr=JSONObject.toJSONString(subArr, SerializerFeature.WriteClassName);
            List<SubRights> subRights = JSONObject.parseArray(subStr, SubRights.class);
            System.out.println(subRights);

            // 分别获取两个联表的集合
            List<RoleRightsKey> roleRightsKey = roleRightsServiceImpl.getRoleRightsKey();
            List<RoleSubKey> roleSubKey = roleSubServiceImpl.getRoleSubKey();

            // 先将要赋予的一级权限插入到数据库（去重）
            for (Rights parent : parentRights) {
                Integer rightsId = parent.getId();
                RoleRightsKey roleRights = new RoleRightsKey(roleId, rightsId);
                roleRightsServiceImpl.addRoleRightsKey(roleRights);
            }
            // 获取该角色的所有一级权限
            List<RoleRightsKey> roleRightsKeyStream = roleRightsKey.stream().filter(item -> item.getRoleId() == roleId).collect(Collectors.toList());
            System.out.println(roleRightsKeyStream);
            for (RoleRightsKey item : roleRightsKeyStream) {
                Integer rightId = item.getRightId();
                Optional<Rights> optionalRights = parentRights.stream().filter(parent -> parent.getId().equals(rightId)).findFirst();
                if(!optionalRights.isPresent()){
                    // 一级权限列表不存在这条权限， 则删除该权限
                    roleRightsServiceImpl.deleteRoleRightsKey(item);
                }
            }

            // 先将要赋予的二级权限插入到数据库（去重）
            for (SubRights sub : subRights) {
                Integer subRightsId = sub.getId();
                RoleSubKey roleSub = new RoleSubKey(roleId, subRightsId);
                roleSubServiceImpl.addRoleSubKey(roleSub);
            }

            // 获取该角色的所有二级权限
            List<RoleSubKey> roleSubKeyStream = roleSubKey.stream().filter(item -> item.getRoleId() == roleId).collect(Collectors.toList());
            System.out.println(roleSubKeyStream);
            for (RoleSubKey item : roleSubKeyStream) {
                Integer subId = item.getSubId();
                Optional<SubRights> optionalSubRights = subRights.stream().filter(sub -> sub.getId().equals(subId)).findFirst();
                if(!optionalSubRights.isPresent()){
                    // 二级权限列表不存在这条权限， 则删除该权限
                    roleSubServiceImpl.deleteRoleSubKey(item);
                }
            }
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }
}
