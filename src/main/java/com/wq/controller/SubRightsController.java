package com.wq.controller;

import com.wq.entity.SubRights;
import com.wq.service.impl.SubRightsServiceImpl;
import com.wq.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/sub_rights")
@CrossOrigin(origins = "*")
public class SubRightsController {
    @Autowired
    SubRightsServiceImpl subRightsServiceImpl;

    /**
     * 二级权限列表
     * @return
     */
    @GetMapping("/list")
    public Result getSubRightsList(){
        try {
            List<SubRights> subRightsList = subRightsServiceImpl.getSubRightsList();
            Map<String, List<SubRights>> map = new HashMap<>();
            map.put("subRightsList", subRightsList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 删除二级权限
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteSubRights(@PathVariable Integer id){
        Map<String, String> map = new HashMap<>();
        try {
            subRightsServiceImpl.deleteSubRights(id);
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

     /**
     * 更新二级权限
     * @param subRights
     * @return
     */
    @PatchMapping("/{id}")
    public Result updateSubRights(@PathVariable Integer id, SubRights subRights){
        System.out.println(subRights);
        try {
            Map<String, String> map = new HashMap<>();
            subRightsServiceImpl.updateSubRights(subRights);
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }


}
