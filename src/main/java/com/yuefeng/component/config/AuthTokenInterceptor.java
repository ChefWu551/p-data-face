package com.yuefeng.component.config;

import com.yuefeng.constant.UserConstant;
import com.yuefeng.exception.ObjectNotNullException;
import com.yuefeng.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.yuefeng.constant.ResponseCodeConstant.TOKEN_LACK;

/**
 * @Description: 认证处理
 * @Author: Wu Yuefeng
 * @Date: Created on 2019/5/24
 */
@Slf4j
public class AuthTokenInterceptor implements HandlerInterceptor {

    @Value("${oauth.resource.url}")
    private String oauthResourceUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(UserConstant.ACCESS_TOKEN);
        if (token == null) throw new ObjectNotNullException(TOKEN_LACK.getCode(), "缺少token");
        UserDto user = getUserInfoByAuth(token);
        request.setAttribute(UserConstant.USER_ID, user.getUserId());
        request.setAttribute(UserConstant.USER_NAME, user.getUserName());
        return true;
    }

    private UserDto getUserInfoByAuth(String token) {
        UserDto user = new UserDto();
        user.setUserId(999);
        user.setUserName("admin");
        return user;
    }
}
