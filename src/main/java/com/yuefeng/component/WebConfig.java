package com.yuefeng.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authTokenInterceptor()).addPathPatterns("/**").excludePathPatterns("/file/preview");
    }

    @Bean
    public AuthTokenFilter authTokenInterceptor() {
        return new AuthTokenFilter();
    }



}
