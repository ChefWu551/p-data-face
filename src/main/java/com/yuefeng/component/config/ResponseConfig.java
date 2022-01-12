package com.yuefeng.component.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuefeng.common.ResponseData;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description: 返回格式统一控制
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/1/12
 */
@RestControllerAdvice(basePackages = "com.yuefeng.controller")
public class ResponseConfig<T> implements ResponseBodyAdvice<T> {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof String) {
            return objectMapper.writeValueAsString(ResponseData.success(o));
        }

        if (o instanceof ResponseData) {
            return o;
        }

        return ResponseData.success(o);
    }
}
