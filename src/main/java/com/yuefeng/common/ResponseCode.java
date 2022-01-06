package com.yuefeng.common;

public enum ResponseCode {

    SUCCESS(200, "请求成功"),
    AUTH_FAIL(403, "认证出错"),
    NOT_FOUND(404, "访问资源不存在"),
    SERVER_EXCEPTION(500, "服务器异常")
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
