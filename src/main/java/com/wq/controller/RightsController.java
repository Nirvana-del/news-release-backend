package com.wq.controller;

import com.wq.entity.Rights;
import com.wq.entity.Role;
import com.wq.entity.User;
import com.wq.service.impl.RightsServiceImpl;
import com.wq.service.impl.UsersServiceImpl;
import com.wq.util.JwtUtil;
import com.wq.util.Result;
import com.wq.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wq.util.Tool.*;

@RestController
@RequestMapping("/rights")
@CrossOrigin(origins = "*")
public class RightsController {
    @Autowired
    RightsServiceImpl rightsServiceImpl;
    @Autowired
    UsersServiceImpl usersServiceImpl;
    /**
     * 权限树
     * @return
     */
    @GetMapping("/tree")
    public Result<Map<String, List<Rights>>> getPermissionTree(HttpServletRequest request){
        try {
            String token = request.getHeader("Authorization");
            List<String> pathList = getPathListByToken(token);
//            System.out.println("pathList");
//            System.out.println(pathList);
            List<Rights> rightsTree = rightsServiceImpl.getRightsList();
            expandSubRights(rightsTree);
            List<Rights> newRightsTree = filterRightsTree(rightsTree, pathList, true);
            Map<String, List<Rights>> map = new HashMap<>();
            map.put("rightTree", newRightsTree);
//            System.out.println(map);
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
    public Result<Map<String, List<Rights>>> getRightsList(HttpServletRequest request){
        try {
            String token = request.getHeader("Authorization");
            List<String> pathList = getPathListByToken(token);
            List<Rights> rightsList = rightsServiceImpl.getRightsList();
            System.out.println(rightsList);
            List<Rights> newRightsList = filterRightsTree(rightsList, pathList, false);
            Map<String, List<Rights>> map = new HashMap<>();
            map.put("rightsList", newRightsList);
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
    public Result<Map<String, String>> deleteRights(@PathVariable Integer id){
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
    public Result<Map<String, String>> updateRights(@PathVariable Integer id,Rights rights){
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
