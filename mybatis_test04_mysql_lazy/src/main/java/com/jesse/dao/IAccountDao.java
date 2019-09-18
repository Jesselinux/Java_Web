package com.jesse.dao;

import com.jesse.domain.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有账户，同时还要获取到当前账户的所属用户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 根据id查询账户信息
     * @return
     */
    List<Account> findAccountByUid();
}
