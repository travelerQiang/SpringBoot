package com.lzq.sharecommunity.repositry;

import com.lzq.sharecommunity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositry extends JpaRepository<User,Integer> {
    public User findByUsername(String username);
}
