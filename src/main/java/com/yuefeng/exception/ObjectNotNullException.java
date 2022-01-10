package com.yuefeng.exception;

/**
 * 自定义异常 - 对象不能为Null异常
 */
public class ObjectNotNullException extends RuntimeException {

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    public ObjectNotNullException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
