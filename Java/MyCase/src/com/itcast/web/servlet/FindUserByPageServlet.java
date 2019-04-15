package com.itcast.web.servlet;

import com.itcast.domain.PageBean;
import com.itcast.domain.User;
import com.itcast.service.UserService;
import com.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: xue
 * @Date: 2019/4/15 17:56
 * @Version: 1.0
 * @File: ${NAME}
 * @Description: 处理浏览器过来的请求, 返回对应页数的User信息
 */
@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取传来的页格式参数
        String currentPage = request.getParameter("currentPage");//获取当前页码
        String rows = request.getParameter("rows");//每页显示的条数

        //特殊值判断
        currentPage = (currentPage == null || "".equals(currentPage)) ? "1" : currentPage;
        rows = (rows == null || "".equals(rows)) ? "5" : rows;

        UserService service = new UserServiceImpl();
        PageBean<User> pageBean = service.findUserByPage(currentPage, rows);

        //System.out.println(pageBean);

        //将pageBean存入request
        request.setAttribute("pageBean", pageBean);

        //转发
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
