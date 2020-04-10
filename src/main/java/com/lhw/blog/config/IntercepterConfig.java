package com.lhw.blog.config;

import com.lhw.blog.intercepter.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: lihongwei
 * @time: 2020/4/10 3:04 下午
 */
@Configuration
public class IntercepterConfig extends WebMvcConfigurationSupport {
    @Resource
    private LoginIntercepter loginIntercepter;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/api/user/register");
        excludePath.add("/api/user/login");
        registry.addInterceptor(loginIntercepter)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);

        super.addInterceptors(registry);
    }
}
