package com.lhw.blog.intercepter;

import com.google.gson.Gson;
import com.lhw.blog.tool.JWTUtils;
import com.lhw.blog.tool.JsonBuilder;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: 进入controller之前进行拦截
 * @author: lihongwei
 * @time: 2020/4/10 2:45 下午
 */
@Component
public class LoginIntercepter implements HandlerInterceptor {

    private static final Gson gson = new Gson();

    /**
     * 进入controller之前进行拦截，检查token是否合法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token == null) {
            token = request.getParameter("token");
        }
        if(token != null) {
            Claims claims = JWTUtils.checkJWT(token);
            if(claims != null) {
                Integer userId = (Integer) claims.get("id");
                request.setAttribute("user_id", userId);
                return true;

            }
        }
        sendJsonMessage(response, JsonBuilder.buildError("请登录"));
        return false;
    }

    /**
     * 响应给前台数据
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj){
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println(gson.toJson(obj));
            writer.close();
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
