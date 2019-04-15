package com.itcast.web.servlet;

import com.itcast.domain.User;
import com.itcast.service.UserService;
import com.itcast.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author: xue
 * @Date: 2019/4/14 23:21
 * @Version: 1.0
 * @File: ${NAME}
 * @Description:
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取数据
        String checkCodeClient = request.getParameter("verifycode");
        Map<String, String[]> map = request.getParameterMap();

        //2.1校验验证码&获取Session
        HttpSession session = request.getSession();
        String checkCodeServer = (String) session.getAttribute("CHECKCODE_SERVER");
        //使验证码一次性化.
        session.removeAttribute("CHECKCODE_SERVER");

        if (!checkCodeServer.equalsIgnoreCase(checkCodeClient)) {
            //验证码不正确
            //提示信息
            request.setAttribute("login_msg", "验证码错误");
            //跳转
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }


        //3.封装User
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //5.调用service查询
        UserService service = new UserServiceImpl();
        //loginUser是登陆后的User对象
        User loginUser = service.login(user);
        if(loginUser != null)
        {
            //登陆成功,将用户存入Session
            session.setAttribute("user",loginUser);

            //跳转
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
        else
        {
            //登录失败
            request.setAttribute("login_msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
























