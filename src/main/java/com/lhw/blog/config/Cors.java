package com.lhw.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @description: 跨域配置
 * @author: lihongwei
 * @time: 2020/4/10 4:54 下午
 */
@Configuration
public class Cors extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  //所有路径 允许跨域
                .allowedOrigins("*") // 允许某个域名下
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH") // 允许跨域的方法
                .allowCredentials(true) //
                .maxAge(3600); //有效期
    }
}