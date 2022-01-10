package com.yuefeng.component;

import com.yuefeng.common.UserConstant;
import com.yuefeng.exception.ObjectNotNullException;
import com.yuefeng.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.yuefeng.common.ResponseCode.TOKEN_LACK;

@Slf4j
public class AuthTokenFilter implements HandlerInterceptor {

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
