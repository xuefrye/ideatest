package com.itcast.dao.impl;

import com.itcast.dao.UserDao;
import com.itcast.domain.User;
import com.itcast.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: xue
 * @Date: 2019/4/14 18:16
 * @Version: 1.0
 * @File: UserDaoImpl
 * @Description: UserDao实现类
 */
public class UserDaoImpl implements UserDao {

    //连接模板对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> getAllUser() {
        //使用JDBC操作数据库

        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public User getUserByUsernameAndPassword(User user) {
        String sql = String.format("select * from user where username='%s'&&password='%s'", user.getUsername(), user.getPassword());
        user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class));

        return user;
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void deleteUserByID(int id) {
        String sql = "delete from user where id=?";
        template.update(sql, id);
    }

    @Override
    public User getUserByID(int id) {
        String sql = String.format("select * from user where id=%d", id);
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class));

        return user;
    }

    @Override
    public void updateUser(User user) {
        String sql = "update user set name = ?, gender = ?, age = ?, address = ?, qq = ?, email=? where id = ?";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public List<User> findUserByPage(int start, int rows) {
        String sql = "select * from user limit ? , ? ";

        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), start, rows);
    }

    @Override
    public int findTotalUserCount() {
        String sql = "select count(*) from user";
        return template.queryForObject(sql, Integer.class);
    }
}























