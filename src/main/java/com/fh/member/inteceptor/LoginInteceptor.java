package com.fh.member.inteceptor;

import com.alibaba.fastjson.JSONObject;
import com.fh.member.common.Ignore;
import com.fh.member.common.LoginException;
import com.fh.member.model.Member;
import com.fh.member.util.JwtUtil;
import com.fh.member.util.RedisUtil;
import com.fh.member.util.SystemConstant;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLDecoder;

public class LoginInteceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //处理客户端传过来的自定义头信息
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "x-auth,mtoken,content-type");
        // 处理客户端发过来put,delete
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "PUT,POST,DELETE,GET");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(Ignore.class)){
            return true;
        }
        String token = request.getHeader("x-auth");
        //如果没有token 返回登录页面
        if (StringUtils.isEmpty(token)){
                throw new LoginException();
        }
       /* boolean exit=  RedisUtil.exist(SystemConstant.TOKEN_KEY+token);
        if(!exit){
            //token 失效
            throw  new  LoginException();
        }else {
            RedisUtil.get(token);
        }*/

        //验证token
        boolean res = JwtUtil.verify(token);
        if (res){
            String userString = JwtUtil.getUser(token);
            String jsonUser = URLDecoder.decode(userString, "utf-8");
            Member member = JSONObject.parseObject(jsonUser, Member.class);
            request.getSession().setAttribute(SystemConstant.SESSION_KEY,member);
            request.getSession().setAttribute(SystemConstant.TOKEN_KEY,token);
            RedisUtil.set(token,token);
        }else{
            throw new LoginException();
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
