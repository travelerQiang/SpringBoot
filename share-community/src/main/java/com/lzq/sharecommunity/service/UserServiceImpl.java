package com.lzq.sharecommunity.service;

import com.lzq.sharecommunity.entity.User;
import com.lzq.sharecommunity.exception.MySQLException;
import com.lzq.sharecommunity.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepositry repositry;
    @Override
    public List<User> findAllUser() {
        return repositry.findAll();
    }

    @Override
    public User findById(int id) {
        return repositry.getOne(id);
    }

    @Override
    public User addUser(User user){
        User save = null;
        if (user==null){
            try {
                throw new MySQLException("输入user对象为空");
            } catch (MySQLException e) {
                e.printStackTrace();
            }
        }
        else {
            save =repositry.save(user);
        }
        return save;
    }

    @Override
    public User update(User user){
        User save = null;
        if (repositry.existsById(user.getId())){
            save = repositry.save(user);
        }
        else {
            try {
                throw new MySQLException("更新失败，用户不存在");
            } catch (MySQLException e) {
                e.printStackTrace();
            }
        }
        return save;
    }

    @Override
    public User findUserByUsername(String username) {
        return repositry.findByUsername(username);
    }

    @Override
    public boolean checkUser(User user) {
        if (user!=null){
            User user1 = repositry.findByUsername(user.getUsername());
            if (user1!=null && user.getPassword().equals(user1.getPassword())){
                user.setHeadUrl(user1.getHeadUrl());
                user.setId(user1.getId());
                user1.setLastLogin(new Date());
                update(user1);
                return true;
            }
        }
        return false;
    }
}
