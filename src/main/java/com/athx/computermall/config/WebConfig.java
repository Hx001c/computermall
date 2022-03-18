package com.athx.computermall.config;

import com.athx.computermall.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/12 20:40
 * @description：配置类
 * @modified By：
 * @version: $
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 白名单
        List<String> patterns = new ArrayList<String>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/index.html");
        patterns.add("/web/product.html");
        patterns.add("/districts/**");
        patterns.add("/products/**");
        patterns.add("/user/login");
        patterns.add("/user/regist");

        // 通过注册工具添加拦截器

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//拦截所有请求
                .excludePathPatterns(patterns);
    }
}