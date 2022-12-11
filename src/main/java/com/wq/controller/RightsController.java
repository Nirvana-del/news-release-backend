package com.wq.controller;

import com.wq.entity.Rights;
import com.wq.service.impl.RightsServiceImpl;
import com.wq.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wq.util.Tool.expandSubRights;

@RestController
@RequestMapping("/rights")
@CrossOrigin(origins = "*")
public class RightsController {
    @Autowired
    RightsServiceImpl rightsServiceImpl;

    /**
     * 权限树
     * @return
     */
    @GetMapping("/tree")
    public Result getPermissionTree(){
        try {
            List<Rights> rightsTree = rightsServiceImpl.getPermissionTree();
            expandSubRights(rightsTree);
            Map<String, List<Rights>> map = new HashMap<>();
            map.put("rightTree", rightsTree);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 一级权限列表
     * @return
     */
    @GetMapping("/list")
    public Result getRightsList(){
        try {
            List<Rights> rightsList = rightsServiceImpl.getRightsList();
            Map<String, List<Rights>> map = new HashMap<>();
            map.put("rightsList", rightsList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteRights(@PathVariable Integer id){
        try {
            Map<String, String> map = new HashMap<>();
            rightsServiceImpl.deleteRights(id);
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

     /**
     * 更新权限
     * @param rights
     * @return
     */
    @PatchMapping("/{id}")
    public Result updateRights(@PathVariable Integer id,Rights rights){
        System.out.println(rights);
        try {
            Map<String, String> map = new HashMap<>();
            rightsServiceImpl.updateRights(rights);
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }


}
