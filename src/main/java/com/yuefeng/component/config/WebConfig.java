package com.yuefeng.component.config;

import com.yuefeng.component.config.AuthTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: webMVC配置拦截器
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/1/12
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authTokenInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public AuthTokenInterceptor authTokenInterceptor() {
        return new AuthTokenInterceptor();
    }



}
