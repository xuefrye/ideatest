package com.itcast.service.impl;

import com.itcast.dao.UserDao;
import com.itcast.dao.impl.UserDaoImpl;
import com.itcast.domain.PageBean;
import com.itcast.domain.User;
import com.itcast.service.UserService;

import java.util.List;

/**
 * @Author: xue
 * @Date: 2019/4/14 18:13
 * @Version: 1.0
 * @File: UserServiceImpl
 * @Description: 操作用户数据的业务逻辑实现类
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用DAO完成查询
        return dao.getAllUser();
    }

    @Override
    public User login(User user) {
        return dao.getUserByUsernameAndPassword(user);
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void deleteUser(String userid) {
        dao.deleteUserByID(Integer.parseInt(userid));
    }

    @Override
    public User findUserByID(String id) {
        return dao.getUserByID(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows) {
        PageBean<User> pageBean = new PageBean<User>();

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //设置当前页和每页行数
        pageBean.setRows(rows);

        //设置总用户数量
        int totalUserCount = dao.findTotalUserCount();
        pageBean.setTotalCount(totalUserCount);

        int totalPage = (totalUserCount % rows == 0) ? (totalUserCount / rows) : (totalUserCount / rows + 1);
        pageBean.setTotalPage(totalPage);

        //防止页码超出范围
        if (currentPage <= 0)
            currentPage = 1;
        else if (currentPage >= totalPage)
            currentPage = totalPage;

        System.out.println(totalPage);
        pageBean.setCurrentPage(currentPage);

        //查询用户集合
        int start = (currentPage - 1) * rows; //start是开始的条目索引值
        List<User> list = dao.findUserByPage(start, rows); //开始的条目索引和条目数量

        //设置UserList
        pageBean.setList(list);

        return pageBean;
    }
}
