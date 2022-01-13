package com.yuefeng.component.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/1/12
 */
@Component
public class ObjectMapperConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}
