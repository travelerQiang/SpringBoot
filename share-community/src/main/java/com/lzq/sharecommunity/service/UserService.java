package com.lzq.sharecommunity.service;

import com.lzq.sharecommunity.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUser();

    public User findUserByUsername(String username);

    public User findById(int id);

    public User addUser(User user);

    public User update(User user);

    public boolean checkUser(User user);
}
