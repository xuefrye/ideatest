package com.itcast.dao;

import com.itcast.domain.User;

import java.util.List;

/**
 * @Author: xue
 * @Date: 2019/4/14 18:14
 * @Version: 1.0
 * @File: UserDao
 * @Description: 操作user表的DAO接口, 由于是user的接口, 所以方法名中的user可以省略
 */
public interface UserDao {
    /**
     * 从数据库查询并返回所有用户信息
     *
     * @return 返回用户信息列表
     */
    List<User> getAllUser();

    /**
     * 使用账号密码查询用户是否存在
     *
     * @param user
     * @return 如果没有查询到用户返回null否则返回用户的完整信息
     */
    User getUserByUsernameAndPassword(User user);

    /**
     * 将用户信息添加至数据库user表
     * @param user
     */
    void addUser(User user);

    void deleteUserByID(int id);

    User getUserByID(int id);

    void updateUser(User user);

    List<User> findUserByPage(int currentPage, int rows);

    int findTotalUserCount();
}
