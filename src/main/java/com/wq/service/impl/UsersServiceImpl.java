package com.wq.service.impl;

import com.wq.entity.User;
import com.wq.entity.UserExample;
import com.wq.mapper.UserMapper;
import com.wq.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getUserList() {
        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);
        return userList;
    }
    @Override
    public void addUser(User user) {
        userMapper.insertSelective(user);
    }
    @Override
    public void deleteUser(String id) {
        userMapper.deleteByPrimaryKey(id);
    }
    @Override
    public void updateUser(String id, User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 查找用户
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public List<User> searchUser(String username, String password) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        criteria.andRoleStateEqualTo(true);
        List<User> userList = userMapper.selectByExample(userExample);
       return userList;
    }
    @Override
    public User searchUserById(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }
}
