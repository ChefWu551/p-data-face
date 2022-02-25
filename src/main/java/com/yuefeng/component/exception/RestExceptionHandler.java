package com.yuefeng.component.exception;

import com.yuefeng.constant.ResponseCodeConstant;
import com.yuefeng.model.ResponseData;
import com.yuefeng.exception.ObjectNotNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description: 全局异常处理
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/1/12
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.yuefeng.controller")
public class RestExceptionHandler {
    /**
     * 默认全局异常处理。
     *
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData<String> exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        // todo: 代码优化
        if (e instanceof ObjectNotNullException) {
            return ResponseData.fail(((ObjectNotNullException) e).getCode(), ((ObjectNotNullException) e).getMsg());
        }

        return ResponseData.fail(ResponseCodeConstant.SERVER_EXCEPTION.getCode(), e.getMessage());
    }

}
