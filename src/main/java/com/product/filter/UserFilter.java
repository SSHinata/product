package com.product.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
public class UserFilter implements HandlerInterceptor {

    //preHandle           在请求处理前执行
    //postHandle          在请求处理后执行
    //afterCompletion     在请求结束后执行
    //Object表示被拦截的请求的目标对象
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //true和false的区别
        if(request.getSession().getAttribute("user") == null){
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
                //如果是ajax请求响应头会有，x-requested-with
                System.out.print("发生ajax请求...");
                return false;
            }
            response.sendRedirect("/product/userToLogin");
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //通过modelAndView改变显示的视图或修改发往视图的方法
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
