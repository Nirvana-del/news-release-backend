package com.wq.controller;

import com.wq.entity.Role;
import com.wq.entity.User;
import com.wq.service.impl.RightsServiceImpl;
import com.wq.service.impl.SubRightsServiceImpl;
import com.wq.service.impl.UsersServiceImpl;
import com.wq.util.JwtUtil;
import com.wq.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public Result<Map<String, List<User>>> getDraftsList(){
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
    public Result<Map<String, String>> addUser(User user){
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
    public Result<Map<String, String>> deleteUser(@PathVariable String id){
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
    public Result<Map<String, String>> updateUser(@PathVariable String id,User user){
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
    public Result<Map<String, String>> userLogin(User user){
        try {
            Map<String, String> successMap = new HashMap<>();
            Map<String, String> failMap = new HashMap<>();
            String username = user.getUsername();
            String password = user.getPassword();
            List<User> userList = usersServiceImpl.searchUser(username, password);
            if (userList.size() == 1){
                User login_user = userList.get(0);
                String userId = login_user.getId();
                userExpandRole(login_user);
                Role userRole = login_user.getRole();
                roleExpandAllRights(userRole);
                successMap.put("username", login_user.getUsername());
                successMap.put("region", login_user.getRegion());
                successMap.put("userDefault", login_user.getUserDefault().toString());
                successMap.put("roleState", login_user.getRoleState().toString());
                successMap.put("roleId", login_user.getRoleId().toString());
                successMap.put("roleName", login_user.getRole().getRoleName());
                successMap.put("pathList", login_user.getRole().getPathList().toString());
                //生成JWT字符串
                String token = JwtUtil.sign(userId, successMap);
                System.out.println(token);
                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put("token",token);
//                return Result.success(successMap);
                return Result.success(tokenMap);
            }else{
                failMap.put("status","用户名或密码错误！");
                return Result.error(failMap);
            }
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }
    /**
     * 根据token查找用户
     */
    @GetMapping
    public Result<Map<String, User>> getUserInfo(HttpServletRequest request){
        try {
            Map<String, User> successMap = new HashMap<>();
            //从 http 请求头中取出 token
            String token = request.getHeader("Authorization");
            String userId = JwtUtil.getUserId(token);
            User user = usersServiceImpl.searchUserById(userId);
            user.setPassword(null);
            userExpandRole(user);
            Role userRole = user.getRole();
            roleExpandAllRights(userRole);
            successMap.put("userInfo",user);
            return Result.success(successMap);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

}
