package com.wq.controller;

import com.wq.entity.Role;
import com.wq.entity.User;
import com.wq.service.impl.RightsServiceImpl;
import com.wq.service.impl.SubRightsServiceImpl;
import com.wq.service.impl.UsersServiceImpl;
import com.wq.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.wq.util.Tool.roleExpandAllRights;
import static com.wq.util.Tool.userExpandRole;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UsersServiceImpl usersServiceImpl;

    @Autowired
    RightsServiceImpl rightsServiceImpl;
    @Autowired
    SubRightsServiceImpl subRightsServiceImpl;

    /**
     * 用户列表
     * @return
     */
    @GetMapping("/list")
    public Result getDraftsList(){
        try {
            List<User> userList = usersServiceImpl.getUserList();
            userExpandRole(userList);
            Map<String, List<User>> map = new HashMap<>();
            map.put("userList", userList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping
    public Result addUser(User user){
        System.out.println(user);
        Map<String, String> map = new HashMap<>();
        try {
            String userId = UUID.randomUUID().toString();
            user.setId(userId);
            usersServiceImpl.addUser(user);
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable String id){
        System.out.println(id);
        Map<String, String> map = new HashMap<>();
        try {
            usersServiceImpl.deleteUser(id);
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 修改用户信息
     * @param id
     * @param user
     * @return
     */
    @PatchMapping("/{id}")
    public Result updateUser(@PathVariable String id,User user){
        System.out.println(id);
        System.out.println(user);
        Map<String, String> map = new HashMap<>();
        try {
            usersServiceImpl.updateUser(id, user);
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result userLogin(User user){
        System.out.println(user);
        try {
            Map<String, User> successMap = new HashMap<>();
            Map<String, String> failMap = new HashMap<>();

            String username = user.getUsername();
            String password = user.getPassword();
            List<User> userList = usersServiceImpl.searchUser(username, password);
            if (userList.size() == 1){
                User login_user = userList.get(0);
                login_user.setPassword(null);
                userExpandRole(login_user);
                Role userRole = login_user.getRole();
                roleExpandAllRights(userRole);

                successMap.put("userInfo", login_user);
                return Result.success(successMap);
            }else{
                failMap.put("status","用户名或密码错误！");
                return Result.error(failMap);
            }
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

}
