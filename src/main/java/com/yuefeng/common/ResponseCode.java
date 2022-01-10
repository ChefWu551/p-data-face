package com.yuefeng.common;

public enum ResponseCode {

    SUCCESS(200, "请求成功"),
    AUTH_FAIL(403, "认证出错"),
    TOKEN_LACK(40301, "缺少token信息"),
    NOT_FOUND(404, "访问资源不存在"),
    SERVER_EXCEPTION(500, "服务器异常"),
    CONFIG_EXCEPTION(50001, "配置异常")
    ;

    private final int code;
    private final String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
