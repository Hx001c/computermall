package com.athx.computermall.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/12 20:34
 * @description：拦截器
 * @modified By：
 * @version: $
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检查是否登录(检查session是否保存有uid数据)
        HttpSession session = request.getSession();
        Object uid = session.getAttribute("uid");

        if(uid!=null){
            //已经登录 放行
            return true;
        }
        //未登录 拦截住 跳转到登录界面
//        request.getRequestDispatcher("/").forward(request,response);
        response.sendRedirect("/web/login.html");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
