package com.wq.controller;

import com.wq.entity.Rights;
import com.wq.entity.SubRights;
import com.wq.service.impl.SubRightsServiceImpl;
import com.wq.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wq.util.Tool.*;


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
    public Result<Map<String, List<SubRights>>> getSubRightsList(HttpServletRequest request){
        try {
            String token = request.getHeader("Authorization");
            List<String> pathList = getPathListByToken(token);
            List<SubRights> subRightsList = subRightsServiceImpl.getSubRightsList();
            List<SubRights> newSubRightsList = filterSubRightsTree(subRightsList, pathList);
            Map<String, List<SubRights>> map = new HashMap<>();
            map.put("subRightsList", newSubRightsList);
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
    public Result<Map<String, String>> deleteSubRights(@PathVariable Integer id){
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
    public Result<Map<String, String>> updateSubRights(@PathVariable Integer id, SubRights subRights){
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
