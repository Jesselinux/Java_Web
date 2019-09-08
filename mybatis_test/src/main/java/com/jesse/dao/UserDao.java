package com.jesse.dao;

import com.jesse.domain.User;

import java.util.List;

public interface UserDao {

    // 查询所有接口：
    List<User> findAll();
}
