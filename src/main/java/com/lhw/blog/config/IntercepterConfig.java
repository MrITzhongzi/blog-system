package com.lhw.blog.config;

import com.lhw.blog.intercepter.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

    /**
     * 配置api接口过滤器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/api/user/register");
        excludePath.add("/api/user/login");


        List<String> includePath = new ArrayList<>();
        includePath.add("/api/**");

        registry.addInterceptor(loginIntercepter)
                .addPathPatterns(includePath)
                .excludePathPatterns(excludePath);

        super.addInterceptors(registry);
    }

    /**
     * 重新配置可以访问静态资源路径，不配置这个 会导致静态资源无法访问
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //重新配置静态资源
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        super.addResourceHandlers(registry);
    }
}
