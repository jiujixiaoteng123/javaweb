package com.lushunchenwendong.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String requestURI = req.getRequestURI();
        //获取最后一段路径，方法名
        int index = requestURI.lastIndexOf("/");
        String substring = requestURI.substring(index + 1);

        //执行方法
        //获取BrandServlet /UserServlet 字节码对象 Class
        Class<? extends BaseServlet> aClass = this.getClass();
        //获取Method对象
        try {
            Method method = aClass.getMethod(substring, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            try {
                method.invoke(this,req,resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
