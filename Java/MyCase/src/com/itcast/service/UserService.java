package com.itcast.service;

import com.itcast.domain.PageBean;
import com.itcast.domain.User;

import java.util.List;

/**
 * @Author: xue
 * @Date: 2019/4/14 18:10
 * @Version: 1.0
 * @Description: 用户管理业务接口
 */

public interface UserService {
    /**
     * 查找所有用户的信息
     *
     * @return 返回所有的用户信息
     */
    List<User> findAll();

    /**
     * 登录方法
     *
     * @param user 登录的用户名和密码
     * @return 返回一个登陆后有全部信息的User对象, 登录失败返回null
     */
    User login(User user);

    /**
     * 将用户信息存入数据库
     *
     * @param user 网页post上来的user信息
     */
    void addUser(User user);


    /**
     * 将传来的id参数所对应的user从数据库中删除
     * @param userid user的id值
     */
    void deleteUser(String userid);


    /**
     * 查询用户
     * @param id
     * @return 返回用户信息,如果没有查到返回null
     */
    User findUserByID(String id);


    void updateUser(User user);

    PageBean<User> findUserByPage(String currentPage, String rows);
}
